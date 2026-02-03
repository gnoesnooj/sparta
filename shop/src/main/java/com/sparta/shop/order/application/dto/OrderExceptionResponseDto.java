package com.sparta.shop.order.application.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderExceptionResponseDto {

    private static final String CATEGORY = "[ORDER]";

    private String message;

    public static OrderExceptionResponseDto from(String message) {
        return OrderExceptionResponseDto.builder()
                .message(CATEGORY + message)
                .build();
    }
}
