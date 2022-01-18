package ru.gb.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdatajpa.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
