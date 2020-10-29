package com.example.product.controller;

import com.example.product.entity.Alert;
import com.example.product.entity.Product;
import com.example.product.repository.AlertRepository;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AlertRepository alertRepository;


    @Test
    public void given_existing_product_when_get_product_by_id_api_is_called_then_return_status_200() throws Exception {
        int existing_product_id = 1;
        Product existing_product = new Product( existing_product_id,"name1", "category1", true, 9, true);
        productRepository.save(existing_product);

        mockMvc.perform(get("/products/{product_id}", existing_product_id)
                .contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.productName", Matchers.is("name1")))
                .andExpect(jsonPath("$.category", Matchers.is("category1")))
                .andExpect(jsonPath("$.touchscreen", Matchers.is(true)))
                .andExpect(jsonPath("$.size",Matchers.is(9)))
                .andExpect(jsonPath("$.portable",Matchers.is(true)));
    }

    @Test
    public void given_non_existing_product_when_get_product_by_id_api_is_called_then_return_status_400() throws Exception {
        int non_existing_product_id = 101;

        mockMvc.perform(get("/products/{product_id}", non_existing_product_id)
                .contentType("application/json"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.code", Matchers.is("400 BAD_REQUEST")))
                .andExpect(jsonPath("$.message", Matchers.is("Product not found with id = "+non_existing_product_id)));
    }

    @Test
    public void given_existing_products_when_get_all_products_api_is_called_then_return_status_200() throws Exception {
        Product product1 = new Product( 1,"name1", "category1", true, 9, true);
        Product product2 = new Product( 2,"name2", "category2", false, 11, true);
        Product product3 = new Product( 3,"name3", "category1", true, 7, false);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        mockMvc.perform(get("/products/all")
                .contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void given_new_product_when_add_product_api_is_called_then_return_status_200() throws Exception {
        int new_product_id = 1;
        Product new_product = new Product(new_product_id,"name1", "category1", true, 9, true);

        mockMvc.perform(post("/products/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new_product)))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.productName", Matchers.is("name1")))
                .andExpect(jsonPath("$.category", Matchers.is("category1")))
                .andExpect(jsonPath("$.touchscreen", Matchers.is(true)))
                .andExpect(jsonPath("$.size",Matchers.is(9)))
                .andExpect(jsonPath("$.portable",Matchers.is(true)));
    }

    @Test
    public void given_non_existing_productId_when_update_product_by_id_api_is_called_then_return_status_400() throws Exception {
        int non_existing_product_id = 101;
        Product updated_product_details = new Product( (int)Math.random(),"name1", "category1", true, 9, true);

        mockMvc.perform(put("/products/update/{product_id}", non_existing_product_id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(updated_product_details)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.code", Matchers.is("400 BAD_REQUEST")))
                .andExpect(jsonPath("$.message", Matchers.is("Product not found with id = "+non_existing_product_id)));
    }

    @Test
    public void given_existing_productId_when_update_product_by_id_api_is_called_then_return_status_200() throws Exception {
        int existing_product_id = 1;
        Product existing_product = new Product( existing_product_id,"name1", "category1", true, 9, true);
        productRepository.save(existing_product);

        Product updated_product_details = new Product( (int)Math.random(),"name2", "category2", true, 9, true);

        mockMvc.perform(put("/products/update/{product_id}", existing_product_id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(updated_product_details)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.productName", Matchers.is("name2")))
                .andExpect(jsonPath("$.category", Matchers.is("category2")))
                .andExpect(jsonPath("$.touchscreen", Matchers.is(true)))
                .andExpect(jsonPath("$.size",Matchers.is(9)))
                .andExpect(jsonPath("$.portable",Matchers.is(true)));
    }

    @Test
    public void given_existing_productId_when_delete_product_by_id_api_is_called_then_return_status_200() throws Exception {
        int existing_product_id = 1;
        Product existing_product = new Product( existing_product_id,"name1", "category1", true, 9, true);
        productRepository.save(existing_product);

        mockMvc.perform(delete("/products/delete/{product_id}", existing_product_id)
                .contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.productName", Matchers.is("name1")))
                .andExpect(jsonPath("$.category", Matchers.is("category1")))
                .andExpect(jsonPath("$.touchscreen", Matchers.is(true)))
                .andExpect(jsonPath("$.size",Matchers.is(9)))
                .andExpect(jsonPath("$.portable",Matchers.is(true)));
    }

    @Test
    public void given_non_existing_productId_when_delete_product_by_id_api_is_called_then_return_status_400() throws Exception {
        int non_existing_product_id = 101;

        mockMvc.perform(delete("/products/delete/{product_id}", non_existing_product_id)
                .contentType("application/json"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.code", Matchers.is("400 BAD_REQUEST")))
                .andExpect(jsonPath("$.message", Matchers.is("Product not found with id = "+non_existing_product_id)));
    }

    @Test
    public void given_new_alert_when_add_alert_api_is_called_then_return_status_200() throws Exception {
        int new_alert_id = 1;
        Alert new_alert = new Alert(new_alert_id,"10", "prodName10", "msg1");

        mockMvc.perform(post("/products/createAlert")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new_alert)))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.productName", Matchers.is("prodName10")))
                .andExpect(jsonPath("$.productId", Matchers.is("10")))
                .andExpect(jsonPath("$.message", Matchers.is("msg1")));
    }

    @Test
    public void given_existing_alerts_when_get_all_alerts_api_is_called_then_return_status_200() throws Exception {
        Alert alert1 = new Alert(1,"10", "prodName10", "msg1");
        Alert alert2 = new Alert(2,"11", "prodName11", "msg2");
        Alert alert3 = new Alert(3,"9", "prodName9", "msg3");
        alertRepository.save(alert1);
        alertRepository.save(alert2);
        alertRepository.save(alert3);

        mockMvc.perform(get("/products/alerts/all")
                .contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void given_existing_products_when_search_products_based_on_specification_api_is_called_then_return_status_200() throws Exception {
        Product product1 = new Product( 1,"name1", "category1", true, 9, true);
        Product product2 = new Product( 2,"name2", "category2", false, 11, true);
        Product product3 = new Product( 3,"name3", "category1", true, 7, false);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        Product productSpecification = new Product( (int)Math.random(),null, "category1", true, 7, false);

        mockMvc.perform(post("/products/search")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(productSpecification)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }



}