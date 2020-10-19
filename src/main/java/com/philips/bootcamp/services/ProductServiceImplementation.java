package com.philips.bootcamp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.philips.bootcamp.domain.Product;
import com.philips.bootcamp.dto.ProductDTO;
import com.philips.bootcamp.dal.ProductDAO;
import com.philips.bootcamp.repositories.AdminRepositories;

@Service()
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productDAO.findAll();
    }

    @Override
    public int addNewProduct(ProductDTO product) {
        return productDAO.save(product).getProductID();
    }

    @Override
    public void deleteProductByID(int id) {
        productDAO.deletebyId(id);
    }

    @Override
    public Product findProductByID(int id) {
        return productDAO.findById(id);
    }
}
