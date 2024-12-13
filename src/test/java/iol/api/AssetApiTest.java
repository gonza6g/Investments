package iol.api;

import iol.Asset;
import iol.AssetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AssetApi.class)
public class AssetApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetRepository assetRepository;

    @Test
    public void getAssets_ShouldReturnAllAssets() throws Exception {
        // Given
        Asset asset1 = new Asset(
            100f,  // cantidad
            0f,    // comprometido
            0f,    // puntosVariacion
            0f,    // variacionDiaria
            150.0f,// ultimoPrecio
            145.0f,// ppc
            3.45f, // gananciaPorcentaje
            517.5f,// gananciaDinero
            15000f,// valorizado
            "AAPL",// symbol
            "Apple Inc.", // descripcion
            "USA",  // pais
            "NASDAQ",// mercado
            "STOCK", // tipo
            "T+2",   // plazo
            "USD",   // moneda
            false,   // parking
            "Technology" // industry
        );

        Asset asset2 = new Asset(
            50f,   // cantidad
            0f,    // comprometido
            0f,    // puntosVariacion
            0f,    // variacionDiaria
            2800.0f,// ultimoPrecio
            2750.0f,// ppc
            1.82f,  // gananciaPorcentaje
            2500f,  // gananciaDinero
            140000f,// valorizado
            "GOOGL",// symbol
            "Alphabet Inc.", // descripcion
            "USA",  // pais
            "NASDAQ",// mercado
            "STOCK", // tipo
            "T+2",   // plazo
            "USD",   // moneda
            false,   // parking
            "Technology" // industry
        );
        
        List<Asset> assets = List.of(asset1, asset2);
        
        when(assetRepository.findAll()).thenReturn(assets);

        // When/Then
        mockMvc.perform(get("/api/assets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].symbol").value("AAPL"))
                .andExpect(jsonPath("$[1].symbol").value("GOOGL"));
    }
} 