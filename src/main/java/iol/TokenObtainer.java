package iol;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

public class TokenObtainer {
    private static final String API_URL = "https://api.invertironline.com/token";
    private static final String CHARSET = "UTF-8";
    private static final String GRANT_TYPE = "password";

    private String username;
    private String password;
    private String token;

    public TokenObtainer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public TokenObtainer() {
        Properties prop = new Properties();
        String fileName = "/Users/gonzalo/IdeaProjects/Investments/src/main/resources/app.properties";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("ex = " + ex);
        } catch (IOException ex) {
            System.out.println("ex = " + ex);
        }
        this.username = prop.getProperty("app.user");
        this.password = prop.getProperty("app.password");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return token;
    }

    public void obtainAccessToken() throws IOException, JSONException {
        String requestBody = "username=" + URLEncoder.encode(username, CHARSET)
                + "&password=" + URLEncoder.encode(password, CHARSET)
                + "&grant_type=" + URLEncoder.encode(GRANT_TYPE, CHARSET);

        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(requestBody.getBytes(CHARSET));
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            token = jsonResponse.getString("access_token");
        } else {
            throw new IOException("Request failed. Response code: " + responseCode);
        }
    }
}