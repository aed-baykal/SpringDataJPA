package ru.gb.springdatajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdatajpa.controller.exeptions.ProductNotFoundExceptionResolver;
import ru.gb.springdatajpa.controller.exeptions.ProductsErrorResponse;
import ru.gb.springdatajpa.model.Product;
import ru.gb.springdatajpa.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RESTController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getStudentById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        product.setId(0L);
        productService.save(product);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updateProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return HttpStatus.OK.value();
    }

    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleException(ProductNotFoundExceptionResolver exc) {
        ProductsErrorResponse productsErrorResponse = new ProductsErrorResponse();
        productsErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        productsErrorResponse.setMessage(exc.getMessage());
        productsErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(productsErrorResponse, HttpStatus.NOT_FOUND);
    }

}
