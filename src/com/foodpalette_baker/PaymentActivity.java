package com.foodpalette_baker;

import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PaymentActivity extends MainActivity{
	 private static final String[] CONTENT = new String[] { "Pending", "All"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          framed = (FrameLayout)findViewById(R.id.activity_frame);
		  LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  defaultView = layoutInflater.inflate(R.layout.activity_payment, null,false);
		  framed.removeAllViews();
		  framed.addView(defaultView);
        FragmentPagerAdapter adapter = new OrderAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        
	      
        final float density = getResources().getDisplayMetrics().density;
        indicator.setBackgroundColor(0xFF4F4F4F);
        indicator.setFooterColor(0xFFFFFFFF);
        indicator.setFooterLineHeight(2 * density); 
        indicator.setFooterIndicatorHeight(5 * density); 
        indicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);
        indicator.setTextColor(0xAAFFFFFF);
        indicator.setSelectedColor(0xFFFFFFFF);
        indicator.setSelectedBold(true);

	      getSupportActionBar().setTitle("Payment Tracker");		
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
    			return new PendingPaymentFragment();
    		case 1:
    			return new PaymentHistoryFragment();
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
