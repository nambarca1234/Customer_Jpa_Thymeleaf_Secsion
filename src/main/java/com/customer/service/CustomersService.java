package com.customer.service;

import com.customer.model.Customers;

import java.util.ArrayList;

public interface CustomersService {
    ArrayList<Customers> read();
    void create(Customers customers);
    void update(Customers customers);
    void delete(int id);
    void checkId(Customers customers);
    Customers findById(int id);
    ArrayList<Customers> findAll(String name);
}
