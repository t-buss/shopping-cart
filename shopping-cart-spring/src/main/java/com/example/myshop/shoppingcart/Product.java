package com.example.myshop.shoppingcart;

public class Product {

    private final Long id;
    private final String description;

    public Product(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
