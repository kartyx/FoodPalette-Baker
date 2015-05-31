package com.foodpalette_baker;

import android.graphics.Bitmap;


public class OrderStructure {
	 
    private String Product;
    private String Price;
    private Bitmap image;
    private String topping;
    private String description;
	private OrdersAdapter ia;
    
 
 
    public OrderStructure(String Product,String Price,String topping,String description) {
                this.Product = Product;
                this.Price = Price;
                this.image = null;
                this.topping=topping;
                this.description=description;
    }	

    public String getProduct() {
        return Product;
    }
 
    public void setProduct(String Products) {
        this.Product = Products;
    }
    public String getTopping(){
    	return topping;
    }
    
    public void setTopping(String topping)
    {
    	this.topping=topping;
    }
    
    public String getDescription(){
    	return topping;
    }
    
    public void setDescription(String description)
    {
    	this.description=description;
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
