package iol;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AssetManagerTest {
    @Test
    public void saveAsset() throws JsonProcessingException {
        String jsonAsset = "{ \"cantidad\": 74.0000, \"comprometido\": 0.0000, \"puntosVariacion\": 164.00000000, \"variacionDiaria\": 0.78, \"ultimoPrecio\": 20944.00000000, \"ppc\": 11192.500, \"gananciaPorcentaje\": 87.12, \"gananciaDinero\": 7216.11, \"valorizado\": 15498.560000000000, \"titulo\": {  \"simbolo\": \"AL30\",  \"descripcion\": \"Bono Rep. Argentina Usd Step Up 2030\",  \"pais\": \"argentina\",  \"mercado\": \"bcba\",  \"tipo\": \"TitulosPublicos\",  \"plazo\": \"t2\",  \"moneda\": \"peso_Argentino\" }, \"parking\": null}";
        AssetManager assetManager = new AssetManager();
        assetManager.mapAsset(jsonAsset);
    }

    @Test
    public void saveManyAssets() throws JsonProcessingException {
        AssetManager assetManager = new AssetManager();
        ArrayList<Object> portfolio = getPortfolioArray();
        for (Object a : portfolio) {
            System.out.println("a = " + a);
            assetManager.mapAsset(a.toString());
        }
    }
    public ArrayList<Object> getPortfolioArray() {
        String portfolio = getPortfolio();
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
    public String getPortfolio() {
        return "{\"pais\": \"argentina\",\"activos\": [{\"cantidad\": 1.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 107.50000000,\"variacionDiaria\": 0.83,\"ultimoPrecio\": 13018.00000000,\"ppc\": 13050.000,\"gananciaPorcentaje\": -0.24,\"gananciaDinero\": -32.00,\"valorizado\": 13018.000000000000,\"titulo\": {\"simbolo\": \"AAPL\",\"descripcion\": \"Apple\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 74.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 52.00000000,\"variacionDiaria\": 0.24,\"ultimoPrecio\": 21040.00000000,\"ppc\": 11192.500,\"gananciaPorcentaje\": 87.98,\"gananciaDinero\": 7287.15,\"valorizado\": 15569.600000000000,\"titulo\": {\"simbolo\": \"AL30\",\"descripcion\": \"Bono Rep. Argentina Usd Step Up 2030\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"TitulosPublicos\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 21.0000,\"comprometido\": 0.0000,\"puntosVariacion\": -4.50000000,\"variacionDiaria\": -0.60,\"ultimoPrecio\": 742.00000000,\"ppc\": 710.778,\"gananciaPorcentaje\": 4.39,\"gananciaDinero\": 655.67,\"valorizado\": 15582.000000000000,\"titulo\": {\"simbolo\": \"AMZN\",\"descripcion\": \"Amazon\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 5.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 32.00000000,\"variacionDiaria\": 1.42,\"ultimoPrecio\": 2280.00000000,\"ppc\": 2130.000,\"gananciaPorcentaje\": 7.04,\"gananciaDinero\": 750.00,\"valorizado\": 11400.000000000000,\"titulo\": {\"simbolo\": \"BBD\",\"descripcion\": \"Banco Bradesco S.A.\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 1.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 183.00000000,\"variacionDiaria\": 1.74,\"ultimoPrecio\": 10660.00000000,\"ppc\": 10186.000,\"gananciaPorcentaje\": 4.65,\"gananciaDinero\": 474.00,\"valorizado\": 10660.000000000000,\"titulo\": {\"simbolo\": \"C\",\"descripcion\": \"Citigroup\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 4.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 52.50000000,\"variacionDiaria\": 1.02,\"ultimoPrecio\": 5199.00000000,\"ppc\": 5241.250,\"gananciaPorcentaje\": -0.8,\"gananciaDinero\": -169.00,\"valorizado\": 20796.000000000000,\"titulo\": {\"simbolo\": \"DISN\",\"descripcion\": \"The Walt Disney Company\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 0.2000,\"comprometido\": 0.0000,\"puntosVariacion\": 1.90000000,\"variacionDiaria\": 3.90,\"ultimoPrecio\": 50.50000000,\"ppc\": 35.400,\"gananciaPorcentaje\": 42.65,\"gananciaDinero\": 3.02,\"valorizado\": 10.100000000000,\"titulo\": {\"simbolo\": \"FERR\",\"descripcion\": \"Ferrum\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"ACCIONES\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 6.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 25.00000000,\"variacionDiaria\": 1.43,\"ultimoPrecio\": 1770.00000000,\"ppc\": 1738.000,\"gananciaPorcentaje\": 1.84,\"gananciaDinero\": 192.00,\"valorizado\": 10620.000000000000,\"titulo\": {\"simbolo\": \"GOOGL\",\"descripcion\": \"Google\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 1.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 138.50000000,\"variacionDiaria\": 0.63,\"ultimoPrecio\": 21877.00000000,\"ppc\": 20951.208,\"gananciaPorcentaje\": 4.41,\"gananciaDinero\": 925.79,\"valorizado\": 21877.000000000000,\"titulo\": {\"simbolo\": \"IBM\",\"descripcion\": \"Ibm\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 2.0000,\"comprometido\": 0.0000,\"puntosVariacion\": -19.50000000,\"variacionDiaria\": -0.24,\"ultimoPrecio\": 8090.00000000,\"ppc\": 5232.000,\"gananciaPorcentaje\": 54.62,\"gananciaDinero\": 5716.00,\"valorizado\": 16180.000000000000,\"titulo\": {\"simbolo\": \"JNJ\",\"descripcion\": \"Johnson & Johnson\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 1.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 248.00000000,\"variacionDiaria\": 2.63,\"ultimoPrecio\": 9642.00000000,\"ppc\": 9169.816,\"gananciaPorcentaje\": 5.14,\"gananciaDinero\": 472.18,\"valorizado\": 9642.000000000000,\"titulo\": {\"simbolo\": \"META\",\"descripcion\": \"Meta Platforms Inc\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 1.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 265.50000000,\"variacionDiaria\": 1.77,\"ultimoPrecio\": 15227.00000000,\"ppc\": 15676.000,\"gananciaPorcentaje\": -2.86,\"gananciaDinero\": -449.00,\"valorizado\": 15227.000000000000,\"titulo\": {\"simbolo\": \"MMM\",\"descripcion\": \"3M\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 2.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 54.13400000,\"variacionDiaria\": 0.66,\"ultimoPrecio\": 8171.00000000,\"ppc\": 8150.000,\"gananciaPorcentaje\": 0.25,\"gananciaDinero\": 42.00,\"valorizado\": 16342.000000000000,\"titulo\": {\"simbolo\": \"MO\",\"descripcion\": \"Altria Group\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 1.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 246.17400000,\"variacionDiaria\": 1.55,\"ultimoPrecio\": 16074.00000000,\"ppc\": 16182.500,\"gananciaPorcentaje\": -0.67,\"gananciaDinero\": -108.50,\"valorizado\": 16074.000000000000,\"titulo\": {\"simbolo\": \"MRK\",\"descripcion\": \"Merck\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 2.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 93.50000000,\"variacionDiaria\": 1.12,\"ultimoPrecio\": 8385.50000000,\"ppc\": 5969.500,\"gananciaPorcentaje\": 40.47,\"gananciaDinero\": 4832.00,\"valorizado\": 16771.000000000000,\"titulo\": {\"simbolo\": \"MSFT\",\"descripcion\": \"Microsoft\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 4.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 115.00000000,\"variacionDiaria\": 1.93,\"ultimoPrecio\": 6050.00000000,\"ppc\": 4867.693,\"gananciaPorcentaje\": 24.28,\"gananciaDinero\": 4729.23,\"valorizado\": 24200.000000000000,\"titulo\": {\"simbolo\": \"NKE\",\"descripcion\": \"Nike\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 3.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 46.00000000,\"variacionDiaria\": 0.32,\"ultimoPrecio\": 14064.00000000,\"ppc\": 11585.549,\"gananciaPorcentaje\": 21.39,\"gananciaDinero\": 7435.35,\"valorizado\": 42192.000000000000,\"titulo\": {\"simbolo\": \"NVDA\",\"descripcion\": \"Nvidia\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 1.0000,\"comprometido\": 0.0000,\"puntosVariacion\": -26.50000000,\"variacionDiaria\": -0.20,\"ultimoPrecio\": 12651.00000000,\"ppc\": 12822.000,\"gananciaPorcentaje\": -1.33,\"gananciaDinero\": -171.00,\"valorizado\": 12651.000000000000,\"titulo\": {\"simbolo\": \"PFE\",\"descripcion\": \"Pfizer\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 1.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 274.50000000,\"variacionDiaria\": 1.20,\"ultimoPrecio\": 22969.50000000,\"ppc\": 22714.000,\"gananciaPorcentaje\": 1.12,\"gananciaDinero\": 255.50,\"valorizado\": 22969.500000000000,\"titulo\": {\"simbolo\": \"PG\",\"descripcion\": \"Procter & Gamble\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 4.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 115.50000000,\"variacionDiaria\": 3.20,\"ultimoPrecio\": 3723.50000000,\"ppc\": 3651.750,\"gananciaPorcentaje\": 1.96,\"gananciaDinero\": 287.00,\"valorizado\": 14894.000000000000,\"titulo\": {\"simbolo\": \"T\",\"descripcion\": \"At&T\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 1.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 172.50000000,\"variacionDiaria\": 3.17,\"ultimoPrecio\": 5600.00000000,\"ppc\": 5546.500,\"gananciaPorcentaje\": 0.96,\"gananciaDinero\": 53.50,\"valorizado\": 5600.000000000000,\"titulo\": {\"simbolo\": \"WBA\",\"descripcion\": \"Walgreens Boots Alliance Inc.\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 266.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 3.36000000,\"variacionDiaria\": 2.62,\"ultimoPrecio\": 131.51000000,\"ppc\": 0.000,\"gananciaPorcentaje\": 0.0,\"gananciaDinero\": 0.0,\"valorizado\": 349.816600000000,\"titulo\": {\"simbolo\": \"X23N3\",\"descripcion\": \"Letra Rep Arg Ajust. Cer Dto Vt. 23/11/23 $\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"Letras\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null},{\"cantidad\": 1.0000,\"comprometido\": 0.0000,\"puntosVariacion\": 337.50000000,\"variacionDiaria\": 1.95,\"ultimoPrecio\": 17580.00000000,\"ppc\": 16622.820,\"gananciaPorcentaje\": 5.75,\"gananciaDinero\": 957.18,\"valorizado\": 17580.000000000000,\"titulo\": {\"simbolo\": \"XOM\",\"descripcion\": \"Exxon Mobil\",\"pais\": \"argentina\",\"mercado\": \"bcba\",\"tipo\": \"CEDEARS\",\"plazo\": \"t2\",\"moneda\": \"peso_Argentino\"},\"parking\": null}]}\n";
    }
    public String getPortfolio_12_sep_23() {
        return "{\n" +
                "    \"pais\": \"argentina\",\n" +
                "    \"activos\": [\n" +
                "        {\n" +
                "            \"cantidad\": 74.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 164.00000000,\n" +
                "            \"variacionDiaria\": 0.78,\n" +
                "            \"ultimoPrecio\": 20944.00000000,\n" +
                "            \"ppc\": 11192.500,\n" +
                "            \"gananciaPorcentaje\": 87.12,\n" +
                "            \"gananciaDinero\": 7216.11,\n" +
                "            \"valorizado\": 15498.560000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"AL30\",\n" +
                "                \"descripcion\": \"Bono Rep. Argentina Usd Step Up 2030\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"TitulosPublicos\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 14.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": -8.50000000,\n" +
                "            \"variacionDiaria\": -1.15,\n" +
                "            \"ultimoPrecio\": 725.00000000,\n" +
                "            \"ppc\": 698.634,\n" +
                "            \"gananciaPorcentaje\": 3.77,\n" +
                "            \"gananciaDinero\": 369.13,\n" +
                "            \"valorizado\": 10150.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"AMZN\",\n" +
                "                \"descripcion\": \"Amazon\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 5.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 23.00000000,\n" +
                "            \"variacionDiaria\": 1.04,\n" +
                "            \"ultimoPrecio\": 2216.50000000,\n" +
                "            \"ppc\": 2130.000,\n" +
                "            \"gananciaPorcentaje\": 4.06,\n" +
                "            \"gananciaDinero\": 432.50,\n" +
                "            \"valorizado\": 11082.500000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"BBD\",\n" +
                "                \"descripcion\": \"Banco Bradesco S.A.\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 1.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 307.00000000,\n" +
                "            \"variacionDiaria\": 3.07,\n" +
                "            \"ultimoPrecio\": 10275.50000000,\n" +
                "            \"ppc\": 10186.000,\n" +
                "            \"gananciaPorcentaje\": 0.87,\n" +
                "            \"gananciaDinero\": 89.50,\n" +
                "            \"valorizado\": 10275.500000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"C\",\n" +
                "                \"descripcion\": \"Citigroup\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 4.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 71.00000000,\n" +
                "            \"variacionDiaria\": 1.40,\n" +
                "            \"ultimoPrecio\": 5133.00000000,\n" +
                "            \"ppc\": 5241.250,\n" +
                "            \"gananciaPorcentaje\": -2.06,\n" +
                "            \"gananciaDinero\": -433.00,\n" +
                "            \"valorizado\": 20532.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"DISN\",\n" +
                "                \"descripcion\": \"The Walt Disney Company\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 0.2000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 1.65000000,\n" +
                "            \"variacionDiaria\": 3.61,\n" +
                "            \"ultimoPrecio\": 47.35000000,\n" +
                "            \"ppc\": 35.400,\n" +
                "            \"gananciaPorcentaje\": 33.75,\n" +
                "            \"gananciaDinero\": 2.39,\n" +
                "            \"valorizado\": 9.470000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"FERR\",\n" +
                "                \"descripcion\": \"Ferrum\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"ACCIONES\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 1.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": -291.00000000,\n" +
                "            \"variacionDiaria\": -1.33,\n" +
                "            \"ultimoPrecio\": 21544.00000000,\n" +
                "            \"ppc\": 20951.208,\n" +
                "            \"gananciaPorcentaje\": 2.82,\n" +
                "            \"gananciaDinero\": 592.79,\n" +
                "            \"valorizado\": 21544.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"IBM\",\n" +
                "                \"descripcion\": \"Ibm\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 2.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 85.50000000,\n" +
                "            \"variacionDiaria\": 1.07,\n" +
                "            \"ultimoPrecio\": 8051.00000000,\n" +
                "            \"ppc\": 5232.000,\n" +
                "            \"gananciaPorcentaje\": 53.88,\n" +
                "            \"gananciaDinero\": 5638.00,\n" +
                "            \"valorizado\": 16102.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"JNJ\",\n" +
                "                \"descripcion\": \"Johnson & Johnson\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 2.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 88.00000000,\n" +
                "            \"variacionDiaria\": 1.07,\n" +
                "            \"ultimoPrecio\": 8284.50000000,\n" +
                "            \"ppc\": 8150.000,\n" +
                "            \"gananciaPorcentaje\": 1.65,\n" +
                "            \"gananciaDinero\": 269.00,\n" +
                "            \"valorizado\": 16569.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"MO\",\n" +
                "                \"descripcion\": \"Altria Group\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 2.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": -129.50000000,\n" +
                "            \"variacionDiaria\": -1.55,\n" +
                "            \"ultimoPrecio\": 8184.00000000,\n" +
                "            \"ppc\": 5969.500,\n" +
                "            \"gananciaPorcentaje\": 37.09,\n" +
                "            \"gananciaDinero\": 4429.00,\n" +
                "            \"valorizado\": 16368.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"MSFT\",\n" +
                "                \"descripcion\": \"Microsoft\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 4.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 11.50000000,\n" +
                "            \"variacionDiaria\": 0.19,\n" +
                "            \"ultimoPrecio\": 5945.00000000,\n" +
                "            \"ppc\": 4867.693,\n" +
                "            \"gananciaPorcentaje\": 22.13,\n" +
                "            \"gananciaDinero\": 4309.23,\n" +
                "            \"valorizado\": 23780.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"NKE\",\n" +
                "                \"descripcion\": \"Nike\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 3.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": -5.00000000,\n" +
                "            \"variacionDiaria\": -0.03,\n" +
                "            \"ultimoPrecio\": 13840.00000000,\n" +
                "            \"ppc\": 11585.549,\n" +
                "            \"gananciaPorcentaje\": 19.45,\n" +
                "            \"gananciaDinero\": 6763.35,\n" +
                "            \"valorizado\": 41520.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"NVDA\",\n" +
                "                \"descripcion\": \"Nvidia\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 7.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": -38.50000000,\n" +
                "            \"variacionDiaria\": -0.23,\n" +
                "            \"ultimoPrecio\": 16472.00000000,\n" +
                "            \"ppc\": 16314.558,\n" +
                "            \"gananciaPorcentaje\": 0.96,\n" +
                "            \"gananciaDinero\": 1102.09,\n" +
                "            \"valorizado\": 115304.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"SPY\",\n" +
                "                \"descripcion\": \"Etf Spdr S&P 500\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 4.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 6.00000000,\n" +
                "            \"variacionDiaria\": 0.16,\n" +
                "            \"ultimoPrecio\": 3571.00000000,\n" +
                "            \"ppc\": 3651.750,\n" +
                "            \"gananciaPorcentaje\": -2.21,\n" +
                "            \"gananciaDinero\": -323.00,\n" +
                "            \"valorizado\": 14284.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"T\",\n" +
                "                \"descripcion\": \"At&T\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 1.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 134.50000000,\n" +
                "            \"variacionDiaria\": 2.55,\n" +
                "            \"ultimoPrecio\": 5400.00000000,\n" +
                "            \"ppc\": 5546.500,\n" +
                "            \"gananciaPorcentaje\": -2.64,\n" +
                "            \"gananciaDinero\": -146.50,\n" +
                "            \"valorizado\": 5400.000000000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"WBA\",\n" +
                "                \"descripcion\": \"Walgreens Boots Alliance Inc.\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"CEDEARS\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"cantidad\": 179.0000,\n" +
                "            \"comprometido\": 0.0000,\n" +
                "            \"puntosVariacion\": 0.15000000,\n" +
                "            \"variacionDiaria\": 0.11,\n" +
                "            \"ultimoPrecio\": 127.35000000,\n" +
                "            \"ppc\": 0.000,\n" +
                "            \"gananciaPorcentaje\": 0.0,\n" +
                "            \"gananciaDinero\": 0.0,\n" +
                "            \"valorizado\": 227.956500000000,\n" +
                "            \"titulo\": {\n" +
                "                \"simbolo\": \"X23N3\",\n" +
                "                \"descripcion\": \"Letra Rep Arg Ajust. Cer Dto Vt. 23/11/23 $\",\n" +
                "                \"pais\": \"argentina\",\n" +
                "                \"mercado\": \"bcba\",\n" +
                "                \"tipo\": \"Letras\",\n" +
                "                \"plazo\": \"t2\",\n" +
                "                \"moneda\": \"peso_Argentino\"\n" +
                "            },\n" +
                "            \"parking\": null\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}
