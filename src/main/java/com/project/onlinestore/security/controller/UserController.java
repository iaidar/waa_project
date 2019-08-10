package com.project.onlinestore.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

        @RequestMapping("/default")
        public String defaultAfterLogin(HttpServletRequest request) {
            if (request.isUserInRole("ADMIN")) {
                return "redirect:/admin/";
            } else if (request.isUserInRole("BUYER")) {
                return "redirect:/buyer/";
            } else if (request.isUserInRole("SELLER")) {
                return "redirect:/seller/";
            }
            return "pages/errors/404";
        }
}
