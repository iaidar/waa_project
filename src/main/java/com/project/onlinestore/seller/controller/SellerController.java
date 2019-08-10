package com.project.onlinestore.seller.controller;

import com.project.onlinestore.product.service.ProductService;
import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.security.service.UserService;
import com.project.onlinestore.seller.domain.Seller;
import com.project.onlinestore.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private ProductService productService;
    private SellerService sellerService;
    private UserService userService;

    @Autowired
    public SellerController(ProductService productService, SellerService sellerServicel, UserService userService) {
        this.productService = productService;
        this.sellerService = sellerServicel;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(){
        return "pages/seller/home";
    }

    @GetMapping("/myproducts")
    public String listSellerProducts(Model model, Principal principal) {
        User user = userService.findUserByUserName(principal.getName());
        Seller seller = sellerService.getSellerByUser(user);
        System.out.println(seller);
        model.addAttribute("products", productService.findProductsBySeller(seller));
        return "pages/seller/products/list";
    }
}
