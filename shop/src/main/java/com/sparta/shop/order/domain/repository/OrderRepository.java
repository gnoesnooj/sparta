package com.sparta.shop.order.domain.repository;

import com.sparta.shop.order.domain.Order;
import com.sparta.shop.order.exception.OrderNotFoundException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join fetch o.product where o.id = :id")
    Optional<Order> findOneById(@Param("id") Long id);

    @EntityGraph(attributePaths = "product")
    Page<Order> findAll(Pageable pageable);

}
