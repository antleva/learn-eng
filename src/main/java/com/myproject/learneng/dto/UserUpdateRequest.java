package com.myproject.learneng.dto;

public class UserUpdateRequest {
    private String username;
    private String password;

    public UserUpdateRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserUpdateRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
