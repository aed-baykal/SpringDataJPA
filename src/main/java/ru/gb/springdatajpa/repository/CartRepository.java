package ru.gb.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdatajpa.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
