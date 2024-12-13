package iol;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class AssetManagerTest {
    @Mock
    private AssetRepository assetRepository;

    private AssetManager assetManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assetManager = new AssetManager(assetRepository);
    }

    @Test
    public void saveAsset() throws JsonProcessingException, JSONException {
        String jsonAsset = "{ \"cantidad\": 74.0000, \"comprometido\": 0.0000, \"puntosVariacion\": 164.00000000, \"variacionDiaria\": 0.78, \"ultimoPrecio\": 20944.00000000, \"ppc\": 11192.500, \"gananciaPorcentaje\": 87.12, \"gananciaDinero\": 7216.11, \"valorizado\": 15498.560000000000, \"titulo\": {  \"simbolo\": \"AL30\",  \"descripcion\": \"Bono Rep. Argentina Usd Step Up 2030\",  \"pais\": \"argentina\",  \"mercado\": \"bcba\",  \"tipo\": \"TitulosPublicos\",  \"plazo\": \"t2\",  \"moneda\": \"peso_Argentino\" }, \"parking\": null}";
        assetManager.mapAsset(jsonAsset);
    }

    @Test
    public void saveManyAssets() throws JsonProcessingException, JSONException {
        ArrayList<Object> portfolio = getPortfolioArray();
        for (Object a : portfolio) {
            System.out.println("a = " + a);
            assetManager.mapAsset(a.toString());
        }
    }

    public ArrayList<Object> getPortfolioArray() throws JSONException {
        String portfolio = getPortfolio();
        JSONObject jsonObject = new JSONObject(portfolio);
        JSONArray jsonArray = jsonObject.getJSONArray("activos");
        ArrayList<Object> listData = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                listData.add(jsonArray.get(i));
            }
        }
        return listData;
    }

    public String getPortfolio() {
        return "{\"pais\": \"argentina\",\"activos\": [" +
                // ... rest of your JSON string ...
                "]}";
    }

    public String getPortfolio_12_sep_23() {
        return "{\n" +
                // ... rest of your JSON string ...
                "}";
    }
}
