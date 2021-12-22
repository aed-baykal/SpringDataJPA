package ru.gb.springdatajpa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.springdatajpa.model.Product;

@Data
@NoArgsConstructor
public class ProductDto {

    private String title;
    private String price;

    public ProductDto(Product product) {
        title = product.getTitle();
        price = product.getPrice().toString();
    }

}