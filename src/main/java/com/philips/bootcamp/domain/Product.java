package com.philips.bootcamp.domain;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.philips.bootcamp.dto.ProductDTO;

import org.modelmapper.ModelMapper;;
@Entity
@Table(name="Product")
public class Product {

    @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int ProductID;
	private String ProductName;
	private String ProductDescription;
	private String ProductCaption;
    boolean touchScreenEnabled;

    public Product() {

    }
    
    public int getProductID() {
		return ProductID;
    }
    
    public String getProductName(){
        return ProductName;
    }

    public String getProductDescription(){
        return ProductDescription;
    }

    public String getProductCaption(){
        return ProductCaption;
    }

    public Boolean getIsTouchScreenEnabled(){
        return touchScreenEnabled;
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

    public void setProductCaption(String ProductCaption){
        this.ProductCaption = ProductCaption;
    }

    public void setProductTouchScreenAvailability(Boolean isToucBoolean){
        this.touchScreenEnabled = isToucBoolean;
    }
    
    public Product(int ProductID, String ProductName, String ProductDescription, String ProductCaption, Boolean isToucBoolean ) {
	    super();
	    this.ProductID = ProductID;
	    this.ProductName = ProductName;
	    this.ProductDescription = ProductDescription;
	    this.ProductCaption = ProductCaption;
	    this.touchScreenEnabled = isToucBoolean;
    }
    
    public ProductDTO changeEntityToDto(Product product)
    {
        final ModelMapper model = new ModelMapper();
        final ProductDTO productDTO = model.map(product, ProductDTO.class);
        return productDTO;
    }
}
