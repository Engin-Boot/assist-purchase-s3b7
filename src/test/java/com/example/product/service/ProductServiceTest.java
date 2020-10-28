package com.example.product.service;

import com.example.product.entity.Alert;
import com.example.product.entity.Product;
import com.example.product.exceptions.ProductNotFoundException;
import com.example.product.repository.AlertRepository;
import com.example.product.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    AlertRepository alertRepository;

    @Mock
    ProductRepository productRepository;


    ProductService productService;

    @Captor
    ArgumentCaptor<Product> productArgumentCaptor;

    @Captor
    ArgumentCaptor<Alert> alertArgumentCaptor;

    @Before
    public void setUp() {
        productService = new ProductService(productRepository, alertRepository);
    }

    @Test
    public void given_Existing_Product_Id_When_Get_Product_By_Id_Then_Throw_No_Exception() throws ProductNotFoundException {
        int existing_product_id = 101;
        Product existing_product = new Product( existing_product_id,"name1", "category1", true, 9, true);

        when(productRepository.findById(existing_product_id)).thenReturn(Optional.of(existing_product));
        Product product = productService.getProductById(existing_product_id);
        Assert.assertNotNull(product.getCategory());
        Assert.assertNotNull(product.getSize());
        Assert.assertNotNull(product.getProductName());
        Assert.assertNotNull(product.isPortable());
        Assert.assertNotNull(product.isTouchscreen());
    }

    @Test(expected = ProductNotFoundException.class)
    public void given_Non_Existing_Product_Id_When_Get_Product_By_Id_Then_Throw_Exception() throws ProductNotFoundException {
        int non_existing_product_id = 102;

        when(productRepository.findById(any())).thenReturn(Optional.empty());
        productService.getProductById(non_existing_product_id);
    }

    @Test
    public void given_Product_When_Added_Then_Throw_No_Exception() {
        Product new_product = new Product(101, "name1", "category1", true, 9, true);
        productService.addProduct(new_product);

        verify(productRepository, times(1)).save(productArgumentCaptor.capture());

        Assert.assertEquals(101, productArgumentCaptor.getValue().getProductId());
        Assert.assertEquals(9, productArgumentCaptor.getValue().getSize());
        Assert.assertEquals("category1", productArgumentCaptor.getValue().getCategory());
        Assert.assertEquals("name1", productArgumentCaptor.getValue().getProductName());
        Assert.assertEquals(true, productArgumentCaptor.getValue().isPortable());
        Assert.assertEquals(true, productArgumentCaptor.getValue().isTouchscreen());
    }

    @Test
    public void given_Existing_Product_Id_When_Updating_Product_Then_Throw_No_Exception() throws ProductNotFoundException {
        int existing_product_id = 101;
        Product existing_product = new Product(existing_product_id, "name1", "category1", true, 9, true);
        Product updated_product = new Product(existing_product_id, "name_updated", "category_updated", true, 9, true);

        when(productRepository.findById(existing_product_id)).thenReturn(Optional.of(existing_product));

        productService.updateProduct(existing_product_id, updated_product);

        verify(productRepository, times(1)).save(productArgumentCaptor.capture());

        Assert.assertEquals(existing_product_id, productArgumentCaptor.getValue().getProductId());
        Assert.assertEquals(9, productArgumentCaptor.getValue().getSize());
        Assert.assertEquals("category_updated", productArgumentCaptor.getValue().getCategory());
        Assert.assertEquals("name_updated", productArgumentCaptor.getValue().getProductName());
        Assert.assertEquals(true, productArgumentCaptor.getValue().isPortable());
        Assert.assertEquals(true, productArgumentCaptor.getValue().isTouchscreen());
    }

    @Test(expected = ProductNotFoundException.class)
    public void given_Non_Existing_Product_Id_When_Updating_Product_Then_Throw_Exception() throws ProductNotFoundException {
        int non_existing_product_id = 101;
        Product updated_product = new Product(non_existing_product_id, "name_updated", "category_updated", true, 9, true);

        when(productRepository.findById(non_existing_product_id)).thenReturn(Optional.empty());

        productService.updateProduct(non_existing_product_id, updated_product);
    }

    @Test
    public void given_Existing_Product_Id_When_Deleting_Product_Then_Throw_No_Exception() throws ProductNotFoundException {
        int existing_product_id = 101;
        Product existing_product = new Product(existing_product_id, "name", "category", true, 9, true);

        when(productRepository.findById(existing_product_id)).thenReturn(Optional.of(existing_product));

        productService.deleteProductById(existing_product_id);

        verify(productRepository, times(1)).delete(productArgumentCaptor.capture());

        Assert.assertEquals(existing_product_id, productArgumentCaptor.getValue().getProductId());
        Assert.assertEquals(9, productArgumentCaptor.getValue().getSize());
        Assert.assertEquals("category", productArgumentCaptor.getValue().getCategory());
        Assert.assertEquals("name", productArgumentCaptor.getValue().getProductName());
        Assert.assertEquals(true, productArgumentCaptor.getValue().isPortable());
        Assert.assertEquals(true, productArgumentCaptor.getValue().isTouchscreen());
    }

    @Test(expected = ProductNotFoundException.class)
    public void given_Non_Existing_Product_Id_When_Deleting_Product_Then_Throw_Exception() throws ProductNotFoundException {
        int non_existing_product_id = 101;

        when(productRepository.findById(non_existing_product_id)).thenReturn(Optional.empty());

        productService.deleteProductById(non_existing_product_id);
    }

    @Test
    public void given_Products_When_Getting_All_Products_Then_Throw_No_Exception() {
        Product product1 = new Product(101, "name", "category", true, 9, true);
        Product product2 = new Product(102, "name", "category", true, 9, true);
        Product product3 = new Product(103, "name", "category", true, 9, true);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> products = productService.getAllProducts();

        Assert.assertEquals(3, products.size());
    }

    @Test
    public void given_Alerts_When_Getting_All_Alerts_Then_Throw_No_Exception() {
        Alert alert1 = new Alert(101, "1001", "prodName", "message1" );
        Alert alert2 = new Alert(102, "1001", "prodName", "message2" );
        Alert alert3 = new Alert(103, "1001", "prodName", "message3" );
        List<Alert> alertList = new ArrayList<>();
        alertList.add(alert1);
        alertList.add(alert2);
        alertList.add(alert3);

        when(alertRepository.findAll()).thenReturn(alertList);

        List<Alert> alerts = productService.getAllAlerts();

        Assert.assertEquals(3, alerts.size());
    }

    @Test
    public void given_Alert_When_Added_Then_Throw_No_Exception() {
        Alert alert = new Alert(101, "1001", "prodName", "message1" );
        productService.addAlert(alert);

        verify(alertRepository, times(1)).save(alertArgumentCaptor.capture());

        Assert.assertEquals(101, alertArgumentCaptor.getValue().getAlertId());
        Assert.assertEquals("message1", alertArgumentCaptor.getValue().getMessage());
        Assert.assertEquals("prodName", alertArgumentCaptor.getValue().getProductName());
        Assert.assertEquals("1001", alertArgumentCaptor.getValue().getProductId());
    }

    @Test
    public void given_Products_When_Searching_Products_Based_On_Parameters_Then_Throw_No_Exception() {
        Product product1 = new Product(101, "name1", "category1", true, 9, true);
        Product product2 = new Product(102, "name2", "category2", true, 9, true);
        Product product3 = new Product(103, "name1", "category1", true, 9, true);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        Product productParameters = new Product((int)Math.random(), "name1", "category1", true, 9, true);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> searchedProducts = productService.searchProductsBasedOnParameter(productParameters);

        Assert.assertEquals(2, searchedProducts.size());
    }


}
