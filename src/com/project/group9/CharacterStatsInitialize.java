package com.project.group9;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/*
 * Called when user first creates character.
 */
public class CharacterStatsInitialize extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_stats_initialize);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.character_stats_initialize, menu);
		return true;
	}

}
