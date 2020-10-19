package com.philips.bootcamp.dto;

import com.philips.bootcamp.domain.Product;
import org.modelmapper.ModelMapper;

public class ProductDTO {
    int productID;
    String productName;
    String productDescription;
    String productFeatures;
    String productCaption;
    boolean touchScreenEnabled;

    public ProductDTO() {
        super();
    }

    public int getProductID() {
		return productID;
    }
    
    public String getProductName(){
        return productName;
    }

    public String getProductDescription(){
        return productDescription;
    }

    public String getProductCaption(){
        return productCaption;
    }

    public boolean getIsTouchScreenEnabled(){
        return touchScreenEnabled;
    }

	public void setProductID(int id) {
		this.productID = id;
    }
    
    public void setProductName(String ProductName){
        this.productName = ProductName;
    }

    public void setProductDescription(String ProductDescription){
        this.productDescription = ProductDescription;
    }

    public void setProductCaption(String ProductCaption){
        this.productCaption = ProductCaption;
    }

    public void setProductTouchScreenAvailability(Boolean isToucBoolean){
        this.touchScreenEnabled = isToucBoolean;
    }

    @Override
    public String toString() {
        return "Product [productID=" + productID + ", productName=" + productName + ", productDescription=" + productDescription + ", productCaption=" + productCaption + ", touchScreenEnabled=" + touchScreenEnabled + "]";
    }

    public Product changeDTOToEntity(ProductDTO productDTO)
    {
        final ModelMapper model = new ModelMapper();
        final Product product = model.map(productDTO, Product.class);
        return product;
    }
}
