package com.customer.service.impl;

import com.customer.model.Address;
import com.customer.model.Student;
import com.customer.model.StudentDto;
import com.customer.repository.StudentRepository;
import com.customer.service.StudentService;
import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    //@PersistenceContext
    @PersistenceUnit
    private EntityManagerFactory factory;
    private EntityManager entityManager;

    private EntityTransaction transaction;

//    public StudentServiceImpl() {
//        this.factory = factory;
//        entityManager = factory.createEntityManager();
//        transaction = entityManager.getTransaction();
//    }


    @Override
    public ArrayList<StudentDto> read() {
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();

        String query = "select s.*, a.province from student as s, address as a where s.aid=a.aid";
        ArrayList<StudentDto> list = (ArrayList<StudentDto>) entityManager.createNativeQuery(query, StudentDto.class).getResultList();

        return list;
    }

    @Override

    public void create(Student student) {
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();
        if (student.getSid() > 0) {
            update(student);
        } else {
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
        }
    }

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }


    @Override
    public void update(Student student) {
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(student);
        transaction.commit();
    }


    @Override
    public void delete(int id) {
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();
        String query = "delete from Student s where s.sid = :id";

        transaction.begin();
        entityManager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();

    }

    @Override
    public List<Student> finByAID(int aid) {
        entityManager = factory.createEntityManager();
        String queryHQL = "select s from  Student as s where address.aid =?1";
        String querySQL = "select * from student where aid = ?1";
//        List<Student> list = entityManager.createQuery(queryHQL,Student.class)
//                .setParameter(1,aid).getResultList();


        List<Student> list2 = entityManager.createNativeQuery(querySQL, Student.class)
                .setParameter(1, aid).getResultList();
        return list2;
    }

    @Override
    public Student findById(int id) {
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();
        Student student = entityManager.find(Student.class, id);
        return student;
    }


    @Override
    public ArrayList<Student> findAll(String name) {
        return null;
    }
}
