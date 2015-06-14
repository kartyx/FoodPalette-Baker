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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HomeFragment  extends Fragment{
	private ListView orderslist;
	private View activityView;
	private FragmentActivity fa;
	private TextView BakerName,Rating,TotalProducts,TotalReviews;
	private ToggleButton availability;
	private RoundedImageView BakerImage;
	public String[] orderid,productid,deliverydate,customerusername,orderdescription,productname,productimage,productprice,toppingname;
	private JSONArray ordersArray;
	String available;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		activityView = inflater.inflate(R.layout.activity_home, container,false);
		orderslist=(ListView)activityView.findViewById(R.id.orderslist);
		fa=(FragmentActivity)getActivity();
		
		new GetBakerDetails().execute();
		  BakerName=(TextView)activityView.findViewById(R.id.bakerName);
	      Rating=(TextView)activityView.findViewById(R.id.bakerRating);
	      TotalProducts=(TextView)activityView.findViewById(R.id.totalProducts);
	      TotalReviews=(TextView)activityView.findViewById(R.id.totalReviews);
	      
	      availability=(ToggleButton)activityView.findViewById(R.id.availability);
	      BakerImage=(RoundedImageView)activityView.findViewById(R.id.bakerImage);
		  
	      availability.setOnClickListener(new OnClickListener(){
	    	  
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(availability.isChecked())
					{
						available="yes";
					}
					else
					{
						available="no";
					}
					new UpdateAvailability().execute();

				}
	    	  
	      });
	      
		return activityView;
	}
	
	class UpdateAvailability extends AsyncTask<Void,Void,Void>
	{

		private String result;
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient httpclient = new DefaultHttpClient();
			String url="http://www.thebigstudio.com/foodpalettesample/Api/HomeActivityApi.php";
		    HttpPost httppost = new HttpPost(url);

		    try {
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("tag","Availability"));
		        nameValuePairs.add(new BasicNameValuePair("username", WelcomeActivity.username));
		        nameValuePairs.add(new BasicNameValuePair("sessionkey",WelcomeActivity.session_key));
		        nameValuePairs.add(new BasicNameValuePair("availability",available));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost);
		        result=Response.ReadJSON(response);
		    } catch (ClientProtocolException e) {
		    	e.printStackTrace();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
			return null;
		}
		@Override
		protected void onPostExecute(Void rslt) {
			Log.d("result",result);
			if(result.equalsIgnoreCase("yes"))
				Toast.makeText(fa, "Availability Updated Successfully!", Toast.LENGTH_SHORT).show();
			else if(result.equalsIgnoreCase("no"))
			{
				Toast.makeText(fa, "Availability Not Updated !!", Toast.LENGTH_SHORT).show();
				//availability.setChecked(!availability.isChecked());
			}
			else
			{
				Toast.makeText(fa, "Something went wrong !!", Toast.LENGTH_SHORT).show();
			}
		}
	}
class GetBakerDetails extends AsyncTask<Void,Void,Void>
	{

		private String jsonResponse;

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient httpclient = new DefaultHttpClient();
			String url="http://www.thebigstudio.com/foodpalettesample/Api/HomeActivityApi.php";
		    HttpPost httppost = new HttpPost(url);

		    try {
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("tag","baker_details"));
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
public void parseResponse(String jsonResponse)
{
	try
	{
	Log.v("return",jsonResponse);
	JSONObject jObject = new JSONObject(jsonResponse);
	
	if(!jObject.optBoolean("error"))
	{
	BakerName.setText(jObject.optString("FirstName")+" "+jObject.optString("LastName"));
	Rating.setText(jObject.optString("Rating").toString());
	TotalProducts.setText(jObject.optString("TotalProduct").toString());
	TotalReviews.setText(jObject.optString("TotalReview").toString());
	MainActivity.TotalOrders.setText(jObject.optString("TotalOrder").toString());
	String ImageURL=jObject.optString("Image");
	if(jObject.optString("Availability").equalsIgnoreCase("yes"))
		availability.setChecked(true);
	else
		availability.setChecked(false);
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
