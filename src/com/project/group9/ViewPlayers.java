package com.project.group9;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
/*import android.content.Intent;
import android.content.SharedPreferences;*/
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.savagelook.android.UrlJsonAsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ViewPlayers extends ListActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		PlayerTask playerTask = new PlayerTask(ViewPlayers.this);
        playerTask.setMessageLoading("Fetching Players...");
        playerTask.execute();
		
	
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

	//get selected items
	String selectedValue = (String) getListAdapter().getItem(position);
	Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

	}

	
	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.view_players, menu);
	//	return true;
	//}
	
	
	private class PlayerTask extends UrlJsonAsyncTask {
	    public PlayerTask(Context context) {
	        super(context);
	    }

	    @Override
	    protected JSONObject doInBackground(String... urls) {
	    	StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet("http://cryptic-hollows-1268.herokuapp.com/users.json");
		    
	        HttpResponse response1 = null;
	        JSONObject json = new JSONObject();

	        try {
	            try {
	                // setup the returned values in case
	                // something goes wrong
	                
	                // add the user email and password to
	                // the params
	                response1 = client.execute(httpGet);
	                StatusLine statusLine = response1.getStatusLine();
	    		    int statusCode = statusLine.getStatusCode();
	    		    if(statusCode == 200)
	    		    {
	                HttpEntity entity = response1.getEntity();
	    	        InputStream content = entity.getContent();
	    	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	    	        String line;
	    	        while ((line = reader.readLine()) != null) {
	    	          builder.append(line);
	    	        }
	    	        JSONArray arr = new JSONArray(builder.toString());
	                json = json.put("a", arr);
	    		    }

	            } catch (HttpResponseException e) {
	                e.printStackTrace();
	                Log.e("ClientProtocol", "" + e);
	                json.put("info", "Unable to Fetch Players");
	            } catch (IOException e) {
	                e.printStackTrace();
	                Log.e("IO", "" + e);
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	            Log.e("JSON", "" + e);
	        }

	        return json;
	    }

	    @Override
	    protected void onPostExecute(JSONObject json) {
	        try {
	        	Log.i(ViewPlayers.class.getName(), json.toString());
	        	 JSONArray jsonArray = json.getJSONArray("a");
	        	 String[] players =new String[jsonArray.length()]; 
			      Log.i(ViewPlayers.class.getName(),
			          "Number of entries " + jsonArray.length());
			      for (int i = 0; i < jsonArray.length(); i++) {
			        JSONObject jsonObject = jsonArray.getJSONObject(i);
			        players[i]= jsonObject.getString("name");
			        Log.i(ViewPlayers.class.getName(), jsonObject.getString("name"));
			      }
			      
			      setListAdapter(new PlayerArrayAdapter(context, players));
		            
		            Toast.makeText(context, json.getString("info"), Toast.LENGTH_LONG).show();
			    } catch (Exception e) {
			      e.printStackTrace();
			    }	
	        finally {
	            super.onPostExecute(json);
	        }
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.profile, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_refresh:
	            return true;
	        case R.id.action_back:
	        	Intent intent = new Intent(ViewPlayers.this, HomeActivity.class);
	        	startActivityForResult(intent, 0);
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onBackPressed() {
		 Toast.makeText(getBaseContext(),"Please use Back from Menu" , Toast.LENGTH_LONG).show();
	}
}

