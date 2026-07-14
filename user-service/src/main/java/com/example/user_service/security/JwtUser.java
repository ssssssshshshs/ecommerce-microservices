package com.example.user_service.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtUser {

    private Integer id;

    private String email;

    private String role;
}