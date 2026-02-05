package com.sparta.shop;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.shop.order.application.OrderService;
import com.sparta.shop.order.application.dto.OrderCreateDto;
import com.sparta.shop.product.domain.Product;
import com.sparta.shop.product.domain.repository.ProductRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopApplicationTests {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductRepository productRepository;

    @Test
    void 비관적락_재고차감_동시성_테스트() throws InterruptedException {
        // given
        Product product = Product.builder()
                .name("테스트 상품")
                .price(1000)
                .stock(100)
                .isActive(true)
                .build();

        productRepository.save(product);

        int user = 100;
        int quantity = 1;

        ExecutorService executorService = Executors.newFixedThreadPool(user);
        CountDownLatch latch = new CountDownLatch(user);
        // when
        for (int i = 0; i < user; i++) {
            executorService.submit(() -> {
                try {
                    orderService.create(
                            new OrderCreateDto(product.getId(), quantity)
                    );
                    System.out.println("구매 성공");
                } catch (Exception e) {
                    System.out.println("재고 부족");
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // then
        Product result = productRepository.findById(product.getId()).orElseThrow();

        System.out.println("남은 재고 = " + result.getStock());

        assertEquals(0, result.getStock());
    }
}
