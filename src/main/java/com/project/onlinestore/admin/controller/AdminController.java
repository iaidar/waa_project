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

    @GetMapping("/pendingUsers")
    public String getPendingUsers(Model model){
        model.addAttribute("pendingSellers",adminService.getPendingSellers());
        return "pages/admin/pendingUsers";
    }

    @PostMapping("/accept/{id}")
    public String acceptSeller(@PathVariable Long id,RedirectAttributes redirectAttributes){
        adminService.acceptSeller(id);
        redirectAttributes.addFlashAttribute("successMessage", "Seller has been accepted!");
        return "redirect:/admin/pendingUsers";
    }

    @PostMapping("/reject/{id}")
    public String rejectSeller(@PathVariable Long id,RedirectAttributes redirectAttributes){
        adminService.rejectSeller(id);
        redirectAttributes.addFlashAttribute("successMessage", "Seller has been rejected!");
        return "redirect:/admin/pendingUsers";
    }

    @GetMapping("/adChange")
    public String home(@ModelAttribute Ad ad, Model model){
        return "pages/admin/adChange";
    }

    @PostMapping("/adChange")
    public String changeAd(@Valid @ModelAttribute("ad") Ad ad, BindingResult result, HttpServletRequest request,
                           RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "pages/admin/adChange";
        }
        MultipartFile productImage = ad.getImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        if (productImage != null && !productImage.isEmpty()){
            redirectAttributes.addFlashAttribute("successMessage", "Ad has been changed successfully!");
            adminService.changeAd(ad,rootDirectory);
        }
        return "redirect:/admin/adChange";
    }


}
