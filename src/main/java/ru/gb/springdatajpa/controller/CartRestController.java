package ru.gb.springdatajpa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdatajpa.dto.CartItem;
import ru.gb.springdatajpa.model.Cart;
import ru.gb.springdatajpa.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart/rest")
public class CartRestController {
    private final CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/all_items")
    public List<CartItem> getAllCartItemsForCurrentUser(){return cartService.getCartForCurrnetUser().getItems();}
    @GetMapping// GET cart
    public Cart getCart() {
        return cartService.getCartForCurrnetUser();
    }

    @PostMapping("/add/{id}") // POST cart/product/add/1
    public Cart addProduct(@PathVariable Long id) {
        return cartService.addProductById(id);
    }

    @DeleteMapping("/del/{id}") // DELETE cart/product/del/1
    public Cart deleteProduct(@PathVariable Long id) {
        return cartService.removeProductById(id);
    }

}
