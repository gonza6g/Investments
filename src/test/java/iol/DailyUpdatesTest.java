package iol;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Test class for handling daily portfolio updates from IOL (Invertir Online).
 * 
 * This class provides functionality to:
 * 1. Fetch the current portfolio from IOL using authentication
 * 2. Parse the portfolio JSON response into a list of assets
 * 3. Process each asset through an AssetManager
 */
public class DailyUpdatesTest {
    private AssetManager assetManager = new AssetManager(new AssetRepositoryImpl());

    @Test
    public void dailyUpdate() throws IOException {
        String portfolioString = getPortfolio();
        System.out.println("portfolioString = " + portfolioString);
        ArrayList<Object> portfolio = getPortfolioArray(portfolioString);

        for (Object a : portfolio) {
            System.out.println("a = " + a);
            assetManager.mapAsset(a.toString());
        }
    }

    /**
     * Retrieves the portfolio data from IOL by:
     * 1. Obtaining an authentication token
     * 2. Using the token to fetch portfolio data
     */
    public String getPortfolio() throws IOException {
        TokenObtainer tokenObtainer = new TokenObtainer();
        tokenObtainer.obtainAccessToken();

        String token = tokenObtainer.getAccessToken();

        PortfolioManager portfolioManager = new PortfolioManager(tokenObtainer);
        return portfolioManager.getPortfolio();
    }

    /**
     * Parses a portfolio JSON string into an ArrayList of assets
     * The JSON is expected to have an "activos" array containing the portfolio assets
     */
    public ArrayList<Object> getPortfolioArray(String portfolio) {
        JSONObject jsonObject = new JSONObject(portfolio);
        JSONArray jsonArray = jsonObject.getJSONArray("activos");
        ArrayList<Object> listData = new ArrayList<Object>();
        if (jsonArray != null) {
            for (int i=0;i<jsonArray.length();i++){
                listData.add(jsonArray.get(i));
            }
        }
        return listData;
    }
}
