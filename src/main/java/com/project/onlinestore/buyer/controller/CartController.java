package com.project.onlinestore.buyer.controller;

import com.project.onlinestore.buyer.domain.Cart;
import com.project.onlinestore.buyer.service.BuyerService;
import com.project.onlinestore.buyer.service.CartServiceImpl;
import com.project.onlinestore.notification.service.NotificationService;
import com.project.onlinestore.utils.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class CartController {
    @Autowired
    private CartServiceImpl service;

    @Autowired
    FileService fileService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    NotificationService notificationService;

    @ModelAttribute("link")
    private String getAdLink(HttpServletRequest request){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        return fileService.readFile(rootDirectory+"//link.txt");
    }

    @ModelAttribute("notification_number")
    private int getNotificationNumber(Principal principal){
        return notificationService.countUnseenNotifications(principal.getName());
    }

    @ModelAttribute("line_number")
    private int getLinesNumber(Principal principal){
        return buyerService.countLinesNumber(principal.getName());
    }

    @ModelAttribute("cart")
    private Cart getCart(Principal principal){
        return buyerService.getBuyerByUsername(principal.getName()).getCart();
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") Long productId, @RequestParam(value = "qty",required = false,defaultValue = "1") Integer qty, Principal principal) {
        String username = principal.getName();
        service.addToCart(productId, username, qty);
        return "redirect:/buyer/cart";
    }

    @GetMapping("/buyer/cart")
    public String showCart() {
        return "/pages/buyer/cart";
    }
}
