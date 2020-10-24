package com.example.product.service;

import com.example.product.entity.Alert;
import com.example.product.entity.Product;
import com.example.product.exceptions.ProductNotFoundException;
import com.example.product.repository.AlertRepository;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    List<Product> touchscreenList;
    List<Product> sizeList;
    List<Product> categoryList;
    List<Product> portableList;
    List<Product> allProducts;

    ProductRepository productRepository;

    AlertRepository alertRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, AlertRepository alertRepository){
        this.productRepository = productRepository;
        this.alertRepository = alertRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(int product_id) throws ProductNotFoundException {
        if(productRepository.findById(product_id).isPresent()) {
            return productRepository.findById(product_id).get();
        }
        else{
            throw new ProductNotFoundException("Product not found with id = " + product_id);
        }
    }

    public Product addProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public Product updateProduct(int product_id, Product updatedProduct) throws ProductNotFoundException {
        Product existingProduct =  getProductById(product_id);
        if(existingProduct != null){
            updatedProduct.setProductId(existingProduct.getProductId());
            return productRepository.save(updatedProduct);
        }
        return null;
    }


    public Product deleteProductById(int product_id) throws ProductNotFoundException {
        Product existingProduct =  getProductById(product_id);
        if(existingProduct != null){
            productRepository.delete(existingProduct);
            return existingProduct;
        }
        return null;
    }

    public Alert addAlert(Alert newAlert) {
        return alertRepository.save(newAlert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public void storeAllProductsInList(){
        allProducts = getAllProducts();
    }

    public void getProductsTouchScreen(Boolean touchscreen){
        List<Product> productList = allProducts;
        if(touchscreen != null) {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).isTouchscreen() == touchscreen) {
                    touchscreenList.add(productList.get(i));
                }
            }
        }
        else
        {
            touchscreenList = allProducts;
        }
    }


    public void getProductsSize(int size){
        List<Product> productList = allProducts;
        if(size != 0) {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getSize() == size)
                    sizeList.add(productList.get(i));
            }
        }
        else{
            sizeList = allProducts;
        }
    }



    public void getProductsCategory(String category){
        List<Product> productList = allProducts;
        if(category != null) {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getCategory().equals(category))
                    categoryList.add(productList.get(i));
            }
        }
        else{
            categoryList = allProducts;
        }
    }

    public void getProductsPortable(Boolean portable){
        List<Product> productList = allProducts;
        if(portable != null) {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).isPortable() == portable)
                    portableList.add(productList.get(i));
            }
        }
        else
        {
            portableList = allProducts;
        }
    }


    public Set<Product> getProductSpecs(){
        Set<Product> intersectionSet1=categoryList.stream().distinct().filter(portableList::contains)
                .collect(Collectors.toSet());
        Set<Product> intersectionSet2=touchscreenList.stream().distinct().filter(sizeList::contains)
                .collect(Collectors.toSet());
        intersectionSet1.retainAll(intersectionSet2);
        return intersectionSet1;
    }

    public void initializeLists(){
        touchscreenList = new ArrayList<>();
        sizeList = new ArrayList<>();
        categoryList = new ArrayList<>();
        portableList = new ArrayList<>();
    }


    public List<Product> searchProductsBasedOnParameter(Product product) {
        initializeLists();
        storeAllProductsInList();
        getProductsCategory(product.getCategory());
        getProductsPortable(product.isPortable());
        getProductsSize(product.getSize());
        getProductsTouchScreen(product.isTouchscreen());
        List<Product> userRequestedProd = new ArrayList<>(getProductSpecs());
        return userRequestedProd;
    }


}
