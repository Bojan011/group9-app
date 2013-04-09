package com.project.group9;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class CharacterBuild extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_build);
		
		findViewById(R.id.addPicture).setOnClickListener(
		        new View.OnClickListener() {
		        	@Override
		            public void onClick(View v) {
		        		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		        		photoPickerIntent.setType("image/*");
		        		startActivityForResult(photoPickerIntent, 1);
		        	}
		        });
		
		findViewById(R.id.questMain).setOnClickListener(
		        new View.OnClickListener() {
		        	@Override
		            public void onClick(View v) {
		        		Intent intent = new Intent(getApplicationContext(), CharacterStatsInitialize.class);
		                startActivity(intent);
		        	}
		        });
	}
	
	protected void onActivityResult(int requestCode, int resultCode,
	        Intent intent) {
	    super.onActivityResult(requestCode, resultCode, intent);
	  
	    if (resultCode == RESULT_OK) {
	        Uri photoUri = intent.getData();

	        if (photoUri != null) {
	            try {
	            	ImageView your_imgv = (ImageView) findViewById(R.id.addPicture);
	                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this
	                    .getContentResolver(), photoUri);
	                
					your_imgv.setImageBitmap(bitmap);
					your_imgv.getLayoutParams().height = 150;
					your_imgv.getLayoutParams().width = 150;
	                String profilePicPath = photoUri.toString();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.character_build, menu);
		return true;
	}
	


}
