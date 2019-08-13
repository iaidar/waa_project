package com.project.onlinestore.buyer.controller;

import com.project.onlinestore.buyer.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartServiceImpl service;

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long productId, Principal principal) {
        String username = principal.getName();
        service.addToCart(productId, username, 1);
        return "redirect:/products/list";
    }
}
