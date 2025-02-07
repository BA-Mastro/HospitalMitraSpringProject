package com.binary.myhospital.dto;

public class UserCredentialDto {

    private  String username;
    private String role;

    public UserCredentialDto(String username, String role) {
        this.username = username;
        this.role = role;
    }
    public UserCredentialDto(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserCredentialDto{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
