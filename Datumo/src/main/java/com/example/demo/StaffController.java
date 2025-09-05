package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;
    
    // 一覧表示
    @GetMapping
    public String showStaffList(Model model) {
        List<Staff> staffList = staffService.getAllStaff();
        model.addAttribute("staffList", staffList);
        return "admin/staff";
    }

    // 新規登録フォーム表示
    @GetMapping("/new")
    public String showNewForm() {
        return "admin/staff-new";
    }

    // 新規登録処理
    @PostMapping("/create")
    public String createStaff(Staff staff) {
        staffService.createStaff(staff);
        return "redirect:/admin/staff";
    }
    // 論理削除
    @PostMapping("/delete")
    public String deleteStaff(@RequestParam("userId") String userId) {
        staffService.deleteStaff(userId);
        return "redirect:/admin/staff";
    }
    
    // 編集画面表示
    @GetMapping("/edit/{userId}")
    public String showEditForm(@PathVariable("userId") String userId, Model model) {
        Staff staff = staffService.getStaffByUserId(userId);
        model.addAttribute("staff", staff);
        return "admin/edit";  // 編集画面のテンプレート
    }

    // 更新処理
    @PostMapping("/edit/{userId}")
    public String updateStaff(@PathVariable("userId") String userId,
                              @ModelAttribute Staff staff) {
        staff.setUserId(userId); // 念のためセット
        staffService.updateStaff(staff);
        return "redirect:/admin/staff";
    }
}