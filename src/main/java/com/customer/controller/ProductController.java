package com.customer.controller;

import com.customer.model.Product;
import com.customer.model.ProductDto;
import com.customer.model.Student;
import com.customer.repository.ProductRepository;
import com.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.util.ArrayList;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    ProductRepository repository;

    @GetMapping("/test")
    @Transactional
    //ghi tam, thanh cong het thi moi ghi vao database
    public String homeTest(Model model){
        repository.insertProduct(100,1,"test1");
        repository.insertProduct(101,1,"test2");

        //trung id no se rollback lai 2 cau insert truoc do
        repository.insertProduct(100,1,"test3");
        repository.insertProduct(102,1,"test4");
        model.addAttribute("list", service.getInfor());
        return "product/product";
    }

    @GetMapping
    public String getHome(Model model) {
//        model.addAttribute("list", service.findAll());
        model.addAttribute("list", service.getInfor());
        return "product/product";
    }

    @PostMapping("/save")
    public String postCreate(@ModelAttribute Product product){
        service.save(product);
        return "redirect:/product";
    }

    @GetMapping("/create")
    public String register(Model model){
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Product product = service.finById(id);
        model.addAttribute("product", product);
        return "product/create";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "redirect:/product";
    }

    @GetMapping("/find")
    public String findByName(@RequestParam("pname") String pname, Model model) {
        model.addAttribute("list", service.finByName(pname));
        return "product/product";
    }



}
