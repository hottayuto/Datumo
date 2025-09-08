package com.example.demo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean login(String username, String rawPassword) {
        AdminUser admin = adminUserMapper.findByUsername(username);
        if (admin != null) {
            return passwordEncoder.matches(rawPassword, admin.getPassword());
        }
        return false;
    }
}
