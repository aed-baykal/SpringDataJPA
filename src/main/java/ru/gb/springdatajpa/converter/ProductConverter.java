package ru.gb.springdatajpa.converter;

import ru.gb.springdatajpa.dto.ProductDto;
import ru.gb.springdatajpa.model.Product;

public class ProductConverter {

  public static ProductDto productToProductDto(Product product) {
    if (product == null) {
      return null;
    }
    ProductDto productDto = new ProductDto();
    productDto.setId(product.getId());
    productDto.setTitle(product.getTitle());
    if (product.getPrice() != null) productDto.setPrice(product.getPrice());

    return productDto;
  }

  public static Product productDtoToProduct(ProductDto productDto) {
    if (productDto == null) {
      return null;
    }
    Product product = new Product();
    product.setTitle(productDto.getTitle());
    product.setPrice(productDto.getPrice());

    return product;
  }
}
