package iol;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TokenObtainerTest {
    @Test
    public void obtainToken() throws IOException, JSONException {
        String username = "username";
        String password = "password";

        TokenObtainer tokenObtainer = new TokenObtainer(username, password);
        tokenObtainer.obtainAccessToken();

        String token = tokenObtainer.getAccessToken();
        System.out.println("token = " + token);

        Assertions.assertNotNull(token);
    }

    @Test
    public void testTokenJsonParsing() throws JSONException {
        String jsonToken = "{\"token\":\"test-token\"}";
        TokenObtainer tokenObtainer = new TokenObtainer("username", "password");
        // Rest of your test
        Assertions.assertNotNull(tokenObtainer);
    }
}