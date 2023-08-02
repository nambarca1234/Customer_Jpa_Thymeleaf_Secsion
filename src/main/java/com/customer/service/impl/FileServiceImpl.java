package com.customer.service.impl;

import com.customer.model.MyFile;
import com.customer.repository.FileRepository;
import com.customer.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public MyFile findByFileName(String fileName) {
        return fileRepository.findByFileName(fileName);
    }

    @Override
    public MyFile findByUid(int uid) {
        return fileRepository.findByUid(uid);
    }

    @Override
    public void save(MultipartFile file, int uid) {
        MyFile myFile = new MyFile();
        myFile.setUid(uid);
        try{
            myFile.setFileName(file.getOriginalFilename());
            myFile.setContentType(file.getContentType());
            myFile.setContent(file.getBytes());
            fileRepository.save(myFile);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
