package com.project.group9;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
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
import android.widget.TextView;
import android.widget.Toast;

/*
 * Called when user clicks on the opponent character.
 */
public class OpponentCharacterStats extends Activity {

	public String opponentId ="2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opp_character_stats);
		
		
		Intent intent = getIntent();
		
		Bundle data = intent.getExtras();
		this.opponentId = String.valueOf(data.get("id"));
				
		PlayerStats playerstats = new PlayerStats(this,this.opponentId);
		
        playerstats.setMessageLoading("Fetching Details...");
        playerstats.execute();


		//get the button
		Button contMain = (Button) findViewById(R.id.viewPlayers);
		
		contMain.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
                // Back to Profile 
                 finish();
            } 
        }); 

		//get the button
		Button battleMain = (Button) findViewById(R.id.beginBattleButton);

		 
		battleMain.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
                // Back to Profile 
        		//dev
        		Intent intent = new Intent(getApplicationContext(), BattleResult.class);
        		
        		intent.putExtra("id", OpponentCharacterStats.this.opponentId );
        	    
        		startActivity(intent);
        		//dev
        	
                 
            } 
        }); 

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.oponent_stats, menu);
		return true;
	}


	private class PlayerStats extends UrlJsonAsyncTask {
		private String url = "http://cryptic-hollows-1268.herokuapp.com/users.json?id=";
		
	    public PlayerStats(Context context,String userId) {
	        super(context);

	        //set id
	        this.url+= userId;
	        Log.i(ViewPlayers.class.getName(), this.url);
	    }
	
	    @Override
	    protected JSONObject doInBackground(String... urls) {
	    	StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet(url);
		    
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
	    	        
	    	        json = new JSONObject(builder.toString());
	                
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

		        	
		        	TextView oppNameText = (TextView) findViewById(R.id.oponentNameTextView);
		        	oppNameText.setText(String.valueOf(json.get("name")) + " Statistics");

		        	TextView aimText = (TextView) findViewById(R.id.aimTextView);
		        	aimText.setText(String.valueOf(json.get("aim")));

		        	TextView dogeText = (TextView) findViewById(R.id.dodgeTextView);
		        	dogeText.setText(String.valueOf(json.get("dodge")));

		        	TextView regenText = (TextView) findViewById(R.id.regenTextView);
		        	regenText.setText(String.valueOf(json.get("regen")));

		        	TextView levelText = (TextView) findViewById(R.id.levelTextView);
		        	levelText.setText(String.valueOf(json.get("level")));

		        	TextView healthText = (TextView) findViewById(R.id.healthTextView);
		        	healthText.setText(String.valueOf(json.get("health")));
		        	
			        Toast.makeText(context, json.getString("info"), Toast.LENGTH_LONG).show();
			    } catch (Exception e) {
			      e.printStackTrace();
			      //Toast.makeText(context, "Network Error Occured. Try again", Toast.LENGTH_LONG).show();
			    }	
	        finally {
	            super.onPostExecute(json);

	        }
	    }
	}	
}
