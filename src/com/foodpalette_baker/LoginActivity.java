package com.foodpalette_baker;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
EditText username,password;
private String user,pass;
public String jsonResponse;
public JSONObject jObject;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		username=(EditText)findViewById(R.id.username);
		password=(EditText)findViewById(R.id.password);	
		
	}
public void login(View v) 
{
	user=username.getText().toString();
	pass=password.getText().toString();
	if(user!=null && pass!=null && user!="" && pass!="")
	{
		//       Intent MainActivity= new Intent("com.foodpalette_baker.MAINACTIVITY");
		//LoginActivity.this.finish();
		//startActivity(MainActivity);
		ConnectionDetector detector = new ConnectionDetector(this);
		if(detector.isConnectingToInternet())
		{				new Login().execute();
			/*Intent MainActivity= new Intent("com.foodpalette_baker.MAINACTIVITY");
			LoginActivity.this.finish();
			startActivity(MainActivity);*/

		}
		else
		{
			Toast.makeText(getApplicationContext(), "Not Connected to the Internet", Toast.LENGTH_SHORT).show();
		}
		
	}
}

class Login extends AsyncTask<Void,Void,Void>{
	private String salt="123";
	protected void onPreExecute()
	{
	super.onPreExecute();	
	}
	@Override
	protected Void doInBackground(Void... params) {

		HttpClient httpclient = new DefaultHttpClient();
		String url=WelcomeActivity.host+"/LoginAPI.php";
	    HttpPost httppost = new HttpPost(url);

	    try {
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        String hash=generateHash(pass+salt);
	        Log.d("hash", hash);
	        nameValuePairs.add(new BasicNameValuePair("tag","login"));
	        nameValuePairs.add(new BasicNameValuePair("username", user));
	        nameValuePairs.add(new BasicNameValuePair("password",pass));	
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
		Log.v("return",jsonResponse);
			try {
				jObject=new JSONObject(jsonResponse);
			
			boolean error=jObject.optBoolean("error");
			if(error)
			{
				Toast.makeText(getBaseContext(), "Wrong UserName or Password. Try Again!!", 2000).show();
			}
			else
			{
				WelcomeActivity.username=user;
				WelcomeActivity.session_key=jObject.optString("SessionKey");
				WelcomeActivity.login.edit().putString("username", user).commit();
				WelcomeActivity.login.edit().putString("session_key",WelcomeActivity.session_key).commit();
				
				Toast.makeText(getBaseContext(), "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
				Intent MainActivity= new Intent("com.foodpalette_baker.MAINACTIVITY");
				LoginActivity.this.finish();
				startActivity(MainActivity);
			}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			catch(NullPointerException e)
			{
				e.printStackTrace();
			}
			

		  
}
}
public String generateHash(String toHash) {
    MessageDigest md = null;
    byte[] hash = null;
    try {
        md = MessageDigest.getInstance("SHA-512");
        hash = md.digest(toHash.getBytes("UTF-8"));
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    return convertToHex(hash);
}
 
private String convertToHex(byte[] raw) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < raw.length; i++) {
        sb.append(Integer.toString((raw[i] & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
}
}
