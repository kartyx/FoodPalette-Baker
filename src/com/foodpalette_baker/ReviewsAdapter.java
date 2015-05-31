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

import com.foodpalette_baker.ReviewsAdapter.ViewHolder;

public class ReviewsAdapter extends BaseAdapter {
	private static LayoutInflater mInflater;
	 
    private List<ReviewStructure> items = new ArrayList<ReviewStructure>();

	private Context context;
 
    public ReviewsAdapter(Context context, List<ReviewStructure> items) {
        mInflater = LayoutInflater.from(context);
        this.items = items;
        this.context=context;
    }
     public  ReviewsAdapter(List<ReviewStructure> items)
      {		
    	  this.items=items;
      }
    public int getCount() {
        return items.size();
    }
 
    public ReviewStructure getItem(int position) {
        return items.get(position);
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ReviewStructure s = items.get(position);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.reviews_list, null);
            holder = new ViewHolder();
            holder.review = (TextView) convertView.findViewById(R.id.ReviewContent);
            holder.date=(TextView)convertView.findViewById(R.id.ReviewDate);
            holder.username=(TextView)convertView.findViewById(R.id.ReviewUsername);	            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.date.setText(s.getDate());
        holder.username.setText(s.getUserName());
        holder.review.setText(s.getReview());
       

        return convertView;
    }
 
    static class ViewHolder {
        TextView username,date,review;
        
    }

	public void setContext(FragmentActivity activity) {
		// TODO Auto-generated method stub
        mInflater = LayoutInflater.from(activity);

	}

}
