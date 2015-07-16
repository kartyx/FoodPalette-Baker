package com.foodpalette_baker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends ActionBarActivity {
private static final String[] CONTENT = new String[] { "Manage Products","Home", "Pending Orders"};
private DrawerLayout mDrawerLayout = null;
private ActionBarDrawerToggle mDrawerToggle = null;
static final Class[] classArray = new Class[] {com.foodpalette_baker.MainActivity.class, com.foodpalette_baker.OrdersActivity.class,com.foodpalette_baker.PaymentActivity.class,com.foodpalette_baker.SelectProductsActivity.class };
static final String[] names={"com.foodpalette_baker.MAINACTIVITY","com.foodpalette_baker.ORDERSACTIVITY","com.foodpalette_baker.PAYMENTACTIVITY","com.foodpalette_baker.SELECTPRODUCTSACTIVITY"};
List<NavBarItems> navBarItems;
ListView mylistview;
private LinearLayout navBarLayout;
FrameLayout framed;
View defaultView;
private String[] NavBarTitles;
private TypedArray NavBarImages;
private float lastTranslate = 0.0f;
static TextView TotalOrders;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_container);
		framed = (FrameLayout)findViewById(R.id.activity_frame);
		  LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  defaultView = layoutInflater.inflate(R.layout.activity_main, null,false);
		  framed.addView(defaultView);
		  TotalOrders=(TextView)findViewById(R.id.totalOrders);
		  
		  navBarLayout=(LinearLayout)findViewById(R.id.left_drawer);
	      mylistview = (ListView) findViewById(R.id.left_drawer_list);
		  mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		  
		  mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		  mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.drawable.ic_drawer,R.string.drawer_open, R.string.drawer_close) {
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public void onDrawerOpened(View view) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
				{
		              invalidateOptionsMenu();
				}
		          }
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public void onDrawerClosed(View view) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
			{
	              invalidateOptionsMenu();
			}
		              
		          }
           @TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public void onDrawerSlide(View drawerView, float slideOffset)
           {
               float moveFactor = (mylistview.getWidth() * slideOffset);

               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
               {
                   framed.setTranslationX(moveFactor);
                   // oa=ObjectAnimator.ofFloat(framed, "rotationY",-moveFactor);
                   //oa.start();
                   //framed.setRotationY(moveFactor);
                   //framed.setScaleX(-i++);
               }
               else
               {
            	   //ObjectAnimator oa=ObjectAnimator.ofFloat(framed, "rotationY",moveFactor);
                   TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                   anim.setDuration(0);
                   anim.setFillAfter(true);
                   //oa.start();
                   framed.startAnimation(anim);

                   lastTranslate = moveFactor;
               }
           }
		  };
		      
		  	  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		      getSupportActionBar().setHomeButtonEnabled(true);
		      getSupportActionBar().setTitle("FoodPalette");
		      mDrawerLayout.setDrawerListener(mDrawerToggle);
		      
		      navBarItems = new ArrayList<NavBarItems>();
		      
		      NavBarTitles = getResources().getStringArray(R.array.navBarItems);

		      NavBarImages = getResources().obtainTypedArray(R.array.navBarImages);

		      
		    
		      for (int i = 0; i < NavBarTitles.length; i++) {
		    	  NavBarItems item = new NavBarItems(NavBarTitles[i], NavBarImages.getResourceId(i, -1));
		    	  navBarItems.add(item);
		      }
		      
		      NavBarAdapter navBarAdapter = new NavBarAdapter(this, navBarItems);
		      mylistview.setAdapter(navBarAdapter);
		      NavBarImages.recycle();
		      mylistview.setOnItemClickListener(new DrawerItemClickListener());

        FragmentPagerAdapter adapter = new MainAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        
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
     			return new ProductsFragment();
     		case 1:
     			return new HomeFragment();
     		case 2:
     			return new PendingOrdersFragment();
     		}
             return null;		        
         }

	        @Override
	        public CharSequence getPageTitle(int position) {
	            return CONTENT[position % CONTENT.length];
	        }

	        @Override
	        public int getCount() {
	            return CONTENT.length;
	        }
	    }
	 
	 
	 
	 @Override
	 protected void onPostCreate(Bundle savedInstanceState) {
	     super.onPostCreate(savedInstanceState);
	     mDrawerToggle.syncState();
	 }

	 @Override
	 public void onConfigurationChanged(Configuration newConfig) {
	     super.onConfigurationChanged(newConfig);
	     mDrawerToggle.onConfigurationChanged(newConfig);
	 }
	 
	 @Override
	 public boolean onPrepareOptionsMenu(Menu menu) {
	     boolean drawerOpen = mDrawerLayout.isDrawerOpen(navBarLayout);

	     for (int index = 0; index < menu.size(); index++) {
	         MenuItem menuItem = menu.getItem(index);
	         if (menuItem != null) {
	             menuItem.setVisible(!drawerOpen);
	         }
	     }

	     return super.onPrepareOptionsMenu(menu);
	 }

	 @Override
	 public void onBackPressed(){
		 if(mDrawerLayout.isDrawerOpen(Gravity.LEFT))
		 {
			 mDrawerLayout.closeDrawer(Gravity.LEFT);
		 }
		 else
		 {
			 MainActivity.this.finish();
		 }
		
	 }

	 public void onWalletClicked(View v)
	 {
		Intent WalletActivity=new Intent("com.foodpalette_baker.WALLETACTIVITY");
		startActivity(WalletActivity);
	 }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	 	if (mDrawerToggle.onOptionsItemSelected(item)) {
	         return true;
	     }
	 	else
		return false;
	 }
	 private class DrawerItemClickListener implements ListView.OnItemClickListener{
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int position,
		   long id) {
			 mDrawerLayout.closeDrawers();
			
		  Intent i=new Intent(MainActivity.this,classArray[position]);
		  i.setAction(names[position]);
		  startActivity(i);
			 }
		 }
	
}
