package com.customer.service.impl;

import com.customer.common.Comm;
import com.customer.model.*;
import com.customer.repository.ProductRepository;
import com.customer.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseResponse implements ProductService {

    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private EntityTransaction transaction;



    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getProductDto() {
        entityManager= factory.createEntityManager();

        return null;
    }

    @Override
    @Transactional
    public void save(Product product) {
        if(product.getPid()>0){
            productRepository.saveAndFlush(product);
        } else {
            productRepository.save(product);
        }

    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product finById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new RuntimeException();
        }
        return product.get();
    }

    @Override
    public List<ProductDto> finByName(String name) {
//        List<Map < String, Object>> mapList = productRepository.findAllByPname(name);
        List<Map < String, Object>> mapList = productRepository.findAllByPname(name);
        ObjectMapper mapper = new ObjectMapper();
        List<ProductDto> products = new ArrayList<>();
        for (Map m: mapList) {
            ProductDto dto=mapper.convertValue(m,ProductDto.class);
            products.add(dto);
        }
        return  products;
    }

    @Override
    public List<Product> findByCategory(int cid) {
        return productRepository.findAllByCid(cid);
    }

    @Override
    public List<ProductDto> getInfor() {
        List<Map<String, Object>> mapList = productRepository.findInfor();
        ObjectMapper mapper = new ObjectMapper();
        List<ProductDto> products = new ArrayList<>();
        for (Map m : mapList) {
            ProductDto dto = mapper.convertValue(m, ProductDto.class);
            products.add(dto);
        }
        return products;
    }


    @Override
    public ResponseEntity<?> getListProductApiModel() {
        WebClient webClient = WebClient.create(Comm.URL8088);
        MyResponse myResponse = webClient.get()
                .uri(Comm.URLGETPRODUCT)
                .retrieve()
                .bodyToMono(MyResponse.class).block();


        List<ProductAPI> apiList = (List<ProductAPI>) myResponse.getData();
        //ep kieu de tra ve data de ben nay hung va xu ly
        System.out.println(apiList.get(0));


        return getResponseEntity(myResponse.getData());
    }

    @Override
    public ProductAPI getOne() {
        WebClient webClient = WebClient.create(Comm.URL8088);
        ProductAPI api = webClient.get()
                .uri("/api/v1/product/getOne")
                .retrieve()
                .bodyToMono(ProductAPI.class).block();

        System.out.println(api);
        return api;
    }

    @Override
    public ResponseEntity<?> postProductApi(ProductAPI productAPI) {
        WebClient client = WebClient.create(Comm.URL8088);
        MyResponse myResponse = client.post()
                .uri("/api/v1/product/save")
                .retrieve()
                .bodyToMono(MyResponse.class)
                .block();
        System.out.println(myResponse.getData());
        return getResponseEntity(myResponse.getData());
    }
}
