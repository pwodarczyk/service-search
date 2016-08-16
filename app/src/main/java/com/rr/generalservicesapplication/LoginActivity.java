package com.rr.generalservicesapplication;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import drawer.MainActivityDrawer;
import drawer.MainActivityDrawerForEmp;
import userProfile.SelectCategory;

public class LoginActivity extends Activity implements OnClickListener {

	Button SgnWoerker, SignIn;
//	Button SgnUser;
		String passwordtxt;
	String result_email,result_password,user_type;
	public static	String usertxt;
 	EditText password;
	EditText user;
	public static final String PREFS_NAME = "LoginPrefs";
	Context context;
	ProgressDialog dialog;
	MainActivity mainActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		context = this;
		SignIn = (Button) findViewById(R.id.btnSignIn);
	//	SgnUser = (Button) findViewById(R.id.button2);
		SignIn.setOnClickListener(this);
		//SgnUser.setOnClickListener(this);
		mainActivity = new MainActivity();
		user = (EditText) findViewById(R.id.editText);
		password = (EditText) findViewById(R.id.editText3);
//String settings = Utils.getPreferences("logged",context);
//String setting2 = Utils.getPreferences("logged_epm",context);
//		if (settings.equals("logged")) {
//			// User is already logged in. Take him to main activity
//			Intent intent = new Intent(getApplicationContext(), MainActivityDrawer.class);
//
//			startActivity(intent);
//			finish();
//		}else
//		if(setting2.equals("logged_epm"))
//		{
//
//			Intent intent = new Intent(getApplicationContext(), MainActivityDrawerForEmp.class);
//			startActivity(intent);
//
//
//			finish();
//		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSignIn:
			usertxt = user.getText().toString();
			passwordtxt = password.getText().toString();
			new AsyncCallWS2().execute();
			user.setText("");
			password.setText("");
			break;
			}

		}

	private static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}
	public void logupload() {
		String url = Utils.REGISTER_URL;
//        saveScaledPhoto(Data);
		HttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(client.getParams(),
				100000); // Timeout Limit
		HttpResponse response;
		JSONObject json = new JSONObject();

		try {
			HttpPost post = new HttpPost(url);
//            File file = new File(ImagePath);
			AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
					new AndroidMultiPartEntity.ProgressListener() {

						@Override
						public void transferred(long num) {
//                                publishProgress((int) ((num / (float) totalSize) * 100));
						}
					});




			entity.addPart("username", new StringBody(usertxt));
			entity.addPart("password", new StringBody(passwordtxt));
			entity.addPart("login", new StringBody("true"));



//            Log.d("Encodeed Img ", encodedImage);

			post.setEntity(entity);
			response = client.execute(post);
                    /* Checking response */
			if (response != null) {
				InputStream in = response.getEntity().getContent();
				String result = convertInputStreamToString(in);
				Utils.savePreferences("login_result", result, this);
				Log.d("InputStream", result);


				JSONObject jsonObject;
				jsonObject = new JSONObject(result);

				JSONObject object = jsonObject.getJSONObject("message");
				String user_id = object.getString("id");
				String user_phone = object.getString("phone");
				String user_name = object.getString("name");
				Utils.savePreferences("user_name1", user_name, this);
				Utils.savePreferences("user_id", user_id, this);
				Utils.savePreferences("user_phone", user_phone, this);
				result_email = object.getString("email");
				result_password = object.getString("password");
				user_type = object.getString("user_type");
if(result_email.equalsIgnoreCase(usertxt)){
	Intent intent=new Intent(LoginActivity.this, MainActivityDrawer.class);
					startActivity(intent);
	Utils.savePreferences("login", "true", this);
	Utils.savePreferences("usertype", user_type, this);
//	Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();

}else{
	Toast.makeText(LoginActivity.this, "Incorrect Email Or Password", Toast.LENGTH_SHORT).show();
}
				String    strParsedValue = "Attribute 1 value => " + user_phone;
				strParsedValue += "\n Attribute 2 value => " + result_email;

//                JSONObject subObject = object.getJSONObject("sub");
//                JSONArray subArray = subObject.getJSONArray("sub1");
//
//                strParsedValue += "\n Array Length => " + subArray.length();
//
//                for (int i = 0; i < subArray.length(); i++) {
//                    strParsedValue += "\n"
//                            + subArray.getJSONObject(i).getString("sub1_attr")
//                            .toString();
//                }

//				tv_forgot_pass.setText(strParsedValue);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				String response1 = client.execute(post, responseHandler);
				Log.d("InputStream", response1);
//				tv_forgot_pass.setText(response1.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error", "Cannot Estabilish Connection");
		}
	}
	private class AsyncCallWS2 extends AsyncTask<String, Void, Void> {

		final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);

		@Override
		protected Void doInBackground(String... params) {
			logupload();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			dialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}
}