package iol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Account {
    private static final String API_ACCOUNT_URL = "https://api.invertironline.com/api/v2/estadocuenta";
    private static final String API_TRANSACTIONS_URL = "https://api.invertironline.com/api/v2/operaciones";

    private String accessToken;

    public Account(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccountStatus() throws IOException {
        URL url = new URL(API_ACCOUNT_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new IOException("Request failed. Response code: " + responseCode);
        }
    }

    public String getAccountTransactions(String filtroNumero, String filtroEstado, String filtroFechaDesde,
                                                String filtroFechaHasta, String filtroPais) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_TRANSACTIONS_URL);
        urlBuilder.append("?filtro.numero=").append(URLEncoder.encode(filtroNumero, StandardCharsets.UTF_8))
                .append("&filtro.estado=").append(URLEncoder.encode(filtroEstado, StandardCharsets.UTF_8))
                .append("&filtro.fechaDesde=").append(URLEncoder.encode(filtroFechaDesde, StandardCharsets.UTF_8))
                .append("&filtro.fechaHasta=").append(URLEncoder.encode(filtroFechaHasta, StandardCharsets.UTF_8))
                .append("&filtro.pais=").append(URLEncoder.encode(filtroPais, StandardCharsets.UTF_8));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new IOException("Request failed. Response code: " + responseCode);
        }
    }
}
