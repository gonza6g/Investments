package iol;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AccountTest {
    @Test
    public void getAccountStatus() throws IOException, JSONException {
        String username = "username";
        String password = "password";

        TokenObtainer tokenObtainer = new TokenObtainer(username, password);
        tokenObtainer.obtainAccessToken();

        String token = tokenObtainer.getAccessToken();

        Account account = new Account(token);
        String accountStatus = account.getAccountStatus();
        System.out.println("accountStatus = " + accountStatus);

        Assertions.assertNotNull(accountStatus);
    }

    @Test
    public void getTransactions() throws IOException, JSONException {
        String username = "username";
        String password = "password";

        TokenObtainer tokenObtainer = new TokenObtainer(username, password);
        tokenObtainer.obtainAccessToken();

        String token = tokenObtainer.getAccessToken();

        Account account = new Account(token);
        String accountTransactions = account.getAccountTransactions("0", "todas", "2023-05-01", "2023-06-16", "argentina");
        System.out.println("accountTransactions = " + accountTransactions);

        Assertions.assertNotNull(accountTransactions);
    }
}