package com.foodpalette_baker;

import android.graphics.Bitmap;

public class PendingPaymentStructure {
	private String Product;
    private String Price;
   
 
 
    public PendingPaymentStructure(String Product,String Price) {
                this.Product = Product;
                this.Price = Price;
    }	

    public String getProduct() {
        return Product;
    }
 
    public void setProduct(String Products) {
        this.Product = Products;
    }

    public String getPrice() {
        return Price;
    }
 
    public void setPrice(String Price) {
        this.Price = Price;
    }
 
    
   



}
