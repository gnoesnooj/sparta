package com.sparta.shop.order.application.dto;

import com.sparta.shop.order.domain.Order;
import com.sparta.shop.product.application.dto.ProductResponseDto;
import com.sparta.shop.product.domain.Product;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDto {

    private Long id;

    private int amount;

    private int totalPrice;

    private String productName;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public static OrderResponseDto from(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .amount(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .productName(order.getProduct().getName())
                .createdAt(order.getCreatedAt())
                .modifiedAt(order.getModifiedAt())
                .build();
    }
}
