package com.project.onlinestore.buyer.controller;

import com.project.onlinestore.buyer.domain.Buyer;
import com.project.onlinestore.buyer.service.BuyerService;
import com.project.onlinestore.notification.service.NotificationService;
import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.seller.domain.Seller;
import com.project.onlinestore.utils.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @ModelAttribute("points")
    private Long getPoints(Principal principal){
        return buyerService.getBuyerByUsername(principal.getName()).getPoints();
    }

    @ModelAttribute("line_number")
    private int getLinesNumber(Principal principal){
        return buyerService.countLinesNumber(principal.getName());
    }



    @GetMapping("/")
    public String home(){
        return "pages/buyer/home";
    }

    @GetMapping("/follow/{id}")
    public @ResponseBody void follow(@PathVariable Long id, Principal principal){
        buyerService.follow(principal.getName(),id);
    }

    @GetMapping("/unfollow/{id}")
    public @ResponseBody void unfollow(@PathVariable Long id, Principal principal){
        buyerService.unfollow(principal.getName(),id);
    }

    @GetMapping("/notifications")
    public String getNotifications(Model model,Principal principal){
        model.addAttribute("notifications",notificationService.getUnseenNotifications(principal.getName()));
        notificationService.makeAllNotificationsSeen();
        return "pages/buyer/notifications";
    }


}
