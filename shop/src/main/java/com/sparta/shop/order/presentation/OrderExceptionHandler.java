package com.sparta.shop.order.presentation;

import com.sparta.shop.order.application.dto.OrderExceptionResponseDto;
import com.sparta.shop.order.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<OrderExceptionResponseDto> handleOrderNotFound(OrderNotFoundException e) {
        return new ResponseEntity(OrderExceptionResponseDto.from(e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
