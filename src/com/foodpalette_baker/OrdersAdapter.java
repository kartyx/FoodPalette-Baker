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
import android.widget.Toast;



public class OrdersAdapter extends BaseAdapter {
	 
	 private static LayoutInflater mInflater;
	 
	    private List<OrderStructure> items = new ArrayList<OrderStructure>();

		private Context context;
	 
	    public OrdersAdapter(Context context, List<OrderStructure> items) {
	        mInflater = LayoutInflater.from(context);
	        this.items = items;
	        this.context=context;
	    }
	     public  OrdersAdapter(List<OrderStructure> items)
	      {		
	    	  this.items=items;
	      }
	    public int getCount() {
	        return items.size();
	    }
	 
	    public OrderStructure getItem(int position) {
	        return items.get(position);
	    }
	 
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	        ViewHolder holder;
	        OrderStructure s = items.get(position);
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.orders_list, null);
	            holder = new ViewHolder();
	            holder.image = (ImageView) convertView.findViewById(R.id.orderimage);
	            holder.price=(TextView)convertView.findViewById(R.id.orderprice);
	            holder.product=(TextView)convertView.findViewById(R.id.orderproduct);	            
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        holder.price.setText(s.getPrice());
	        holder.product.setText(s.getProduct());
	       
	        if (s.getImage() != null) {
	            holder.image.setImageBitmap(s.getImage());
	        } else {
	                // MY DEFAULT IMAGE
	            holder.image.setImageResource(R.drawable.prod);
	        }

	        return convertView;
	    }
	 
	    static class ViewHolder {
	        ImageView image;
	        TextView product,price;
	        
	    }

		public void setContext(FragmentActivity activity) {
			// TODO Auto-generated method stub
	        mInflater = LayoutInflater.from(activity);

		}
		}
