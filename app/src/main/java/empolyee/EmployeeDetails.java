package empolyee;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rr.generalservicesapplication.AndroidMultiPartEntity;
import com.rr.generalservicesapplication.AppController;
import com.rr.generalservicesapplication.CircularNetworkImageView;
import com.rr.generalservicesapplication.ImageLoader;
import com.rr.generalservicesapplication.MainActivity;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.Utils;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeDetails extends Activity{

    private com.android.volley.toolbox.ImageLoader imageLoader;
    Button btnCall, btnstartJob;
    CircularNetworkImageView profileimage;
    ImageView profileCoverPhoto;
    TextView tvDiscription,tv_emp_category,tv_emp_name;
    String strEmpName,emp_Category, phoneNumber,experience;
    String empImage;
    public Dialog dialog;
    TextView date, time;
    EditText user_address, discription;
    public Bitmap customdialog ;
    TextView address, txt_addresslocal;
    String add2, addresslocal;
    String singleEpmId;
    ParseObject parseObject;
    String userId;
    String	strUserName;
    String strEmpId;
    String empSelfDescription;
    String job_descr,str_user_address;
    Context context;
    String   user_phone,user_id,user_name;


    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_emp);
        context = this;
//        imageLoader = new ImageLoader(getApplicationContext());
        tvDiscription = (TextView)findViewById(R.id.tv_discription_detailed);
        tv_emp_name = (TextView)findViewById(R.id.tv_emp_name);
        tv_emp_category = (TextView)findViewById(R.id.tv_emp_category);
        btnCall = (Button)findViewById(R.id.btn_cal_to_emp);
        btnstartJob = (Button)findViewById(R.id.btn_start_job);
        profileimage = (CircularNetworkImageView)findViewById(R.id.imgprofile);
        profileCoverPhoto = (ImageView)findViewById(R.id.bgprofile);



        final ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.custem_action_bar);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("");
        actionBar.setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
//        actionBar.setDisplayUseLogoEnabled(false);
      //  actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00a1d8")));





        Intent i = getIntent();

        experience = i.getStringExtra("experience");

        strEmpName = i.getStringExtra("name");
        phoneNumber = i.getStringExtra("phone");
        empImage = i.getStringExtra("emp_imgUrl");
        singleEpmId = i.getStringExtra("id");
        empSelfDescription = i.getStringExtra("description");
        tv_emp_name.setText(strEmpName);


        tv_emp_category.setText(experience +"Years Exp");
//        emp_Category = i.getStringExtra("emp_Category");
//         StringToBitMap(empImage);
       // profileCoverPhoto.setImageBitmap(StringToBitMap(empImage));
//        tv_emp_category.setText(emp_Category);
        tvDiscription.setText(empSelfDescription);
//        imageLoader.DisplayImage(empImage, profileimage);
//        imageLoader.DisplayImage(empImage,profileCoverPhoto);

        imageLoader = AppController.getInstance().getImageLoader();


//        String emp_imgUrl = Utils.IMAGEURL + empImage;


        profileimage.setImageUrl(empImage, imageLoader);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                startActivity(intent);


            }
        });


        btnstartJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          String usertype = Utils.getPreferences("usertype",context);
                if(usertype.equalsIgnoreCase("2")){
                    String login1 = Utils.getPreferences("login", context);
                    if(login1.equalsIgnoreCase("true")) {
                        showDialog();
                    }else{
                        Intent intent=new Intent(EmployeeDetails.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(EmployeeDetails.this, "Please Login first", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(EmployeeDetails.this, "Sorry .... ! You are registered as employee", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    public Bitmap StringToBitMap(String empImage) {
        try {
            byte[] encodeByte = Base64.decode(empImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }



    public void showDialog(){
        // Create custom dialog object
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Include dialog.xml file
        dialog.setContentView(R.layout.custem_alert_dialog);
        dialog.setCancelable(false);
        // Set dialog title
        //   dialog.setTitle("Custom Dialog");
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // set values for custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.tvdate);
        text.setText("Custom dialog Android example.");
        ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
        // image.setImageResource(R.drawable.shape);
        date = (TextView) dialog.findViewById(R.id.tvdate);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM, dd yyyy");
        String currentDate = sdf.format(new Date());
//		String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        date.setText(currentDate);
        time = (TextView) dialog.findViewById(R.id.tv_worker_name);
        SimpleDateFormat stf = new SimpleDateFormat("HH:mma");
        String currentTimeString = stf.format(new Date());
        time.setText(currentTimeString);

        user_address = (EditText)dialog.findViewById(R.id.et_user_address);
        //	strUserAddress = user_address.getText().toString();




        discription = (EditText)dialog.findViewById(R.id.et_comments_new);
String dscr=discription.getText().toString();
        String address=user_address.getText().toString();
        if(TextUtils.isEmpty(dscr)){
            Toast.makeText(EmployeeDetails.this, "Please enter job description", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(address)){
            Toast.makeText(EmployeeDetails.this, "Please enter your address", Toast.LENGTH_SHORT).show();
        }
        image.setImageBitmap(customdialog);

        dialog.show();

        Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
        //address = (TextView) dialog.findViewById(R.id.tvaddress);
        txt_addresslocal = (TextView) dialog.findViewById(R.id.tvaddresslocal);
        //address.setText(add2);
        txt_addresslocal.setText(addresslocal);
        Button btnOk = (Button) dialog.findViewById(R.id.okButton);
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {

                if((user_address.getText().toString().equals("")) || (discription.getText().toString().equals("")))
                {

                    Toast.makeText(context, "Please fill the feilds", Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(context, "job start", Toast.LENGTH_SHORT).show();

                    //dialog.holder.txtJobDone.setText("job done");
                    new AsyncCallWS2().execute();
//                    clicker();
//                    clicker2();
                    dialog.dismiss();
                }
            }
        });
    }


    public void clicker2(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.getInBackground(singleEpmId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    parseObject.put("job_status", "job panding");
                    parseObject.saveInBackground();

                }
            }
        });
    }


    public void clicker(){

        //ParseUser usern = new ParseUser();
        parseObject = new ParseObject("Jobs");

        userId =	ParseUser.getCurrentUser().getObjectId();
        ParseUser User=ParseUser.getCurrentUser();
        strUserName = User.getUsername();
        String phoneNo=User.get("phone").toString();
//	ParseFile	image = (ParseFile) parseObject.get("photo");
        parseObject.put("user_phone", phoneNo);

        parseObject.put("employee_name", strEmpName);
        //parseObject.put("category", spcategory);
        //parseObject.put("experience", experiencetxt);
        parseObject.put("employee_phone", phoneNumber);
        parseObject.put("user_id", userId);
        parseObject.put("user_name", strUserName);
        discription.getText().toString();
        parseObject.put("employee_image2", empImage);
        parseObject.put("user_address", user_address.getText().toString());
        parseObject.put("employee_id", strEmpId);
        parseObject.put("job_discription", discription.getText().toString());
        parseObject.put("job_status", "Panding");

        //    parseObject.put("emp_category", strEmpCategory);
        //parseObject.put("address", s);
        parseObject.saveInBackground();


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
//            File file = new File(ImagePath);
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new AndroidMultiPartEntity.ProgressListener() {

                        @Override
                        public void transferred(long num) {
//                                publishProgress((int) ((num / (float) totalSize) * 100));
                        }
                    });

            user_name = Utils.getPreferences("user_name1",context);
            user_id = Utils.getPreferences("user_id",context);
            user_phone = Utils.getPreferences("user_phone",context);

//            entity.addPart("image", new FileBody(file));
            job_descr=  discription.getText().toString();
            str_user_address = user_address.getText().toString();
            entity.addPart("emp_image", new StringBody(empImage));
            entity.addPart("user_id", new StringBody(user_id));
            entity.addPart("user_name", new StringBody(user_name));
            entity.addPart("emp_name", new StringBody(strEmpName));
            entity.addPart("emp_phone", new StringBody(phoneNumber));

            entity.addPart("user_phone", new StringBody(user_phone));
//            entity.addPart("user_id", new StringBody(user_id));
            entity.addPart("emp_id", new StringBody(singleEpmId));
            entity.addPart("user_address", new StringBody(str_user_address));
            entity.addPart("job_description", new StringBody(job_descr));
            entity.addPart("rating", new StringBody("1"));
            entity.addPart("start_job", new StringBody("true"));



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
}
