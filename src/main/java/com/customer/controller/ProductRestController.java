package com.customer.controller;

import com.customer.model.ProductAPI;
import com.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    @Autowired
    private ProductService service;
    @GetMapping("/getallproduct")
    public ResponseEntity<?> getAllProduct(){
        return service.getListProductApiModel();
    }

    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(){
        return ResponseEntity.ok(service.getOne());
    }

    @PostMapping("/save")
    private ResponseEntity<?> save(@RequestBody ProductAPI productAPI){
        System.out.println(productAPI);
        return service.postProductApi(productAPI);
    }
}
