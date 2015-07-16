package com.foodpalette_baker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SelectProductsActivity extends MainActivity {

	private ListView selectProductList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		framed = (FrameLayout)findViewById(R.id.activity_frame);
		  LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  defaultView = layoutInflater.inflate(R.layout.select_product, null,false);
		  framed.removeAllViews();
		  framed.addView(defaultView);
		  
		  selectProductList=(ListView)findViewById(R.id.selectproductslist);
		  
		  SelectProductsAdapter adapter = new SelectProductsAdapter(getBaseContext(),ProductsFragment.Items);	
		  
		  selectProductList.setAdapter(adapter);
		  
		  selectProductList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent ReviewsActivity=new Intent("com.foodpalette_baker.REVIEWSACTIVITY");
				ReviewsActivity.putExtra("ProductId", ProductsFragment.productid[position]);
				startActivity(ReviewsActivity);
			}
			});
	}

}
