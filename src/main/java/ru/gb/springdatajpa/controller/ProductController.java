package ru.gb.springdatajpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.springdatajpa.converter.ProductConverter;
import ru.gb.springdatajpa.dto.ProductDto;
import ru.gb.springdatajpa.service.ProductService;

import javax.validation.Valid;
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
        List<ProductDto> page = productService.findAll(pageable).stream().map(ProductConverter::productToProductDto).toList();
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
    public String getProductAddFrom(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "product_form";
    }

    // http://localhost:8080/app/add POST
    @PostMapping("/add")
    public String saveProduct(@Valid ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.save(ProductConverter.productDtoToProduct(productDto));
        return "redirect:/all";
    }

    // http://localhost:8080/app/change/3 GET
    @GetMapping("/change/{id}")
    public String getProductChangeFrom(@PathVariable Long id, Model model) {
        model.addAttribute("productDto", ProductConverter.productToProductDto(productService.findById(id)));
        return "product_form";
    }

    // http://localhost:8080/app/delete/3 POST
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/all";
    }

}
