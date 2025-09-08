package com.example.demo.admin;

import lombok.Data;

@Data
public class Staff {
    private Integer id;
    private String userId;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private String role;
    private String status;
}
