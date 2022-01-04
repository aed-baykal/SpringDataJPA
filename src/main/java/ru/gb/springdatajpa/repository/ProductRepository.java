package ru.gb.springdatajpa.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springdatajpa.model.Product;

import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class ProductRepository{

    @PersistenceContext
    private Session session;

    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return session.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Transactional
    public void save(Product product) {
        session.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(session.get(Product.class, id));
    }

    @Transactional
    public void deleteById(Long id){
        session.createQuery("delete from Product p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
