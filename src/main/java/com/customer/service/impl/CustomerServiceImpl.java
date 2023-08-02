package com.customer.service.impl;

import com.customer.file.MyFile;
import com.customer.model.Customers;
import com.customer.service.CustomersService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("svimp")

public class CustomerServiceImpl implements CustomersService {

    ArrayList<Customers> listCustomer = new ArrayList<>();

    public void checkList(){
        if(listCustomer.isEmpty()) {
            listCustomer.add(new Customers(1, "nam", "8/3", "922033", 1));
            listCustomer.add(new Customers(2, "nam", "8/3", "922033", 1));
            listCustomer.add(new Customers(3, "nam", "8/3", "922033", 1));
            listCustomer.add(new Customers(4, "nam", "8/3", "922033", 1));
            listCustomer.add(new Customers(5, "nam", "8/3", "922033", 1));
            listCustomer.add(new Customers(6, "nam", "8/3", "922033", 1));
        }
    }


    @Override
    public ArrayList<Customers> read() {
        checkList();
        return listCustomer;
    }

    @Override
    public void create(Customers customers) {
        checkList();
        customers.setCusId(this.listCustomer.size()+1);
        this.listCustomer.add(customers);
    }

    @Override
    public void update(Customers customers) {
        for (Customers c: listCustomer) {
            if (c.getCusId()==customers.getCusId()){
                c.setCusName(customers.getCusName());
                c.setBirthDay(customers.getBirthDay());
                c.setPhone(customers.getPhone());
                c.setaId(customers.getaId());
            }
        }
    }

    @Override
    public void delete(int id) {
        for (Customers c: listCustomer) {
            if (c.getCusId()==id){
                listCustomer.remove(c);
                reloadID(listCustomer);
                break;
            }
        }
    }

    @Override
    public void checkId(Customers customers) {
        if (customers.getCusId()==0){
            create(customers);
        } else {
            update(customers);
        }
    }

    @Override
    public Customers findById(int id) {
        checkList();
        for (Customers c: listCustomer) {
            if(c.getCusId()==id){
                return c;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Customers> findAll(String name) {
        return null;
    }

    public void reloadID(ArrayList<Customers> listCustomer){
        for (int i = 0; i <listCustomer.size() ; i++) {
            listCustomer.get(i).setCusId(i+1);
        }
    }

}
