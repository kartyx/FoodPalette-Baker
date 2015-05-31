package com.foodpalette_baker;


public class ReviewStructure {
	private String UserName;
    private String date;
    private String review;

 
 
    public ReviewStructure(String UserName,String date,String Review) {
                this.UserName = UserName;
                this.date = date;
                this.review=Review;
    }	

    public String getUserName() {
        return UserName;
    }
 
    public void setUserName(String UserNames) {
        this.UserName = UserNames;
    }
    public String getReview(){
    	return review;
    }
    
    public void setReview(String Review)
    {
    	this.review=Review;
    }
    
    public String getDate(){
    	return date;
    }
    
    public void setDate(String date)
    {
    	this.date=date;
    }
 }
