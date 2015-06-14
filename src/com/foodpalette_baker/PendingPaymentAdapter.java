package com.foodpalette_baker;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class PendingPaymentAdapter extends BaseAdapter{
	 private static LayoutInflater mInflater;
	 
	    private List<PendingPaymentStructure> items = new ArrayList<PendingPaymentStructure>();

		private Context context;
	 
	    public PendingPaymentAdapter(Context context, List<PendingPaymentStructure> items) {
	        mInflater = LayoutInflater.from(context);
	        this.items = items;
	        this.context=context;
	    }
	
	    public int getCount() {
	        return items.size();
	    }
	 
	    public PendingPaymentStructure getItem(int position) {
	        return items.get(position);
	    }
	 
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	        ViewHolder holder;
	        PendingPaymentStructure s = items.get(position);
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.pending_payment_list, null);
	            holder = new ViewHolder();
	            holder.price=(TextView)convertView.findViewById(R.id.pendingPay);
	            holder.product=(TextView)convertView.findViewById(R.id.pendingOrder);	    
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        holder.price.setText(s.getPrice());
	        holder.product.setText(s.getProduct());
	               

	        return convertView;
	    }
	 
	    static class ViewHolder {
	        TextView product,price;
	    }

		public void setContext(FragmentActivity activity) {
			// TODO Auto-generated method stub
	        mInflater = LayoutInflater.from(activity);

		}
}
