package userProfile;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rr.generalservicesapplication.AndroidMultiPartEntity;
import com.rr.generalservicesapplication.AppController;
import com.rr.generalservicesapplication.CircularNetworkImageView;
import com.rr.generalservicesapplication.ImageLoader;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.Utils;
import com.rr.generalservicesapplication.WorldPopulation;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
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
import java.util.List;

import drawer.MainActivityDrawer;


public class MainActivityUserProfile extends Fragment {

	public MainActivityUserProfile(){}
	// Declare Variables
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewAdapterUserProfile adapter;
	ParseFile image;
	String emailll;
	String str_exprience;
	String strObjectIdEmployeeUser;
	String str_category;
	private List<WorldPopulation> worldpopulationlist;
	public  String Slecteditemm;
	String empId;
	private com.android.volley.toolbox.ImageLoader imageLoader;
	String str_job_status;
	String username;
	String strpassword;
	String strEmail;
	String strPhone;
	String strAddress;
	String description;
	ParseFile file;
	Bitmap bitmap;
	String imageUrl,ImagePath;


	int	REQUEST_IMAGE_CAPTURE = 100;
	String myUsername, myAddress, myEmail, myPhone, myExperience, myDescription;

	String empName;
	ProgressDialog dialogg;
	String result_id,result_email,result_name,result_user_name,result_address,result_phone,result_image,result_user_type,result_cat_id,result_experience,result_description;

		TextView population,epm_name, emp_address, emp_category, emp_experience, txtJobDone, tvUpdateProfile;
		Button emp_phone,btnUpdate,btnCancel;
		EditText etsignupname,etsignupusername,etsignuppassword,etsignupemail,etsignupphone,etsignupaddress,etexperiance,et_epm_discription;
		ImageView flag,bgprofile;
	CircularNetworkImageView imgprofile;
	private static int LOAD_IMAGE_RESULTS = 1;
	private int PICK_IMAGE_REQUEST = 1;
	JSONObject object ;


	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}


	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.mainprofile,
				null);

//		imageLoader = new ImageLoader(getActivity());



		epm_name = (TextView) view.findViewById(R.id.tv_client_name);
		emp_category = (TextView) view.findViewById(R.id.tv_job_disription);
		emp_experience = (TextView) view.findViewById(R.id.tvexperianceshow);
		emp_phone = (Button)view.findViewById(R.id.btnCall);
		txtJobDone = (TextView)view.findViewById(R.id.tvjobstatusforuser);
		tvUpdateProfile =(TextView) view.findViewById(R.id.tvUpdateProfile);
		btnUpdate = (Button) view.findViewById(R.id.btnUpdateEmp);
		btnCancel = (Button) view.findViewById(R.id.btnCancel);
		bgprofile = (ImageView) view.findViewById(R.id.bgprofile);
		etsignupname = (EditText)view.findViewById(R.id.etsignupname);
		etsignupusername = (EditText)view.findViewById(R.id.etsignupusername);
		etsignuppassword = (EditText)view.findViewById(R.id.etsignuppassword);
		etsignupemail = (EditText)view.findViewById(R.id.etsignupemail);
		etsignupphone = (EditText)view.findViewById(R.id.etsignupphone);
		etsignupaddress = (EditText)view.findViewById(R.id.etsignupaddress);
		//	holder.etexperiance = (EditText)view.findViewById(R.id.etexperiance);
		//	holder.et_epm_discription = (EditText)view.findViewById(R.id.et_epm_discription);

		// Locate the ImageView in listview_item.xml
		imgprofile = (CircularNetworkImageView) view.findViewById(R.id.imgprofile);
		imgprofile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				// Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
				startActivityForResult(i, LOAD_IMAGE_RESULTS);

			}
		});
		String result = Utils.getPreferences("login_result",getActivity());
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}


		try {
			object = jsonObject.getJSONObject("message");
			result_id = object.getString("id");
			result_email = object.getString("email");
			result_name = object.getString("name");
			result_user_name = object.getString("user_name");
			result_address = object.getString("address");
			result_phone = object.getString("phone");
			result_image = object.getString("image");
			result_user_type = object.getString("user_type");
			result_cat_id = object.getString("category");
			result_experience = object.getString("experience");
			result_description= object.getString("description");
//			result_image= object.getString("image");

			etsignupname.setText(result_name);
			etsignupusername.setText(result_user_name);
			etsignupemail.setText(result_email);
			etsignupphone.setText(result_phone);
			etsignupaddress.setText(result_address);
			imageLoader = AppController.getInstance().getImageLoader();


			String emp_imgUrl = Utils.IMAGEURL+result_image;


			imgprofile.setImageUrl(emp_imgUrl, imageLoader);
//			etexperiance.setText(result_name);
//			et_epm_discription.setText(result_name);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		tvUpdateProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
					startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				}

			}
		});





		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Fragment fragment = new SelectCategory();
// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment)
						.commit();
			}
		});




		btnUpdate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				myUsername = etsignupusername.getText().toString();
				empName = etsignupname.getText().toString();
				myEmail = etsignupemail.getText().toString();
				myPhone = etsignupphone.getText().toString();
				myAddress = etsignupaddress.getText().toString();
				String image = Utils.getPreferences("image",getActivity());
				if(image.equalsIgnoreCase("true")) {
					new  AsyncCallWS2User().execute();
					Utils.savePreferences("image", "false", getActivity());
					Toast.makeText(getActivity(), "Profile Successfully Updated", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(getActivity() , MainActivityDrawer.class);
					Utils.savePreferences("login", "false", getActivity());
					startActivity(intent);

				}else {
					Toast.makeText(getActivity(), "Please select image first", Toast.LENGTH_LONG).show();
				}
//				myExperience = holder.etexperiance.getText().toString();
				//	myDescription = holder.et_epm_discription.getText().toString();


//				myPassword  = etsignuppassword.getText().toString();
//				resetPassword();
//				clicker2();

			}
		});


		Intent i = getActivity().getIntent();

		Slecteditemm = i.getStringExtra("Slecteditem");
//		new RemoteDataTask().execute();
		return view;
	}

//	// RemoteDataTask AsyncTask
//	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			// Create a progressdialog
//			mProgressDialog = new ProgressDialog(getActivity());
//			// Set progressdialog title
//
//			// Set progressdialog message
//			mProgressDialog.setMessage("Loading...");
//			mProgressDialog.setIndeterminate(false);
//			// Show progressdialog
//			mProgressDialog.show();
//		}
//
//
//		protected Void doInBackground(Void... params) {
//			// Create the array
//			worldpopulationlist = new ArrayList<WorldPopulation>();
//			try {
//				String img="";
//
//
//
//				// Locate the class table named "Country" in Parse.com
//				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
//						"_User");
//				// Locate the column named "ranknum" in Parse.com and order list
//				// by ascending
//				//query.whereDoesNotExist("score");
//			//	query.whereStartsWith("category", "Electrician");
//				ParseUser userr= ParseUser.getCurrentUser();
//				String current =	userr.getObjectId();
//				query.whereEqualTo("objectId", current);
//
//			//	query.whereNotEqualTo("category", "Select Category");
//
//
//
//
//				//	query.whereDoesNotExist("Select Category");
//			//	query.whereNotEqualTo("10", str_exprience);
//				ob = query.find();
//				for (ParseObject country : ob) {
//					// Locate images in flag column
//
//          			 empId =	country.getObjectId();
//
//					WorldPopulation map = new WorldPopulation(null,null,null,null,null,null,null,null,null);
//				//	map.setRank((String) country.get("username"));
//					 emailll = (String) country.get("email");
//
//					if(emailll!=null) {
//						map.setCountry((String) country.get("email"));
//					}else{
//						emailll="";
//					}
//
//
//
//					map.setCountry((String) country.get("email"));
//
//
//					map.setEmp_category((String) country.get("category"));
//
//					str_category = (String) country.get("category");
//					if(str_category!=null) {
//						map.setEmp_category((String) country.get("category"));
//					}else{
//						str_category="";
//					}
//
//
//					str_job_status = (String) country.get("job_status");
//					if(str_job_status!=null) {
//						map.setJob_status((String) country.get("job_status"));
//					}else{
//						str_job_status="";
//					}
//
//
//					strAddress = (String) country.get("address");
//					if(strAddress!=null) {
//						map.setEmp_address((String) country.get("address"));
//					}else{
//						strAddress="";
//					}
//
//
//
//					strPhone = (String) country.get("phone");
//					if(strPhone!=null) {
//						map.setEmp_phone((String) country.get("phone"));
//					}else{
//						strPhone="";
//					}
//
//
//
//					username = (String) country.get("username");
//					if(username!=null) {
//						map.setStrUserName((String) country.get("username"));
//					}else{
//						username="";
//					}
//
//					strpassword = (String) country.get("password");
//					if(strpassword!=null) {
//						map.setPassword((String) country.get("password"));
//					}else{
//						strpassword="";
//					}
//
//					strEmail = (String) country.get("email");
//					if(strEmail!=null) {
//						map.setEmail((String) country.get("email"));
//					}else{
//						strEmail="";
//					}
//
//					str_exprience = (String) country.get("experience");
//
//					if(str_exprience!=null) {
//						map.setEmp_experience((String) country.get("experience"));
//					}else{
//						str_exprience="";
//					}
//
//
//
//
//							description = (String) country.get("emp_discription");
//
//					if(description!=null) {
//						map.setEmp_discription((String) country.get("emp_discription"));
//					}else{
//						description="";
//					}
//
//
//
//					strObjectIdEmployeeUser = (String) country.get("objectId");
//
//					 empName = (String) country.get("name");
//
//					map.setEpm_name((String) country.get("name"));
//					map.setEmpId(empId);
//
//				//	map.setEmpId((String) country.get("objectId"));
//
//					image = (ParseFile) country.get("photo");
//					if(image!=null) {
//						imageUrl = image.getUrl();
//					}else{
//						imageUrl="";
//					}
////
//					map.setFlag(imageUrl);
//					worldpopulationlist.add(map);
//				}
//			} catch (ParseException e) {
//				Log.e("Error", e.getMessage());
//				e.printStackTrace();
//			}
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//
//			etsignupname.setText(empName);
//			etsignupusername.setText(username);
//			etsignupemail.setText(strEmail);
//			etsignupphone.setText(strPhone);
//			etsignupaddress.setText(strAddress);
//			if(image== null) {
//
//
//				final int stub_id = R.drawable.temp_img;
//				bgprofile.setImageResource(stub_id);
//				imgprofile.setImageResource(stub_id);
//
//			}else {
//				imageUrl = image.getUrl();
//				imageLoader.DisplayImage(imageUrl,
//						imgprofile);
//				imageLoader.DisplayImage(imageUrl,
//						bgprofile);
//
//			}
//			mProgressDialog.dismiss();
//		}
//
//
//
//
//	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == LOAD_IMAGE_RESULTS && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
			Uri path_uri = data.getData();
			ImagePath =path_uri.getPath();
			if(ImagePath.isEmpty()) {
				Toast.makeText(getActivity(), "Please select correct path of image", Toast.LENGTH_LONG).show();
			}else{
				try {
					//Getting the Bitmap from Gallery
					bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path_uri);
					//Setting the Bitmap to ImageView
					imgprofile.setImageBitmap(bitmap);
					Utils.savePreferences("image", "true", getActivity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
//           ==========================test

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

	public void resetPassword()
	{
		dialogg = new ProgressDialog(getActivity());
		dialogg.setMessage("Progress start");
		dialogg.setCanceledOnTouchOutside(false);
		dialogg.show();

		ParseUser.requestPasswordResetInBackground(myEmail,
				new RequestPasswordResetCallback() {
					public void done(ParseException e) {
						//	turnOffProgressDialog();
						dialogg.dismiss();
						if (e == null) {
							reportSuccessfulReset();
						} else {
							reportResetError(e);
						}
					}
				});
	}//eof-reset

	private void reportSuccessfulReset(){
		//	Toast.makeText(context, "password updated successfully", Toast.LENGTH_SHORT).show();
	}
	private void reportResetError(ParseException e)
	{
		//	Toast.makeText(context, "password not updated successfully", Toast.LENGTH_SHORT).show();
	}







	public void clicker2(){

		ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
		ParseUser user = ParseUser.getCurrentUser();

		String userid = user.getObjectId();
		query.getInBackground(userid, new GetCallback<ParseObject>() {
			@Override
			public void done(ParseObject parseObject, ParseException e) {
				if (e == null) {
					parseObject.put("name", empName);
					parseObject.put("username", myUsername);
					parseObject.put("email", myEmail);
					parseObject.put("phone", myPhone);
					parseObject.put("address", myAddress);


					if(file == null){
						 bitmap = ((BitmapDrawable)imgprofile.getDrawable()).getBitmap();

						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						// Compress image to lower quality scale 1 - 100
						bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
						byte[] image = stream.toByteArray();
						file = new ParseFile("profile"+".png",image);
						parseObject.put("photo", file);



					}else {
						parseObject.put("photo", file);
					}

					parseObject.saveInBackground();
					Toast.makeText(getActivity(), "Profile Updated", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}


//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
//			Bundle extras = data.getExtras();
//			Bitmap imageBitmap = (Bitmap) extras.get("data");
//			ByteArrayOutputStream stream = new ByteArrayOutputStream();
//			// Compress image to lower quality scale 1 - 100
//			imageBitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
//			byte[] image = stream.toByteArray();
//			file = new ParseFile("profile"+".png",image);
//		//	Bitmap bbmm = MLRoundedImageView.getCroppedBitmap(imageBitmap, 200);
//			imgprofile.setImageBitmap(imageBitmap);
//			bgprofile.setImageBitmap(imageBitmap);
//		}
//	}

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


			entity.addPart("image", new FileBody(file));
			entity.addPart("name", new StringBody(empName));
			entity.addPart("user_name", new StringBody(myUsername));
			entity.addPart("email", new StringBody(myEmail));
			entity.addPart("id", new StringBody(result_id));
			entity.addPart("address", new StringBody(myAddress));
			entity.addPart("phone", new StringBody(myPhone));
			entity.addPart("user_type", new StringBody(result_user_type));
			entity.addPart("update", new StringBody("true"));



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

}