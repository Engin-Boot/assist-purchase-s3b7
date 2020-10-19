package com.philips.bootcamp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philips.bootcamp.domain.Product;
import com.philips.bootcamp.dto.ProductDTO;

@Service
public interface ProductService {

    @Autowired
    public List<Product> findAllProducts();
    public int addNewProduct(ProductDTO product);
    public void deleteProductByID(int id );
    public Product findProductByID(int id); 
}
