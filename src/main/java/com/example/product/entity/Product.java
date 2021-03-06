package com.example.product.entity;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int productId;

    @Column
    private String productName;

    @Column
    private String category;

    @Column
    private Boolean touchscreen;

    @Column
    private int size;

    @Column
    private Boolean portable;

    public Product(){}

    public Product(int productId, String productName, String category, Boolean touchscreen, int size, Boolean portable) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.touchscreen = touchscreen;
        this.size = size;
        this.portable = portable;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean isTouchscreen() {
        return touchscreen;
    }

    public void setTouchscreen(Boolean touchscreen) {
        this.touchscreen = touchscreen;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Boolean isPortable() {
        return portable;
    }

    public void setPortable(Boolean portable) {
        this.portable = portable;
    }
}