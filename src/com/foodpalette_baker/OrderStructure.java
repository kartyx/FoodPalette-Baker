package com.foodpalette_baker;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;


public class OrderStructure {
	 
    private String Product;
    private String Price;
    private String topping;
    private String description;
	private OrdersAdapter ia;
	private String imgUrl;
	private Bitmap image;
    
 
 
    public OrderStructure(String Product,String Price,String topping,String description,String imgUrl) {
                this.Product = Product;
                this.Price = Price;
                this.imgUrl = imgUrl;
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
    
    public void setImgUrl(String ImgUrl) {
        this.imgUrl = ImgUrl;
    }
    public String getImgUrl(){
    	return imgUrl;
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
 
        public OrdersAdapter getAdapter() {
	        return ia;
	    }
	 
	    public void setAdapter(OrdersAdapter ia) {
	        this.ia = ia;
	    }
        public void loadImage(OrdersAdapter ia) {
	        // HOLD A REFERENCE TO THE ADAPTER
	        this.ia = ia;
	        if (imgUrl != null && !imgUrl.equals("")) {
	            new ImageLoadTask().execute(imgUrl);
	        }
	    }
	 
	    // ASYNC TASK TO AVOID CHOKING UP UI THREAD
	    private class ImageLoadTask extends AsyncTask<String, String, Bitmap> {
	 
	        @Override
	        protected void onPreExecute() {
	            Log.i("ImageLoadTask", "Loading image...");
	        }
	 
	        // PARAM[0] IS IMG URL
	        protected Bitmap doInBackground(String... param) {
	            Log.i("ImageLoadTask", "Attempting to load image URL: " + param[0]);
	            try {
	                Bitmap b = ImageService.getBitmapFromURL(param[0]);
	                return b;
	            } catch (Exception e) {
	                e.printStackTrace();
	                return null;
	            }
	        }
	 
	        protected void onProgressUpdate(String... progress) {
	            // NO OP
	        }
	 
	        protected void onPostExecute(Bitmap ret) {
	            if (ret != null) {
	                Log.i("ImageLoadTask", "Successfully loaded " + imgUrl + " image");
	                image = ret;
	                if (ia != null) {
	                    // WHEN IMAGE IS LOADED NOTIFY THE ADAPTER
	                    ia.notifyDataSetChanged();
	                }
	            } else {
	                Log.e("ImageLoadTask", "Failed to load " + imgUrl + " image");
	            }
	        }
	    }

}
