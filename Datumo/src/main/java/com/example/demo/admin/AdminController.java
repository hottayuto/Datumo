package com.example.demo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminUserService adminUserService;

    // ===== ログイン画面表示 =====
    @GetMapping("/admin/login")
    public String showLoginForm() {
        return "admin/login"; // templates/admin/login.html
    }

    // ===== ログイン処理 =====
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

    // ===== ダッシュボード =====
    @GetMapping("/admin/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Object adminUser = session.getAttribute("adminUser");
        if (adminUser == null) {
            return "redirect:/admin/login"; // 未ログインなら戻す
        }

        model.addAttribute("adminName", adminUser); // 表示用
        return "admin/dashboard"; // templates/admin/dashboard.html
    }

    // ===== 顧客管理ページ =====
    @GetMapping("/admin/customers")
    public String customers(HttpSession session, Model model) {
        Object adminUser = session.getAttribute("adminUser");
        if (adminUser == null) {
            return "redirect:/admin/login";
        }

        // TODO: customerService から顧客一覧取得予定
        return "admin/customers"; // templates/admin/customers.html
    }

    // ===== ケアプラン管理ページ =====
    @GetMapping("/admin/care-plans")
    public String carePlans(HttpSession session, Model model) {
        Object adminUser = session.getAttribute("adminUser");
        if (adminUser == null) {
            return "redirect:/admin/login";
        }

        // TODO: carePlanService からプラン一覧取得予定
        return "admin/care-plans"; // templates/admin/care-plans.html
    }

    // ===== レポートページ =====
    @GetMapping("/admin/reports")
    public String reports(HttpSession session, Model model) {
        Object adminUser = session.getAttribute("adminUser");
        if (adminUser == null) {
            return "redirect:/admin/login";
        }

        // TODO: reportService からデータ取得予定
        return "admin/reports"; // templates/admin/reports.html
    }

    // ===== ログアウト処理 =====
    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // セッション削除
        return "redirect:/admin/login";
    }
}
