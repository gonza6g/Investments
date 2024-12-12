package ibkr;

import org.junit.jupiter.api.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;

public class SessionIdFetcher {

    @Test
    public void getSessionId() {
        try {
            // Disable SSL certificate validation
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManager[] trustManagers = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };
            sslContext.init(null, trustManagers, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

            // Define the API endpoint URL
            String apiUrl = "https://localhost:5001/v1/api/tickle";

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response to extract the sessionId
            String jsonResponse = response.toString();
            String sessionId = parseSessionIdFromJson(jsonResponse);

            // Print the sessionId
            System.out.println("Session ID: " + sessionId);

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Parse the sessionId from the JSON response
    private static String parseSessionIdFromJson(String jsonResponse) {
        // Replace this with your JSON parsing logic based on the actual response format
        // For simplicity, we assume that the sessionId is a string and can be extracted directly
        return jsonResponse;
    }
}