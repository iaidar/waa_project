package com.project.onlinestore.admin.controller;

import com.project.onlinestore.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("pendingSellers",adminService.getPendingSellers());
        return "pages/admin/home";
    }

    @PostMapping("/accept/{id}")
    public String acceptSeller(@PathVariable Long id,Model model){
        adminService.acceptSeller(id);
        return "redirect:/admin/";
    }

    @PostMapping("/reject/{id}")
    public String rejectSeller(@PathVariable Long id,Model model){
        adminService.rejectSeller(id);
        return "redirect:/admin/";
    }


}
