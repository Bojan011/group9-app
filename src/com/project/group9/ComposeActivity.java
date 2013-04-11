package com.project.group9;


import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ComposeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
/*		Button contMain = (Button) findViewById(R.id.loginButton);
		contMain.setOnClickListener(new View.OnClickListener() { 
  
            @Override
            public void onClick(View view) { 
            	
                // Launching World Map Screen 
                Intent i = new Intent(getApplicationContext(), HomeActivity.class); 
                startActivity(i); 
 
            } 
        });*/ 
	}
	public void sendmail(View button) {
		new sendmailasynch().execute();
	}
 class sendmailasynch extends AsyncTask
 {
		ProgressDialog pDialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			 pDialog = new ProgressDialog(ComposeActivity.this);
	
		        pDialog.setMessage("Sending mail. Please wait...");
		        pDialog.setIndeterminate(false);
		        pDialog.setCancelable(true);
		        pDialog.show();	
		          
	     
		}
	@Override
	protected Object doInBackground(Object... params) {
        try {   
            GMailSender sender = new GMailSender("yourmailid@gmail.com", "password");
            sender.sendMail("This is Subject",   
                    "This is Body",   
                    "sender@gmail.com",   
                    "receipient@gmail.com");   
        } catch (Exception e) {   
            Log.e("SendMail", e.getMessage(), e);   
        } 
		return null;
	}
	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pDialog.dismiss();

			Toast.makeText(getApplicationContext(), "Message send", 4000).show();

		
	}
 }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}

}
