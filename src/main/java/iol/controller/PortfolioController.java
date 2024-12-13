package iol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import iol.PortfolioManager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;  // Add this import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "http://localhost:5173")  // Add this annotation
public class PortfolioController {

    private final PortfolioManager portfolioManager;
    private final ObjectMapper objectMapper;

    public PortfolioController(PortfolioManager portfolioManager) {
        this.portfolioManager = portfolioManager;
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPortfolio() throws IOException {
        String rawJson = portfolioManager.getPortfolio();
        // Parse the raw JSON and pretty print it
        Object jsonObject = objectMapper.readValue(rawJson, Object.class);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
    }
}