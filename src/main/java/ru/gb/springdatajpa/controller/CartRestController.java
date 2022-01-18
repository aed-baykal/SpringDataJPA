package ru.gb.springdatajpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdatajpa.model.Cart;
import ru.gb.springdatajpa.model.Purchase;
import ru.gb.springdatajpa.service.CartServise;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartRestController {
    private final CartServise cartServise;

    public CartRestController(CartServise cartServise) {
        this.cartServise = cartServise;
    }

    // http://localhost:8080/app/cart GET
    @GetMapping("/")
    public List<Cart> getAllCart() {
        return cartServise.findAll().stream().toList();
    }

    // http://localhost:8080/app/cart/info/1 GET
    @GetMapping("/info/{id}")
    public Cart getCartInfo(@PathVariable Long id) {
        return cartServise.findById(id);
    }

    // http://localhost:8080/app/cart/purchase_info/1 GET
    @GetMapping("/purchase_info/{id}")
    public List<Purchase> getCartPurchasesInfo(@PathVariable Long id) {
        return cartServise.findById(id).getPurchasesList();
    }

    // http://localhost:8080/app/cart/add POST
    @PostMapping("/add")
    public Cart saveCart(@RequestBody Cart cart) {
        cartServise.save(cart);
        return cart;
    }

    // http://localhost:8080/app/cart/delete/1 GET
    @GetMapping("/delete/{id}")
    public void deleteCartById(@PathVariable Long id) {
        cartServise.deleteById(id);
    }

}
