package com.sparta.shop.order.application.dto;

import com.sparta.shop.order.domain.Order;
import com.sparta.shop.product.application.dto.ProductResponseDto;
import com.sparta.shop.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDto {

    private Long id;

    private int amount;

    private int totalPrice;

    private String productName;

    public static OrderResponseDto from(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .amount(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .productName(order.getProduct().getName())
                .build();
    }
}
