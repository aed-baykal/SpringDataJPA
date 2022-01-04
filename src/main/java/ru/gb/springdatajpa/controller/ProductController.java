package ru.gb.springdatajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.springdatajpa.model.Product;
import ru.gb.springdatajpa.service.ProductService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductController {

    int pageNumber = 1;
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/app/all GET
    @GetMapping("/all")
    public String getAllProducts(Model model) {
        List<Product> productsPage = new ArrayList<>();
        if (pageNumber*10 < productService.findAll().size()) {
            for (int i = (pageNumber - 1) * 10; i < pageNumber * 10; i++) {
                productsPage.add(productService.findAll().get(i));
            }
        } else if (pageNumber*10 >= productService.findAll().size() && (pageNumber - 1)*10 < productService.findAll().size()) {
            int max = productService.findAll().size() - (pageNumber - 1)*10;
            for (int i = (pageNumber - 1) * 10; i < (pageNumber - 1) * 10 + max; i++) {
                productsPage.add(productService.findAll().get(i));
            }
        } else {
            pageNumber--;
            for (int i = (pageNumber - 1) * 10; i < pageNumber * 10; i++) {
                productsPage.add(productService.findAll().get(i));
            }
        }
        model.addAttribute("products", productsPage);
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

    // http://localhost:8080/app/all/ GET
    @GetMapping("/all/next_page")
    public String getProductsNextPage(Model model) {
        List<Product> productsPage = new ArrayList<>();
        pageNumber++;
        if (pageNumber*10 < productService.findAll().size()) {
            for (int i = (pageNumber - 1) * 10; i < pageNumber * 10; i++) {
                productsPage.add(productService.findAll().get(i));
            }
        } else if (pageNumber*10 >= productService.findAll().size() && (pageNumber - 1)*10 < productService.findAll().size()) {
            int max = productService.findAll().size() - (pageNumber - 1)*10;
            for (int i = (pageNumber - 1) * 10; i < (pageNumber - 1) * 10 + max; i++) {
                productsPage.add(productService.findAll().get(i));
            }
        } else {
            pageNumber--;
            int max = productService.findAll().size() - (pageNumber - 1)*10;
            for (int i = (pageNumber - 1) * 10; i < (pageNumber - 1) * 10 + max; i++) {
                productsPage.add(productService.findAll().get(i));
            }
        }
        model.addAttribute("products", productsPage);
        return "product_list";
    }

    // http://localhost:8080/app/all GET
    @GetMapping("/all/previos_page/")
    public String getProductsPreviosPage(Model model) {
        if (pageNumber > 1) {
            pageNumber--;
        }
        int max = pageNumber*10;
        if (productService.findAll().size() < 10) max = productService.findAll().size();
        List<Product> productsPage = new ArrayList<>();
        for (int i = (pageNumber - 1) * 10; i < max; i++) {
            productsPage.add(productService.findAll().get(i));
        }
        model.addAttribute("products", productsPage);
        return "product_list";
    }
}
