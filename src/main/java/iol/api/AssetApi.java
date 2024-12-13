package iol.api;

import iol.Asset;
import iol.AssetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssetApi {
    private final AssetRepository assetRepository;

    public AssetApi(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @GetMapping("/assets")
    public List<Asset> getAssets() {
        return assetRepository.findAll(); // Assuming the method is called findAll()
    }
} 