package com.foodpalette_baker;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;

public class WelcomeActivity extends Activity {
static SharedPreferences login;
static String username,session_key;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		Thread t=new Thread()
		{
			public void run()
			{
				try{
					sleep(3000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
				login=getSharedPreferences("LOGINPREFERS",Context.MODE_PRIVATE);
				if(login.contains("session_key"))
				{
					username=login.getString("username", null);
					session_key=login.getString("session_key",null);
					Log.d("sssn",session_key);
					Intent MainActivity= new Intent("com.foodpalette_baker.MAINACTIVITY");
					WelcomeActivity.this.finish();
					startActivity(MainActivity);
				}
				else
				{
					Intent mainActivity=new Intent("com.foodpalette_baker.LOGINACTIVITY");
					WelcomeActivity.this.finish();
					startActivity(mainActivity);
				}
				}
		}
	};
	t.start();

	}

}
