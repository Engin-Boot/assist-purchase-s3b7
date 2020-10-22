package com.example.product.controller;

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
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

//
//    /*
//    This Api returns the list of products  according to user specifications
//     */
//    @RequestMapping(method=RequestMethod.GET)
//    public List<Product> getProductUserSpec(@RequestParam(value="touchscreen") boolean touchscreen, @RequestParam(value="size") int size, @RequestParam(value="category") String category, @RequestParam(value="transportMonitor") boolean transportMonitor) throws Exception{
//        return service.getProductsAccParameters(touchscreen,size,category,transportMonitor);
//
//
//    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProdById(@PathVariable int product_id)throws Exception
    {
        Product product =  productService.getProductById(product_id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

        Product savedProduct = productService.addProduct(product);
        return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int product_id, @RequestBody Product product ) throws ProductNotFoundException {

        Product updatedProduct = productService.updateProduct(product_id, product);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int product_id) throws Exception {
        Product deletedProduct = productService.deleteProductById(product_id);
        return new ResponseEntity<Product>(deletedProduct, HttpStatus.OK);
    }
}
