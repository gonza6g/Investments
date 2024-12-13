package iol;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;

@Repository
public class AssetRepositoryImpl implements AssetRepository {
    private final PostgreSqlDao postgreSqlDao;

    public AssetRepositoryImpl() {
        this.postgreSqlDao = new PostgreSqlDao();
    }

    @Override
    public List<Asset> findAll() {
        return new ArrayList<>();
    }

    @Override
    public void save(Asset asset) {
        postgreSqlDao.save(asset);
    }
} 