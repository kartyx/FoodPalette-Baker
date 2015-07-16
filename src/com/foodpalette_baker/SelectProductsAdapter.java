package com.foodpalette_baker;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectProductsAdapter extends BaseAdapter{
	 private static LayoutInflater mInflater;
	 String status;
	    private List<ProductStructure> items = new ArrayList<ProductStructure>();

		private Context context;
		protected Button b;
		protected String ChangeStatus;
	 
	    public SelectProductsAdapter(Context context, List<ProductStructure> items) {
	        mInflater = LayoutInflater.from(context);
	        this.items = items;
	        this.context=context;
	    }
	     public  SelectProductsAdapter(List<ProductStructure> items)
	      {		
	    	  this.items=items;
	      }
	    public int getCount() {
	        return items.size();
	    }
	 
	    public ProductStructure getItem(int position) {
	        return items.get(position);
	    }
	 
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	        ViewHolder holder;
	        ProductStructure s = items.get(position);
	        Log.d(String.valueOf(position), s.getProductId());
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.review_select_product, null);
	            holder = new ViewHolder();
	            holder.image = (ImageView) convertView.findViewById(R.id.selectProductImage);
	            holder.product=(TextView)convertView.findViewById(R.id.selectProductName);	    
	              convertView.setTag(holder);
	            
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        holder.product.setText(s.getProductName());
	        holder.image.setImageResource(R.drawable.prod);
	         
	        return convertView;
	    }
	 
	    static class ViewHolder {
	        ImageView image;
	        TextView product;
	    }

}
