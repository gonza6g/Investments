package iol;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PortfolioTest {

    @Test
    public void testPortfolioJsonParsing() throws JSONException {
        // Your test code here
        String jsonPortfolio = "{\"activos\":[\"asset1\",\"asset2\"]}";
        PortfolioManager portfolioManager = new PortfolioManager(new TokenObtainer());
        // Rest of your test
    }
}
