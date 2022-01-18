package ru.gb.springdatajpa.service;

import org.springframework.stereotype.Service;
import ru.gb.springdatajpa.model.Cart;
import ru.gb.springdatajpa.repository.CartRepository;

import java.util.List;

@Service
public class CartServise {
    private final CartRepository cartRepository;

    public CartServise(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
