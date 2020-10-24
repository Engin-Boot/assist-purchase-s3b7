package com.example.product.controller;

import com.example.product.entity.Alert;
import com.example.product.entity.Product;
import com.example.product.exceptions.ProductNotFoundException;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Product>> searchDBForProduct(@RequestBody Product product) throws Exception{
        List<Product> list1 = productService.searchProductsBasedOnParameter(product);
        return new ResponseEntity<List<Product>>(list1, HttpStatus.OK);
    }

    @GetMapping("/{product_id}")
    @CrossOrigin
    public ResponseEntity<Product> getProdById(@PathVariable int product_id)throws Exception
    {
        Product product =  productService.getProductById(product_id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("/add")
    @CrossOrigin
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

        Product savedProduct = productService.addProduct(product);
        return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update/{product_id}")
    @CrossOrigin
    public ResponseEntity<Product> updateProduct(@PathVariable int product_id, @RequestBody Product product ) throws ProductNotFoundException {

        Product updatedProduct = productService.updateProduct(product_id, product);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{product_id}")
    @CrossOrigin
    public ResponseEntity<Product> deleteProduct(@PathVariable int product_id) throws Exception {
        Product deletedProduct = productService.deleteProductById(product_id);
        return new ResponseEntity<Product>(deletedProduct, HttpStatus.OK);
    }

    @PostMapping("/createAlert")
    @CrossOrigin
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {

        Alert savedAlert = productService.addAlert(alert);
        return new ResponseEntity<Alert>(savedAlert, HttpStatus.CREATED);
    }

    @GetMapping("/alerts/all")
    @CrossOrigin
    public ResponseEntity<List<Alert>> getAllAlerts(){
        List<Alert> alerts = productService.getAllAlerts();
        return new ResponseEntity<List<Alert>>(alerts, HttpStatus.OK);
    }
}
