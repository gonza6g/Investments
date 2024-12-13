package iol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import iol.PortfolioManager;
import iol.PortfolioUpdateService;
import iol.TokenObtainer;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PortfolioController {
    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);
    private final PortfolioUpdateService portfolioUpdateService;
    private final PortfolioManager portfolioManager;

    @Autowired
    public PortfolioController(PortfolioUpdateService portfolioUpdateService, TokenObtainer tokenObtainer) {
        this.portfolioUpdateService = portfolioUpdateService;
        this.portfolioManager = new PortfolioManager(tokenObtainer);
    }

    @GetMapping("/portfolio")
    public Map<String, Object> getPortfolio() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("portfolio", portfolioManager.getPortfolio());
            response.put("lastUpdate", portfolioUpdateService.getLastUpdateTimeFormatted());
            return response;
        } catch (Exception e) {
            logger.error("Error getting portfolio", e);
            throw new RuntimeException("Error getting portfolio: " + e.getMessage());
        }
    }
}