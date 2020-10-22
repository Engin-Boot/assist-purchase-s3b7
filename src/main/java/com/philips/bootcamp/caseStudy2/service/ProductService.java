package com.philips.bootcamp.caseStudy2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.philips.bootcamp.caseStudy2.entity.Product;
import com.philips.bootcamp.caseStudy2.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public Product updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setFeatures(product.getFeatures());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCaption(product.getCaption());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImage(product.getImage());
        return repository.save(existingProduct);
    }


}
