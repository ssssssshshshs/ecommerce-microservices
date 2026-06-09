package com.example.orderservice.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {

    private Long id;
    private String name;
    private String email;
}