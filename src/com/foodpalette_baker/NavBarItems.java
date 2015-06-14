package com.foodpalette_baker;

public class NavBarItems {

	 private String title;
	 private int image_id;
	 

	 public NavBarItems(String title,int image_id)
	 {
		 this.title=title;
		 this.image_id=image_id;
	 }
	 public String getTitle() {
	  return title;
	 }

	 public void setTitle(String title) {
	  this.title = title;
	 }

	 public int getPicId() {
	  return image_id;
	 }

	 public void setPicId(int image_id) {
	  this.image_id = image_id;
	 }
}
