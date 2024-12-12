package iol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AssetManager {
    public void mapAsset(String jsonAsset) throws JsonProcessingException {
        JsonNode productNode = new ObjectMapper().readTree(jsonAsset);

        Asset asset = new Asset();

        asset.setCantidad(Float.parseFloat(productNode.get("cantidad").toString()));
        asset.setComprometido(Float.parseFloat(productNode.get("comprometido").toString()));
        asset.setPuntosVariacion(Float.parseFloat(productNode.get("puntosVariacion").toString()));
        asset.setVariacionDiaria(Float.parseFloat(productNode.get("variacionDiaria").toString()));
        asset.setUltimoPrecio(Float.parseFloat(productNode.get("ultimoPrecio").toString()));
        asset.setPpc(Float.parseFloat(productNode.get("ppc").toString()));
        asset.setGananciaPorcentaje(Float.parseFloat(productNode.get("gananciaPorcentaje").toString()));
        asset.setGananciaDinero(Float.parseFloat(productNode.get("gananciaDinero").toString()));
        asset.setValorizado(Float.parseFloat(productNode.get("valorizado").toString()));

        asset.setSymbol(productNode.get("titulo").get("simbolo").toString().replaceAll("\"", ""));

        asset.setDescripcion(productNode.get("titulo").get("descripcion").toString().replaceAll("\"", ""));

        asset.setPais(searchAssetCountry(asset.getSymbol()));

        asset.setMercado(productNode.get("titulo").get("mercado").toString().replaceAll("\"", ""));
        asset.setTipo(productNode.get("titulo").get("tipo").toString().replaceAll("\"", ""));
        asset.setPlazo(productNode.get("titulo").get("plazo").toString().replaceAll("\"", ""));
        asset.setMoneda(productNode.get("titulo").get("moneda").toString().replaceAll("\"", ""));

        asset.setParking(Boolean.valueOf(productNode.get("parking").toString()));

        asset.setIndustry(searchAssetIndustry(asset.getSymbol()));

        PostgreSqlDao postgreSqlDao = new PostgreSqlDao();
        postgreSqlDao.save(asset);
    }

    private String searchAssetCountry(String symbol) {
        String country = "EMPTY";
        try {
            country = AssetRepository.valueOf(symbol.replaceAll("\"", "")).getCountry();
        } catch (IllegalArgumentException e) {
            return country;
        }
        return country;
    }

    private String searchAssetIndustry(String symbol) {
        String industry = "EMPTY";
        try {
            industry = AssetRepository.valueOf(symbol.replaceAll("\"", "")).getIndustry();
        } catch (IllegalArgumentException e) {
            return industry;
        }
        return industry;
    }
}
