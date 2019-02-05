package com.example.myshop.shoppingcart;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return shoppingCartService.getAllProducts();
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        shoppingCartService.addProduct(product);
    }

    @DeleteMapping
    public Optional<Product> deleteProduct(@RequestBody Product product) {
        return shoppingCartService.deleteProduct(product);
    }
}
