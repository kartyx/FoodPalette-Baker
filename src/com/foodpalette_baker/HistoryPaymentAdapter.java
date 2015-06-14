package com.foodpalette_baker;

import java.util.ArrayList;
import java.util.List;

import com.foodpalette_baker.PendingPaymentAdapter.ViewHolder;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class HistoryPaymentAdapter extends BaseAdapter{
	 private static LayoutInflater mInflater;
	 
	    private List<HistoryPaymentStructure> items = new ArrayList<HistoryPaymentStructure>();

		private Context context;
	 
	    public HistoryPaymentAdapter(Context context, List<HistoryPaymentStructure> items) {
	        mInflater = LayoutInflater.from(context);
	        this.items = items;
	        this.context=context;
	    }
	
	    public int getCount() {
	        return items.size();
	    }
	 
	    public HistoryPaymentStructure getItem(int position) {
	        return items.get(position);
	    }
	 
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	        ViewHolder holder;
	        HistoryPaymentStructure s = items.get(position);
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.history_payment_list, null);
	            holder = new ViewHolder();
	            holder.price=(TextView)convertView.findViewById(R.id.historyAmount);
	            holder.product=(TextView)convertView.findViewById(R.id.historyOrder);	
	            holder.date=(TextView)convertView.findViewById(R.id.historyDate);
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        holder.price.setText(s.getPrice());
	        holder.product.setText(s.getProduct());
	        holder.date.setText(s.getDate());
	               

	        return convertView;
	    }
	 
	    static class ViewHolder {
	        TextView product,price,date;
	    }

		public void setContext(FragmentActivity activity) {
			// TODO Auto-generated method stub
	        mInflater = LayoutInflater.from(activity);

		}
}
