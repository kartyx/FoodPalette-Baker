package com.foodpalette_baker;

import android.graphics.Bitmap;

public class ProductStructure {
	 	private String Product;
	    private Bitmap image;
	    private String available;
	    private String toppings;
	    
	 
	 
	    public ProductStructure(String Product,String toppings) {
	                this.Product = Product;
	                this.available = "Available Toppings";
	                this.image = null;
	                this.toppings=toppings;
	    }	

	    public String getProduct() {
	        return Product;
	    }
	 
	    public void setProduct(String Products) {
	        this.Product = Products;
	    }
	    public String getTopping(){
	    	return toppings;
	    }
	    
	    public void setTopping(String toppings)
	    {
	    	this.toppings=toppings;
	    }
	          public Bitmap getImage() {
	        return image;
	    }
	 
}
