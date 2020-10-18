package com.philips.bootcamp.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.philips.bootcamp.domain.Product;

@Repository("adminRepositories")
public interface AdminRepositories extends CrudRepository<Product, Integer>{
    
}