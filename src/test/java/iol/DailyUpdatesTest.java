package iol;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class DailyUpdatesTest {
    @Test
    public void dailyUpdate() throws IOException {
        String portfolioString = getPortfolio();
        System.out.println("portfolioString = " + portfolioString);
        ArrayList<Object> portfolio = getPortfolioArray(portfolioString);

        AssetManager assetManager = new AssetManager();
        for (Object a : portfolio) {
            System.out.println("a = " + a);
            assetManager.mapAsset(a.toString());
        }
    }

    public String getPortfolio() throws IOException {
        TokenObtainer tokenObtainer = new TokenObtainer();
        tokenObtainer.obtainAccessToken();

        String token = tokenObtainer.getAccessToken();

        PortfolioManager portfolioManager = new PortfolioManager(token);
        return portfolioManager.getPortfolio();
    }

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
