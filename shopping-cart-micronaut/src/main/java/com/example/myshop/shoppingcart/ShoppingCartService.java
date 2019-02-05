package com.example.myshop.shoppingcart;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class ShoppingCartService {

    private final ArrayList<Product> products = new ArrayList<>();

    public List<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Optional<Product> deleteProduct(Product product) {
        Optional<Product> result = products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst();
        result.ifPresent(products::remove);
        return result;
    }
}
