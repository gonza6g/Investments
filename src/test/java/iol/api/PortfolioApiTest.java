package iol.api;

import iol.PortfolioManager;
import iol.TokenObtainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PortfolioApi.class)
public class PortfolioApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PortfolioManager portfolioManager;

    @MockBean
    private TokenObtainer tokenObtainer;

    @Test
    public void getCurrentPortfolio_ShouldReturnPortfolioData() throws Exception {
        // Given
        String token = "test-token";
        String portfolioJson = "{\"someKey\": \"someValue\"}";  // Simple test JSON
        
        when(tokenObtainer.getAccessToken()).thenReturn(token);
        when(portfolioManager.getPortfolio()).thenReturn(portfolioJson);

        // When/Then
        mockMvc.perform(get("/api/portfolio"))
                .andExpect(status().isOk())
                .andExpect(content().json(portfolioJson));
    }
} 