package com.sparta.shop.product.application.dto;


import com.sparta.shop.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductResponseDto {

    private Long id;

    private String name;

    private int stock;

    private int price;

    private boolean isActive;

    public static ProductResponseDto from(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .price(product.getPrice())
                .isActive(product.isActive())
                .build();
    }
}
