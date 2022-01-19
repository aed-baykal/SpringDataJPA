package ru.gb.springdatajpa.converter;

import ru.gb.springdatajpa.dto.CartItem;
import ru.gb.springdatajpa.dto.ProductDto;
import ru.gb.springdatajpa.model.Product;

import java.util.Optional;

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

  public static CartItem productToCartItem(Optional<Product> optionalProduct) {
    Product product = optionalProduct.orElse(null);
    CartItem cartItem = new CartItem();
    if (product != null) {
      cartItem.setProductId(product.getId());
      cartItem.setTitle(product.getTitle());
      cartItem.setPricePerOne(product.getPrice());
      cartItem.setPrice(product.getPrice());
    }
    return cartItem;
  }

  public static Product productDtoToProduct(ProductDto productDto) {
    if (productDto == null) {
      return null;
    }
    Product product = new Product();
    product.setTitle(productDto.getTitle());
    product.setPrice(productDto.getPrice());
    product.setId(productDto.getId());

    return product;
  }
}
