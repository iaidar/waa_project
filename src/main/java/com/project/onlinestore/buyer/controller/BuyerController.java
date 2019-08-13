package com.project.onlinestore.buyer.controller;

import com.project.onlinestore.buyer.domain.Buyer;
import com.project.onlinestore.buyer.service.BuyerService;
import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.seller.domain.Seller;
import com.project.onlinestore.utils.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    FileService fileService;

    @Autowired
    BuyerService buyerService;


    @ModelAttribute("link")
    private String getAdLink(HttpServletRequest request){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        return fileService.readFile(rootDirectory+"//link.txt");
    }

    @GetMapping("/")
    public String home(){
        return "pages/buyer/home";
    }

    @PostMapping("/follow/{id}")
    public @ResponseBody void follow(@PathVariable Long id, Principal principal){
        buyerService.follow(principal.getName(),id);
    }

    @PostMapping("/unfollow/{id}")
    public @ResponseBody void unfollow(@PathVariable Long id, Principal principal){
        buyerService.unfollow(principal.getName(),id);
    }
}
