package com.customer.controller;

import com.customer.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.session.StandardSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class UserController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login/login";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute User user, HttpServletResponse response,
                         HttpServletRequest request) {
//        Cookie cookie = new Cookie("username", user.getUsername());
//        cookie.setMaxAge(100);  ///thoi gian song 100s
//        Cookie cookie2 = new Cookie("namtest", user.getUsername());
//        cookie2.setMaxAge(10);
//        response.addCookie(cookie);
//        response.addCookie(cookie2);

        HttpSession session = request.getSession();
        session.setAttribute("username-ss", user.getUsername());
        session.setMaxInactiveInterval(30);

        return "redirect:/employee";
    }
    @GetMapping("/logout")
    public String logout(@ModelAttribute User user, HttpServletResponse response,
                         HttpServletRequest request) {
//
//        Cookie[] arrCookie=request.getCookies();
//        for (Cookie c:arrCookie) {
//            c.setMaxAge(0);
//        }

        HttpSession session =request.getSession();
        session.removeAttribute("username-ss");


        return "redirect:/login";
    }
}
