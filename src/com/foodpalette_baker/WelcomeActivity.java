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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
static SharedPreferences login;
static String username,session_key;
static String host="http://www.thebigstudio.com/demos/foodpalettesample/Api";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		login=getSharedPreferences("LOGINPREFERS",Context.MODE_PRIVATE);
		if(login.contains("session_key"))
		{
			username=login.getString("username", null);
			session_key=login.getString("session_key",null);
			ConnectionDetector detector=new ConnectionDetector(this);
			if(detector.isConnectingToInternet())
			{
				Log.d("Internet", "Available");
				new  CheckSession().execute();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "No Internet Connection!!", Toast.LENGTH_SHORT).show();
				Intent mainActivity=new Intent("com.foodpalette_baker.LOGINACTIVITY");
				WelcomeActivity.this.finish();
				startActivity(mainActivity);
			}
			
		}
		else
		{
			Log.d("No", "Session Key");
			Intent mainActivity=new Intent("com.foodpalette_baker.LOGINACTIVITY");
			WelcomeActivity.this.finish();
			startActivity(mainActivity);
		}
	}
class CheckSession extends AsyncTask<Void,Void,Void>
{

	private String jsonResponse;

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		HttpClient httpclient = new DefaultHttpClient();
		String url=host+"/CheckSessionApi.php";
	    HttpPost httppost = new HttpPost(url);

	    try {
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("tag","CheckSessionApi"));
	        nameValuePairs.add(new BasicNameValuePair("username", WelcomeActivity.username));
	        nameValuePairs.add(new BasicNameValuePair("sessionkey",WelcomeActivity.session_key));	
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost);
	        jsonResponse=Response.ReadJSON(response);
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		
		if(jsonResponse.equalsIgnoreCase("yes"))
		{
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

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		ProgressDialog pd = new ProgressDialog(WelcomeActivity.this);
	       pd.setMessage("loading");
	       pd.show();
	}
	 
}
}
