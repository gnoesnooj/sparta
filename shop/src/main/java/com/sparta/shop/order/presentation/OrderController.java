package com.sparta.shop.order.presentation;

import com.sparta.shop.order.application.OrderService;
import com.sparta.shop.order.application.dto.OrderCreateDto;
import com.sparta.shop.order.application.dto.OrderResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderCreateDto createDto) {
        return new ResponseEntity<>(orderService.create(createDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> readById(@PathVariable("id") long id) {
        return new ResponseEntity<>(orderService.readById(id), HttpStatus.OK);
    }

//    @GetMapping
//    public List<OrderResponseDto> readByCursor(@RequestParam Long cursorId, @RequestParam(defaultValue = "5") long limit){
//        orderService.readByCursor(cursorId, limit);
//    }
}
