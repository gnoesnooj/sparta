package com.sparta.shop.product.domain.repository;

import com.sparta.shop.product.domain.Product;
import com.sparta.shop.product.exception.ProductNotFoundException;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    default Product findProductById(Long id) {
        return findById(id).orElseThrow(ProductNotFoundException::new);
    }

    List<Product> findAll();

    List<Product> findByIsActive(boolean isActive);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = :id")
    Optional<Product> findByIdForLock(@Param("id") Long id);

    default Product findProductWithLock(Long id){
        return findByIdForLock(id).orElseThrow(ProductNotFoundException::new);
    }
}