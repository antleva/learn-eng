package com.myproject.learneng.dto;

public class RefreshTokenRequest {
    private String token;
    private String username;

    public RefreshTokenRequest() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
