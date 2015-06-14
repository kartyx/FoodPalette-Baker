package com.foodpalette_baker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

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
public class WalletActivity extends ActionBarActivity{

	private ListView paymentHistoryList;
	private String[] productname,paymentamount,paymentdate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_payment);
		paymentHistoryList=(ListView)findViewById(R.id.paymenthistory);
		
		new GetPaymentHistoryList().execute();
		getSupportActionBar().setHomeButtonEnabled(true);
		
	}
	public class GetPaymentHistoryList extends AsyncTask<Void,Void,Void>
	{

		private String jsonResponse;

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			HttpClient httpclient = new DefaultHttpClient();
			String url="http://www.thebigstudio.com/foodpalettesample/Api/PaymentApi.php";
		    HttpPost httppost = new HttpPost(url);

		    try {
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("tag","GetPaymentHistory"));
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
			
			parseResponse(jsonResponse);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		 

	}

	public void parseResponse(String jsonResponse) {
		// TODO Auto-generated method stub
		Log.v("return",jsonResponse);
		try{
		JSONObject jObject = new JSONObject(jsonResponse);
		if(!jObject.optBoolean("error"))
		{
		
		JSONArray PaymentList = new JSONArray(jObject.optString("paymentlist"));
		productname=new String[PaymentList.length()];
		paymentamount=new String[PaymentList.length()];
		paymentdate=new String[PaymentList.length()];
		for(int i=0;i<PaymentList.length();i++)
		{
		JSONObject Payment=PaymentList.getJSONObject(i);
		productname[i]=Payment.getString("productname").toString();
		paymentamount[i]=Payment.getString("paymentamount").toString();
		paymentdate[i]=Payment.getString("paymentdate").toString();
		}
		List<HistoryPaymentStructure> Items = new ArrayList<HistoryPaymentStructure>();
		for (int i = 0; i <PaymentList.length() ; i++) {
			HistoryPaymentStructure item = new HistoryPaymentStructure(productname[i],paymentamount[i],paymentdate[i]);
			   Items.add(item);
			 }
		HistoryPaymentAdapter adapter = new HistoryPaymentAdapter(getBaseContext(),Items);	
		
		paymentHistoryList.setAdapter(adapter);	
		}
		else
		{
			Toast.makeText(getBaseContext(), "Something Went Wrong!!",Toast.LENGTH_SHORT).show();
		}
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}

}
