package com.spring_cloud.eureka.client.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductTestController {

    @Value("${server.port}")
    private String PORT;

    @GetMapping("/product")
    public String getProduct(){
        return "Product response" + PORT;
    }
}
