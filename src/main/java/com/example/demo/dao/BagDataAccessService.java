package com.example.demo.dao;

import com.example.demo.model.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("bag")
public class BagDataAccessService implements  BagDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BagDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertBag(UUID id, Bag bag, UUID idCart) {
        final String sql = "INSERT INTO bag (idBag, brand, color, price, profitMargin, id_cart) VALUES(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, id, bag.getBrand(), bag.getColor(), bag.getPrice(), bag.getProfitMargin(), idCart);
    }

    @Override
    public List<Bag> selectAllBags() {
        final String sql = "SELECT idBag, brand, color, price, profitMargin FROM bag";
        return jdbcTemplate.query(
                sql,
                (resultSet, i) -> {
                    UUID idBag = UUID.fromString(resultSet.getString("idBag"));
                    String brand = resultSet.getString("brand");
                    String color = resultSet.getString("color");
                    double price = resultSet.getDouble("price");
                    double profitMargin = resultSet.getDouble("profitMargin");
                    return new Bag(idBag, brand, color, price, profitMargin);
        });
    }

    @Override
    public Optional<Bag> selectBagById(UUID id) {
        final String sql = "SELECT idBag, brand, color, price, profitMargin  FROM bag WHERE idBag = ?";
        Bag bag = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID idBag = UUID.fromString(resultSet.getString("idBag"));
                    String brand = resultSet.getString("brand");
                    String color = resultSet.getString("color");
                    double price = resultSet.getDouble("price");
                    double profitMargin = resultSet.getDouble("profitMargin");
                    return new Bag(idBag, brand, color, price, profitMargin);
                });
        return Optional.ofNullable(bag);
    }

    @Override
    public int deleteBagById(UUID id) {
        final String sql = "DELETE FROM bag WHERE idBag = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int updateBagByID(UUID id, double price) {
        final String sql = "UPDATE bag SET price = ? WHERE idBag = ?";
        return jdbcTemplate.update(sql, price, id);
    }
}
