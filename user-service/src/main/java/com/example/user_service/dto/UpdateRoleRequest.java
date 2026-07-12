package com.example.user_service.dto;

import com.example.user_service.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoleRequest {

    private Role role;

}