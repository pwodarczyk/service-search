package com.rr.generalservicesapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import db.DataBaseClassifiedApp;


public class MainActivityEmloyeeList extends Activity {
    // Declare Variables
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapterAllEpmz adapter;
    ParseFile image;
    String emailll;
    String str_exprience;
    String strObjectIdEmployeeUser;
    String str_category;
//     List<Const> EmpList;
    private ArrayList<Const> EmpList;
    public String Slecteditemm;
    String empId;
    String str_job_status;
    String emp_SelfDiscription;
    TextView tvNoJob;
    DataBaseClassifiedApp db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview_main);
        db=new DataBaseClassifiedApp(this);
        final ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.custem_action_bar);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00a1d8")));

//        Intent i = getIntent();
//        Slecteditemm = i.getStringExtra("Slecteditem");
        try {
            getEmployeesList();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        PopulateList();
//        new RemoteDataTask().execute();
    }


    public void EmployeDetails(final String empId){
        try {
            ParseQuery<ParseObject> query = ParseQuery
                    .getQuery("EmpRating");
            query.whereEqualTo("empObjectId", empId);

            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> scoreList,
                                 ParseException e) {
                    if (e == null) {
                        double rating;
                        int ratcounter;
                        for (int j = 0; j < scoreList.size(); j++) {
                            Object rat = scoreList.get(j).get("rating");
                            Object wh = scoreList.get(j).get("ratingCounter");
                            String ratting = String.valueOf(rat);
                            String rattingcounter = String.valueOf(wh);
                            
                            rating=Double.valueOf(ratting);
                            ratcounter=Integer.parseInt(rattingcounter);
                            double EmpRatting = rating / ratcounter;
                            String EmployeeRatting=String.valueOf(EmpRatting);
                            db.updateRatting(EmployeeRatting,empId);

                        }
                    } else {
                        Log.d("score",
                                "Error: " + e.getMessage());
                    }
                }
            });


        } catch (IndexOutOfBoundsException Ie) {
            Ie.printStackTrace();
        }

    }
    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivityEmloyeeList.this);
            // Set progressdialog title
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            EmpList = new ArrayList<Const>();
            db.deleteAll();
            try {
                String img = "";

                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "_User");
                query.whereNotEqualTo("category", "Select Category");
                query.whereEqualTo("category", Slecteditemm);

                ob = query.find();
                for (ParseObject country : ob) {
                    // Locate images in flag column
                    empId = country.getObjectId();
                    emailll = (String) country.get("email");
                    str_job_status = (String) country.get("job_status");
                    str_category = (String) country.get("category");
                    emp_SelfDiscription = (String) country.get("emp_discription");
                    strObjectIdEmployeeUser = (String) country.get("objectId");
                    str_exprience = (String) country.get("experience");
                    image = (ParseFile) country.get("photo");
                    String phone=(String) country.get("phone");
                    String employename=(String) country.get("name");
                    if (image != null) {
                        img = image.getUrl();
                    } else {
                        img = "";
                    }
                    WorldPopulation map = new WorldPopulation(empId,employename,emailll,phone,str_category,str_job_status,emp_SelfDiscription,strObjectIdEmployeeUser,img);
//                    map.setFlag(img);
                    db.addEmployees(map);
//                    EmployeDetails(empId);
//                    worldpopulationlist.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
//            worldpopulationlist=db.getAllEmployees();
//            listview = (ListView) findViewById(R.id.listview);
//            tvNoJob = (TextView) findViewById(R.id.tvNoJob);
//            // Pass the results into ListViewAdapter.java
//            Collections.reverse(worldpopulationlist);
//            adapter = new ListViewAdapterAllEpmz(MainActivityEmloyeeList.this,
//                    worldpopulationlist);
//            listview.setAdapter(adapter);
//            mProgressDialog.dismiss();
//            if (worldpopulationlist.isEmpty()) {
//                tvNoJob.setVisibility(View.VISIBLE);
//                tvNoJob.setText("There is no employee exist");
//            } else {
//                tvNoJob.setVisibility(View.GONE);
//            }

//            listview.setOnItemClickListener(
//                    new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> arg0, View view,
//                                                int position, long id) {
//
//                            Toast.makeText(getApplicationContext(), "jhglj", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//            );

        }
    }
    public void PopulateList(){
//        EmpList=db.getAllEmployees();
        listview = (ListView) findViewById(R.id.listview);
        tvNoJob = (TextView) findViewById(R.id.tvNoJob);
        // Pass the results into ListViewAdapter.java
        Collections.reverse(EmpList);
        adapter = new ListViewAdapterAllEpmz(MainActivityEmloyeeList.this,EmpList
                );
        listview.setAdapter(adapter);
        mProgressDialog.dismiss();
        if (EmpList.isEmpty()) {
            tvNoJob.setVisibility(View.VISIBLE);
            tvNoJob.setText("There is no employee exist");
        } else {
            tvNoJob.setVisibility(View.GONE);
        }
    }
    public void getEmployeesList() throws JSONException {
        String emp_list = Utils.getPreferences("emp_list",getApplicationContext());
        JSONObject first = new JSONObject(emp_list);


        try {
            JSONArray JA = first.getJSONArray("data");
            Utils.EmpList.clear();
            try {
                for (int i = 0; i <= JA.length(); i++) {
                    JSONObject secondobj = JA.getJSONObject(i);
                    String id = secondobj.getString("id");
                    String result_name = secondobj.getString("name");
                    String result_email = secondobj.getString("email");
                    String result_phone = secondobj.getString("phone");
                    String result_address = secondobj.getString("address");
                    String result_image = secondobj.getString("image");
                    String result_user_type = secondobj.getString("user_type");
                    String result_cat_id = secondobj.getString("category");
                    String result_exp = secondobj.getString("experience");
                    String result_desceription = secondobj.getString("description");

//                    Const empObject = new Const(id,result_name,result_email,result_phone,result_address,result_image,result_user_type,result_cat_id,result_exp,result_desceription);
//                    Utils.EmpList.add(empObject);
                    listview = (ListView) findViewById(R.id.listview);
                    tvNoJob = (TextView) findViewById(R.id.tvNoJob);
                    // Pass the results into ListViewAdapter.java
                    Collections.reverse(EmpList);
                    adapter = new ListViewAdapterAllEpmz(MainActivityEmloyeeList.this,EmpList
                    );
                    listview.setAdapter(adapter);
                    mProgressDialog.dismiss();
                    if (EmpList.isEmpty()) {
                        tvNoJob.setVisibility(View.VISIBLE);
                        tvNoJob.setText("There is no employee exist");
                    } else {
                        tvNoJob.setVisibility(View.GONE);
                    }
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