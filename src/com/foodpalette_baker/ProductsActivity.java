package com.foodpalette_baker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ProductsActivity extends Activity {
private ListView products;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		products=(ListView)findViewById(R.id.productslist);
		
	}
	

}
