package com.project.group9;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class InboxActivity extends Activity {

static String[] items = new String[] {"Inbox", "Drafts", "Sent", "OutBox", "Trash"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inbox);
		
		ListView listView = (ListView) findViewById(R.id.listView);
              
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                  R.layout.list_layout, items);
        
        listView.setAdapter(adapter);
    }


	public void compose(View button) {
		Intent i = new Intent(getApplicationContext(), ComposeActivity.class); 
        startActivity(i); 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inbox, menu);
		return true;
	}
	   @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK) {
               Intent i = new Intent(InboxActivity.this, HomeActivity.class); 
               startActivity(i); 
	            return true;
	        }
	        return super.onKeyDown(keyCode, event);
	    }
}

