package com.project.group9;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ViewPlayers extends ListActivity {
	
	static final String[] players = 
            new String[] { "Andrew", "Dave", "Jamie", "Nick"};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new PlayerArrayAdapter(this, players));
	
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

	//get selected items
	String selectedValue = (String) getListAdapter().getItem(position);
	Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_players, menu);
		return true;
	}
}

