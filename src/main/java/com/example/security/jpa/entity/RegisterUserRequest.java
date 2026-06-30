package com.example.security.jpa.entity;


import com.example.security.enums.ROLES;

public class RegisterUserRequest {

    private String username;
    private String password;
    private ROLES role;

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

    public ROLES getRole() {
        return role;
    }

    public void setRole(ROLES role) {
        this.role = role;
    }
}
