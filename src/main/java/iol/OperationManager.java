package iol;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OperationManager {
    private String accessToken;
    private static final String API_SELL = "https://api.invertironline.com/api/v2/operar/Vender";

    public OperationManager(String token) {
        this.accessToken = accessToken;
    }

    public String sell(String market, String symbol, double amount, double price,
                       String valid, String orderType) throws IOException {
        URL url = new URL(API_SELL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonBody = String.format(
                "{\"mercado\":\"%s\",\"simbolo\":\"%s\",\"cantidad\":%f,\"precio\":%f,\"validez\":\"%s\",\"tipoOrden\":\"%s}",
                market, symbol, amount, price, valid, orderType
        );

        System.out.println("jsonBody = " + jsonBody);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return "Request successful: OK";
        } else {
            return "Request failed. Response code: " + responseCode;
        }
    }
}
