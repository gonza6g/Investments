package iol.api;

import iol.PortfolioManager;
import iol.TokenObtainer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PortfolioApi {

    private final PortfolioManager portfolioManager;
    private final TokenObtainer tokenObtainer;

    public PortfolioApi(PortfolioManager portfolioManager, TokenObtainer tokenObtainer) {
        this.portfolioManager = portfolioManager;
        this.tokenObtainer = tokenObtainer;
    }

    @GetMapping("/portfolio")
    public ResponseEntity<String> getCurrentPortfolio() {
        try {
            String token = tokenObtainer.getAccessToken();
            String portfolio = portfolioManager.getPortfolio();
            return ResponseEntity.ok(portfolio);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body("Error fetching portfolio: " + e.getMessage());
        }
    }
} 