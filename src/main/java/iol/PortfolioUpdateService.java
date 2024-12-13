package iol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class PortfolioUpdateService {
    private static final Logger logger = LoggerFactory.getLogger(PortfolioUpdateService.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    private final AssetManager assetManager;
    private final PortfolioManager portfolioManager;
    private volatile LocalDateTime lastUpdateTime;
    
    public PortfolioUpdateService(AssetManager assetManager, TokenObtainer tokenObtainer) {
        this.assetManager = assetManager;
        this.portfolioManager = new PortfolioManager(tokenObtainer);
        startScheduling();
    }
    
    @PostConstruct
    private void startScheduling() {
        // Schedule task to run every 30 minutes (1800000 milliseconds)
        scheduler.scheduleAtFixedRate(this::updatePortfolio, 0, 30, TimeUnit.MINUTES);
        logger.info("Portfolio update scheduler initialized");
    }
    
    @PreDestroy
    private void stopScheduling() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
        logger.info("Portfolio update scheduler shutdown");
    }
    
    public void updatePortfolio() {
        try {
            logger.info("Starting scheduled portfolio update");
            String portfolioString = portfolioManager.getPortfolio();
            ArrayList<Object> portfolio = getPortfolioArray(portfolioString);
            
            for (Object asset : portfolio) {
                logger.debug("Processing asset: {}", asset);
                assetManager.mapAsset(asset.toString());
            }
            lastUpdateTime = LocalDateTime.now();
            logger.info("Completed scheduled portfolio update at {}", lastUpdateTime);
        } catch (Exception e) {
            logger.error("Error during scheduled portfolio update", e);
        }
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }
    
    public String getLastUpdateTimeFormatted() {
        if (lastUpdateTime == null) {
            return "Never updated";
        }
        return lastUpdateTime.toString();
    }
    
    private ArrayList<Object> getPortfolioArray(String portfolio) {
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
} 