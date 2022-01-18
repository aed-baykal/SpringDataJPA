package ru.gb.springdatajpa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdatajpa.converter.ProductConverter;
import ru.gb.springdatajpa.dto.ProductDto;
import ru.gb.springdatajpa.model.Product;
import ru.gb.springdatajpa.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/app/products GET
    @GetMapping("/")
    public List<ProductDto> getAllProducts() {
        return productService.findAll().stream().map(ProductConverter::productToProductDto).toList();
    }

    // http://localhost:8080/app/products/3 GET
    @GetMapping("/info/{id}")
    public ProductDto getStudentInfo(@PathVariable Long id) {
        return ProductConverter.productToProductDto(productService.findById(id));
    }

    // http://localhost:8080/app/products/add POST
    @PostMapping("/add")
    public ProductDto saveProduct(@Valid @RequestBody ProductDto productDto) {
        Product product = ProductConverter.productDtoToProduct(productDto);
        productService.save(product);
        return ProductConverter.productToProductDto(product);
    }

    // http://localhost:8080/app/products/delete/1 GET
    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
