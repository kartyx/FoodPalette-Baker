package com.foodpalette_baker;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ToppingActivity extends ActionBarActivity{
private LinearLayout hidden;
private TextView ProductName;
private String name;
List<EditText> newEditText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		newEditText=new ArrayList<EditText>();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topping);
		hidden=(LinearLayout)findViewById(R.id.editText);
		ProductName=(TextView)findViewById(R.id.ProductName);
		name=getIntent().getStringExtra("ProductName");
		ProductName.setText(name);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		  getSupportActionBar().setHomeButtonEnabled(true);
		  getSupportActionBar().setTitle("Add Toppings");
	}
public void AddTopping(View v)
{
EditText editText = new EditText(this);
newEditText.add(editText);
hidden.addView(newEditText.get(newEditText.size()-1));
}

public void OnDone(View v)
{
	String[] toppings=new String[newEditText.size()];
for(int i=0;i<newEditText.size();i++)	
{
	toppings[i]=newEditText.get(i).getText().toString();
}
hidden.removeAllViews();
for(int i=0;i<toppings.length;i++)	
{
	TextView textView = new TextView(this);
	textView.setText(toppings[i]);
	textView.setTextSize(30);
	textView.setGravity(Gravity.CENTER);
	hidden.addView(textView);
	
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

