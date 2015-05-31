package com.foodpalette_baker;

import android.graphics.Bitmap;

public class PendingOrderStructure {
	 	private String Product;
	    private String Price;
	    private Bitmap image;
	    private String deliveryDate;
	   
	 
	 
	    public PendingOrderStructure(String Product,String Price,String topping,String deliveryDate) {
	                this.Product = Product;
	                this.Price = Price;
	                this.image = null;
	                this.deliveryDate=deliveryDate;
	    }	

	    public String getProduct() {
	        return Product;
	    }
	 
	    public void setProduct(String Products) {
	        this.Product = Products;
	    }
	    public String getdeliveryDate(){
	    	return deliveryDate;
	    }
	    
	    public void setdeliveryDate(String deliveryDate)
	    {
	    	this.deliveryDate=deliveryDate;
	    }
	    public String getPrice() {
	        return Price;
	    }
	 
	    public void setPrice(String Price) {
	        this.Price = Price;
	    }
	 
	        public Bitmap getImage() {
	        return image;
	    }
	 
	   


}
