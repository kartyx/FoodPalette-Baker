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

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodpalette_baker.OrdersAdapter.ViewHolder;

public class ProductsAdapter extends BaseAdapter{
	 private static LayoutInflater mInflater;
	 String status;
	    private List<ProductStructure> items = new ArrayList<ProductStructure>();

		private Context context;
		protected Button b;
		protected String ChangeStatus;
	 
	    public ProductsAdapter(Context context, List<ProductStructure> items) {
	        mInflater = LayoutInflater.from(context);
	        this.items = items;
	        this.context=context;
	    }
	     public  ProductsAdapter(List<ProductStructure> items)
	      {		
	    	  this.items=items;
	      }
	    public int getCount() {
	        return items.size();
	    }
	 
	    public ProductStructure getItem(int position) {
	        return items.get(position);
	    }
	 
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	        final ViewHolder holder;
	        ProductStructure s = items.get(position);
	        status=s.getStatus();
	        ChangeStatus=status;
	        //Log.d(String.valueOf(position), s.getProductId());
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.products_list, null);
	            holder = new ViewHolder();
	            holder.image = (ImageView) convertView.findViewById(R.id.productImage);
	            holder.toppings=(TextView)convertView.findViewById(R.id.Toppings);
	            holder.product=(TextView)convertView.findViewById(R.id.ProductName);	    
	            holder.available=(TextView)convertView.findViewById(R.id.AvailableToppings);
	            holder.productstatus=(Button)convertView.findViewById(R.id.productstatus);
	            
	            convertView.setTag(holder);
	            
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        
	        
	        holder.toppings.setText(s.getAvailableToppings());
	        holder.product.setText(s.getProductName());
	        holder.available.setText("AvailableToppings");
	        holder.image.setImageResource(R.drawable.prod);
	        if(ChangeStatus.equalsIgnoreCase("no"))
		    {
		    	holder.productstatus.setText("ENABLE");
		    	holder.productstatus.setBackgroundColor(0xFF0EE9AF);
		    }
		    else
		    {
		    	holder.productstatus.setText("DISABLE");
		    	holder.productstatus.setBackgroundColor(0xFFE04A49);
		    }
	        b=holder.productstatus;
	        holder.productstatus.setOnClickListener(new OnClickListener(){
    	        
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.d("prevStatus", items.get(position).getStatus().toString());
				    if(items.get(position).getStatus().equalsIgnoreCase("no"))
				    {
				    	ChangeStatus="yes";	
				    	/*holder.productstatus.setText("ENABLE");
				    	holder.productstatus.setBackgroundColor(0xFF0EE9AF);*/
						//Toast.makeText(context, "Button "+position+" Clicked. Status: "+items.get(position).getStatus()+"Productid: "+items.get(position).getProductId(),Toast.LENGTH_SHORT).show();
				    }
				    else
				    {
				    	/*holder.productstatus.setText("DISABLE");
				    	holder.productstatus.setBackgroundColor(0xFFE04A49);*/
				    	ChangeStatus="no";
						//Toast.makeText(context, "Button "+position+" Clicked. Status: "+items.get(position).getStatus()+"Productid: "+items.get(position).getProductId(),Toast.LENGTH_SHORT).show();
				    }
			    	new ChangeProductStatus().execute(items.get(position).getProductId(),ChangeStatus,String.valueOf(position));
			    	//Log.d("status",position+" "+ChangeStatus);
				}
	        });
	        return convertView;
	    }
	 
	    static class ViewHolder {
	        ImageView image;
	        TextView product,toppings,available;
	        Button productstatus;
	        
	    }

		public void setContext(FragmentActivity activity) {
	        mInflater = LayoutInflater.from(activity);
		}
		
		class ChangeProductStatus extends AsyncTask<String,Void,Void>{
			private String jsonResponse;
			int pos;
			ProgressDialog dialog = new ProgressDialog(context);
            
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				dialog.setMessage("Please Wait...");
	            dialog.setIndeterminate(true);
	            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	            dialog.setCancelable(false);
	            dialog.show();  
			}
			@Override
			protected void onProgressUpdate(Void... values) {
				// TODO Auto-generated method stub
				super.onProgressUpdate(values);
				
				
			}
			@Override
			protected Void doInBackground(String... params) {
				pos=Integer.parseInt(params[2]);
				HttpClient httpclient = new DefaultHttpClient();
				String url=WelcomeActivity.host+"/ManageProductsApi.php";
			    HttpPost httppost = new HttpPost(url);

			    try {
			    	String productid=params[0];
			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			        nameValuePairs.add(new BasicNameValuePair("tag","SetStatus"));
			        nameValuePairs.add(new BasicNameValuePair("username", WelcomeActivity.username));
			        nameValuePairs.add(new BasicNameValuePair("sessionkey",WelcomeActivity.session_key));
			        nameValuePairs.add(new BasicNameValuePair("status",params[1]));
			        Log.d(params[0],params[1]);
			        nameValuePairs.add(new BasicNameValuePair("productid",productid));
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
					items.get(pos).setStatus(ChangeStatus);
					if(items.get(pos).getStatus().equalsIgnoreCase("no"))
					{
						Toast.makeText(context, "Product is Disabled !!",Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(context, "Product is Enabled !!",Toast.LENGTH_SHORT).show();
					}
					//Log.d("NewStatus", items.get(pos).getStatus().toString());
					updateResults();
				}
				else
				{
					Toast.makeText(context, "Product Status Not Changed !!",Toast.LENGTH_SHORT).show();
				}
				dialog.dismiss();
			}
			
			
			}

		public void updateResults() {
			// TODO Auto-generated method stub
			notifyDataSetChanged();
		}
}
