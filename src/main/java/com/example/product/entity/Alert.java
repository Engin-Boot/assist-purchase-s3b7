package com.example.product.entity;

import javax.persistence.*;

@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int alertId;

    @Column
    private String productId;

    @Column
    private String productName;

    @Column
    private String message;

    public Alert(){}

    public Alert(int alertId, String productId, String productName, String message) {
        this.alertId = alertId;
        this.productId = productId;
        this.productName = productName;
        this.message = message;
    }

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


