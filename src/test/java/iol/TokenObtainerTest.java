package iol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TokenObtainerTest {
    @Test
    public void obtainToken() throws IOException {
        String username = "username";
        String password = "password";

        TokenObtainer tokenObtainer = new TokenObtainer(username, password);
        tokenObtainer.obtainAccessToken();

        String token = tokenObtainer.getAccessToken();
        System.out.println("token = " + token);

        Assertions.assertNotNull(token);
    }
}