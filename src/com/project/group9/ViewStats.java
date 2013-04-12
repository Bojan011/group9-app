package com.project.group9;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ViewStats extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_stats);
        Button contMain = (Button) findViewById(R.id.statsMain);
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
		getMenuInflater().inflate(R.menu.view_stats, menu);
		return true;
	}
	   @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK) {
                Intent i = new Intent(ViewStats.this, HomeActivity.class); 
                startActivity(i); 
	            return true;
	        }
	        return super.onKeyDown(keyCode, event);
	    }
}
