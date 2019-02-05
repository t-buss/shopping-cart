package com.example.myshop.shoppingcart;

import io.micronaut.http.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Get
    public List<Product> getAllProducts() {
        return shoppingCartService.getAllProducts();
    }

    @Post
    public void addProduct(@Body Product product) {
        shoppingCartService.addProduct(product);
    }

    @Delete
    public Optional<Product> deleteProduct(@Body Product product) {
        return shoppingCartService.deleteProduct(product);
    }
}