package ru.gb.springdatajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // http://localhost:8080/app/ GET
    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

}
