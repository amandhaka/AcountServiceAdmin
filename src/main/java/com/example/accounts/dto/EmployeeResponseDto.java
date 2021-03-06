package com.example.accounts.dto;

import lombok.Data;

@Data
public class EmployeeResponseDto {
    private Long id;
    private String username;
    private String password;
    private String name;
}
