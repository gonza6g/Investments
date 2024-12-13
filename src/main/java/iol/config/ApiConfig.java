package iol.config;

import iol.PortfolioManager;
import iol.TokenObtainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import org.json.JSONException;

@Configuration
public class ApiConfig {

    @Bean
    public TokenObtainer tokenObtainer() {
        return new TokenObtainer();
    }

    @Bean
    public PortfolioManager portfolioManager(TokenObtainer tokenObtainer) {
        return new PortfolioManager(tokenObtainer);
    }
} 