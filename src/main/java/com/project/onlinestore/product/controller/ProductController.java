package com.project.onlinestore.product.controller;

import com.project.onlinestore.buyer.domain.Buyer;
import com.project.onlinestore.buyer.service.BuyerService;
import com.project.onlinestore.notification.service.NotificationService;
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
    private BuyerService buyerService;
    private FileService fileService;
    private NotificationService notificationService;

    @ModelAttribute("link")
    private String getAdLink(HttpServletRequest request){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        return fileService.readFile(rootDirectory+"//link.txt");
    }

    @ModelAttribute("notification_number")
    private int getNotificationNumber(Principal principal){
        return notificationService.countUnseenNotifications(principal.getName());
    }

    @Autowired
    public ProductController(ProductService productService, FileService fileService,BuyerService buyerService, NotificationService notificationService) {
        this.productService = productService;
        this.fileService = fileService;
        this.buyerService = buyerService;
        this.notificationService = notificationService;
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
