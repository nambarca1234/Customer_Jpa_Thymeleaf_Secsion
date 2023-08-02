package com.customer.service;


import com.customer.model.Product;
import com.customer.model.ProductDto;

import java.util.List;
public interface ProductService {
    List<ProductDto> getProductDto ();
    void save(Product product);
    List<Product> findAll();
    void delete(int id);
    Product finById(int id);
    List<ProductDto> finByName(String name);
    List<Product> findByCategory(int cid);

    List<ProductDto> getInfor();

}
