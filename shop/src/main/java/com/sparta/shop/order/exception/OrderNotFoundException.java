package com.sparta.shop.order.exception;

public class OrderNotFoundException extends RuntimeException {
    private static final String MESSAGE = "[Order] 해당 ID의 주문은 존재하지 않습니다.";

    public OrderNotFoundException() {
        super(MESSAGE);
    }
}