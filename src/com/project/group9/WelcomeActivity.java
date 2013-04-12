package com.project.group9;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;

public class WelcomeActivity extends Activity {
	
	private SharedPreferences mPreferences;
    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

    	public void onServiceConnected(ComponentName name, IBinder
         binder) {
    		mServ = ((MusicService.ServiceBinder)binder).getService();
    	}

    	public void onServiceDisconnected(ComponentName name) {
    		mServ = null;
    	}
    };

    	void doBindService(){
     		bindService(new Intent(this,MusicService.class),
    				Scon,Context.BIND_AUTO_CREATE);
    		mIsBound = true;
    	}

    	void doUnbindService()
    	{
    		if(mIsBound)
    		{
    			unbindService(Scon);
          		mIsBound = false;
    		}
    	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_welcome);
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
	    if(mPreferences.getString("AuthToken", "").length()>2)
	    {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
	    }
	    this.doBindService();
	    mServ = new MusicService();

	    Intent music = new Intent();
	    music.setClass(this,MusicService.class);
	    startService(music);
	    //finish();

	    //mServ.onDestroy();

	    findViewById(R.id.registerButton).setOnClickListener(
	        new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                // No account, load new account view
	            	mServ.onDestroy();
	                Intent intent = new Intent(WelcomeActivity.this,
	                    RegisterActivity.class);
	                startActivity(intent);
	        
	            }
	        });

	    findViewById(R.id.loginButton).setOnClickListener(
	        new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                // Existing Account, load login view
	            	///STOP MUSIC when we click login
	            	mServ.onDestroy();
	                Intent intent = new Intent(WelcomeActivity.this,
	                    LoginActivity.class);
	                startActivity(intent);
	
	            }
	        });

	}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


    		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(WelcomeActivity.this);
     
    			// set title
    			alertDialogBuilder.setTitle("Exit");
     
    			// set dialog message
    			alertDialogBuilder
    				.setMessage("Do you want to exit ?")
    				.setCancelable(false)
    				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,int id) {
    						// if this button is clicked, close
    		                // The neutral button was clicked
    		            	Intent i = new Intent();
    		        		i.setAction(Intent.ACTION_MAIN);
    		        		i.addCategory(Intent.CATEGORY_HOME);
    		        		startActivity(i); 
    		        		finish();  
    					}
    				  })
    				.setNegativeButton("No",new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,int id) {
    						// if this button is clicked, just close
    						// the dialog box and do nothing
    						dialog.cancel();
    					}
    				});
     				// create alert dialog
    				AlertDialog alertDialog = alertDialogBuilder.create();
     
    				// show it
    				alertDialog.show();

            
        
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
