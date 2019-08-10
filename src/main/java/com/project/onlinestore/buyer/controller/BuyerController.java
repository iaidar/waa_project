package com.project.onlinestore.buyer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

    @GetMapping("/")
    public String home(){
        return "pages/buyer/home";
    }
}
