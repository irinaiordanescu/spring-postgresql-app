package com.example.demo.model;

import java.util.List;
import java.util.UUID;

public class Cart {
    private final UUID idCart;
    List<Bag> bags;

    public Cart(UUID idCart, List<Bag> bags) {
        this.idCart = idCart;
        this.bags = bags;
    }

    public UUID getIdCart() {
        return idCart;
    }

    public List<Bag> getBags() {
        return bags;
    }

    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }
}
