package ru.gb.springdatajpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdatajpa.dto.ProductDto;
import ru.gb.springdatajpa.model.Product;
import ru.gb.springdatajpa.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/app/products GET
    @GetMapping("/products")
    public List<ProductDto> getAllStudents() {
        return productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    // http://localhost:8080/app/products/3 GET
    @GetMapping("/products/{id}")
    public ProductDto getStudentInfo(@PathVariable Long id) {
        return new ProductDto(productService.findById(id));
    }

    // http://localhost:8080/app/products POST
    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) {
        productService.save(product);
        return product;
    }

    // http://localhost:8080/app/products/delete/1 GET
    @GetMapping("products/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
