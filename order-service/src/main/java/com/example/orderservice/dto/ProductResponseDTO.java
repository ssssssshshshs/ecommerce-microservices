package com.example.orderservice.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class ProductResponseDTO {

    private Integer id;
    private String name;
    private Double price;
    private Integer stockQuantity;

    // getters setters

}