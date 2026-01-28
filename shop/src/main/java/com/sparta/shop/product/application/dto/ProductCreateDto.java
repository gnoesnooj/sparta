package com.sparta.shop.product.application.dto;

import com.sparta.shop.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ProductCreateDto {

    private String name;

    private int stock;

    private int price;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .stock(stock)
                .price(price)
                .isActive(true)
                .build();
    }
}
