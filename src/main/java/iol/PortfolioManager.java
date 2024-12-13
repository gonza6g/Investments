package iol;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PortfolioManager {
    private static final String API_AR_PORTFOLIO_URL = "https://api.invertironline.com/api/v2/portafolio/argentina";
    private static final String API_US_PORTFOLIO_URL = "https://api.invertironline.com/api/v2/portafolio/estados_Unidos";
    
    private final TokenObtainer tokenObtainer;
    private final ObjectMapper objectMapper;

    public PortfolioManager(TokenObtainer tokenObtainer) {
        this.tokenObtainer = tokenObtainer;
        this.objectMapper = new ObjectMapper();
    }

    public String getPortfolio() throws IOException {
        URL url = new URL(API_AR_PORTFOLIO_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + tokenObtainer.getAccessToken());

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return calculateGains(response.toString());
        } else {
            throw new IOException("Request failed. Response code: " + responseCode);
        }
    }

    private String calculateGains(String portfolioJson) throws IOException {
        JsonNode rootNode = objectMapper.readTree(portfolioJson);
        JsonNode activosNode = rootNode.get("activos");

        BigDecimal totalGains = BigDecimal.ZERO;
        BigDecimal totalLosses = BigDecimal.ZERO;

        // Map to store latest entry for each symbol
        Map<String, ObjectNode> latestEntries = new HashMap<>();

        if (activosNode != null && activosNode.isArray()) {
            ArrayNode activosArray = (ArrayNode) activosNode;
            for (JsonNode activo : activosArray) {
                if (activo instanceof ObjectNode) {
                    ObjectNode activoObj = (ObjectNode) activo;
                    String symbol = activo.path("titulo").path("simbolo").asText();
                    
                    // Update map with latest entry (assuming the API returns them in chronological order)
                    latestEntries.put(symbol, activoObj);
                }
            }
        }

        // Create new ArrayNode for deduplicated entries
        ArrayNode deduplicatedArray = objectMapper.createArrayNode();
        
        // Process each unique entry
        for (ObjectNode activoObj : latestEntries.values()) {
            // Get required values for calculation
            double ppc = activoObj.path("ppc").asDouble(0.0);
            double cantidad = activoObj.path("cantidad").asDouble(0.0);
            double ultimoPrecio = activoObj.path("ultimoPrecio").asDouble(0.0);
            
            // Skip entries with zero quantity or price
            if (cantidad <= 0 || ultimoPrecio <= 0) {
                continue;
            }
            
            // Calculate gains/losses
            BigDecimal investedAmount = BigDecimal.valueOf(ppc * cantidad);
            BigDecimal currentAmount = BigDecimal.valueOf(ultimoPrecio * cantidad);
            BigDecimal difference = currentAmount.subtract(investedAmount);
            
            // Add to totals
            if (difference.compareTo(BigDecimal.ZERO) > 0) {
                totalGains = totalGains.add(difference);
            } else {
                totalLosses = totalLosses.add(difference.abs());
            }

            // Format country name
            String country = activoObj.path("titulo").path("pais").asText("Unknown");
            String formattedCountry = Arrays.stream(country.toLowerCase().split("\\s+"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
            
            // Add industry if missing
            if (activoObj.path("titulo").path("industry").isMissingNode()) {
                ((ObjectNode) activoObj.path("titulo")).put("industry", "Financial Services");
            }
            
            // Add gains/losses to the JSON object
            activoObj.put("ganancia", difference.setScale(2, RoundingMode.HALF_UP).doubleValue());
            ((ObjectNode) activoObj.path("titulo")).put("pais", formattedCountry);
            
            // Add to deduplicated array
            deduplicatedArray.add(activoObj);
        }

        // Replace original array with deduplicated one
        ((ObjectNode) rootNode).set("activos", deduplicatedArray);
        
        // Add totals to the response
        ((ObjectNode) rootNode).put("totalGains", totalGains.setScale(2, RoundingMode.HALF_UP).doubleValue());
        ((ObjectNode) rootNode).put("totalLosses", totalLosses.setScale(2, RoundingMode.HALF_UP).doubleValue());

        return objectMapper.writeValueAsString(rootNode);
    }

    public String getPosition() {
        return "";
    }
}
