package ru.gb.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdatajpa.model.WebSiteUser;

@Repository
public interface UserRepository extends JpaRepository<WebSiteUser, Long> {
    WebSiteUser findByName(String name);

}
