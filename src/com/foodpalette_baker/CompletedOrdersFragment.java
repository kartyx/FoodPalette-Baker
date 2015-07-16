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
import android.widget.Toast;

public class CompletedOrdersFragment extends Fragment {
	private ListView orderslist;
	private View activityView;
	private List<String> priceArray,productArray;
	private FragmentActivity fa;
	public String[] orderid,productid,deliverydate,customerusername,orderdescription,productname,productimage,productprice,toppingname;
	private List<String> deliveryDateArray;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		activityView = inflater.inflate(R.layout.activity_all, container,false);
		fa=(FragmentActivity)getActivity();
		orderslist=(ListView)activityView.findViewById(R.id.orders);

		new GetCompletedOrders().execute();
		return activityView;
		
	}
	public class GetCompletedOrders extends AsyncTask<Void,Void,Void>
	{

		private String jsonResponse;

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			HttpClient httpclient = new DefaultHttpClient();
			String url=WelcomeActivity.host+"/OrdersApi.php";
		    HttpPost httppost = new HttpPost(url);

		    try {
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("tag","Completed"));
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
	public void parseResponse(String response) {
		JSONArray ordersArray=null;
		// TODO Auto-generated method stub
		Log.v("return",response);
		try{
		JSONObject jObject = new JSONObject(response);
		
		if(!jObject.optBoolean("error"))
		{
		
			ordersArray=new JSONArray(jObject.optString("orderlist"));
			orderid=new String[ordersArray.length()];
			productid=new String[ordersArray.length()];
			deliverydate=new String[ordersArray.length()];
			customerusername=new String[ordersArray.length()];
			orderdescription=new String[ordersArray.length()];
			productname=new String[ordersArray.length()];
			productprice=new String[ordersArray.length()];
			productimage=new String[ordersArray.length()];
			toppingname=new String[ordersArray.length()];
			for(int i=0;i<ordersArray.length();i++)
			{
				JSONObject c = ordersArray.getJSONObject(i);
				orderid[i]=c.getString("orderid").toString();
				productid[i]=c.getString("productid").toString();
				deliverydate[i]=c.getString("deliverydate").toString();
				customerusername[i]=c.getString("customerusername").toString();
				orderdescription[i]=c.getString("orderdescription").toString();
				productname[i]=c.getString("productname").toString();
				productprice[i]=c.getString("productprice").toString();
				productimage[i]=c.getString("productimage").toString();
				toppingname[i]=c.getString("toppingname").toString();		
			}	
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
		List<OrderStructure> Items = new ArrayList<OrderStructure>();
		for (int i = 0; i <ordersArray.length() ; i++) {
			OrderStructure item = new OrderStructure(productname[i],productprice[i],toppingname[i],orderdescription[i],productimage[i]);
			   Items.add(item);
			 }
		      OrdersAdapter adapter = new OrdersAdapter(fa,Items);	
		
		      orderslist.setAdapter(adapter);	
	}

}