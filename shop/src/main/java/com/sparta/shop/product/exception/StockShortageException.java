package com.sparta.shop.product.exception;

public class StockShortageException extends RuntimeException {
    private static final String MESSAGE = "[Product] 남아있는 재고가 부족합니다.";

    public StockShortageException() {
        super(MESSAGE);
    }
}
