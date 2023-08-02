package com.customer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fid;
    private int uid;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String contentType;
    @Column(nullable = false,length = 20971520)
    @Lob
    private byte[] content;
}
