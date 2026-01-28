package com.sparta.shop.product.application;

import com.sparta.shop.product.application.dto.ProductCreateDto;
import com.sparta.shop.product.application.dto.ProductResponseDto;
import com.sparta.shop.product.application.dto.ProductUpdateDto;
import com.sparta.shop.product.domain.Product;
import com.sparta.shop.product.domain.repository.ProductRepository;
import com.sparta.shop.product.exception.ProductNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto create(ProductCreateDto dto) {
        Product product = dto.toEntity();
        productRepository.save(product);
        return ProductResponseDto.from(product);
    }

    public ProductResponseDto update(ProductUpdateDto dto) {
        Product product = findById(dto.getProductId());

        if (dto.getPrice() != null) {
            product.changePrice(dto.getPrice());
        }

        if (dto.getName() != null) {
            product.changeName(dto.getName());
        }

        if (dto.getStock() != null) {
            product.restock(dto.getStock());
        }

        productRepository.save(product);
        return ProductResponseDto.from(product);
    }

    public ProductResponseDto readById(long id) {
        return ProductResponseDto.from(findById(id));
    }

    public List<ProductResponseDto> readActiveProducts() {
        List<ProductResponseDto> dtos = productRepository.findByIsActive(true)
                .stream()
                .map(ProductResponseDto::from)
                .toList();

        return dtos;
    }

    public void deleteById(long id) {
        Product product = findById(id);
        product.activeOff();
        productRepository.save(product);
    }

    private Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }
}
