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

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ProductsFragment extends Fragment {
	private ListView products;
	private View activityView;
	private FragmentActivity fa;
	private String[] productname,productimage,productprice,productdescription,Availability,NoOfBuys,toppings;
	static String[] productid;
	private String[][] toppingid,toppingname;
	private String[] AvailableToppings;
	static List<ProductStructure> Items;
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
			activityView = inflater.inflate(R.layout.activity_products, container,false);
			fa=(FragmentActivity)getActivity();
			products=(ListView)activityView.findViewById(R.id.productslist);
						
			new GetProducts().execute();
			
						      
			products.setFocusable(true);
			getActivity().setTitle("Product Manager");
			      
			      return activityView;
		}
	
public class GetProducts extends AsyncTask<Void,Void,Void>
{

	private String jsonResponse;

	@Override
	protected Void doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		HttpClient httpclient = new DefaultHttpClient();
		String url=WelcomeActivity.host+"/ManageProductsApi.php";
	    HttpPost httppost = new HttpPost(url);

	    try {
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("tag","GetProducts"));
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
	
	JSONArray Products = new JSONArray(jObject.optString("Products"));
	productid=new String[Products.length()];
	productname=new String[Products.length()];
	productimage=new String[Products.length()];
	productprice=new String[Products.length()];
	productdescription=new String[Products.length()];
	Availability=new String[Products.length()];
	NoOfBuys=new String[Products.length()];
	toppings=new String[Products.length()];
	AvailableToppings=new String[Products.length()];
	for(int each=0;each<Products.length();each++)
	{
		 AvailableToppings[each]="";
	}
	for(int i=0;i<Products.length();i++)
	{
	JSONObject Product=Products.getJSONObject(i);
	productid[i]=Product.optString("ProductId").toString();
	productname[i]=Product.optString("ProductName").toString();
	productimage[i]=Product.optString("ProductImage").toString();
	productprice[i]=Product.optString("ProductPrice").toString();
	productdescription[i]=Product.optString("ProductDescription").toString();
	Availability[i]=Product.optString("Availability").toString();
	NoOfBuys[i]=Product.optString("NoOfBuys").toString();
	toppings[i]=Product.optString("Toppings").toString();
	JSONArray Toppings = new JSONArray(toppings[i]);
	toppingid=new String[Products.length()][Toppings.length()];
	toppingname=new String[Products.length()][Toppings.length()];
	 
	for(int j=0;j<Toppings.length();j++)
	{
		JSONObject Topping = Toppings.getJSONObject(j);
		toppingid[i][j]= Topping.optString("ToppingId");
		toppingname[i][j]= Topping.optString("ToppingName");
		if(j==Toppings.length()-1)
			AvailableToppings[i]+=Topping.optString("ToppingName");
		else
			AvailableToppings[i]+=Topping.optString("ToppingName")+",";
	}
	 Items = new ArrayList<ProductStructure>();
	for (int k = 0; k <Products.length() ; k++) {
		ProductStructure item = new ProductStructure(productid[k],productimage[k],productname[k],productprice[k],productdescription[k],Availability[k],NoOfBuys[k],AvailableToppings[k]);
		   Items.add(item);
		 }
	      ProductsAdapter adapter = new ProductsAdapter(fa,Items);	
	
	      products.setAdapter(adapter);	
	      
	      products.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent ToppingActivity=new Intent("com.foodpalette_baker.TOPPINGACTIVITY");
				ToppingActivity.putExtra("ProductName", productname[position]);
				ToppingActivity.putExtra("ProductId", productid[position]);
				startActivity(ToppingActivity);
			}
	    	  
	      });
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
	
}
}
