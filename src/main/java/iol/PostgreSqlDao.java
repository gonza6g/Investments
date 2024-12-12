package iol;

import java.sql.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class PostgreSqlDao implements Dao<Asset, Integer> {
    private final Optional<Connection> connection;

    public PostgreSqlDao() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Asset> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Asset> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> save(Asset asset) {
        String message = "The asset to be added should not be null";
        Asset nonNullAsset = Objects.requireNonNull(asset, message);
        String sql = "INSERT INTO "
                + "assets(cantidad, comprometido, puntosVariacion, variacionDiaria, ultimoPrecio, ppc, gananciaPorcentaje, gananciaDinero, valorizado, simbolo, descripcion, pais, mercado, tipo, plazo, moneda, parking, industry) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement =
                         conn.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {

                statement.setFloat(1, nonNullAsset.getCantidad());
                statement.setFloat(2, nonNullAsset.getComprometido());
                statement.setFloat(3, nonNullAsset.getPuntosVariacion());
                statement.setFloat(4, nonNullAsset.getVariacionDiaria());
                statement.setFloat(5, nonNullAsset.getUltimoPrecio());
                statement.setFloat(6, nonNullAsset.getPpc());
                statement.setFloat(7, nonNullAsset.getGananciaPorcentaje());
                statement.setFloat(8, nonNullAsset.getGananciaDinero());
                statement.setFloat(9, nonNullAsset.getValorizado());
                statement.setString(10, nonNullAsset.getSymbol());
                statement.setString(11, nonNullAsset.getDescripcion());
                statement.setString(12, nonNullAsset.getPais());
                statement.setString(13, nonNullAsset.getMercado());
                statement.setString(14, nonNullAsset.getTipo());
                statement.setString(15, nonNullAsset.getPlazo());
                statement.setString(16, nonNullAsset.getMoneda());
                statement.setBoolean(17, nonNullAsset.getParking());
                statement.setString(18, nonNullAsset.getIndustry());

                int numberOfInsertedRows = statement.executeUpdate();

                // Retrieve the auto-generated id
                if (numberOfInsertedRows > 0) {
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            generatedId = Optional.of(resultSet.getInt(1));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("ex = " + ex);
            }

            return generatedId;
        });
    }

    @Override
    public void update(Asset asset) {

    }

    @Override
    public void delete(Asset asset) {

    }
}