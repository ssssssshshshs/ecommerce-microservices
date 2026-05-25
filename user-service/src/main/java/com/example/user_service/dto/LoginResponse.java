package com.example.user_service.dto;

public class LoginResponse {

    private String token;

    private String type;

    private String email;

    public LoginResponse() {
    }

    public LoginResponse(String token,
                         String type,
                         String email) {

        this.token = token;
        this.type = type;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}