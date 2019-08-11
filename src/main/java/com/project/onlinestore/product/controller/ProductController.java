package com.project.onlinestore.product.controller;

import com.project.onlinestore.product.domain.Product;
import com.project.onlinestore.product.service.ProductService;
import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.security.service.UserService;
import com.project.onlinestore.seller.domain.Seller;
import com.project.onlinestore.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ProductController {

    private ProductService productService;
    private SellerService sellerService;
    private UserService userService;

    @Autowired
    public ProductController(ProductService productService, SellerService sellerService, UserService userService) {
        this.productService = productService;
        this.sellerService = sellerService;
        this.userService = userService;
    }

//    anyone should have access to this link
    @GetMapping("/products/list")
    public String findAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "pages/products/list";
    }

//    anyone should have access to this link
    @GetMapping("/products/details/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "pages/products/details";
    }

    @GetMapping("/seller/products/add")
    public String showAddProductForm(@ModelAttribute Product product) {
        return "pages/products/add-form";
    }


    @PostMapping("/seller/products/add")
    public String addProduct(@Valid Product product, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "redirect:/products/add";
        }
        User user = userService.findUserByUserName(principal.getName());
        product.setSeller(sellerService.getSellerByUser(user));
        productService.save(product);
        redirectAttributes.addFlashAttribute("created", "Product was created successfully");
        return "redirect:/products/list";
    }


}
