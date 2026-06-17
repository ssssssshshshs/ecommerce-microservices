package com.example.orderservice.client;

import com.example.orderservice.dto.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8081"
)
public interface ProductFeignClient {

    @GetMapping("/products/{id}")
    ProductResponseDTO getProduct(
            @PathVariable Integer id
    );
}
