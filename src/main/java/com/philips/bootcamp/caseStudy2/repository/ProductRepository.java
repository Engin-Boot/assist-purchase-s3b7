package com.philips.bootcamp.caseStudy2.repository;

import com.philips.bootcamp.caseStudy2.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}

