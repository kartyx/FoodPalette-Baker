package com.foodpalette_baker;

public class HistoryPaymentStructure {
	private String Product;
    private String Price;
    private String deliveryDate;
   
 
 
    public HistoryPaymentStructure(String Product,String Price,String deliveryDate) {
                this.Product = Product;
                this.Price = Price;
                this.deliveryDate=deliveryDate;
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
 
    public void setDate(String deliveryDate)
    {
    	this.deliveryDate=deliveryDate;
    }
    
    public String getDate()
    {
    	return deliveryDate;
    }
}
