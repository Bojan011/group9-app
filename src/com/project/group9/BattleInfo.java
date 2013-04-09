package com.project.group9;

import java.io.IOException;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.savagelook.android.UrlJsonAsyncTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BattleInfo extends Activity {
	private final static String USER_ENDPOINT_URL = "http://cryptic-hollows-1268.herokuapp.com/admin/users.json";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle_info);
		BattleTask battleTask = new BattleTask(BattleInfo.this);
		battleTask.setMessageLoading("Battle in progress...");
        battleTask.execute(USER_ENDPOINT_URL);
        Button contMain = (Button) findViewById(R.id.gotoMain);
		contMain.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
                // Launching World Map Screen 
                Intent i = new Intent(getApplicationContext(), HomeActivity.class); 
                startActivity(i); 
            } 
        }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle_info, menu);
		return true;
	}
	
	private class BattleTask extends UrlJsonAsyncTask {
		public BattleTask(Context context) {
	        super(context);
	    }
		
		@Override
	    protected JSONObject doInBackground(String... urls) {
	        DefaultHttpClient client = new DefaultHttpClient();
	        //HttpPost post = new HttpPost(urls[0]);
	        HttpGet get = new HttpGet(urls[0]);
	        
	        //HttpPut to update
	        JSONObject holder = new JSONObject();
	        JSONObject userObj = new JSONObject();
	        String response = null;
	        JSONArray jsonArray = new JSONArray();
	        JSONObject json = new JSONObject();
	        int attack;
	        //String urlnew = "http://cryptic-hollows-1268.herokuapp.com/admin/users.json";
	        try {
	            try {
	            	 ResponseHandler<String> responseHandler = new BasicResponseHandler();
		             response = client.execute(get, responseHandler);
		             jsonArray = new JSONArray(response);
		             for (int i = 0 ; i < jsonArray.length(); i++ ) {
		            	  json = jsonArray.getJSONObject(i);
		            	  attack = json.getInt("attack");
		            	  attack = attack + 1;
		            	  HttpPut put = new HttpPut("http://cryptic-hollows-1268.herokuapp.com/admin/users/"+json.getString("id")
		            			  +".json");
		            	  //update database.
		            	  userObj.put("attack", attack);
		            	  json.put("user", userObj);
		            	  StringEntity se = new StringEntity(json.toString());
			              put.setEntity(se);

			                // setup the request headers
			              put.setHeader("Accept", "application/json");
			              put.setHeader("Content-Type", "application/json");

			              responseHandler = new BasicResponseHandler();
			              response = client.execute(put, responseHandler);
			              json = new JSONObject(response);
		             }
	            } catch (HttpResponseException e) {
	                e.printStackTrace();
	                Log.e("ClientProtocol", "" + e);
	                json.put("info", "Something went wrong. Retry!");
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
				
			} catch (Exception e) {
				Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
			} finally {
				super.onPostExecute(json);
			}
		}
		
	}
}
