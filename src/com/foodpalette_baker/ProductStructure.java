package com.foodpalette_baker;

public class ProductStructure {
	 	private String ProductId,ProductImage,ProductName,ProductPrice,ProductDescription,Availability,NoOfBuys,AvailableToppings;
	       
	 
	 
	    public ProductStructure(String ProductId,String ProductName,String ProductImage,String ProductPrice,String ProductDescription,String Availability,String NoOfBuys,String AvailableToppings) {
	                this.ProductId = ProductId;
	                this.ProductImage = ProductImage;
	                this.ProductName = ProductName;
	                this.ProductPrice = ProductPrice;
	                this.ProductDescription = ProductDescription;
	                this.Availability = Availability;
	                this.NoOfBuys=NoOfBuys;	      
	                this.AvailableToppings=AvailableToppings;
	    }	

	    public String getProductId() {
	        return ProductId;
	    }
	 
	    public void setProductId(String ProductId) {
	        this.ProductId = ProductId;
	    }
	    
	    public String getProductImage(){
	    	return ProductImage;
	    }
	    public void setProductImage(String ProductImage){
	    	this.ProductImage=ProductImage;
	    }
	    
	    public String getProductName(){
	    	return ProductName;
	    }
	    public void setProductName(String ProductName){
	    	this.ProductName=ProductName;
	    }
	    
	    public String getProductPrice(){
	    	return ProductPrice;
	    }
	    public void setProductPrice(String ProductPrice){
	    	this.ProductPrice=ProductPrice;
	    }
	    
	    public String getProductDescription(){
	    	return ProductDescription;
	    }
	    public void setProductDescription(String ProductDescription){
	    	this.ProductDescription=ProductDescription;
	    }
	    
	    public String getAvailability(){
	    	return Availability;
	    }
	    public void setAvailability(String Availability){
	    	this.Availability=Availability;
	    }
	    
	    public String getNoOfBuys(){
	    	return NoOfBuys;
	    }
	    public void setNoOfBuys(String NoOfBuys){
	    	this.NoOfBuys=NoOfBuys;
	    }
	    
	    public String getAvailableToppings(){
	    	return AvailableToppings;
	    }
	    public void setAvailableToppings(String AvailableToppings){
	    	this.AvailableToppings=AvailableToppings;
	    }
}
