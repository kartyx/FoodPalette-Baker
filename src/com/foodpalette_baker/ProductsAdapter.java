package com.foodpalette_baker;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodpalette_baker.OrdersAdapter.ViewHolder;

public class ProductsAdapter extends BaseAdapter{
	 private static LayoutInflater mInflater;
	 
	    private List<ProductStructure> items = new ArrayList<ProductStructure>();

		private Context context;
	 
	    public ProductsAdapter(Context context, List<ProductStructure> items) {
	        mInflater = LayoutInflater.from(context);
	        this.items = items;
	        this.context=context;
	    }
	     public  ProductsAdapter(List<ProductStructure> items)
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
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.products_list, null);
	            holder = new ViewHolder();
	            holder.image = (ImageView) convertView.findViewById(R.id.productImage);
	            holder.toppings=(TextView)convertView.findViewById(R.id.Toppings);
	            holder.product=(TextView)convertView.findViewById(R.id.ProductName);	    
	            holder.available=(TextView)convertView.findViewById(R.id.AvailableToppings);
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        holder.toppings.setText(s.getAvailableToppings());
	        holder.product.setText(s.getProductName());
	        holder.available.setText("AvailableToppings");
	        holder.image.setImageResource(R.drawable.prod);
	        

	        return convertView;
	    }
	 
	    static class ViewHolder {
	        ImageView image,arrow;
	        TextView product,toppings,available;
	        
	    }

		public void setContext(FragmentActivity activity) {
			// TODO Auto-generated method stub
	        mInflater = LayoutInflater.from(activity);

		}
}
