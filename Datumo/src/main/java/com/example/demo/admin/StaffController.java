package com.example.demo.admin;

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
    public String createStaff(@ModelAttribute Staff staff, Model model) {
    	
        // パスワードのバリデーション
        String password = staff.getPassword();
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

        if (!password.matches(passwordRegex)) {
            model.addAttribute("errorPassword", 
                "パスワードの要件を満たしていません。");
            return "admin/staff-new";
        }

        // メール重複チェック
        if (staffService.existsByEmail(staff.getEmail())) {
            model.addAttribute("errorEmail", "このメールアドレスは既に使用されています。");
            return "admin/staff-new"; // 新規登録画面に戻す
        }

        // 電話番号重複チェック
        if (staffService.existsByPhone(staff.getPhone())) {
            model.addAttribute("errorPhone", "この電話番号は既に使用されています。");
            return "admin/staff-new";
        }

        // 問題なければ登録処理
        staffService.createStaff(staff);
        return "redirect:/admin/staff";
    }
    // 論理削除
    @PostMapping("/delete")
    public String deleteStaff(@RequestParam("userId") String userId) {
        staffService.deleteStaff(userId);
        return "redirect:/admin/staff";
    }

    // 更新処理 POST
    @PostMapping("/update/{userId}")
    public String updateStaff(@PathVariable String userId, Staff staff) {
        staff.setUserId(userId); // URL の userId をセット
        staffService.updateStaff(staff); // Staff 全体を渡す
        return "redirect:/admin/staff"; // 一覧へ戻る
    }

}