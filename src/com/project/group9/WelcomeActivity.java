package com.project.group9;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;

public class WelcomeActivity extends Activity {

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
	                startActivityForResult(intent, 0);
	                finish();
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
	                startActivityForResult(intent, 0);
	                finish();
	            }
	        });

	}

	@Override
	public void onBackPressed() {
	    Intent startMain = new Intent(Intent.ACTION_MAIN);
	    startMain.addCategory(Intent.CATEGORY_HOME);
	    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    startActivity(startMain);
	    //stopService(music);
	    mServ.onDestroy();
	    finish();
	}

}
