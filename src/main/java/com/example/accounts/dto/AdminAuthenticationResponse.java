package com.example.accounts.dto;

import lombok.Data;

@Data
public class AdminAuthenticationResponse {
    private String jwt;
    private String username;
    private String role;
}
