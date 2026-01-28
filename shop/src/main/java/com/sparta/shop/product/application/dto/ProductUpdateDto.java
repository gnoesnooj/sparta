package com.sparta.shop.product.application.dto;

import lombok.Getter;

@Getter
public class ProductUpdateDto {

    private long productId;

    private String name;

    private Integer stock;

    private Integer price;

    private Boolean isActive;
}
