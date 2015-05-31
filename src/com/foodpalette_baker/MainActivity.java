package com.foodpalette_baker;

import java.util.ArrayList;
import java.util.List;

import com.foodpalette_baker.OrdersActivity.OrderAdapter;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
private LinearLayout orders,products,payment,reviews;
private static final String[] CONTENT = new String[] { "Manage Products","Home", "Pending Orders"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_main);

        FragmentPagerAdapter adapter = new MainAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);

	}
		
	 class MainAdapter extends FragmentPagerAdapter {

			public MainAdapter(FragmentManager fm) {
	            super(fm);
	        }

	        @Override
	        public Fragment getItem(int position) {
	        	switch(position)
     		{
     		case 0:
     			return new ProductsActivity();
     		case 1:
     			return new HomeActivity();
     		case 2:
     			return new PendingActivity();
     		}
             return null;		        
         }

	        @Override
	        public CharSequence getPageTitle(int position) {
	            return CONTENT[position % CONTENT.length].toUpperCase();
	        }

	        @Override
	        public int getCount() {
	            return CONTENT.length;
	        }
	    }

}
