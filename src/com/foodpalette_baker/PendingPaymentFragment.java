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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.foodpalette_baker.PaymentHistoryFragment.GetPaymentHistoryList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PendingPaymentFragment extends Fragment {
private View activityView;
private FragmentActivity fa;
private ListView paymentPendingList;
public String[] productname,paymentamount;
private TextView TotalPending;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		activityView = inflater.inflate(R.layout.activity_pending_payment, container,false);
		fa=(FragmentActivity)getActivity();
		paymentPendingList=(ListView)activityView.findViewById(R.id.pendingpayment);
		TotalPending=(TextView)activityView.findViewById(R.id.TotalPending);
		new GetPendingPaymentList().execute();
		return activityView;
	}
	
	public class GetPendingPaymentList extends AsyncTask<Void,Void,Void>
	{

		private String jsonResponse;

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			HttpClient httpclient = new DefaultHttpClient();
			String url=WelcomeActivity.host+"/PaymentApi.php";
		    HttpPost httppost = new HttpPost(url);

		    try {
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("tag","GetPendingPayment"));
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
		String TotalPendingPayment=jObject.getString("pendingPayment").toString();
		TotalPending.setText(TotalPendingPayment);
		if(!jObject.optBoolean("error"))
		{
		
		JSONArray PaymentList = new JSONArray(jObject.optString("paymentlist"));
		productname=new String[PaymentList.length()];
		paymentamount=new String[PaymentList.length()];
		for(int i=0;i<PaymentList.length();i++)
		{
		JSONObject Product=PaymentList.getJSONObject(i);
		productname[i]=Product.getString("productname").toString();
		paymentamount[i]=Product.getString("paymentamount").toString();
		
		}
		List<PendingPaymentStructure> Items = new ArrayList<PendingPaymentStructure>();
		for (int i = 0; i <PaymentList.length() ; i++) {
			PendingPaymentStructure item = new PendingPaymentStructure(productname[i],paymentamount[i]);
			   Items.add(item);
			 }
		PendingPaymentAdapter adapter = new PendingPaymentAdapter(fa,Items);	
		
		paymentPendingList.setAdapter(adapter);	
		}
		else
		{
			Toast.makeText(fa, "Something Went Wrong!!",Toast.LENGTH_SHORT).show();
		}
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}