package com.customer.service;
import com.customer.model.Student;
import com.customer.model.StudentDto;

import java.util.ArrayList;
import java.util.List;

public interface StudentService {

    ArrayList<StudentDto> read();
    void create(Student student);
    Student save(Student student);
    void update(Student student);
    void delete(int id);
    Student findById(int id);
    List<Student> finByAID(int aid);
    ArrayList<Student> findAll(String name);
}
