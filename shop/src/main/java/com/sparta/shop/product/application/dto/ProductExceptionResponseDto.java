package com.sparta.shop.product.application.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductExceptionResponseDto {

    private static final String CATEGORY = "[PRODUCT]";

    private String message;

    public static ProductExceptionResponseDto from(String message) {
        return ProductExceptionResponseDto.builder()
                .message(CATEGORY + message)
                .build();
    }
}
