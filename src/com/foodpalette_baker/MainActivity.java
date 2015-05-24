package com.foodpalette_baker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity {
private LinearLayout orders,products,payment,reviews;
private ListView orderslist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		orders=(LinearLayout)findViewById(R.id.orders);
		products=(LinearLayout)findViewById(R.id.products);
		payment=(LinearLayout)findViewById(R.id.payment);
		reviews=(LinearLayout)findViewById(R.id.reviews);
		orderslist=(ListView)findViewById(R.id.orderslist);
		orders.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent OrdersActivity=new Intent("com.foodpalette_baker.ORDERSACTIVITY");
				startActivity(OrdersActivity);
			}
			
		});
		
		products.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ProductsActivity=new Intent("com.foodpalette_baker.PRODUCTSACTIVITY");
				startActivity(ProductsActivity);
			}
			
		});
		
		payment.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent PayemntActivity=new Intent("com.foodpalette_baker.PAYMENTACTIVITY");
				startActivity(PayemntActivity);
			}
			
		});
		
		reviews.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ReviewsActivity=new Intent("com.foodpalette_baker.REVIEWSACTIVITY");
				startActivity(ReviewsActivity);
			}
			
		});
		
	}

}
