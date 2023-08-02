package com.customer.controller;

import com.customer.model.Customers;
import com.customer.service.CustomersService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@Controller
@RequestMapping("/customer")

public class CustomerController {
//    @Autowired
//    @Qualifier("svdao")
    CustomersService service;
    Scanner scanner = new Scanner(System.in);
//    @Autowired
//    @Qualifier("svdao")
//    CustomersService dao;
    private final CustomersService dao;

    public CustomerController(@Qualifier("svdao") CustomersService dao) {
        this.dao = dao;
    }


    @GetMapping
    public String getHome(Model model, @CookieValue(value = "username", defaultValue = "") String username, @CookieValue String namtest) {
        System.out.println(username);
        System.out.println(namtest);
        model.addAttribute("list", dao.read());
        return "customer/home";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        Customers customers = new Customers();
        model.addAttribute("customer", customers);
        return "customer/create";
    }

    @PostMapping("/save")
    public String postCreate(@ModelAttribute Customers customer) {
        dao.checkId(customer);
        return "redirect:/customer";
    }


    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Customers customers = dao.findById(id);
        model.addAttribute("customer", customers);
        return "customer/update";
    }


    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        dao.delete(id);
        model.addAttribute("list", dao.read());
        return "redirect:/customer";
    }

    @GetMapping("/find")
    public String search(@RequestParam("cusName") String cusName, Model model) {
        model.addAttribute("list", dao.findAll(cusName));
        return "customer/home";
    }
}
