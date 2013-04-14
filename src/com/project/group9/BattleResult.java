package com.project.group9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
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
import android.widget.TextView;
import android.widget.Toast;

public class BattleResult extends Activity {
	private final static String USER_ENDPOINT_URL = "http://cryptic-hollows-1268.herokuapp.com/admin/users.json";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle_result);
		
		
		Bundle data = getIntent().getExtras();
		
		String user_id ="2";
		//get only if we have userid or 
		//set leave the default id

		if (data != null && data.size() > 0){
			user_id = String.valueOf(data.get("id"));
		}
		
		BattleTask battleTask = new BattleTask(BattleResult.this,user_id);
		
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
		private String url = "http://cryptic-hollows-1268.herokuapp.com/battle/";
		
	    public BattleTask(Context context,String userId) {
	        super(context);
	        this.url += LoginActivity.USER_ID + "/";
	        //set id
	        this.url+= userId;
	        
	        Log.i(ViewPlayers.class.getName(), this.url);
	    }
	
	    @Override
	    protected JSONObject doInBackground(String... urls) {
	    	StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet(this.url);
		    
	        HttpResponse response1 = null;
	        JSONObject json = new JSONObject();
	
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

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("ClientProtocol", "" + e);
            }
	
	        return json;
	    }
	
	    @Override
	    protected void onPostExecute(JSONObject json) {
	        try {
	        	Log.i(ViewPlayers.class.getName(), json.toString());
	        	 JSONArray jsonArray = json.getJSONArray("a");

	        	 for (int i = 0; i < jsonArray.length(); i++) {
			        JSONObject jsonObject = jsonArray.getJSONObject(i);
			        Log.i(ViewPlayers.class.getName(), jsonObject.toString() + " R: " + String.valueOf(i));
			        
			        switch(i){
			        case 0:
			        	TextView oppNameText = (TextView) findViewById(R.id.roundLabel1TextView);
			        	oppNameText.setText("Round 1 ");

			        	TextView opp0NameText = (TextView) findViewById(R.id.round1TextView);
			        	opp0NameText.setText(" Won: "  + String.valueOf(jsonObject.get("won_player_name")));
			        	break;

			        case 1:
			        	TextView opp1NameText = (TextView) findViewById(R.id.roundLabel2TextView);
			        	opp1NameText.setText("Round 2" );

			        	TextView opp11NameText = (TextView) findViewById(R.id.round2TextView);
			        	opp11NameText.setText("Won: "  + String.valueOf(jsonObject.get("won_player_name")));
			        	break;
			        	
			        case 2:
			        	TextView opp2NameText = (TextView) findViewById(R.id.roundLabel3TextView);
			        	opp2NameText.setText("Round 3 ");

			        	TextView opp22NameText = (TextView) findViewById(R.id.round3TextView);
			        	opp22NameText.setText("Won: "  + String.valueOf(jsonObject.get("won_player_name")));

			        	break;

			        case 3:
			        	TextView opp3NameText = (TextView) findViewById(R.id.roundLabel4TextView);
			        	opp3NameText.setText("Round 4" );

			        	TextView opp33NameText = (TextView) findViewById(R.id.round4TextView);
			        	opp33NameText.setText("Won: "  + String.valueOf(jsonObject.get("won_player_name")));
			        	break;			        	

			        case 4:
			        	TextView opp4NameText = (TextView) findViewById(R.id.roundLabel5TextView);
			        	opp4NameText.setText("Round 5" );

			        	TextView opp44NameText = (TextView) findViewById(R.id.round5TextView);
			        	opp44NameText.setText("Won: "  + String.valueOf(jsonObject.get("won_player_name")));
			        	break;			        	
			        }


			      }
			      
		            Toast.makeText(context, json.getString("info"), Toast.LENGTH_LONG).show();
			    } catch (Exception e) {
			      e.printStackTrace();
			    }	
	        finally {
	            super.onPostExecute(json);
	        }
	    }
	}	
}
