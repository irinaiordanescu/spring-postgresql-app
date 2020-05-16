package com.example.demo.dao;

import com.example.demo.model.Cart;
import com.example.demo.model.Person;

import java.util.List;
import java.util.UUID;

public interface CartDao {
    int insertCart(UUID id, Cart cart, Person person);

    default int insertCart(Cart cart, Person person) {
        UUID id = UUID.randomUUID();
        return insertCart(id, cart, person);
    }

    List<Cart> selectAllCarts();

    Cart selectAllProductsFromCart(UUID idCart);

    int deleteAllCartById(UUID idCart);

    int deleteProductFromCartById(UUID idCart, UUID idBag);

    int updateProductFromCartById(UUID id, UUID idBag, double price);
}
