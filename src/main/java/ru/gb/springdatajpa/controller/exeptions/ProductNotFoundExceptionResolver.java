package ru.gb.springdatajpa.controller.exeptions;

public class ProductNotFoundExceptionResolver extends RuntimeException {
    public ProductNotFoundExceptionResolver(String message) {
        super(message);
    }
}