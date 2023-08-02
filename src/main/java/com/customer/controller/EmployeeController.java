package com.customer.controller;

import com.customer.model.Employee;
import com.customer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @GetMapping
    private String getAll(Model model, @SessionAttribute("username-ss") String username){
        System.out.println(username);
        model.addAttribute("list", service.getAll());
        return "employee/employee";
    }

    @GetMapping("/register")
    private String save(Model model){
        model.addAttribute("employee", new Employee());
        return "employee/register";
    }
    @PostMapping("/save")
    private String create(@ModelAttribute Employee employee){
        service.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/update/{id}")
    private String update(Model model, @PathVariable int id){
        Employee employee = service.finById(id).get();
        model.addAttribute("employee",employee);
        return "employee/register";
    }
    @GetMapping("/delete/{id}")
    private String delete(@PathVariable int id){
        service.delete(id);
        return "redirect:/employee";
    }
}
