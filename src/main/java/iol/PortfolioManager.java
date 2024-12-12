package iol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PortfolioManager {
    private static final String API_AR_PORTFOLIO_URL = "https://api.invertironline.com/api/v2/portafolio/argentina";
    private static final String API_US_PORTFOLIO_URL = "https://api.invertironline.com/api/v2/portafolio/estados_Unidos";
    private String accessToken;
    public PortfolioManager(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getPortfolio() throws IOException {
        URL url = new URL(API_AR_PORTFOLIO_URL);
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

    public String getPosition() {
        return "";
    }
}
