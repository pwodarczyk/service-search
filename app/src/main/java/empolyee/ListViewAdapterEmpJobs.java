package empolyee;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
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

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapterEmpJobs extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ImageLoader imageLoader;
	private List<WorldPopulation> worldpopulationlist = null;
	private ArrayList<WorldPopulation> arraylist;
	String phoneNumber, strEmpName;
	String done;
	public String strEmpId;
	ParseObject parseObject;
	String userId;
	String	strUserName;
	String obId;
	String singleEpmId;
	public Dialog dialog;
	EditText et_jobCancelReason;
	String 	reasonJobCancel;
	//TextView tv_jobCancelReason;

	public ListViewAdapterEmpJobs(Context context,
								  List<WorldPopulation> worldpopulationlist) {
		this.context = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<WorldPopulation>();
		this.arraylist.addAll(worldpopulationlist);
		imageLoader = new ImageLoader(context);
	}

	public class ViewHolder {
		TextView population,epm_name, emp_address, emp_category, emp_experience, txtJobDone, tv_get_clientAddress, tvjobsCancel,tvjobsPandind,tv_jobCancelReason,tv_get_client_phoneNumber;
		Button emp_phone;
		String empId;
		ImageView flag, emp_photo;

	}

	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}



	public int getViewTypeCount() {

		return getCount();
	}

	@Override
	public int getItemViewType(int position) {

		return position;
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
			view = inflater.inflate(R.layout.listview_adaptor_emp_jobs, null);

			// Locate the TextViews in listview_item.xml
			holder.epm_name = (TextView) view.findViewById(R.id.tv_client_name);
			holder.emp_category = (TextView) view.findViewById(R.id.tv_job_disription);
			holder.emp_experience = (TextView) view.findViewById(R.id.tvexperianceshow);
			//holder.population = (TextView) view.findViewById(R.id.btnCall);
			holder.tvjobsPandind = (TextView)view.findViewById(R.id.tvjobsPandind);
			holder.tvjobsCancel = (TextView)view.findViewById(R.id.tvjobsCancel);
holder.tv_get_client_phoneNumber = (TextView)view.findViewById(R.id.tv_get_client_phoneNumber);
			holder.emp_phone = (Button)view.findViewById(R.id.btnCall);

			holder.txtJobDone = (TextView)view.findViewById(R.id.tvjobstatus);
			holder.tv_get_clientAddress = (TextView)view.findViewById(R.id.tv_get_clientAddress);
			// Locate the ImageView in listview_item.xml
			holder.flag = (ImageView) view.findViewById(R.id.imgprofile);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews

		holder.epm_name.setText(worldpopulationlist.get(position).getEpm_name());
		holder.emp_category.setText(worldpopulationlist.get(position).getEmp_category());
		holder.tv_get_clientAddress.setText(worldpopulationlist.get(position).getEmp_experience());
		holder.tvjobsPandind.setText(worldpopulationlist.get(position).getJob_status());
		String userphone = worldpopulationlist.get(position).getUserPhone();
		holder.tv_get_client_phoneNumber.setText(userphone);
		//holder.txtJobDone.setText(worldpopulationlist.get(position).getJob_status());


	holder.empId =	worldpopulationlist.get(position).getEmpId();


	//	obId =	worldpopulationlist.get(position).getSingleEmpId();




		holder.emp_phone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				phoneNumber = worldpopulationlist.get(position).getEmp_phone();
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
				worldpopulationlist.get(position).getEmp_phone();
				context.startActivity(intent);
			}
		});


		holder.txtJobDone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				int a=position;
				singleEpmId =  worldpopulationlist.get(position).getSingleEmpId();
//				Toast.makeText(context, "job done", Toast.LENGTH_SHORT).show();
				clicker2(a);
				//holder.txtJobDone.setText("job done");

			}
		});




		holder.tvjobsCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				int pos=position;
				singleEpmId = worldpopulationlist.get(position).getSingleEmpId();
				showDialog(pos);

			}
		});




		// Set the results into ImageView
		imageLoader.DisplayImage(worldpopulationlist.get(position).getFlag(),
				holder.flag);
		// Listen for ListView Item Click





		String reason = 	worldpopulationlist.get(position).getJob_status();

		if(reason.equals("Cancelled")){

			holder.txtJobDone.setVisibility(View.GONE);
			holder.tvjobsPandind.setVisibility(View.GONE);

			holder.tvjobsCancel.setText(worldpopulationlist.get(position).getJob_status());

			holder.tvjobsCancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {

					dialog = new Dialog(context);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custem_alert_dialog_reason_job_cancel);
					holder.tv_jobCancelReason = (TextView)dialog.findViewById(R.id.tv_jobCancelReason);
					dialog.show();
				//	String reson = worldpopulationlist.get(position).getStrReasonJobCancel();
					holder.tv_jobCancelReason.setText(worldpopulationlist.get(position).getStrReasonJobCancel());



				}
			});

		}else {
			holder.tvjobsCancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					int pos=position;
					singleEpmId = worldpopulationlist.get(position).getSingleEmpId();
					showDialog(pos);


				}
			});

		}
		if(reason.equals("Completed")){

			holder.txtJobDone.setVisibility(View.GONE);
			holder.tvjobsCancel.setVisibility(View.GONE);
			this.notifyDataSetChanged();
		}









		return view;
	}
	public void clicker(){



		//ParseUser usern = new ParseUser();
		parseObject = new ParseObject("Jobs");

		userId =	ParseUser.getCurrentUser().getObjectId();
		ParseUser User=ParseUser.getCurrentUser();
		strUserName = User.getUsername();

		parseObject.put("employee_name", strEmpName);
		//parseObject.put("category", spcategory);
		//parseObject.put("experience", experiencetxt);
		parseObject.put("employee_phone", done);
		parseObject.put("user_id", userId);
		parseObject.put("user_name", strUserName);
		parseObject.put("employee_id", strEmpId);
		//parseObject.put("address", s);
		parseObject.saveInBackground();



		//Toast.makeText(context, "" + strEmpId, Toast.LENGTH_SHORT).show();



	}

	public void clicker2(final int postion){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Jobs");
		query.getInBackground(singleEpmId, new GetCallback<ParseObject>() {
			@Override
			public void done(ParseObject parseObject, ParseException e) {
				if (e == null) {
					parseObject.put("job_status", "Completed");
					parseObject.saveInBackground();

					worldpopulationlist.get(postion).setJob_status("Completed");
					notifyDataSetChanged();

					Toast.makeText(context, "Job Closed" , Toast.LENGTH_SHORT).show();
				}
			}
		});
	}





	public void showDialog(final int postion){
		dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.custem_alert_dialog_job_cancel);


		et_jobCancelReason = (EditText)dialog.findViewById(R.id.et_jobCancelReason);
		dialog.show();
		Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
		Button btnOk = (Button) dialog.findViewById(R.id.okButton);

		declineButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				dialog.dismiss();
			}
		});



		btnOk.setOnClickListener(new OnClickListener() {


			public void onClick(View arg0) {
			 	reasonJobCancel = et_jobCancelReason.getText().toString();
				if((et_jobCancelReason.getText().toString().equals("")))
				{

					Toast.makeText(context,"Please fill the feilds", Toast.LENGTH_SHORT).show();
				}
				else {

					ParseQuery<ParseObject> query = ParseQuery.getQuery("Jobs");
					query.getInBackground(singleEpmId, new GetCallback<ParseObject>() {
						@Override
						public void done(ParseObject parseObject, ParseException e) {
							if (e == null) {

								parseObject.put("reason_jobCancel", reasonJobCancel);
								parseObject.put("job_status", "Cancelled");
								parseObject.saveInBackground();
								worldpopulationlist.get(postion).setStrReasonJobCancel(reasonJobCancel);
								worldpopulationlist.get(postion).setJob_status("Cancelled");
								notifyDataSetChanged();

							}
						}
					});
					dialog.dismiss();
				}
			}
		});
	}







}


