package com.project.onlinestore.product.controller;

import com.project.onlinestore.product.domain.Product;
import com.project.onlinestore.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
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
    public String addProduct(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "redirect:/products/add";
        }
        productService.save(product);
        redirectAttributes.addFlashAttribute("created", "Product was created successfully");
        return "redirect:/products/list";
    }


}
