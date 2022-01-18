package ru.gb.springdatajpa.service;

import org.springframework.stereotype.Service;
import ru.gb.springdatajpa.model.Purchase;
import ru.gb.springdatajpa.repository.PurchaseRepository;

import java.util.List;

@Service
public class PurchaseServise {

    private final PurchaseRepository purchaseRepository;

    public PurchaseServise(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public Purchase findById(Long id) {
       return purchaseRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        purchaseRepository.deleteById(id);
    }
}
