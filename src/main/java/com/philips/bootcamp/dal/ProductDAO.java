package com.philips.bootcamp.dal;

import java.util.List;
import com.philips.bootcamp.domain.Product;
import com.philips.bootcamp.dto.ProductDTO;

public interface ProductDAO {
    Product save(ProductDTO Product);
    List<Product> findAll();
    Product findById(int ProductId);
    void deletebyId(int ProductId);
}
