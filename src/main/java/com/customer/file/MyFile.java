package com.customer.file;

import com.customer.model.Customers;

import java.io.*;
import java.util.ArrayList;

public class MyFile {
    public ArrayList<Customers> readFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("Customers.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Customers> list = (ArrayList<Customers>) objectInputStream.readObject();
            objectInputStream.close();
            return list;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeFile(ArrayList<Customers> list){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("Customers.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            if (!list.isEmpty()&&list!=null){
                objectOutputStream.writeObject(list);
            }
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
