package empolyee;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rr.generalservicesapplication.ImageLoader;
import com.rr.generalservicesapplication.MLRoundedImageView;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.WorldPopulation;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListViewAdapterSingleEmp extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ImageLoader imageLoader;
	private List<WorldPopulation> worldpopulationlist = null;
	private ArrayList<WorldPopulation> arraylist;
	String phoneNumber, strEmpName, strEmpCategory;
	String done;
	int	REQUEST_IMAGE_CAPTURE = 100;
	public static final int RESULT_OK = -1;
	ParseFile file;
	public String strEmpId;
	ParseObject parseObject;
	String userId;
	String	strUserName;
	String empImage;
	int POSITION;
	public Dialog dialog;
	TextView date, time;
	public Bitmap customdialog ;
	TextView address, txt_addresslocal;
	String add2, addresslocal;
	EditText user_address, discription;
	String strUserAddress;
	String singleEpmId;
	String jobStatus;
	String empName;
	String myUsername, myAddress, myEmail, myPhone, myExperience, myDescription, myPassword;

	private FragmentActivity myContext;
	public ListViewAdapterSingleEmp(Context context,
									List<WorldPopulation> worldpopulationlist) {
		this.context = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<WorldPopulation>();
		this.arraylist.addAll(worldpopulationlist);
		imageLoader = new ImageLoader(context);
	}

	public class ViewHolder {
		TextView population,epm_name, emp_address, emp_category, emp_experience, txtJobDone;
		Button emp_phone,btnUpdate, btnCancel;
		Spinner mySpinner;
		EditText etsignupname,etsignupusername,etsignuppassword,etsignupemail,etsignupphone,etsignupaddress,etexperiance,et_epm_discription;
		ImageView imgprofile, emp_photo;

	}
	final ViewHolder holderr = null;
	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public Object getItem(int position) {
		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;

		if (view == null) {

			holder = new ViewHolder();
			view = inflater.inflate(R.layout.mainprofileemp, null);
			POSITION = position;
			// Locate the TextViews in listview_item.xml

			holder.epm_name = (TextView) view.findViewById(R.id.tv_client_name);

			holder.btnUpdate = (Button) view.findViewById(R.id.btnUpdateEmpp);
			holder.btnCancel = (Button) view.findViewById(R.id.btnCancel);

			holder.etsignupname = (EditText)view.findViewById(R.id.etsignupname);
			holder.etsignupusername = (EditText)view.findViewById(R.id.etsignupusername);
			holder.etsignuppassword = (EditText)view.findViewById(R.id.etsignuppassword);
			holder.etsignupemail = (EditText)view.findViewById(R.id.etsignupemail);
			holder.etsignupphone = (EditText)view.findViewById(R.id.etsignupphone);
			holder.etsignupaddress = (EditText)view.findViewById(R.id.etsignupaddress);
			holder.etexperiance = (EditText)view.findViewById(R.id.etexperiance);
			holder.et_epm_discription = (EditText)view.findViewById(R.id.et_epm_discription);
			holder.mySpinner = (Spinner)view.findViewById(R.id.spinner_category);

			holder.imgprofile = (ImageView) view.findViewById(R.id.imgprofile);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews

//		holder.epm_name.setText(worldpopulationlist.get(position).getEpm_name());




//		holder.mySpinner.setAdapter(new ArrayAdapter<String>(ListViewAdapterSingleEmp.this,
//				android.R.layout.simple_spinner_dropdown_item,
//				worldpopulationlist.get(position).getEmp_category()));






       // holder.mySpinner.setTag(worldpopulationlist.get(position).getEmp_category());
		holder.etsignupname.setText(worldpopulationlist.get(position).getEpm_name());
		holder.etsignupusername.setText(worldpopulationlist.get(position).getStrUserName());
		holder.etsignuppassword.setText(worldpopulationlist.get(position).getPassword());
		holder.etsignupemail.setText(worldpopulationlist.get(position).getEmail());
		holder.etsignupphone.setText(worldpopulationlist.get(position).getEmp_phone());
		holder.etsignupaddress.setText(worldpopulationlist.get(position).getEmp_address());
		holder.et_epm_discription.setText(worldpopulationlist.get(position).getEmp_discription());
		holder.etexperiance.setText(worldpopulationlist.get(position).getEmp_experience());



		// Set the results into ImageView
      imageLoader.DisplayImage(worldpopulationlist.get(position).getFlag(),
			  holder.imgprofile);
		// Listen for ListView  bmp Item Click



		holder.imgprofile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {

					((Activity) context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				}
				Toast.makeText(context,"toasted",Toast.LENGTH_SHORT).show();
			}
		});





		holder.btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
//				SelectCategory selectCategory = new SelectCategory();
//				FragmentManager fragmentManager = myContext.getFragmentManager();
//				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//				fragmentTransaction.replace(R.id.frame_container, selectCategory);
//				fragmentTransaction.commit();
				//myContext.getFragmentManager().beginTransaction().replace(R.id.frame_container, selectCategory).commit();
			}
		});


		holder.btnUpdate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				singleEpmId =  worldpopulationlist.get(position).getSingleEmpId();

				myUsername = holder.etsignupusername.getText().toString();
				empName = holder.etsignupname.getText().toString();
				myEmail = holder.etsignupemail.getText().toString();
				myPhone = holder.etsignupphone.getText().toString();
				myAddress = holder.etsignupaddress.getText().toString();
				myExperience = holder.etexperiance.getText().toString();
				myDescription = holder.et_epm_discription.getText().toString();
				myPassword  = holder.etsignuppassword.getText().toString();

				clicker2();
				Toast.makeText(context,"Updated successfully",Toast.LENGTH_SHORT).show();

			}
		});

		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			}
		});
		return view;
	}
//	public void clicker(){
//
//
//
//				//ParseUser usern = new ParseUser();
//				 parseObject = new ParseObject("Jobs");
//
//	 userId =	ParseUser.getCurrentUser().getObjectId();
//		ParseUser User=ParseUser.getCurrentUser();
//		strUserName = User.getUsername();
////	ParseFile	image = (ParseFile) parseObject.get("photo");
//
//		parseObject.put("employee_name", strEmpName);
//				//parseObject.put("category", spcategory);
//				//parseObject.put("experience", experiencetxt);
//		parseObject.put("employee_phone", done);
//		parseObject.put("user_id", userId);
//		parseObject.put("user_name", strUserName);
//		discription.getText().toString();
//		parseObject.put("user_address", user_address.getText().toString());
//		parseObject.put("employee_id", strEmpId);
//		parseObject.put("job_discription", discription.getText().toString());
//		parseObject.put("job_status", "job panding");
//
//		   //    parseObject.put("emp_category", strEmpCategory);
//				//parseObject.put("address", s);
//				parseObject.saveInBackground();
//
//
//
//
//		//Toast.makeText(context, "" + strEmpId, Toast.LENGTH_SHORT).show();
//
//
//
//	}

//
//	public void showDialog(){
//		// Create custom dialog object
//		dialog = new Dialog(context);
//		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		// Include dialog.xml file
//		dialog.setContentView(R.layout.custem_alert_dialog);
//		dialog.setCancelable(false);
//		// Set dialog title
//		//   dialog.setTitle("Custom Dialog");
//		// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		// set values for custom dialog components - text, image and button
//		TextView text = (TextView) dialog.findViewById(R.id.tvdate);
//		text.setText("Custom dialog Android example.");
//		ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
//		// image.setImageResource(R.drawable.shape);
//		date = (TextView) dialog.findViewById(R.id.tvdate);
//		SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM, dd yyyy");
//		String currentDate = sdf.format(new Date());
////		String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
//		date.setText(currentDate);
//		time = (TextView) dialog.findViewById(R.id.tvtime);
//		SimpleDateFormat stf = new SimpleDateFormat("HH:mma");
//		String currentTimeString = stf.format(new Date());
//		time.setText(currentTimeString);
//
//		user_address = (EditText)dialog.findViewById(R.id.et_user_address);
//	//	strUserAddress = user_address.getText().toString();
//
//
//		discription = (EditText)dialog.findViewById(R.id.et_discrition);
//
//		image.setImageBitmap(customdialog);
//
//		dialog.show();
//
//		Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
//		//address = (TextView) dialog.findViewById(R.id.tvaddress);
//		txt_addresslocal = (TextView) dialog.findViewById(R.id.tvaddresslocal);
//		//address.setText(add2);
//		txt_addresslocal.setText(addresslocal);
//		Button btnOk = (Button) dialog.findViewById(R.id.okButton);
//		// if decline button is clicked, close the custom dialog
//		declineButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// Close dialog
//				dialog.dismiss();
//			}
//		});
//		btnOk.setOnClickListener(new OnClickListener() {
//
//
//			public void onClick(View arg0) {
//				Toast.makeText(context, "" + strEmpName, Toast.LENGTH_SHORT).show();
//				Toast.makeText(context, "job done", Toast.LENGTH_SHORT).show();
//
//				//dialog.holder.txtJobDone.setText("job done");
//				clicker();
//				clicker2();
//				dialog.dismiss();
//
//			}
//		});
//	}

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
					parseObject.put("experience", myExperience);
					parseObject.put("emp_discription", myDescription);
				//	parseObject.put("password", myPassword);
				//	parseObject.put("photo", file);
					//parseObject.put("comments", comments);
					parseObject.saveInBackground();

				}
			}
		});
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

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			// Compress image to lower quality scale 1 - 100
			imageBitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
			byte[] image = stream.toByteArray();
			file = new ParseFile("profile"+".png",image);
			Bitmap bbmm = MLRoundedImageView.getCroppedBitmap(imageBitmap, 200);
			//imgprofile.setImageBitmap(bbmm);
		}
	}



}
