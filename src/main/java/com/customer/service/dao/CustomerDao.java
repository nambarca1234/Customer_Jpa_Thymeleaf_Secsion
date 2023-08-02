package com.customer.service.dao;

import com.customer.conn.ConnectJdbc;
import com.customer.model.Customers;
import com.customer.service.CustomersService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service("svdao")
public class CustomerDao implements CustomersService {

    private PreparedStatement preparedStatement;

    @Override
    public ArrayList<Customers> read() {
        ArrayList<Customers> listCustomers = new ArrayList<>();
        try {
            preparedStatement = ConnectJdbc.con().prepareStatement("select * from customers");
            ResultSet resultSet = preparedStatement.executeQuery();

            while ((resultSet.next())) {
                int cusId = resultSet.getInt(1);
                String cusName = resultSet.getString(2);
                String birthDay = resultSet.getString(3);
                String phone = resultSet.getString(4);
                int aId = resultSet.getInt(5);
                Customers customers = new Customers(cusId, cusName, birthDay, phone, aId);
                listCustomers.add(customers);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listCustomers;
    }

    @Override
    public void create(Customers customers) {
            try {
                int id = (int) (Math.random() * ( 1000 - 1 ));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(simpleDateFormat.parse(customers.getBirthDay()).getTime());
                preparedStatement = ConnectJdbc.con().prepareStatement("insert into customers values (?,?,?,?,?)");
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2,customers.getCusName());
                preparedStatement.setDate(3,date);
                preparedStatement.setString(4,customers.getPhone());
                preparedStatement.setInt(5,customers.getaId());
                preparedStatement.execute();
                System.out.println("success");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
    @Override
    public void update(Customers customers) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(simpleDateFormat.parse(customers.getBirthDay()).getTime());
            preparedStatement = ConnectJdbc.con().prepareStatement("update customers set cusname=?, birthday=?, phone=?,aid=? where cusid=?;");
            preparedStatement.setInt(5,customers.getCusId());
            preparedStatement.setString(1, customers.getCusName());
            preparedStatement.setDate(2,date);
            preparedStatement.setString(3,customers.getPhone());
            preparedStatement.setInt(4,customers.getaId());
            preparedStatement.execute();
            System.out.println("success");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //update customers set cusname='Hung', birthday='1995-3-9', phone='0967489391',aid=2 where cusid=8;

        
    }

    @Override
    public void delete(int id) {
        try {
//        DELETE FROM customers WHERE cusid=?
            preparedStatement = ConnectJdbc.con().prepareStatement("delete from customers where cusid=?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            System.out.println("success");
            // bi rang buoc khoa phu thi khong xoa dc. muôn xoa tu dong phai cai dat khoa phu ơ bang phu nhu duoi
            //FOREIGN KEY (cusid) REFERENCES customers(cusid) ON DELETE CASCADE

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
        ArrayList<Customers> list = read();
        for (Customers c: list) {
            if(c.getCusId()==id){
                return c;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Customers> findAll(String name) {
        ArrayList<Customers> list=read();
        ArrayList<Customers> kq=new ArrayList<>();
        for (Customers c : list) {
            try {
                if ((c.getCusName().equalsIgnoreCase(name)) || (c.getCusId() == Integer.parseInt(name))
                        || (c.getPhone().equalsIgnoreCase(name)) || (c.getBirthDay().equalsIgnoreCase(name))) {
                    kq.add(c);
                }
            } catch (NumberFormatException e) {
                break;
            }
        }

        return kq;
    }
}
