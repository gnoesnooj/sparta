package com.sparta.shop.product.presentation;

import com.sparta.shop.product.application.ProductService;
import com.sparta.shop.product.application.dto.ProductCreateDto;
import com.sparta.shop.product.application.dto.ProductResponseDto;
import com.sparta.shop.product.application.dto.ProductUpdateDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductCreateDto createDto) {
        return new ResponseEntity<>(productService.create(createDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> readById(@PathVariable("id") long id) {
        return new ResponseEntity<>(productService.readById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> readAll() {
        return new ResponseEntity<>(productService.readActiveProducts(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ProductResponseDto> update(@RequestBody ProductUpdateDto updateDto) {
        return new ResponseEntity<>(productService.update(updateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable("id") long id) {
        return new ResponseEntity<>(productService.deleteById(id), HttpStatus.NO_CONTENT);
    }
}
