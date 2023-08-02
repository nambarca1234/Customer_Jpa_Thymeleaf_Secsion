package com.customer.controller;

import com.customer.model.Customers;
import com.customer.model.Student;
import com.customer.service.FileService;
import com.customer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private FileService fileService;

    @GetMapping
    public String getHome(Model model) {
        model.addAttribute("list", service.read());
        return "student/student";
    }

    @PostMapping("/save")
    @Transactional
    public String postCreate(@ModelAttribute @Validated Student student, BindingResult bindingResult,
                             @RequestParam("file") MultipartFile file){
        if(bindingResult.hasErrors()){
            return "student/register";
        }
        Student studentNew = service.save(student);
        fileService.save(file,studentNew.getSid());
        return "redirect:/student";
    }

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable int id) {
        Student student = service.findById(id);

        model.addAttribute("student", student);
        return "student/profile";
    }

    @GetMapping("/register")
    public String register(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/register";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Student student = service.findById(id);
        model.addAttribute("student", student);
        return "student/register";
    }


    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        service.delete(id);
        model.addAttribute("list", service.read());
        return "redirect:/student";
    }

    @GetMapping("/find")
    public String finByAid(@RequestParam("aid") int id,  Model model) {
        model.addAttribute("list", service.finByAID(id));
        return "student/home";
    }

}
