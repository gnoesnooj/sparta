package com.sparta.shop.order.application;

import com.sparta.shop.order.application.dto.OrderCreateDto;
import com.sparta.shop.order.application.dto.OrderResponseDto;
import com.sparta.shop.order.domain.Order;
import com.sparta.shop.order.domain.repository.OrderRepository;
import com.sparta.shop.order.exception.OrderNotFoundException;
import com.sparta.shop.product.domain.Product;
import com.sparta.shop.product.domain.repository.ProductRepository;
import com.sparta.shop.product.exception.ProductNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponseDto create(OrderCreateDto createDto) {
        Product product = productRepository.findByIdForLock(createDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        product.buy(createDto.getQuantity());

        Order order = createDto.toEntity(product);
        orderRepository.save(order);

        return OrderResponseDto.from(order);
    }

    public OrderResponseDto readById(long id) {
        return OrderResponseDto.from(orderRepository.findOneById(id)
                .orElseThrow(OrderNotFoundException::new));
    }

    public List<OrderResponseDto> readAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<OrderResponseDto> dtos = orderRepository.findAll(pageable)
                .stream()
                .map(OrderResponseDto::from)
                .toList();

        return dtos;
    }
}
