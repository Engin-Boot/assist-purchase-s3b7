package com.example.product.service;

import com.example.product.entity.Product;
import com.example.product.exceptions.ProductNotFoundException;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
//    List<Product> touchscreenList=new ArrayList<>();
//    List<Product> sizeList=new ArrayList<>();
//    List<Product> categoryList=new ArrayList<>();
//    List<Product> transportMonitorList=new ArrayList<>();


//    public Iterable<Product> save(List<Product> products) {
//        return dao.saveAll(products);
//    }


    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
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




    /*
    Returns list of products that have touchscreen, and focuses on no other feature of the product
     */
//    @Override
//    public void getProductsTouchScreen(boolean touchscreen){
//        List<Product> lprod=getProducts();
//        for(int i=0;i<lprod.size();i++)
//        {
//            if(lprod.get(i).isTouchscreen()==touchscreen) {
//                touchscreenList.add(lprod.get(i));
//            }
//        }
//    }
//
//
//    /*
//    Returns list of products that have size according to user argument
//     */
//    @Override
//    public void getProductsSize(int size){
//        List<Product> lprod=getProducts();
//        for(int i=0;i<lprod.size();i++)
//        {
//            if(lprod.get(i).getSize()==size)
//                sizeList.add(lprod.get(i));
//        }
//    }
//
//
//    /*
//    Returns list of products that fall into category according to user argument
//     */
//    @Override
//    public void getProductsCategory(String category){
//        List<Product> lprod=getProducts();
//        for(int i=0;i<lprod.size();i++)
//        {
//            if(lprod.get(i).getCategory().equals(category))
//                categoryList.add(lprod.get(i));
//        }
//    }
//
//
//    /*
//    Returns list of products that have transportMonitor as true i.e. the are portable
//     */
//    @Override
//    public void getProductsTransportMonitor(boolean transportMonitor){
//        List<Product> lprod=getProducts();
//        for(int i=0;i<lprod.size();i++)
//        {
//            if(lprod.get(i).isTransportMonitor()==transportMonitor)
//                transportMonitorList.add(lprod.get(i));
//        }
//    }
//
//    /*
//   Creates two sets for two lists each and finally performs intersection operation on them
//     */
//    public Set<Product> getProductSpecs(){
//        Set<Product> intersectionSet1=categoryList.stream().distinct().filter(transportMonitorList::contains)
//                .collect(Collectors.toSet());
//        Set<Product> intersectionSet2=touchscreenList.stream().distinct().filter(sizeList::contains)
//                .collect(Collectors.toSet());
//        intersectionSet1.retainAll(intersectionSet2);
//        return intersectionSet1;
//    }
//
//
//    /*
//    Converts the set created by interscetion to be converted to arraylist
//     */
//    @Override
//    public List<Product> getProductsAccParameters(boolean touchscreen, int size, String category, boolean transportMonitor) {
//        getProductsCategory(category);
//        getProductsTransportMonitor(transportMonitor);
//        getProductsSize(size);
//        getProductsTouchScreen(touchscreen);
//        List<Product> userRequestedProd=new ArrayList<>(getProductSpecs());
//        return userRequestedProd;
//    }


}
