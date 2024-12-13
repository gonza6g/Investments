package iol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class AssetManager {
    private final ObjectMapper objectMapper;
    private final AssetRepository assetRepository;
    
    public AssetManager(AssetRepository assetRepository) {
        this.objectMapper = new ObjectMapper();
        this.assetRepository = assetRepository;
    }

    public void mapAsset(String jsonAsset) throws JsonProcessingException {
        JsonNode productNode = objectMapper.readTree(jsonAsset);
        Asset asset = createAssetFromJson(productNode);
        assetRepository.save(asset);
    }

    private Asset createAssetFromJson(JsonNode productNode) {
        Asset asset = new Asset();
        
        // Numeric fields
        asset.setCantidad(parseFloat(productNode, "cantidad"));
        asset.setComprometido(parseFloat(productNode, "comprometido"));
        asset.setPuntosVariacion(parseFloat(productNode, "puntosVariacion"));
        asset.setVariacionDiaria(parseFloat(productNode, "variacionDiaria"));
        asset.setUltimoPrecio(parseFloat(productNode, "ultimoPrecio"));
        asset.setPpc(parseFloat(productNode, "ppc"));
        asset.setGananciaPorcentaje(parseFloat(productNode, "gananciaPorcentaje"));
        asset.setGananciaDinero(parseFloat(productNode, "gananciaDinero"));
        asset.setValorizado(parseFloat(productNode, "valorizado"));

        // String fields
        String symbol = getStringValue(productNode.get("titulo"), "simbolo");
        asset.setSymbol(symbol);
        asset.setDescripcion(getStringValue(productNode.get("titulo"), "descripcion"));
        asset.setPais(searchAssetCountry(symbol));
        asset.setMercado(getStringValue(productNode.get("titulo"), "mercado"));
        asset.setTipo(getStringValue(productNode.get("titulo"), "tipo"));
        asset.setPlazo(getStringValue(productNode.get("titulo"), "plazo"));
        asset.setMoneda(getStringValue(productNode.get("titulo"), "moneda"));

        // Boolean and other fields
        asset.setParking(productNode.get("parking").asBoolean());
        asset.setIndustry(searchAssetIndustry(symbol));

        return asset;
    }

    private float parseFloat(JsonNode node, String fieldName) {
        return Float.parseFloat(node.get(fieldName).toString());
    }

    private String getStringValue(JsonNode node, String fieldName) {
        return node.get(fieldName).toString().replaceAll("\"", "");
    }

    private String searchAssetCountry(String symbol) {
        try {
            return AssetType.valueOf(symbol).getCountry();
        } catch (IllegalArgumentException e) {
            return "EMPTY";
        }
    }

    private String searchAssetIndustry(String symbol) {
        try {
            return AssetType.valueOf(symbol).getIndustry();
        } catch (IllegalArgumentException e) {
            return "EMPTY";
        }
    }
}
