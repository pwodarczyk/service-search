package com.rr.generalservicesapplication;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
 
public class ParseApplication extends Application {
	static final String TAG = "Classified";
    @Override
    public void onCreate() {
        super.onCreate();
        
        
        
        
        // Add your initialization code here
//        Parse.initialize(this, getResources().getString(R.string.AP_id), getResources().getString(R.string.Cl_ID));
          
        Parse.initialize(this, "AmyVIV634qeq72zm9etDFCjCsS7tlgLlseICwlV3", "1tCB7dDqQn9VWr28TEvdCTFiqVH4axgHG0MqgZff");
         
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

       // ParseFacebookUtils.initialize(getResources().getString(R.string.FBapp_id));
        //ParseFacebookUtils.initialize(getString(R.string.FBapp_id));
 
        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
 
        ParseACL.setDefaultACL(defaultACL, true);
        //Log.d(TAG, "initializing app complete");
    }
 
}