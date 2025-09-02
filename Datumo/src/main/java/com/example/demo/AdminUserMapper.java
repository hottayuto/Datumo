package com.example.demo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper {
    // username で検索
    AdminUser findByUsername(String username);

    // email で検索
    AdminUser findByEmail(String email);
}