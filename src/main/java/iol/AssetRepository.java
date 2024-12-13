package iol;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssetRepository {
    List<Asset> findAll();
    void save(Asset asset);
}
