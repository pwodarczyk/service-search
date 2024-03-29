package com.rr.generalservicesapplication.userJobsList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rr.generalservicesapplication.AndroidMultiPartEntity;
import com.rr.generalservicesapplication.AppController;
import com.rr.generalservicesapplication.CircularNetworkImageView;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.Utils;
import com.parse.ParseFile;

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
import java.util.ArrayList;
import java.util.List;

import employeesList.Constants;

public class FrUserJobsAdaptor extends ArrayAdapter<Constants> {
    private com.android.volley.toolbox.ImageLoader imageLoader;
    Context context;
    TextView    txtJobStatus;
    LayoutInflater inflater;
    List<Constants> worldpopulationlist;
    private final List<Constants> values;
    private List<Constants> cateid;
    String phone;
    public Dialog dialog;
    TextView tvGetRating;
    TextView worker_name;
    EditText et_comments;
    RatingBar ratingBarNew;

    String sttr_emp_phone, user_phone, empImage, user_name, getRatingNew,emp_name,emp_id,emp_rating,emp_name1,user_job_id;
String Str_rating_toServer,comments;
    Button btnOk;
    private ArrayList<Constants> arraylist;

    public FrUserJobsAdaptor(Context context, ArrayList<Constants> search2) {

        // TODO Auto-generated constructor stub
        super(context, 0, search2);
        this.context = context;
        this.values = search2;
        this.worldpopulationlist = search2;
        this.arraylist = new ArrayList<Constants>();
        this.arraylist.addAll(worldpopulationlist);
//        imageLoader = new ImageLoader(context);
    }


    @Override
    public int getCount() {
        return worldpopulationlist.size();
    }

    @Override
    public Constants getItem(int position) {
        return worldpopulationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
//        ViewHolder viewHolder;
        int a = position;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = inflater.inflate(R.layout.listview_adaptor_user_jobs, null);

            holder = new ViewHolder();


            // Locate the TextViews in listview_item.xml
            holder.epm_name = (TextView) view.findViewById(R.id.tv_client_name);
            holder.emp_category = (TextView) view.findViewById(R.id.tv_job_disription);
            holder.emp_experience = (TextView) view.findViewById(R.id.tvexperianceshow);
            holder.emp_phone = (Button) view.findViewById(R.id.btnCall);
            holder.txtJobDone = (TextView) view.findViewById(R.id.tvAddComments);
            holder.tvratingText = (TextView) view.findViewById(R.id.ratingtext);
//            holder.etComments = (EditText) view.findViewById(R.id.etcomments);
            holder.txtJobStatus = (TextView) view.findViewById(R.id.tvjobstatus_for_user);
            holder.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            holder.tvCallMe = (TextView) view.findViewById(R.id.callme);
            holder.tvjobstatusforuser = (TextView) view.findViewById(R.id.tvjobstatusforuser);
            holder.tvJobDiscription = (TextView) view.findViewById(R.id.tvEmpSelfDiscription);
            holder.tvjobCancelbyUser = (TextView) view.findViewById(R.id.tvjobCanceledbyUser);

            // Locate the ImageView in listview_item.xml
            holder.flag = (CircularNetworkImageView) view.findViewById(R.id.imgprofile);

            view.setTag(holder);


        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {

            String user_job_id = values.get(position).getId();
            String em_id = values.get(position).getEmp_id();
            String emp_address = values.get(position).getAddress();
             emp_name = values.get(position).getEmp_name();
               sttr_emp_phone = values.get(position).getEmp_phone();
            String user_id = values.get(position).getUser_id();
            String user_name = values.get(position).getUser_name();
            String user_address = values.get(position).getUser_address();
            String dscr = values.get(position).getJob_description();
            String rating = values.get(position).getRating();
            user_phone = values.get(position).getUser_phone();
            String   emp_Image = values.get(position).getEmp_image();
            String   status_job = values.get(position).getJob_status();
            if(status_job.equalsIgnoreCase("0")){
                holder.txtJobStatus.setText("completed");
                holder.tvjobstatusforuser.setVisibility(View.GONE);
//                holder.tvjobstatusforuser.VISIBLE(View.GONE)
            }else{
                holder.txtJobStatus.setText("pending");
                holder.tvjobstatusforuser.setVisibility(View.VISIBLE);
            }

//            String imageUrl = values.get(position).getUser_;
//            Log.d("values" ,cateName );
            imageLoader = AppController.getInstance().getImageLoader();
            holder.flag.setImageUrl(emp_Image, imageLoader);
            holder.tvratingText.setText(rating);
            holder.epm_name.setText(emp_name);
            holder.tvJobDiscription.setText(dscr);

//            String status = Utils.getPreferences("status",context);
//            if(status.equalsIgnoreCase("completed")){
//
//            }else{
//                Toast.makeText(context, "pending job", Toast.LENGTH_SHORT).show();
//            }


//          String emp_imgUrl = Utils.IMAGEURL+imageUrl;

            holder.tvjobCancelbyUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AsyncCallWS2_Decline().execute();
                }
            });

//            holder.emp_experience.setText(experience);
//            holder.emp_phone.setText(phone);
//            holder.epm_name.setText(name);
//            holder.epm_name.setText(name);
//            holder.epm_name.setText(name);
//            holder.tvEmpSelfDiscription.setText(descr);
            holder.tvjobstatusforuser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int a = position;
//                    singleEpmId = worldpopulationlist.get(position).getSingleEmpId();
//                    strworkername = worldpopulationlist.get(position).getEpm_name();
//                    strEmpObjid = worldpopulationlist.get(position).getStrEmpObjid();

//				clicker2();
                    emp_name1 = values.get(position).getEmp_name();
                    showDialog(a);
                    ratingBarNew.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                            final float ratting = ratingBar.getRating();
                       String     rating_from_server = values.get(position).getRating();
//                            float f = 0.1F;
                            double d = ratting;

                            double rating_double=Double.parseDouble(rating_from_server);
                            double final_rating=rating_double+d/10;
                            tvGetRating.setText("" + final_rating);
                            Str_rating_toServer = String.valueOf(final_rating);
                            getRatingNew = tvGetRating.getText().toString();
                            Utils.savePreferences("rating", "true", context);

                        }
                    });

                }
            });

            holder.tvCallMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + sttr_emp_phone));
                    context.startActivity(intent);

                }
            });

            // final String catId=cateid.get(position).getObjectId();
//            imageLoader.DisplayImage(cateImage, holder.addFriendImageView);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }


        view.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                try {
                    user_job_id = values.get(position).getId();
                      emp_id = values.get(position).getEmp_id();
                    String address = values.get(position).getAddress();
                    emp_name = values.get(position).getEmp_name();
                    sttr_emp_phone = values.get(position).getEmp_phone();
                    String user_id = values.get(position).getUser_id();
                    user_name = values.get(position).getUser_name();
                    String user_address = values.get(position).getUser_address();
                    user_phone = values.get(position).getUser_phone();
                    empImage = values.get(position).getEmp_image();
                    emp_rating = values.get(position).getRating();
                  String  dscr = values.get(position).getJob_description();
                    String  job_status = values.get(position).getJob_status();


                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String rating = Utils.getPreferences("rating", context);
                            if (rating.equalsIgnoreCase("true")) {
                                holder.txtJobStatus.setText("completed");
                                Utils.savePreferences("rating", "false", context);
                                Toast.makeText(context, "Thank You", Toast.LENGTH_SHORT).show();
                            } else {
                                holder.txtJobStatus.setText("pending");
                                Toast.makeText(context, "Please first give rating", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
//                    if(job_status.equalsIgnoreCase()){
//
//                    }


//                    String emp_imgUrl = Utils.IMAGEURL+imageUrl;
//                    Toast.makeText(
//                            getContext(),
//                            "You clciked an item " + emp_id,
//                            Toast.LENGTH_SHORT).show();

//                    Intent intent = new Intent(context, EmployeeDetails.class);
//                    intent.putExtra("id", id);
//                    intent.putExtra("name", name);
//                    intent.putExtra("emp_imgUrl", emp_imgUrl);
//                    intent.putExtra("experience", experience);
//                    intent.putExtra("phone", phone);
//                    intent.putExtra("description", description);
//                    context.startActivity(intent);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }

        });
        return view;
    }

    private static class ViewHolder {

        TextView population, epm_name, emp_address, tvjobstatusforuser, emp_category, emp_experience,
                txtJobDone, tvratingText, txtJobStatus, tvJobDiscription, tvCallMe, tvjobCancelbyUser, tv_jobCancelReason;
        Button emp_phone;

        ParseFile icon33;
        RatingBar ratingBar;

        CircularNetworkImageView flag, emp_photo;
    }


    public void showDialog(final int postion) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custem_alert_dialog_job_closed);

        worker_name = (TextView) dialog.findViewById(R.id.tv_worker_name);
        tvGetRating = (TextView) dialog.findViewById(R.id.ratinformratingbar);
        worker_name.setText("Employee:"+emp_name + " Contact:"+sttr_emp_phone);

        ratingBarNew = (RatingBar) dialog.findViewById(R.id.ratingBar2);
        et_comments = (EditText) dialog.findViewById(R.id.et_comments_new);

        dialog.show();

        Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
         btnOk = (Button) dialog.findViewById(R.id.okButton);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comments=et_comments.getText().toString();
                Utils.savePreferences("status", "pending", context);
//String settings = Utils.getPreferences("logged",context);
                String rating = Utils.getPreferences("rating",context);
                if(rating.equalsIgnoreCase("true") && !TextUtils.isEmpty(comments)&& !TextUtils.isEmpty(user_job_id)&&!(comments =="" && !(comments ==null) && !comments.isEmpty())) {
                    new AsyncCallWS2().execute();
                    dialog.dismiss();
                    Utils.savePreferences("status", "completed", context);
//                    String rating1 = Utils.getPreferences("rating", context);
//                    if (rating1.equalsIgnoreCase("true")) {
//                    txtJobStatus.setText("completed");
//                        Utils.savePreferences("rating", "false", context);
//                        Toast.makeText(context, "Thank You", Toast.LENGTH_SHORT).show();
//                    } else {
//                        txtJobStatus.setText("pending");
//                        Toast.makeText(context, "Please first give rating", Toast.LENGTH_SHORT).show();
//                    }
//                    ViewHolder holder = null;
//                    holder.txtJobStatus.setText("completed");
                    Toast.makeText(context, "Thank You....!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Click on item and Give rating and comments first", Toast.LENGTH_LONG).show();
                }
            }
        });
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog

                dialog.dismiss();
            }
        });
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
    public void logupload_Decline() {
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




            entity.addPart("id", new StringBody(user_job_id));

            entity.addPart("user_cancel_job", new StringBody("true"));



//            Log.d("Encodeed Img ", encodedImage);

            post.setEntity(entity);
            response = client.execute(post);
                    /* Checking response */
            if (response != null) {
                InputStream in = response.getEntity().getContent();
                String result = convertInputStreamToString(in);
//                Utils.savePreferences("result", result, context);
                Log.d("InputStream", result);
//                Utils.savePreferences("rating", "false", context);
                JSONObject jsonObject;
                jsonObject = new JSONObject(result);

//                JSONObject object = jsonObject.getJSONObject("message");
//                String user_id = object.getString("id");
//                String user_phone = object.getString("phone");
//                String user_name = object.getString("name");
//                Utils.savePreferences("user_name", user_name, this);
//                Utils.savePreferences("user_id", user_id, this);
//                Utils.savePreferences("user_phone", user_phone, this);
//                result_email = object.getString("email");
//                result_password = object.getString("password");
//                user_type = object.getString("user_type");
//                if(result_email.equalsIgnoreCase(usertxt)){
//                    Intent intent=new Intent(LoginActivity.this, MainActivityDrawer.class);
//                    startActivity(intent);
//                    Utils.savePreferences("login", "true", this);
//                    Utils.savePreferences("usertype", user_type, this);
//                    Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
//
//                }else{
//                    Toast.makeText(LoginActivity.this, "Incorrect Email Or Password", Toast.LENGTH_SHORT).show();
//                }
//                String    strParsedValue = "Attribute 1 value => " + user_phone;
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



            comments=et_comments.getText().toString();
            entity.addPart("id", new StringBody(user_job_id));
            entity.addPart("rating", new StringBody(Str_rating_toServer));
            entity.addPart("comments", new StringBody(comments));
            entity.addPart("emp_rating", new StringBody("true"));



//            Log.d("Encodeed Img ", encodedImage);

            post.setEntity(entity);
            response = client.execute(post);
                    /* Checking response */
            if (response != null) {
                InputStream in = response.getEntity().getContent();
                String result = convertInputStreamToString(in);
//                Utils.savePreferences("result", result, context);
                Log.d("InputStream", result);
                Utils.savePreferences("rating", "false", context);
                JSONObject jsonObject;
                jsonObject = new JSONObject(result);

//                JSONObject object = jsonObject.getJSONObject("message");
//                String user_id = object.getString("id");
//                String user_phone = object.getString("phone");
//                String user_name = object.getString("name");
//                Utils.savePreferences("user_name", user_name, this);
//                Utils.savePreferences("user_id", user_id, this);
//                Utils.savePreferences("user_phone", user_phone, this);
//                result_email = object.getString("email");
//                result_password = object.getString("password");
//                user_type = object.getString("user_type");
//                if(result_email.equalsIgnoreCase(usertxt)){
//                    Intent intent=new Intent(LoginActivity.this, MainActivityDrawer.class);
//                    startActivity(intent);
//                    Utils.savePreferences("login", "true", this);
//                    Utils.savePreferences("usertype", user_type, this);
//                    Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
//
//                }else{
//                    Toast.makeText(LoginActivity.this, "Incorrect Email Or Password", Toast.LENGTH_SHORT).show();
//                }
//                String    strParsedValue = "Attribute 1 value => " + user_phone;
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

        final ProgressDialog dialog = new ProgressDialog(context);

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
    private class AsyncCallWS2_Decline extends AsyncTask<String, Void, Void> {

        final ProgressDialog dialog = new ProgressDialog(context);

        @Override
        protected Void doInBackground(String... params) {
            logupload_Decline();
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