package com.customer.repository;

import com.customer.model.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<MyFile, Integer> {
    MyFile findByFileName(String fileName);

    MyFile findByUid(int uid);

}
