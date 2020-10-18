package com.philips.bootcamp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philips.bootcamp.domain.Product;

@Service
public interface ProductService {

    @Autowired
    public List<Product> findAll();
    public Product addNewProduct(Product product);
    public void deleteById(int id );
    public Product findById(int id); 
}
