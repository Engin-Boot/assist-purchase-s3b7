package com.philips.bootcamp.web;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import java.net.URI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.philips.bootcamp.domain.Product;
import com.philips.bootcamp.dto.ProductDTO;
import com.philips.bootcamp.services.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/hello")
    public String hello(){
        return "hello1";
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Product> addNewProduct(){

        final ProductDTO product = new ProductDTO();
        // final int size = getAllBeds().size();
        // if(size==bedLimit) {
        //   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }

        try{
            final int id = productService.addNewProduct(product);
            final HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/beds/"+id));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        catch(final Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/products")
    public List<Product> getAllBeds(){
        return productService.findAllProducts();
    }

    @GetMapping(value = "/products/{pid}")
    public ResponseEntity<Product> getBedById(@PathVariable("pid")int id){
  
      final Product product = productService.findProductByID(id);
  
      if(product!=null){
        return new ResponseEntity<>(product, HttpStatus.OK);
      }
      else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

}
