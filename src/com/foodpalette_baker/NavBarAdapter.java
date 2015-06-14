package com.foodpalette_baker;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class NavBarAdapter extends BaseAdapter{
	 private static LayoutInflater mInflater;
		static ViewHolder holder = null;

	    private List<NavBarItems> items = new ArrayList<NavBarItems>();

		private Context context;
	 
	    public NavBarAdapter(Context context, List<NavBarItems> items) {
	        mInflater = LayoutInflater.from(context);
	        this.items = items;
	        this.context=context;
	    }
	     public  NavBarAdapter(List<NavBarItems> items)
	      {		
	    	  this.items=items;
	      }
	    public int getCount() {
	        return items.size();
	    }
	 
	    public NavBarItems getItem(int position) {
	        return items.get(position);
	    }
	 
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	LayoutInflater mInflater = (LayoutInflater) context
	    		    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	    		  if (convertView == null) {
	    		   convertView = mInflater.inflate(R.layout.navbar_list, null);
	    		   holder = new ViewHolder();

	    		   holder.title = (TextView) convertView
	    		     .findViewById(R.id.navtitle);
	    		   holder.image = (ImageView) convertView
	    		     .findViewById(R.id.navimage);
	    		   

	    		   convertView.setTag(holder);
	    		  } else {
	    		   holder = (ViewHolder) convertView.getTag();
	    		  }
	    		  NavBarItems row_pos = items.get(position);

	    		  holder.image.setImageResource(row_pos.getPicId());
	    		  holder.title.setText(row_pos.getTitle());

	    		  return convertView;
	    }
	 
	    static class ViewHolder {
	        ImageView image;
	        TextView title;
	        
	    }
}

