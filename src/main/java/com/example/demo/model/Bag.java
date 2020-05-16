package com.example.demo.model;

import java.util.UUID;

public class Bag {
    private final UUID idBag;
    String brand;
    String color;
    double price;
    double profitMargin;

    public Bag(UUID idBag, String brand, String color, double price, double profitMargin) {
        this.idBag = idBag;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.profitMargin = profitMargin;
    }

    public UUID getIdBag() {
        return idBag;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }
}
