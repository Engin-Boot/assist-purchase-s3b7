package com.philips.bootcamp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.bootcamp.domain.Product;
import com.philips.bootcamp.repositories.AdminRepositories;

@Service("productService")
public class ProductServiceImplementation implements ProductService{
    
    @Autowired
    private AdminRepositories productRepository;

    @Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }
}
