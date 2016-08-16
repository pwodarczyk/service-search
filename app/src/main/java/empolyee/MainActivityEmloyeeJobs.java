package empolyee;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.WorldPopulation;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivityEmloyeeJobs extends Fragment {

    // Declare Variables
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapterEmpJobs adapter;
    ParseFile image;
    String emailll;
    String str_exprience;
    String jobStatus;
    String strObjectIdEmployeeUser;
    String str_category;
    TextView tvNoJob;
    private List<WorldPopulation> worldpopulationlist;
    public String Slecteditemm;
    String empId;
    String userPhone;
    String strReasonJobCancel;

    @Override


    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        final View v = LayoutInflater.from(getActivity()).inflate(R.layout.listview_main,
                null);


        Intent i = getActivity().getIntent();

        Slecteditemm = i.getStringExtra("Slecteditem");
        new RemoteDataTask().execute();
        return v;
    }

    // RemoteDataTask AsyncTask
    class RemoteDataTask extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
//			 Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
//			mProgressDialog.setTitle("Parse.com Custom ListView Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            worldpopulationlist = new ArrayList<WorldPopulation>();
            try {
                String img = "";


                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "Jobs");
                // Locate the column named "ranknum" in Parse.com and order list
                // by ascending

                //query.orderByAscending("ranknum");

                //	query.whereExists("user_address");
//				query.whereDoesNotExist("user_name");
                //query.whereDoesNotExist("score");
                //	query.whereStartsWith("category", "Electrician");
                //	query.whereNotEqualTo("category", "Select Category");

                ParseUser userr = ParseUser.getCurrentUser();
                String current = userr.getObjectId();
                query.whereEqualTo("employee_id", current);
                query.orderByAscending("updatedAt");
                //	query.whereDoesNotExist("Select Category");
                //	query.whereNotEqualTo("10", str_exprience);
                ob = query.find();
                for (ParseObject country : ob) {
                    // Locate images in flag column

//					empId = country.getObjectId();
                    WorldPopulation map = new WorldPopulation(null,null,null,null,null,null,null,null,null);
                    //	map.setRank((String) country.get("username"));
                    emailll = (String) country.get("user_name");

                    if (emailll != null) {
                        map.setCountry((String) country.get("job_discription"));
                    } else {
                        emailll = "";
                    }


                    map.setCountry((String) country.get("job_discription"));

                    map.setEmp_phone((String) country.get("job_discription"));
                    map.setEmp_category((String) country.get("job_discription"));

                    map.setSingleEmpId(country.getObjectId());


                    str_category = (String) country.get("job_discription");
                    if (str_category != null) {
                        map.setEmp_category((String) country.get("job_discription"));
                    } else {
                        str_category = "";
                    }
                    strObjectIdEmployeeUser = (String) country.get("job_discription");

                    str_exprience = (String) country.get("user_address");

                    if (str_exprience != null) {
                        map.setEmp_experience((String) country.get("user_address"));
                    } else {
                        str_exprience = "";
                    }


                    if (strReasonJobCancel != null) {
                        map.setStrReasonJobCancel((String) country.get("reason_jobCancel"));
                    } else {
                        strReasonJobCancel = "";
                    }


                    jobStatus = (String) country.get("job_status");

                    if (jobStatus != null) {

                        map.setJob_status((String) country.get("job_status"));
                    } else {
                        jobStatus = "";
                    }


                    userPhone = (String) country.get("user_phone");

                    if (userPhone != null) {

                        map.setUserPhone(userPhone);
                    } else {
                        userPhone = "";
                    }


                    map.setEmp_experience((String) country.get("user_address"));
                    map.setEpm_name((String) country.get("user_name"));
                    map.setEmpId(empId);

                    //	map.setEmpId((String) country.get("objectId"));

                    image = (ParseFile) country.get("photo");
                    if (image != null) {
                        img = image.getUrl();
                    } else {
                        img = "";
                    }

                    map.setFlag(img);
                    worldpopulationlist.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }


        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) getActivity().findViewById(R.id.listview);
            tvNoJob = (TextView) getActivity().findViewById(R.id.tvNoJob);


            if (worldpopulationlist.isEmpty()) {
                tvNoJob.setVisibility(View.VISIBLE);
                tvNoJob.setText("No Job Available");
            } else {
                tvNoJob.setVisibility(View.GONE);


                // Pass the results into ListViewAdapter.java
                Collections.reverse(worldpopulationlist);
                adapter = new ListViewAdapterEmpJobs(getActivity(),
                        worldpopulationlist);
                // Binds the Adapter to the ListView
                listview.setAdapter(adapter);
            }    // Close the progressdialog
            mProgressDialog.dismiss();


        }
    }
}