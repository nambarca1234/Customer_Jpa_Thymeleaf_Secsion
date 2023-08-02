package com.customer.service;

import com.customer.model.MyFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    MyFile findByFileName(String fileName);

    MyFile findByUid(int uid);
    void save(MultipartFile file, int uid);
}
