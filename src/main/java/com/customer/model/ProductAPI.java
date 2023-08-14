package com.customer.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductAPI {

    private int id;
    private String pname;
    private int cid;
    private int sid;
    private float price;
}
