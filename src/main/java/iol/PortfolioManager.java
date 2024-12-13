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

            // Parse and enrich the response with gains calculation
            return calculateGains(response.toString());
        } else {
            throw new IOException("Request failed. Response code: " + responseCode);
        }
    }

    private String calculateGains(String portfolioJson) throws IOException {
        JsonNode rootNode = objectMapper.readTree(portfolioJson);
        JsonNode activosNode = rootNode.get("activos");

        if (activosNode != null && activosNode.isArray()) {
            ArrayNode activosArray = (ArrayNode) activosNode;
            for (JsonNode activo : activosArray) {
                if (activo instanceof ObjectNode) {
                    ObjectNode activoObj = (ObjectNode) activo;
                    
                    // Get required values for calculation
                    double ppc = activo.path("ppc").asDouble(0.0);
                    double cantidad = activo.path("cantidad").asDouble(0.0);
                    double ultimoPrecio = activo.path("ultimoPrecio").asDouble(0.0);
                    
                    // Calculate gains
                    BigDecimal investedAmount = BigDecimal.valueOf(ppc * cantidad);
                    BigDecimal currentAmount = BigDecimal.valueOf(ultimoPrecio * cantidad);
                    BigDecimal gains = currentAmount.subtract(investedAmount);
                    
                    // Add gains to the JSON object, rounded to 2 decimal places
                    activoObj.put("ganancia", gains.setScale(2, RoundingMode.HALF_UP).doubleValue());
                }
            }
        }

        return objectMapper.writeValueAsString(rootNode);
    }

    public String getPosition() {
        return "";
    }
}
