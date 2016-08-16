package userProfile;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rr.generalservicesapplication.AndroidMultiPartEntity;
import com.rr.generalservicesapplication.AppController;
import com.rr.generalservicesapplication.Const;
import com.rr.generalservicesapplication.MainActivityEmloyeeList;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.SpinAdapterT;
import com.rr.generalservicesapplication.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import drawer.MainActivityDrawer;
import employeesList.CategoryVehical;
import testing.JsonRequestActivity;

/**
 * Created by farhan on 6/20/2015.
 */
public class SelectCategory extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Button btnseachcategory;
Context context;

    String result ;
    String id_employee;



    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.selectcategory,
                null);
        context=getActivity();
        final ActionBar actionBar = getActivity().getActionBar();
        actionBar.setCustomView(R.layout.custem_action_bar);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        actionBar.setDisplayShowHomeEnabled(true);

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00a1d8")));

        spinner = (Spinner) v.findViewById(R.id.spinnercat);



        btnseachcategory = (Button)v.findViewById(R.id.btnseachcategory);

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
//                Toast.makeText(getActivity(), "Id" + id_employee, Toast.LENGTH_SHORT).show();
                Log.d("InputStream", id_employee);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnseachcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncCallWS2().execute();
          String setting2 = Utils.getPreferences("result",context);
//                Toast.makeText(getActivity(), "Response" +setting2, Toast.LENGTH_LONG).show();

//
//                Intent intent = new Intent(getActivity(), JsonRequestActivity.class);
////
//                startActivity(intent);
//                servicename = spinner.getSelectedItem().toString();
//
//                if (servicename.equals("Select Category")) {
//                    Toast.makeText(getActivity(), "Please select a category", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    Toast.makeText(getActivity(), servicename, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), MainActivityEmloyeeList.class);
//                    intent.putExtra("Slecteditem", servicename);
//                    startActivity(intent);
//                }
            }
        });

        return v;
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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




            entity.addPart("category", new StringBody(id_employee));
            entity.addPart("employees", new StringBody("true"));



//            Log.d("Encodeed Img ", encodedImage);

            post.setEntity(entity);
            response = client.execute(post);
                    /* Checking response */
            if (response != null) {
                InputStream in = response.getEntity().getContent();
                 result = convertInputStreamToString(in);
                Utils.savePreferences("result", result, getActivity());
                Utils.savePreferences("emp_list", result, getActivity());
                Intent intent = new Intent(getActivity(), CategoryVehical.class);
//                    intent.putExtra("Slecteditem", servicename);
                    startActivity(intent);
                Log.d("InputStream", result);
//                JSONObject first = new JSONObject(result);
//
//
//                try {
//                    JSONArray JA = first.getJSONArray("data");
//                    Utils.EmpList.clear();
//                    try {
//                        for (int i = 0; i <= JA.length(); i++) {
//                            JSONObject secondobj = JA.getJSONObject(i);
//                            String id = secondobj.getString("id");
//                            String result_name = secondobj.getString("name");
//                            String result_email = secondobj.getString("email");
//                            String result_phone = secondobj.getString("phone");
//                            String result_address = secondobj.getString("address");
//                            String result_image = secondobj.getString("image");
//                            String result_user_type = secondobj.getString("user_type");
//                            String result_cat_id = secondobj.getString("category");
//                            String result_exp = secondobj.getString("experience");
//                            String result_desceription = secondobj.getString("description");
//
//                            Const empObject = new Const(id,result_name,result_email,result_phone,result_address,result_image,result_user_type,result_cat_id,result_exp,result_desceription);
//                            Utils.EmpList.add(empObject);
////									db.addComments(comment);
//                        }
//                    } catch (JSONException | IndexOutOfBoundsException e) {
//                        e.printStackTrace();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                JSONObject jsonObject;
                jsonObject = new JSONObject(result);

//                JSONObject object = jsonObject.getJSONObject("message");
//                String attr1 = object.getString("id");
//                result_email = object.getString("email");
//                result_password = object.getString("password");
//                if(result_email.equalsIgnoreCase(usertxt)){
//                    Intent intent=new Intent(LoginActivity.this, MainActivityDrawer.class);
//                    startActivity(intent);
//                    Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
//
//                }else{
//                    Toast.makeText(LoginActivity.this, "Incorrect Email Or Password", Toast.LENGTH_SHORT).show();
//                }
//                String    strParsedValue = "Attribute 1 value => " + attr1;
//                strParsedValue += "\n Attribute 2 value => " + result_email;

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

        final ProgressDialog dialog = new ProgressDialog(getActivity());

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
