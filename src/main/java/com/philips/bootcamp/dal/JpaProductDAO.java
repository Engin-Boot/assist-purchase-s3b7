package com.philips.bootcamp.dal;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.philips.bootcamp.domain.Product;
import com.philips.bootcamp.dto.ProductDTO;

@Transactional
@Repository
public class JpaProductDAO implements ProductDAO{
    @PersistenceContext
    EntityManager em;

    @Override
    public Product save(ProductDTO product){
        System.out.println("Product : " + product.toString());
        final Product toBeSaved = product.changeDTOToEntity(product);
        System.out.println("ProductDto : " + toBeSaved.toString());
        em.persist(toBeSaved);
        return toBeSaved;
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("select b from Bed b").getResultList();
    }

    @Override
    public Product findById(int ProductId) {
        return em.find(Product.class, ProductId);
    }

    @Override
    public void deletebyId(int ProductId) {
        final Product product = findById(ProductId);
        if(product!=null){
            em.remove(product);
        }
    }
}
