package com.example.myshop.shoppingcart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    private final Long id;
    private final String description;

    public Product(@JsonProperty("id") Long id,
                   @JsonProperty("description") String description) {
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
