package com.sparta.shop.product.exception;

public class StockShortageException extends RuntimeException {
    private static final String MESSAGE = "해당 상품의 남아있는 재고가 부족합니다.";

    public StockShortageException() {
        super(MESSAGE);
    }
}
