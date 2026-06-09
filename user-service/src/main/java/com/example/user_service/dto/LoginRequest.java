
package com.example.user_service.dto;

public class LoginRequest {

    private String email;

    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String email,
                        String password) {

        this.email = email;
        this.password = password;
    }

    // getters setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}