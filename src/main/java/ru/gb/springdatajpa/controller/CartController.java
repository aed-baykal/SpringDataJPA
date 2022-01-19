package ru.gb.springdatajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdatajpa.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/add/{id}") // GET cart/product/add/1
    public String addProduct(@PathVariable Long id) {
        cartService.addProductById(id);
        return "redirect:/all";
    }

    @PostMapping("/del/{id}") // GET cart/product/del/1
    public String deleteProduct(@PathVariable Long id) {
        cartService.removeProductById(id);
        return "redirect:/all";
    }

}
