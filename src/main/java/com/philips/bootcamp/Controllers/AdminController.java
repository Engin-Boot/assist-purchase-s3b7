package com.philips.bootcamp.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.philips.bootcamp.domain.Product;
import com.philips.bootcamp.services.ProductService;

@RestController
@RequestMapping("/")
public class AdminController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/hello")
    public String hello(){
        return "hello1";
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

}
