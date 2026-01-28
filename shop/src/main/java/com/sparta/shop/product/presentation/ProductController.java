package com.sparta.shop.product.presentation;

import com.sparta.shop.product.application.ProductService;
import com.sparta.shop.product.application.dto.ProductCreateDto;
import com.sparta.shop.product.application.dto.ProductResponseDto;
import com.sparta.shop.product.application.dto.ProductUpdateDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductCreateDto createDto) {
        return productService.create(createDto);
    }

    @GetMapping("/{id}")
    public ProductResponseDto readById(@PathVariable("id") long id) {
        return productService.readById(id);
    }

    @GetMapping("/all")
    public List<ProductResponseDto> readAll() {
        return productService.readActiveProducts();
    }

    @PatchMapping
    public ProductResponseDto update(@RequestBody ProductUpdateDto updateDto) {
        return productService.update(updateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        productService.deleteById(id);
    }
}
