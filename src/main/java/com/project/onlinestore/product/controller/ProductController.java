package com.project.onlinestore.product.controller;

import com.project.onlinestore.buyer.domain.Buyer;
import com.project.onlinestore.buyer.service.BuyerService;
import com.project.onlinestore.product.domain.Product;
import com.project.onlinestore.product.service.ProductService;
import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.security.service.UserService;
import com.project.onlinestore.seller.domain.Seller;
import com.project.onlinestore.seller.service.SellerService;
import com.project.onlinestore.utils.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductService productService;
    private SellerService sellerService;
    private BuyerService buyerService;
    private UserService userService;
    private FileService fileService;

    @ModelAttribute("link")
    private String getAdLink(HttpServletRequest request){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        return fileService.readFile(rootDirectory+"//link.txt");
    }

    @Autowired
    public ProductController(ProductService productService, SellerService sellerService, UserService userService, FileService fileService,BuyerService buyerService) {
        this.productService = productService;
        this.sellerService = sellerService;
        this.userService = userService;
        this.fileService = fileService;
        this.buyerService = buyerService;
    }

//    anyone should have access to this link
    @GetMapping("/products/list")
    public String findAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "pages/products/list";
    }

//    anyone should have access to this link
    @GetMapping("/products/details/{id}")
    public String productDetails(@PathVariable Long id, Model model, Principal principal) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("follow",buyerService.isFollowed(buyerService.getBuyerByUsername(principal.getName()),product.getSeller()));
        return "pages/products/details";
    }

}
