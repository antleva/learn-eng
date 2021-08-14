package com.myproject.learneng.dto;

import com.myproject.learneng.domain.User;

public class AuthenticatedRequest {
    private String username;
    private String password;

    public AuthenticatedRequest() {}

    public AuthenticatedRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

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

    public User toUser(){
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        return user;
    }


}