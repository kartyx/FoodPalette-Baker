package com.foodpalette_baker;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomeActivity extends Fragment{
	private ListView orderslist;
	private List<String> priceArray,productArray;
	private View activityView;
	private FragmentActivity fa;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		activityView = inflater.inflate(R.layout.activity_home, container,false);
		orderslist=(ListView)activityView.findViewById(R.id.orderslist);
		fa=(FragmentActivity)getActivity();
		productArray=new ArrayList<String>();
		productArray.add("Product 1");
		productArray.add("Product 2");
		productArray.add("Product 3");
		productArray.add("Product 4");
		priceArray=new ArrayList<String>();
		priceArray.add("Rs.450");
		priceArray.add("Rs.500");
		priceArray.add("RS.600");
		priceArray.add("rS.750");
	
	List<OrderStructure> Items = new ArrayList<OrderStructure>();
		for (int i = 0; i <priceArray.size() ; i++) {
			OrderStructure item = new OrderStructure(productArray.get(i),priceArray.get(i),null,null);
			   Items.add(item);
			 }
		      OrdersAdapter adapter = new OrdersAdapter(fa,Items);	
		
		      orderslist.setAdapter(adapter);	

		return activityView;
	}
		

}
