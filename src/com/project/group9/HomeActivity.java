package com.project.group9;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.savagelook.android.UrlJsonAsyncTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_home); 
  
        /** 
         * Creating all buttons instances 
         * */
        // World Map button 
        Button button_world_map = (Button) findViewById(R.id.world_map_button); 
  
        // View Stats button 
        Button button_view_stats = (Button) findViewById(R.id.game_stats_button); 
  
        // View Players button 
        Button button_view_players = (Button) findViewById(R.id.view_players_button); 
  
        // Edit Profile button 
        Button button_edit_profile = (Button) findViewById(R.id.edit_profile_button); 
  
        // View Gangs button 
        Button button_view_gangs = (Button) findViewById(R.id.view_gangs_button); 
  
        // Battle button
        Button button_battle = (Button) findViewById(R.id.battle_button);
        
        // Inbox button 
        Button button_inbox = (Button) findViewById(R.id.inbox_button); 
  
        // Logout button 
        Button button_logout = (Button) findViewById(R.id.log_out_button); 
  
        /** 
         * Handling all button click events 
         * */
  
        // Listening to World Map button click 
        button_world_map.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
                // Launching World Map Screen 
                Intent i = new Intent(getApplicationContext(), WorldMap.class); 
                startActivity(i); 
            } 
        }); 
  
        // Listening View Stats button click 
        button_view_stats.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
                // Launching View Stats Screen 
                Intent i = new Intent(getApplicationContext(), ViewStats.class); 
                startActivity(i); 
            } 
        }); 
  
        // Listening to View Players button click 
        button_view_players.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
                // Launching View Players Screen 
                Intent i = new Intent(getApplicationContext(), ViewPlayers.class); 
                startActivity(i); 
            } 
        }); 
  
        // Listening to Edit Profile button click 
        button_edit_profile.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
                // Launching Profile Screen 
                Intent i = new Intent(getApplicationContext(), Profile.class); 
                startActivity(i); 
            } 
        }); 
        
     // Listening View Gangs button click 
        button_view_gangs.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
                // Launching SideMissions Screen 
                Intent i = new Intent(getApplicationContext(), ViewGang.class); 
                startActivity(i); 
            } 
        }); 
        
        button_battle.setOnClickListener(new View.OnClickListener() { 
        	  
            @Override
            public void onClick(View view) { 
                // Launching battle Screen 
                Intent i = new Intent(getApplicationContext(), Battle.class); 
                startActivity(i); 
            } 
        }); 
        
     // Listening to Inbox button click 
        button_inbox.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
                // Launching Inbox Activity Screen 
            	Intent i = new Intent(getApplicationContext(), InboxActivity.class); 
                startActivity(i); 
            } 
          }); 
            
        // Listening to Logout button click 
        button_logout.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
                // Launching Login Activity Screen 
            	Intent intent = new Intent(Intent.ACTION_MAIN);
            	intent.addCategory(Intent.CATEGORY_HOME);
            	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	startActivity(intent);
            	finish();
            } 
        }); 
    } 


	@Override
	public void onResume() {
	    super.onResume();
	    
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.home, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_refresh:
	           
	            return true;
	        case R.id.action_back:
	        	Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
	        	startActivityForResult(intent, 0);
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
