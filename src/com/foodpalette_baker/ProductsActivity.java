package com.foodpalette_baker;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ProductsActivity extends Fragment {
private ListView products;
private ArrayList<String> productArray;
private ArrayList<String> ToppingsArray;
private View activityView;
private FragmentActivity fa;
public View onCreateView(LayoutInflater inflater,
		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	// TODO Auto-generated method stub
		activityView = inflater.inflate(R.layout.activity_products, container,false);
		fa=(FragmentActivity)getActivity();
		products=(ListView)activityView.findViewById(R.id.productslist);
		productArray=new ArrayList<String>();
		productArray.add("Product 1");
		productArray.add("Product 2");
		productArray.add("Product 3");
		productArray.add("Product 4");
		ToppingsArray=new ArrayList<String>();
		ToppingsArray.add("Topping 1,Topping 2,Topping 3");
		ToppingsArray.add("Topping 1,Topping 2,Topping 3");
		ToppingsArray.add("Topping 1,Topping 2,Topping 3");
		ToppingsArray.add("Topping 1,Topping 2,Topping 3");
		List<ProductStructure> Items = new ArrayList<ProductStructure>();
		for (int i = 0; i <productArray.size() ; i++) {
			ProductStructure item = new ProductStructure(productArray.get(i),ToppingsArray.get(i));
			   Items.add(item);
			 }
		      ProductsAdapter adapter = new ProductsAdapter(fa,Items);	
		
		      products.setAdapter(adapter);	
		      
		      products.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent ToppingActivity=new Intent("com.foodpalette_baker.TOPPINGACTIVITY");
					ToppingActivity.putExtra("Product", productArray.get(position));
					startActivity(ToppingActivity);
				}
		    	  
		      });
		      
		      getActivity().setTitle("Product Manager");
		      
		      return activityView;
	}
}
