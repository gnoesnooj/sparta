package com.sparta.shop.product.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final String MESSAGE = "해당 ID의 상품은 존재하지 않습니다.";

    public ProductNotFoundException() {
        super(MESSAGE);
    }
}
