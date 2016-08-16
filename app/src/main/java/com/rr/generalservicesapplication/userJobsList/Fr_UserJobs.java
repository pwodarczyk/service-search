package com.rr.generalservicesapplication.userJobsList;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.Utils;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import employeesList.Constants;


public class Fr_UserJobs extends Fragment {

	public Fr_UserJobs(){}
	// Declare Variables
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	FrUserJobsAdaptor adapter;
	ParseFile image;
	String emailll;
	String str_exprience;
	String strObjectIdEmployeeUser;
	String str_category;
	String str_rating;
	String str_comments;
	private List<Constants> UserJobList;
	String employeeName;
	public  String Slecteditemm;
	String empId;
	String jobStatus;
	String strJobDiscription;
	String workerImage;
	String strReasonJobCancel;
	TextView tvNoJob;
	String strRatingCounter;
	String strEmpObjid;
	String img = "";


	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		final View v = LayoutInflater.from(getActivity()).inflate(R.layout.listview_main,
				null);
		listview = (ListView) v.findViewById(R.id.listview);
//		listview = (ListView) getActivity().findViewById(R.id.listview);
		tvNoJob = (TextView) v.findViewById(R.id.tvNoJob);
		// Locate the listview in listview_main.xml

		// Pass the results into ListViewAdapter.java
		try {
			getUserJobList();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(Utils.UserJobList.isEmpty()){

			tvNoJob.setVisibility(View.VISIBLE);
			tvNoJob.setText("You have no job .....!");
		}else {

			tvNoJob.setVisibility(View.GONE);
		}


//		Intent i = getActivity().getIntent();
//
//		Slecteditemm = i.getStringExtra("Slecteditem");
//		new RemoteDataTask().execute();


		return v;
	// RemoteDataTask AsyncTask
//	class RemoteDataTask extends AsyncTask<Void, Void, Void> {
//
//		protected void onPreExecute() {
//			super.onPreExecute();
//			mProgressDialog = new ProgressDialog(getActivity());
//			// Set progressdialog title
////			mProgressDialog.setTitle("Parse.com Custom ListView Tutorial");
//			// Set progressdialog message
//			mProgressDialog.setMessage("Loading...");
//			mProgressDialog.setIndeterminate(false);
//			// Show progressdialog
//			mProgressDialog.show();
//
//		}
//
//
//		protected Void doInBackground(Void... params) {
//			// Create the array
////			worldpopulationlist = new ArrayList<WorldPopulation>();
//			try {
//
//
//
//				// Locate the class table named "Country" in Parse.com
//				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
//						"Jobs");
//				ParseUser User=ParseUser.getCurrentUser();
//				String	strUserName = User.getUsername();
//				query.whereEqualTo("user_name", strUserName);
//
//
//				ob = query.find();
//				for (ParseObject country : ob)
//				{
//					// Locate images in flag column
//
//					WorldPopulation map = new WorldPopulation(null,null,null,null,null,null,null,null,null);
////					String  my_Category_name = country.get("emp_category").toString();
////					String  my_strEmpObjid = country.get("employee_id").toString();
////					String  my_jobStatus =  country.get("job_status").toString();
//////					String	my_str_comments = country.get("comments").toString();
////					String	my_strJobDiscription =country.get("job_discription").toString();
////					String	my_employeeName =  country.get("employee_name").toString();
////					String	my_img =  (String)country.get("employee_image2");
//
////					idz(my_strEmpObjid, my_jobStatus, my_strJobDiscription, my_employeeName, my_img, map);
//
//
//					empId = country.getObjectId();
//					map.setSingleEmpId(empId);
//
//					str_category = (String) country.get("emp_category");
//                    str_comments = (String) country.get("comments");
//                    jobStatus = (String) country.get("job_status");
//                    strRatingCounter = (String) country.get("ratingCounter");
//                    strEmpObjid = (String) country.get("employee_id");
//                    strJobDiscription = (String) country.get("job_discription");
//                    employeeName = (String) country.get("employee_name");
//
//                    map.setEmp_category(str_category);
//                    map.setRating((String) country.get("rating"));
//                    map.setComments(str_comments);
//                    map.setStrReasonJobCancel((String) country.get("reason_jobCancel"));
//                    map.setJob_status(jobStatus);
//                    map.setRatingCounter(strRatingCounter);
//                    map.setStrEmpObjid(strEmpObjid);
//                    map.setEmpId(empId);
//                    map.setEpm_name(employeeName);
//                    map.setJob_Discription(strJobDiscription);
//
////					if (str_category != null) {
////						map.setEmp_category(str_category);
////					} else {
////						str_category = "";
////					}
////
////					if (str_rating != null) {
////						map.setRating((String) country.get("rating"));
////					} else {
////						str_rating = "";
////					}
////
////					if (str_comments != null) {
////						map.setComments(str_comments);
////					} else {
////						str_comments = "";
////					}
////
////
////					if (strReasonJobCancel != null) {
////						map.setStrReasonJobCancel((String) country.get("reason_jobCancel"));
////					} else {
////						strReasonJobCancel = "";
////					}
////
////					if (jobStatus != null) {
////
////						map.setJob_status(jobStatus);
////					} else {
////						jobStatus = "";
////					}
////
////					if (strRatingCounter != null) {
////
////						map.setRatingCounter(strRatingCounter);
////					} else {
////						strRatingCounter = "";
////					}
////					if (strEmpObjid != null) {
////
////						map.setStrEmpObjid(strEmpObjid);
////					} else {
////						strEmpObjid = "";
////					}
////
////					if (strJobDiscription != null) {
////
////						map.setJob_Discription(strJobDiscription);
////					} else {
////						strJobDiscription = "";
////					}
//
//					img =  (String)country.get("employee_image2");
//					if (img != null) {
//						map.setFlag(img);
//					} else {
//						img = "";
//						map.setFlag(img);
//					}
//
//					worldpopulationlist.add(map);
//				}
//			} catch (ParseException e) {
//				Log.e("Error", e.getMessage());
//				e.printStackTrace();
//			}
//			return null;
//
//
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//
//
//				Collections.reverse(worldpopulationlist);
//				adapter = new FrUserJobsAdaptor(getActivity(),
//						worldpopulationlist);
//				//adapter.notifyDataSetChanged();
//				// Binds the Adapter to the ListView
//				listview.setAdapter(adapter);
//
//			}	// Close the progressdialog
////			mProgressDialog.dismiss();
//
//		}
	}

//	private void idz(String objiduser,final String job_Status ,final String strJob_Discription,
//					 final String employee_Name,final String _img, final WorldPopulation _map) {
////		worldpopulationlist = new ArrayList<WorldPopulation>();
//		final ParseQuery<ParseObject> query = ParseQuery.getQuery("EmpRating");
//		String sa=objiduser;
//
//		query.whereEqualTo("empObjectId", objiduser);
//		query.findInBackground(new FindCallback<ParseObject>() {
//			@Override
//			public void done(List<ParseObject> nameList, ParseException e) {
//
//				int siz = nameList.size();
//
//				if (e == null) {
//
//					for (ParseObject nameObj : nameList) {
//						String ob = nameObj.getObjectId();
//						nameObj.get("rating");
//						nameObj.get("empObjectId");
//						nameObj.get("ratingCounter");
//
//
//						String ratingCount = (String) nameObj.get("ratingCounter");
//						if (ratingCount != null) {
//							_map.setNewRatingCounter((String) nameObj.get("ratingCounter"));
//						} else {
//							ratingCount = "";
//						}
//
//						_map.setRating((String) nameObj.get("rating"));
//						_map.setJob_status(job_Status);
////						_map.setComments(str_Comments);
//						_map.setJob_Discription(strJob_Discription);
//						_map.setEpm_name(employee_Name);
//						_map.setFlag(_img);
//
//						worldpopulationlist.add(_map);
//
//					}
//
//
//				} else {
//					Toast.makeText(getActivity(), "there is a problem" + e, Toast.LENGTH_SHORT).show();
//				}
//			}
//		});
//	}
	public void getUserJobList() throws JSONException {
		String user_jobs_result = Utils.getPreferences("user_jobs_result",getActivity());
		JSONObject first = new JSONObject(user_jobs_result);


		try {
			JSONArray JA = first.getJSONArray("data");
			Utils.UserJobList.clear();
			try {
				for (int i = 0; i < JA.length(); i++) {
					JSONObject secondobj = JA.getJSONObject(i);
					String result_job_id = secondobj.getString("id");
					String result_emp_id = secondobj.getString("emp_id");
					String result_emp_name = secondobj.getString("emp_name");
					String result_emp_phone = secondobj.getString("emp_phone");
					String result_user_id = secondobj.getString("user_id");
					String result_user_name = secondobj.getString("user_name");
					String result_user_address = secondobj.getString("user_address");
					String result_user_phone = secondobj.getString("user_phone");
					String result_job_status = secondobj.getString("job_status");
					String result_job_description = secondobj.getString("job_description");
					String result_job_rating = secondobj.getString("rating");
					String emp_image = secondobj.getString("emp_image");

					Constants ob = new Constants(result_job_id,result_emp_id,result_emp_name,result_emp_phone,result_user_id,result_user_name,result_user_address,result_user_phone,result_job_status,result_job_description,result_job_rating,emp_image);
					Utils.UserJobList.add(ob);

					adapter = new FrUserJobsAdaptor(getActivity(), Utils.UserJobList);
					listview.setAdapter(adapter);

//									db.addComments(comment);
				}
			} catch (JSONException | IndexOutOfBoundsException e) {
				e.printStackTrace();
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
