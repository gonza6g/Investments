package iol;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PortfolioTest {
    @Test
    public void getPortfolio() throws IOException {
        TokenObtainer tokenObtainer = new TokenObtainer();
        tokenObtainer.obtainAccessToken();

        String token = tokenObtainer.getAccessToken();

        PortfolioManager portfolioManager = new PortfolioManager(token);
        String portfolio = portfolioManager.getPortfolio();

        System.out.println("portfolio = " + portfolio);
    }
}
