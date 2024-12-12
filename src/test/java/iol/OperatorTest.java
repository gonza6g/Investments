package iol;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class OperatorTest {
    @Test
    public void sellStock() throws IOException {
        TokenObtainer tokenObtainer = new TokenObtainer();
        tokenObtainer.obtainAccessToken();
        String token = tokenObtainer.getAccessToken();

        PortfolioManager portfolioManager = new PortfolioManager(token);
        String portfolioString =  portfolioManager.getPortfolio();

        String market = "bCBA";
        String symbol = "PAMP";

        JsonNode symbolData = getSymbolInfo(portfolioString, symbol);
        double amount = Double.parseDouble(String.valueOf(symbolData.get("cantidad")));
        double price = Double.parseDouble(String.valueOf(symbolData.get("ultimoPrecio")));

        OperationManager operationManager = new OperationManager(token);
        operationManager.sell(market, symbol, amount, price, "2024-11-19T14:01:00.000Z", "precioMercado");
    }

    public JsonNode getSymbolInfo(String jsonString, String simbolo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonString);

        JsonNode activosNode = rootNode.path("activos");

        if (activosNode.isArray()) {
            for (JsonNode activo : activosNode) {
                String currentSimbolo = activo.path("titulo").path("simbolo").asText();
                if (simbolo.equals(currentSimbolo)) {
                    return activo;
                }
            }
        }
        return null;
    }

}
