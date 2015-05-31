package com.foodpalette_baker;



import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class OrdersActivity extends ActionBarActivity {

		 private static final String[] CONTENT = new String[] { "Pending", "All"};

		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_orders);

		        FragmentPagerAdapter adapter = new OrderAdapter(getSupportFragmentManager());

		        ViewPager pager = (ViewPager)findViewById(R.id.pager);
		        pager.setAdapter(adapter);

		        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
		        indicator.setViewPager(pager);
		        
		        

			    	
		        
		        final float density = getResources().getDisplayMetrics().density;
		        indicator.setBackgroundColor(0xFFFFFFFF);
		        indicator.setFooterColor(0xFF0EE9AF);
		        indicator.setFooterLineHeight(2 * density); 
		        indicator.setFooterIndicatorHeight(5 * density); 
		        indicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);
		        indicator.setTextColor(0xAA000000);
		        indicator.setSelectedColor(0xFF000000);
		        indicator.setSelectedBold(true);
		        
		        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			    getSupportActionBar().setHomeButtonEnabled(true);
			    getSupportActionBar().setTitle("Orders");
			   
		    }

		    class OrderAdapter extends FragmentPagerAdapter {
		        public OrderAdapter(FragmentManager fm) {
		            super(fm);
		        }

		        @Override
		        public Fragment getItem(int position) {
		        	switch(position)
	        		{
	        		case 0:
	        			return new PendingActivity();
	        		case 1:
	        			return new AllActivity();
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

