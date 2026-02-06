package com.sparta.shop.product.domain;

import com.sparta.shop.product.exception.StockShortageException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int stock;

    private int price;

    private boolean isActive;

    public void activeOff() {
        this.isActive = false;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public void restock(int quantity) {
        this.stock += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity > stock) {
            throw new StockShortageException();
        }
        this.stock = this.stock - quantity;
    }
}
