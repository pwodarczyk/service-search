package userProfile;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rr.generalservicesapplication.ImageLoader;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.WorldPopulation;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapterUserProfile extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ImageLoader imageLoader;
	private List<WorldPopulation> worldpopulationlist = null;
	private ArrayList<WorldPopulation> arraylist;
	String phoneNumber, strEmpName, strEmpCategory;
	String done;
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
	ProgressDialog dialogg;


	public ListViewAdapterUserProfile(Context context,
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
		Button emp_phone,btnUpdate,btnCancel;
		EditText etsignupname,etsignupusername,etsignuppassword,etsignupemail,etsignupphone,etsignupaddress,etexperiance,et_epm_discription;
		ImageView flag, imgprofile,bgprofile;

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
			view = inflater.inflate(R.layout.mainprofile, null);
			POSITION = position;
			holder.epm_name = (TextView) view.findViewById(R.id.tv_client_name);
		    holder.emp_category = (TextView) view.findViewById(R.id.tv_job_disription);
			holder.emp_experience = (TextView) view.findViewById(R.id.tvexperianceshow);
			holder.emp_phone = (Button)view.findViewById(R.id.btnCall);
			holder.txtJobDone = (TextView)view.findViewById(R.id.tvjobstatusforuser);

			holder.btnUpdate = (Button) view.findViewById(R.id.btnUpdateEmp);
			holder.btnCancel = (Button) view.findViewById(R.id.btnCancel);

			holder.etsignupname = (EditText)view.findViewById(R.id.etsignupname);
			holder.etsignupusername = (EditText)view.findViewById(R.id.etsignupusername);
			holder.etsignuppassword = (EditText)view.findViewById(R.id.etsignuppassword);
			holder.etsignupemail = (EditText)view.findViewById(R.id.etsignupemail);
			holder.etsignupphone = (EditText)view.findViewById(R.id.etsignupphone);
			holder.etsignupaddress = (EditText)view.findViewById(R.id.etsignupaddress);
		//	holder.etexperiance = (EditText)view.findViewById(R.id.etexperiance);
		//	holder.et_epm_discription = (EditText)view.findViewById(R.id.et_epm_discription);

			// Locate the ImageView in listview_item.xml
			holder.imgprofile = (ImageView) view.findViewById(R.id.imgprofile);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews

		holder.etsignupname.setText(worldpopulationlist.get(position).getEpm_name());
		holder.etsignupusername.setText(worldpopulationlist.get(position).getStrUserName());
        holder.etsignuppassword.setText(worldpopulationlist.get(position).getPassword());
		holder.etsignupemail.setText(worldpopulationlist.get(position).getEmail());
		holder.etsignupphone.setText(worldpopulationlist.get(position).getEmp_phone());
		holder.etsignupaddress.setText(worldpopulationlist.get(position).getEmp_address());
	//	holder.et_epm_discription.setText(worldpopulationlist.get(position).getEmp_discription());

		//holder.emp_category.setText(worldpopulationlist.get(position).getEmp_category());
	//    holder.emp_experience.setText(worldpopulationlist.get(position).getEmp_experience());
      imageLoader.DisplayImage(worldpopulationlist.get(position).getFlag(),
			  holder.imgprofile);


		holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				singleEpmId =  worldpopulationlist.get(position).getSingleEmpId();

				myUsername = holder.etsignupusername.getText().toString();
				empName = holder.etsignupname.getText().toString();
				myEmail = holder.etsignupemail.getText().toString();
				myPhone = holder.etsignupphone.getText().toString();
				myAddress = holder.etsignupaddress.getText().toString();
//				myExperience = holder.etexperiance.getText().toString();
			//	myDescription = holder.et_epm_discription.getText().toString();
				myPassword  = holder.etsignuppassword.getText().toString();
				resetPassword();
				clicker2();



			}
		});


		return view;
	}
	public void clicker(){

				 parseObject = new ParseObject("Jobs");

	 userId =	ParseUser.getCurrentUser().getObjectId();
		ParseUser userrr=ParseUser.getCurrentUser();
		strUserName = userrr.getUsername();


//	ParseFile	image = (ParseFile) parseObject.get("photo");

		parseObject.put("employee_name", strEmpName);
				//parseObject.put("category", spcategory);
				//parseObject.put("experience", experiencetxt);
		parseObject.put("employee_phone", done);
		parseObject.put("user_id", userId);
		parseObject.put("user_name", strUserName);
		discription.getText().toString();

		ParseUser parseUser = ParseUser.getCurrentUser();
		parseUser.setPassword(myPassword);

		parseObject.put("user_address", user_address.getText().toString());
		parseObject.put("employee_id", strEmpId);
		parseObject.put("job_discription", discription.getText().toString());
		parseObject.put("job_status", "job panding");
				parseObject.saveInBackground();

	}





	public void resetPassword()
	{
		dialogg = new ProgressDialog(context);
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
					parseObject.saveInBackground();
					Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}




}
