package com.foodpalette_baker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
	}
public void login(View v)
{
	Intent MainActivity=new Intent("com.foodpalette_baker.MAINACTIVITY");
	startActivity(MainActivity);
}
}
