package ibkr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class BrokerageSessionChecker {

    @Test
    public void run() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String initialUrl = "https://localhost:5001/iserver/auth/status";

        // Disable hostname verification (not recommended for production)
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(new TrustSelfSignedStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) {
                // Allow any hostname
                return true;
            }
        }).build();

        // Disable hostname verification
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();
        HttpPost httpPost = new HttpPost(initialUrl);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("statusCode = " + statusCode);
            // Check if it's a redirection (HTTP 302)
            if (statusCode == 302) {
                // Get the redirection URL
                String redirectionUrl = response.getFirstHeader("Location").getValue();
                System.out.println("Redirected to: " + redirectionUrl);

                // Create a new HTTP GET request to the redirection URL
                HttpPost redirectionRequest = new HttpPost(new URI(redirectionUrl));

                // Execute the request again
                response = httpClient.execute(redirectionRequest);
                statusCode = response.getStatusLine().getStatusCode();
            }

            // Log the response status code
            System.out.println("Response status code is: " + statusCode);

            // Log the response headers
            /* System.out.println("Response headers:");
            for (var header : response.getAllHeaders()) {
                System.out.println(header.getName() + ": " + header.getValue());
            } */

            // Read and process the response content
            if (statusCode == 200) {
                StringBuilder responseBody = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }

                // Log the response body
                System.out.println("Response body:");
                System.out.println(responseBody.toString());

                // Parse the JSON response using Jackson
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody.toString());

                // Check if the JSON object contains the "authenticated" field
                if (jsonNode.has("authenticated") && jsonNode.get("authenticated").asBoolean()) {
                    System.out.println("Session is fully authenticated.");
                } else {
                    System.out.println("Session is not fully authenticated.");
                }
            } else {
                System.err.println("HTTP request failed with status code: " + statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
