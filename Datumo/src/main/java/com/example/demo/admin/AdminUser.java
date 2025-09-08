package com.example.demo.admin;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AdminUser {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}