package com.example.demo.dao;

import com.example.demo.model.Bag;
import com.example.demo.model.Cart;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository("cart")
public class CartDataAccessService implements CartDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CartDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertCart(UUID id, Cart cart, Person person) {
        final String sql = "INSERT INTO cart (idcart, id_person) VALUES(?, ?)";
        return jdbcTemplate.update(sql, id, person.getId());
    }

    @Override
    public List<Cart> selectAllCarts() {
        final String sql = "SELECT idcart FROM cart";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID idCart = UUID.fromString(resultSet.getString("idcart"));
            return new Cart(idCart, null);
        });
    }

    @Override
    public Cart selectAllProductsFromCart(UUID idCart) {
        Cart cart = new Cart(idCart, null);
        final String sql = "SELECT idBag, brand, color, price, profitMargin FROM bag WHERE id_cart = ?";
        List<Bag> bags = jdbcTemplate.query(
                sql,
                new Object[]{idCart},
                (resultSet, i) -> {
                    UUID idBag = UUID.fromString(resultSet.getString("idBag"));
                    String brand = resultSet.getString("brand");
                    String color = resultSet.getString("color");
                    double price = resultSet.getDouble("price");
                    double profitMargin = resultSet.getDouble("profitMargin");
                    return new Bag(idBag, brand, color, price, profitMargin);
        });
        cart.setBags(bags);
        return cart;
    }

    @Override
    public int deleteAllCartById(UUID idCart) {
        final String sql = "DELETE FROM cart WHERE idCart = ?";
        Object[] args = new Object[]{idCart};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int deleteProductFromCartById(UUID idCart, UUID idBag) {
        final String sql = "DELETE FROM bag WHERE id_cart = ? AND idBag = ?";
        Object[] args = new Object[]{idCart, idBag};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int updateProductFromCartById(UUID idCart, UUID idBag, double price) {
        final String sql = "UPDATE bag SET price = ? WHERE id_cart = ? AND idBag = ?";
        return jdbcTemplate.update(sql, price, idBag, idCart);
    }
}
