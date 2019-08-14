package com.project.onlinestore.buyer.controller;

import com.project.onlinestore.buyer.domain.Buyer;
import com.project.onlinestore.buyer.domain.Order;
import com.project.onlinestore.buyer.service.BuyerService;
import com.project.onlinestore.buyer.service.OrderService;
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

    @Autowired
    OrderService orderService;


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
        return "redirect:/products/list";
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

    @GetMapping("/orders/{id}")
    public String orderDetails(@PathVariable Long id,Model model){
        model.addAttribute("order",orderService.getOrderById(id));
        return "pages/orders/details";
    }

    @GetMapping("/myorders")
    public String buyerOrders(Model model, Principal principal) {
        Buyer buyer = buyerService.getBuyerByUsername(principal.getName());
        model.addAttribute("orders", orderService.findAllByBuyer(buyer));
        return "pages/buyer/myorders";
    }

    @PostMapping("/orders/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        order.setStatus(4);
        orderService.save(order);
        return "redirect:/buyer/myorders";
    }

}
