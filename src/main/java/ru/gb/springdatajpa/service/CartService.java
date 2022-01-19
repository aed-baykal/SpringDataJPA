package ru.gb.springdatajpa.service;

import org.springframework.stereotype.Service;
import ru.gb.springdatajpa.converter.ProductConverter;
import ru.gb.springdatajpa.model.Cart;
import ru.gb.springdatajpa.model.Product;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class CartService {
    private final ProductService productService;
    private Cart cart;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCartForCurrnetUser() {
        return cart;
    }

    public Cart addProductById(Long id) {
        Product product = productService.findById(id);
        cart.addItem(ProductConverter.productToCartItem(Optional.ofNullable(product)));
        return cart;
    }

    public Cart removeProductById(Long id) {
        cart.removeItem(id);
        return cart;
    }
}
