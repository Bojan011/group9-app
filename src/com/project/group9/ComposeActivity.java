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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComposeActivity extends Activity {
String to,body;
EditText userEmail,composemsg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		userEmail=(EditText)findViewById(R.id.userEmail);
		composemsg=(EditText)findViewById(R.id.userPassword);
 
	}
	public void sendmail(View button) {
		to=userEmail.getText().toString();
		body=composemsg.getText().toString();
		new sendmailasynch().execute();
	}
 class sendmailasynch extends AsyncTask
 {
	 Boolean sendmail=false;
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
        	
        	
        	String user="yourmail@gmail.com";
        	String pass="password";
        	String[] toArr={to};
        	String from="abyuthup@gmail.com";
        	String sub="This is subject";
        	Mail m = new Mail(user,pass,toArr,from,sub,body);
  
            try { 
              /*m.addAttachment("/sdcard/filelocation"); */
       
              if(m.send()) { 
            	  sendmail=true;           
              } else {            
              }
              
            } catch(Exception e) { 
              //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
              Log.e("MailApp", "Could not send email", e); 
            } 
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
        if(sendmail) { 
          Toast.makeText(ComposeActivity.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
  		  Intent i = new Intent(getApplicationContext(), HomeActivity.class); 
          startActivity(i);
        } else { 
          Toast.makeText(ComposeActivity.this, "Email was not sent.", Toast.LENGTH_LONG).show();
          Intent i = new Intent(getApplicationContext(), HomeActivity.class); 
          startActivity(i);
        } 

		
	}
 }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}

}
