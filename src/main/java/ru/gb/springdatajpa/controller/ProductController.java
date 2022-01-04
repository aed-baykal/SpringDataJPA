package ru.gb.springdatajpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.springdatajpa.model.Product;
import ru.gb.springdatajpa.service.ProductService;

import java.util.List;


@Controller
public class ProductController {

    private static final int PAGE_SIZE = 10;
    private int page = 0;
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/app/all GET
    @GetMapping("/all{param}")
    public String getAllProducts(@PathVariable String param, Model model, Pageable pageable) {
        if (!param.equals("")
                && ((page + Integer.parseInt(param)) > -1)
                && ((page + Integer.parseInt(param)) <= productService.findAll(pageable).getTotalPages()))
            page = page + Integer.parseInt(param);
        pageable = PageRequest.of(page, PAGE_SIZE);
        List<Product> page = productService.findAll(pageable).stream().toList();
        model.addAttribute("products", page);
        return "product_list";
    }

    // http://localhost:8080/app/info/3 GET
    @GetMapping("/info/{id}")
    public String getProductInfo(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product_info";
    }

    // http://localhost:8080/app/add GET
    @GetMapping("/add")
    public String getProductAddFrom() {
        return "product_form";
    }

    // http://localhost:8080/app/add POST
    @PostMapping("/add")
    public String saveProduct(Product product) {
        productService.save(product);
        return "redirect:/all";
    }

    // http://localhost:8080/app/delete/3 POST
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/all";
    }

}
