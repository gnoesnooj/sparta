package com.sparta.shop.order.application.dto;

import com.sparta.shop.order.domain.Order;
import com.sparta.shop.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreateDto {

    private long productId;

    private int quantity;

    public Order toEntity(Product product) {
        return Order.builder()
                .product(product)
                .quantity(quantity)
                .totalPrice(product.getPrice() * quantity)
                .build();
    }
}
