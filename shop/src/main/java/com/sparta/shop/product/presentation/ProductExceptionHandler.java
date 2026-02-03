package com.sparta.shop.product.presentation;

import com.sparta.shop.product.application.dto.ProductExceptionResponseDto;
import com.sparta.shop.product.exception.ProductNotFoundException;
import com.sparta.shop.product.exception.StockShortageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductExceptionResponseDto> handleProductNotFound(ProductNotFoundException e) {
        return new ResponseEntity(ProductExceptionResponseDto.from(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockShortageException.class)
    public ResponseEntity<ProductExceptionResponseDto> handleProductStockShortage(StockShortageException e) {
        return new ResponseEntity(ProductExceptionResponseDto.from(e.getMessage()), HttpStatus.CONFLICT);
    }

}
