package com.rr.generalservicesapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import drawer.MainActivityDrawer;
import drawer.MainActivityDrawerForEmp;

public class MainActivity extends Activity {

	Button SgnWoerker, SignIn;
	Button SgnUser;
		String passwordtxt;
	public static	String usertxt;
 	EditText password;
	EditText user;
	public static final String PREFS_NAME = "LoginPrefs";
	Context context;
	public static Activity mainActivity;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main1);
		context = this;
		SignIn = (Button) findViewById(R.id.btnSignIn);
		SgnUser = (Button) findViewById(R.id.btnSignUp);

		//SgnUser.setOnClickListener(this);




			mainActivity = this;





		user = (EditText) findViewById(R.id.editText);
		password = (EditText) findViewById(R.id.editText3);
//		String settings = Utils.getPreferences("result",context);
//		Log.d("result");
		Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
//        String settings = Utils.getPreferences("logged",context);
//        String setting2 = Utils.getPreferences("logged_epm",context);
//		if (settings.equals("logged")) {
//			// User is already logged in. Take him to main activity
//			Intent intent = new Intent(getApplicationContext(), MainActivityDrawer.class);
//			startActivity(intent);
//			finish();
//		}else
//		if(setting2.equals("logged_epm"))
//		{
//
//			Intent intent = new Intent(getApplicationContext(), MainActivityDrawerForEmp.class);
//			startActivity(intent);
//			finish();
//		}



		//Button btn = (Button) findViewById(R.id.button1);
		SignIn.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent(context,LoginActivity.class);
				startActivity(intent);

			}
		});

		SgnUser.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent(context,SignUp.class);
				startActivity(intent);

			}
		});

	}


}