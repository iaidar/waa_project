package com.project.onlinestore.admin.controller;

import com.project.onlinestore.admin.domain.Ad;
import com.project.onlinestore.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/")
    public String home(@ModelAttribute Ad ad, Model model){
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

    @PostMapping("/adChange")
    public String changeAd(@Valid @ModelAttribute("ad") Ad ad, BindingResult result, HttpServletRequest request,
                           RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "pages/admin/home";
        }
        MultipartFile productImage = ad.getImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        if (productImage != null && !productImage.isEmpty()){
            redirectAttributes.addFlashAttribute("successMessage", "Ad has been changed successfully!");
            adminService.changeAd(ad,rootDirectory);}
        return "redirect:/admin/";
    }


}
