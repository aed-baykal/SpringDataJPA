package ru.gb.springdatajpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdatajpa.model.Product;
import ru.gb.springdatajpa.model.Purchase;
import ru.gb.springdatajpa.service.CartServise;
import ru.gb.springdatajpa.service.PurchaseServise;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseRestController {
    private final PurchaseServise purchaseServise;
    private final CartServise cartServise;

    public PurchaseRestController(PurchaseServise purchaseServise, CartServise cartServise) {
        this.purchaseServise = purchaseServise;
        this.cartServise = cartServise;
    }

    // http://localhost:8080/app/purchase GET
    @GetMapping("/")
    public List<Purchase> getAllPurchase() {
        return purchaseServise.findAll().stream().toList();
    }

    // http://localhost:8080/app/purchase/info/1 GET
    @GetMapping("/info/{id}")
    public Purchase getPurchaseInfo(@PathVariable Long id) {
        return purchaseServise.findById(id);
    }

    @PostMapping("/add")
    public Purchase savePurchase(@RequestBody Product product) {
        Purchase purchase = new Purchase();
        purchase.setProductId(product.getId());
        purchase.setPrice(product.getPrice());
        purchase.setCart(cartServise.findById(1L));
        purchaseServise.save(purchase);
        return purchase;
    }

    // http://localhost:8080/app/purchase/delete/1 GET
    @GetMapping("/delete/{id}")
    public void deletePurchaseById(@PathVariable Long id) {
        purchaseServise.deleteById(id);
    }

}
