package com.example.demo.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashExample {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashed = encoder.encode("Admin1234");  // 平文パスワード
        System.out.println(hashed);
    }
}
