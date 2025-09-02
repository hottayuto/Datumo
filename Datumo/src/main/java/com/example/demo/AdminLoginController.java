package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/admin/login")
    public String showLoginForm() {
        return "admin/login"; // templates/admin/login.html
    }

    @PostMapping("/admin/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        boolean success = adminUserService.login(username, password);
        if (success) {
            session.setAttribute("adminUser", username);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "ユーザー名またはパスワードが違います");
            return "admin/login";
        }
    }
    
    @GetMapping("/admin/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Object adminUser = session.getAttribute("adminUser");
        if (adminUser == null) {
            return "redirect:/admin/login"; // ログインしていなければ戻す
        }

        model.addAttribute("adminName", adminUser); // 表示用
        return "admin/dashboard"; // templates/admin/dashboard.html
    }

}
