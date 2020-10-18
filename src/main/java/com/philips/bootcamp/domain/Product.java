package com.philips.bootcamp.domain;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class Product {

    @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int ProductID;
	private String ProductName;
	private String ProductDescription;
	private String ProductFeatures;
	private String ProductCaption;
    private Blob ProductImage;
    
    public int getProductID() {
		return ProductID;
    }
    
    public String getProductName(){
        return ProductName;
    }

    public String getProductDescription(){
        return ProductDescription;
    }

    public String getProductFeatures(){
        return ProductFeatures;
    }

    public String getProductCaption(){
        return ProductCaption;
    }

    public Blob getProductImage(){
        return ProductImage;
    }

	public void setProductID(int id) {
		this.ProductID = id;
    }
    
    public void setProductName(String ProductName){
        this.ProductName = ProductName;
    }

    public void setProductDescription(String ProductDescription){
        this.ProductDescription = ProductDescription;
    }

    public void setProductFeatures(String ProductFeatures){
        this.ProductFeatures = ProductFeatures;
    }

    public void setProductCaption(String ProductCaption){
        this.ProductCaption = ProductCaption;
    }

    public void setProductImage(Blob ProductImage){
        this.ProductImage = ProductImage;
    }
    
    public Product(int ProductID, String ProductName, String ProductDescription, String ProductFeatures, String ProductCaption, Blob ProductImage ) {
	    super();
	    this.ProductID = ProductID;
	    this.ProductName = ProductName;
	    this.ProductDescription = ProductDescription;
	    this.ProductFeatures= ProductFeatures;
	    this.ProductCaption = ProductCaption;
	    this.ProductImage = ProductImage;
	}
}
