package com.sparta.shop.order.application;

import com.sparta.shop.order.application.dto.OrderCreateDto;
import com.sparta.shop.order.application.dto.OrderResponseDto;
import com.sparta.shop.order.domain.Order;
import com.sparta.shop.order.domain.repository.OrderRepository;
import com.sparta.shop.order.exception.OrderNotFoundException;
import com.sparta.shop.product.domain.Product;
import com.sparta.shop.product.domain.repository.ProductRepository;
import com.sparta.shop.product.exception.ProductNotFoundException;
import jakarta.transaction.Transactional;
import java.awt.print.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderResponseDto create(OrderCreateDto createDto) {
        Product product = productRepository.findById(createDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        Order order = createDto.toEntity(product);
        product.buy(createDto.getQuantity());
        orderRepository.save(order);

        return OrderResponseDto.from(order);
    }

    public OrderResponseDto readById(long id) {
        return OrderResponseDto.from(orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new));
    }

    public void readByCursor(Long cursorId, long limit) {
//        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "id"));
    }
}
