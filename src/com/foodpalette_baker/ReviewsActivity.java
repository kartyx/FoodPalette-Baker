package com.foodpalette_baker;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class ReviewsActivity extends ActionBarActivity {
private ListView reviewslist;
private List<String> UserName,date,Review;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reviews);
		reviewslist=(ListView)findViewById(R.id.reviewslist);
		UserName=new ArrayList<String>();
		UserName.add("User 1");
		UserName.add("User 2");
		UserName.add("User 3");
		UserName.add("User 4");
		date=new ArrayList<String>();
		date.add("22-May-2015");
		date.add("23-May-2015");
		date.add("24-May-2015");
		date.add("25-May-2015");
		Review=new ArrayList<String>();
		Review.add("This is a Sample Review of the Sample product that i bought last month. I have been using the product for the last 1 month and i have no problems with the product except for the fact that it was not delivered on time");
		Review.add("This is a Sample Review of the Sample product that i bought last month. I have been using the product for the last 1 month and i have no problems with the product except for the fact that it was not delivered on time");
		Review.add("This is a Sample Review of the Sample product that i bought last month. I have been using the product for the last 1 month and i have no problems with the product except for the fact that it was not delivered on time");
		Review.add("This is a Sample Review of the Sample product that i bought last month. I have been using the product for the last 1 month and i have no problems with the product except for the fact that it was not delivered on time");
		List<ReviewStructure> Items = new ArrayList<ReviewStructure>();
		for (int i = 0; i <Review.size() ; i++) {
			ReviewStructure item = new ReviewStructure(UserName.get(i),date.get(i),Review.get(i));
			   Items.add(item);
			 }
		      ReviewsAdapter adapter = new ReviewsAdapter(getBaseContext(),Items);	
		
		      reviewslist.setAdapter(adapter);	
		      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			  getSupportActionBar().setHomeButtonEnabled(true);
			  getSupportActionBar().setTitle("Reviews");
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 
	        switch (item.getItemId()) {
	        case android.R.id.home: 
	            onBackPressed();
	            return true;
	        }

	    return super.onOptionsItemSelected(item);
	}
}
