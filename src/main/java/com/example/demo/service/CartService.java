package com.example.demo.service;

import com.example.demo.dao.CartDao;
import com.example.demo.model.Cart;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    private final CartDao cartDao;

    @Autowired
    public CartService(@Qualifier("cart") CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public int addCart(Cart cart, Person person) {
        return cartDao.insertCart(cart, person);
    }

    public List<Cart> selectAllCarts() {
        return cartDao.selectAllCarts();
    }

    public Cart selectAllProducts(UUID idCart) {
        return cartDao.selectAllProductsFromCart(idCart);
    }

    public int deleteCartById(UUID idCart) {
        return cartDao.deleteAllCartById(idCart);
    }

    public int deleteProductFromCartById(UUID idCart, UUID idBag) {
        return cartDao.deleteProductFromCartById(idCart, idBag);
    }

    public int updateProductFromCartById(UUID idCart, UUID idBag, double newPrice) {
        return cartDao.updateProductFromCartById(idCart, idBag, newPrice);
    }
}
