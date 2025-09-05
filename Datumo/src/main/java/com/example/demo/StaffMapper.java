package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffMapper {
	// 新規登録
    void insertStaff(Staff staff);
    // スタッフ一覧
    List<Staff> findAll();
    // 論理削除
    void deleteStaff(String userId);
    
    Staff findByUserId(String userId);

    void updateStaff(Staff staff);
}
