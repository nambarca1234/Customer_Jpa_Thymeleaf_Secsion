package com.customer.controller;

import com.customer.model.MyFile;
import com.customer.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/get-file/{uid}")
    public ResponseEntity<?> downloadFile(@PathVariable int uid){
        MyFile myFile = fileService.findByUid(uid);
        return ResponseEntity.status(200).contentType(MediaType.parseMediaType(myFile.getContentType()))
                .body(myFile.getContent());

    }
}
