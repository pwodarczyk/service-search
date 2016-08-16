package com.rr.generalservicesapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.signpost.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import drawer.MainActivityDrawer;
import drawer.MainActivityDrawerForEmp;

public class SignUp extends Activity implements AdapterView.OnItemSelectedListener{

	ProgressDialog mProgressDialog;
	String nametxt;
	String phonetxt;
	String strEmpDiscripion;
	String addresstxt;
	String usernametxt;
	String passwordtxt;
	String emailtxt;
	String experiencetxt;
	EditText password;
	EditText username;
	ParseFile photoFile;
	EditText experience;
	EditText email;
	EditText name;
	EditText phone;
	EditText et_epm_discription;
	ImageButton imageButton;
	EditText address;
	Context context;
	//ImageView btnGetImage;
	CircularImageView mImageView;
	int	REQUEST_IMAGE_CAPTURE = 100;
	Button signup;
	ParseFile file;
	Spinner spinner;
	private ArrayList<Const> Category;
	ArrayList<String> OneVehicle;
	String id_employee;
	RadioGroup rg;
	TextView tvlogin;
	RadioButton rb_user, rb_employee;
	public static String str_EmpId;
	public  static 	String objid;
	ProgressDialog dialog;
	String s;
	private static int LOAD_IMAGE_RESULTS = 1;
	private int PICK_IMAGE_REQUEST = 1;
	private Bitmap bitmap;
	String ImagePath;
	String	categoryId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.signupusernew);
		context=getApplicationContext();
		signup = (Button) findViewById(R.id.btnsignupformm);
		mImageView = (CircularImageView) findViewById(R.id.mImageView);
		username = (EditText) findViewById(R.id.etsignupusername);
		password = (EditText) findViewById(R.id.etsignuppassword);
		email = (EditText) findViewById(R.id.etsignupemail);
		experience =(EditText) findViewById(R.id.etexperiance);
		//	btnGetImage = (ImageView) findViewById(R.id.mImageView);
		name = (EditText) findViewById(R.id.etsignupname);
		phone = (EditText) findViewById(R.id.etsignupphone);
		address = (EditText) findViewById(R.id.etsignupaddress);
		tvlogin =(TextView) findViewById(R.id.tvlogin);
		spinner = (Spinner)findViewById(R.id.spinner_category);
		et_epm_discription = (EditText) findViewById(R.id.et_epm_discription);

//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.category, R.layout.simple_spinner_item);
//		adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//		spinner.setAdapter(adapter);


		rb_user=  (RadioButton)findViewById(R.id.rb_user);
		rb_employee = (RadioButton)findViewById(R.id.rb_employee);

		spinner.setVisibility(View.GONE);
		experience.setVisibility(View.GONE);
		et_epm_discription.setVisibility(View.GONE);

		makeJsonObjforCategory();

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.category, R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
		final SpinAdapterT tradeadapter = new SpinAdapterT(context,
				R.layout.spinner_item,
				Utils.Category);
		tradeadapter.setDropDownViewResource(R.layout.spinner_item);
		spinner.setAdapter(tradeadapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				Const user = tradeadapter.getItem(position);
				 id_employee = user.getId();
				Log.d("usernametxt", id_employee);
				Toast.makeText(context, "Id" + id_employee, Toast.LENGTH_SHORT).show();
				Log.d("InputStream", id_employee);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		tvlogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
			}
		});



		rb_employee.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//				INVISIBLE
				spinner.setVisibility(View.VISIBLE);
				experience.setVisibility(View.VISIBLE);
				et_epm_discription.setVisibility(View.VISIBLE);

			}
		});
		rb_user.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				spinner.setVisibility(View.GONE);
				experience.setVisibility(View.GONE);
				et_epm_discription.setVisibility(View.GONE);

			}
		});
		signup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if(rb_employee.isChecked()) {
////					spcategory = spinner.getSelectedItem().toString();
//
////					if( spcategory.equals("Select Category")){
////						Toast.makeText(getApplicationContext(),
////								"Please select a category", Toast.LENGTH_LONG)
////								.show();
////
//					}else{

					// Retrieve the text entered from the EditText
					usernametxt = username.getText().toString();
					passwordtxt = password.getText().toString();
					emailtxt = email.getText().toString();
					experiencetxt = experience.getText().toString();
					nametxt = name.getText().toString();
					phonetxt = phone.getText().toString();
					strEmpDiscripion = et_epm_discription.getText().toString();


//						spcategory = spinner.getSelectedItem().toString();
					addresstxt = address.getText().toString();
					s = addresstxt;
					Log.d("usernametxt", usernametxt);
					Log.d("passwordtxt", passwordtxt);
					Log.d("emailtxt", emailtxt);
					Log.d("nametxt", nametxt);
					Log.d("phonetxt", phonetxt);
					Log.d("addresstxt", addresstxt);


				if  (usernametxt.equals("") && passwordtxt.equals("") && emailtxt.equals("") && nametxt.equals("")
							&& phonetxt.equals("") && addresstxt.equals("")) {

					Toast.makeText(getApplicationContext(),
							"Please complete the sign up form",
							Toast.LENGTH_LONG).show();
				}
					String image = Utils.getPreferences("image",context);
					if(image.equalsIgnoreCase("true")) {
						new AsyncCallWS2().execute();
						Utils.savePreferences("usertype", "1", context);
						Utils.savePreferences("image", "false", context);
						Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
						Intent intent=new Intent(getApplicationContext() , MainActivityDrawer.class);
						startActivity(intent);

					}else {
						Toast.makeText(getApplicationContext(), "Please select image first", Toast.LENGTH_LONG).show();
					}


				}


				if(rb_user.isChecked()){

					// Retrieve the text entered from the EditText
					usernametxt = username.getText().toString();
					passwordtxt = password.getText().toString();
					emailtxt = email.getText().toString();
					experiencetxt= experience.getText().toString();
					nametxt = name.getText().toString();
					phonetxt = phone.getText().toString();
					strEmpDiscripion = 	et_epm_discription.getText().toString();


//					spcategory = spinner.getSelectedItem().toString();
					addresstxt = address.getText().toString();
					s = addresstxt;
					Log.d("usernametxt", usernametxt);
					Log.d("passwordtxt", passwordtxt);
					Log.d("emailtxt", emailtxt);
					Log.d("nametxt", nametxt);
					Log.d("phonetxt", phonetxt);
					Log.d("addresstxt", addresstxt);



					if (usernametxt.equals("") && passwordtxt.equals("") && emailtxt.equals("") && nametxt.equals("")
							&& phonetxt.equals("") && addresstxt.equals("")) {

						Toast.makeText(getApplicationContext(),
								"Please complete the sign up form",
								Toast.LENGTH_LONG).show();

					} else {

//				SignUpAndLogin();
						String image = Utils.getPreferences("image",context);
						if(image.equalsIgnoreCase("true")) {
							new AsyncCallWS2User().execute();
							Utils.savePreferences("image", "false", context);
							Utils.savePreferences("usertype", "2", context);
							Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
							Intent intent=new Intent(getApplicationContext() , MainActivityDrawer.class);
							startActivity(intent);

						}else {
							Toast.makeText(getApplicationContext(), "Please select image first", Toast.LENGTH_LONG).show();
						}

						Utils.savePreferences("logged", "logged", getApplicationContext());
					}



				}

			}
		});


		mImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Create the Intent for Image Gallery.
				Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				// Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
				startActivityForResult(i, LOAD_IMAGE_RESULTS);
//                new work============================
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
			}
		});
	}
	public void Clicker(View v) {

	}

	public void clicker(){





	}
	private void FinalPoint() {
		// TODO Auto-generated method stub
		ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
		String objid = ParseUser.getCurrentUser().getObjectId();
		// Retrieve the object by id
		query.getInBackground(objid, new GetCallback<ParseObject>() {
			public void done(ParseObject upadteobj, ParseException e) {
				if (e == null) {
					upadteobj.put("photo", file);
					upadteobj.saveInBackground();
				}
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == LOAD_IMAGE_RESULTS && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
			Uri path_uri = data.getData();
			ImagePath =path_uri.getPath();
//           ==========================test
			try {
				//Getting the Bitmap from Gallery
				bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path_uri);
				//Setting the Bitmap to ImageView
				mImageView.setImageBitmap(bitmap);
				Utils.savePreferences("image", "true", context);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Bitmap image = bitmap;
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			image.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
//you can create a new file name "test.jpg" in sdcard folder.
			File f = new File(Environment.getExternalStorageDirectory() + File.separator + "img.jpg");
			try {
				f.createNewFile();
				FileOutputStream fo;

				fo = new FileOutputStream(f);
				fo.write(bytes.toByteArray());
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
// remember close de FileOutput
			File file = new File(Environment.getExternalStorageDirectory() + File.separator + "img.jpg");
			Uri originalUri = Uri.fromFile(file);
			ImagePath = originalUri.getPath();
			//           ==========================test

//            String filepath = string_image_path;
//             imagefile = new File(filepath);
//            FileInputStream fis = null;
//            try {
//                fis = new FileInputStream(imagefile);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }



		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}


	private class AsyncCallWS2 extends AsyncTask<String, Void, Void> {

//		final ProgressDialog dialog = new ProgressDialog(context);

		@Override
		protected Void doInBackground(String... params) {
			logupload();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

//			dialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
//			dialog.setMessage("Loading...");
//			dialog.show();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}
	public void loguploadUser() {
		String url = Utils.REGISTER_URL;
//        saveScaledPhoto(Data);
		HttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(client.getParams(),
				100000); // Timeout Limit
		org.apache.http.HttpResponse response;
		JSONObject json = new JSONObject();

		try {
			HttpPost post = new HttpPost(url);
			File file = new File(ImagePath);
			AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
					new AndroidMultiPartEntity.ProgressListener() {

						@Override
						public void transferred(long num) {
//                                publishProgress((int) ((num / (float) totalSize) * 100));
						}
					});


			strEmpDiscripion = 	et_epm_discription.getText().toString();
			entity.addPart("image", new FileBody(file));
			entity.addPart("name", new StringBody(nametxt));
			entity.addPart("user_name", new StringBody(usernametxt));
			entity.addPart("email", new StringBody(emailtxt));
			entity.addPart("password", new StringBody(passwordtxt));
//			entity.addPart("category", new StringBody("5"));

			entity.addPart("address", new StringBody(addresstxt));
			entity.addPart("phone", new StringBody(phonetxt));
			entity.addPart("user_type", new StringBody("2"));
			entity.addPart("signup", new StringBody("true"));



//            Log.d("Encodeed Img ", encodedImage);

			post.setEntity(entity);
			response = client.execute(post);
                    /* Checking response */
			if (response != null) {
				InputStream in = response.getEntity().getContent();
				String result = convertInputStreamToString(in);

				Log.d("InputStream", result);

			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error", "Cannot Estabilish Connection");
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
	private class AsyncCallWS2User extends AsyncTask<String, Void, Void> {

//		final ProgressDialog dialog = new ProgressDialog(context);

		@Override
		protected Void doInBackground(String... params) {
			loguploadUser();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

//			dialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
//			dialog.setMessage("Loading...");
//			dialog.show();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}
	public void logupload() {
		String url = Utils.REGISTER_URL;
//        saveScaledPhoto(Data);
		HttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(client.getParams(),
				100000); // Timeout Limit
		org.apache.http.HttpResponse response;
		JSONObject json = new JSONObject();

		try {
			HttpPost post = new HttpPost(url);
			File file = new File(ImagePath);
			AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
					new AndroidMultiPartEntity.ProgressListener() {

						@Override
						public void transferred(long num) {
//                                publishProgress((int) ((num / (float) totalSize) * 100));
						}
					});

			usernametxt = username.getText().toString();
			passwordtxt = password.getText().toString();
			emailtxt = email.getText().toString();
			experiencetxt = experience.getText().toString();
			nametxt = name.getText().toString();
			phonetxt = phone.getText().toString();
			strEmpDiscripion = et_epm_discription.getText().toString();


//						spcategory = spinner.getSelectedItem().toString();
			addresstxt = address.getText().toString();
			entity.addPart("image", new FileBody(file));
			entity.addPart("name", new StringBody(nametxt));
			entity.addPart("user_name", new StringBody(usernametxt));
			entity.addPart("email", new StringBody(emailtxt));
			entity.addPart("password", new StringBody(passwordtxt));
			entity.addPart("category", new StringBody(id_employee));

			entity.addPart("address", new StringBody(addresstxt));
			entity.addPart("phone", new StringBody(phonetxt));
			entity.addPart("user_type", new StringBody("1"));
			entity.addPart("experience", new StringBody(experiencetxt));
			entity.addPart("description", new StringBody(strEmpDiscripion));

			entity.addPart("signup", new StringBody("true"));



//            Log.d("Encodeed Img ", encodedImage);

			post.setEntity(entity);
			response = client.execute(post);
                    /* Checking response */
			if (response != null) {
				InputStream in = response.getEntity().getContent();
				String result = convertInputStreamToString(in);

				Log.d("InputStream", result);

			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error", "Cannot Estabilish Connection");
		}
	}



	private void makeJsonObjforCategory() {

		String url = Utils.CATEGORY_URL;
//        String url = Utils.CommentsUrl;
		JsonObjectRequest jsonobject = new JsonObjectRequest(Request.Method.GET,
				url, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d("Volley Response", response.toString());
						try {
							JSONArray JA = response.getJSONArray("data");
							Utils.Category.clear();
							try {
								for (int i = 0; i < JA.length(); i++) {
									JSONObject secondobj = JA.getJSONObject(i);
									String id = secondobj.getString("id");
									String name = secondobj.getString("name");
									Const vehicleOb = new Const(id,name);
									Utils.Category.add(vehicleOb);
//									db.addComments(comment);
								}
							} catch (JSONException | IndexOutOfBoundsException e) {
								e.printStackTrace();
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}


					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d("Volley Error", "Error: " + error.getMessage());
			}
		}) {


		};

		// Adding request to request queue

		AppController.getInstance().addToRequestQueue(jsonobject);
	}

}