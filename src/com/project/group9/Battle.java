package com.project.group9;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Battle extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);
		findViewById(R.id.battle).setOnClickListener(
		        new View.OnClickListener() {
		        	@Override
		            public void onClick(View v) {
		        		Intent intent = new Intent(getApplicationContext(), BattleInfo.class);
		                startActivity(intent);
		        	}
		        });
		//TextView image = (TextView) this.findViewById(R.id.char1);	
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle, menu);
		return true;
	}

}
