package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createStaff(Staff staff) {
        // パスワードをハッシュ化して保存
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staffMapper.insertStaff(staff);
    }
    
    public List<Staff> getAllStaff() {
        return staffMapper.findAll();
    }
    
    public void deleteStaff(String userId) {
        staffMapper.deleteStaff(userId);
    }
    
    public Staff getStaffByUserId(String userId) {
        return staffMapper.findByUserId(userId);
    }

    public void updateStaff(Staff staff) {
        staffMapper.updateStaff(staff);
    }
}
