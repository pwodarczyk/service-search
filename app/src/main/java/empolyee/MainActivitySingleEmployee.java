package empolyee;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rr.generalservicesapplication.ImageLoader;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.WorldPopulation;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivitySingleEmployee extends Fragment {
    // Declare Variables
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapterSingleEmp adapter;
    ParseFile image;
    String emailll;
    String str_exprience;
    String strObjectIdEmployeeUser;
    String str_category;
    private List<WorldPopulation> worldpopulationlist;
    public  String Slecteditemm;
    String empId;
    int milliseconds;
    String username;
//    String strpassword;
    String strEmail;
    String strPhone;
    String strAddress;
    String description;
    String str_job_status;
    ParseFile file;
    ImageLoader imageLoader;
    String imageUrl;
    Bitmap bitmap;

    int	REQUEST_IMAGE_CAPTURE = 100;
    String myUsername, myAddress, myEmail, myPhone, myExperience, myDescription;

    String empName;
    ProgressDialog dialogg;

    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }


    TextView population,epm_name, emp_address, emp_category, emp_experience, txtJobDone, tvUpdateProfile;
    Button emp_phone,btnUpdate,btnCancel;
    EditText etsignupname,etsignupusername,etsignuppassword,etsignupemail,etsignupphone,etsignupaddress,etexperiance,et_epm_discription;
    ImageView flag, imgprofile,bgprofile;
    Spinner spinner;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.mainprofileemp,
                null);



        imageLoader = new ImageLoader(getActivity());

        epm_name = (TextView) view.findViewById(R.id.tv_client_name);
        emp_category = (TextView) view.findViewById(R.id.tv_job_disription);
        emp_experience = (TextView) view.findViewById(R.id.tvexperianceshow);
        emp_phone = (Button)view.findViewById(R.id.btnCall);
        txtJobDone = (TextView)view.findViewById(R.id.tvjobstatusforuser);
        tvUpdateProfile =(TextView) view.findViewById(R.id.tvUpdateProfile);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdateEmpp);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        bgprofile = (ImageView) view.findViewById(R.id.bgprofile);
        etsignupname = (EditText)view.findViewById(R.id.etsignupname);
        etsignupusername = (EditText)view.findViewById(R.id.etsignupusername);
        etsignuppassword = (EditText)view.findViewById(R.id.etsignuppassword);
        etsignupemail = (EditText)view.findViewById(R.id.etsignupemail);
        etsignupphone = (EditText)view.findViewById(R.id.etsignupphone);
        etsignupaddress = (EditText)view.findViewById(R.id.etsignupaddress);
        etexperiance = (EditText)view.findViewById(R.id.etexperiance);
        et_epm_discription = (EditText)view.findViewById(R.id.et_epm_discription);
        spinner = (Spinner) view.findViewById(R.id.spinner_category);

        // Locate the ImageView in listview_item.xml
        imgprofile = (ImageView) view.findViewById(R.id.imgprofile);



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
                Fragment fragment = new MainActivityEmloyeeJobs();
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
				myExperience = etexperiance.getText().toString();
                myDescription = et_epm_discription.getText().toString();
//              myPassword  = etsignuppassword.getText().toString();
//                resetPassword();
                clicker2();

            }
        });



        Intent i = getActivity().getIntent();

        Slecteditemm = i.getStringExtra("Slecteditem");
        new RemoteDataTask().execute();
        return view;
    }

    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
			mProgressDialog = new ProgressDialog(getActivity());
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
                String img="";



                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "_User");

                // Locate the column named "ranknum" in Parse.com and order list
                // by ascending
                ParseUser User=ParseUser.getCurrentUser();
           String     strUserName = User.getUsername();
                query.orderByAscending("ranknum");
                //query.whereDoesNotExist("score");
                //	query.whereStartsWith("category", "Electrician");
               // query.whereNotEqualTo("category", "Select Category");
                query.whereEqualTo("username", strUserName);
               // query.whereEqualTo("password", MainActivity.passwordtxt);
                //	query.whereDoesNotExist("Select Category");
                //	query.whereNotEqualTo("10", str_exprience);
                ob = query.find();
                for (ParseObject country : ob) {
                    // Locate images in flag column

                    empId =	country.getObjectId();

                    WorldPopulation map = new WorldPopulation(null,null,null,null,null,null,null,null,null);
                    //	map.setRank((String) country.get("username"));
                    emailll = (String) country.get("email");

                    if(emailll!=null) {
                        map.setCountry((String) country.get("email"));
                    }else{
                        emailll="";
                    }



                    map.setCountry((String) country.get("email"));


                    map.setEmp_category((String) country.get("category"));

                    str_category = (String) country.get("category");
                    if(str_category!=null) {
                        map.setEmp_category((String) country.get("category"));
                    }else{
                        str_category="";
                    }


                    str_job_status = (String) country.get("job_status");
                    if(str_job_status!=null) {
                        map.setJob_status((String) country.get("job_status"));
                    }else{
                        str_job_status="";
                    }


                    strAddress = (String) country.get("address");
                    if(strAddress!=null) {
                        map.setEmp_address((String) country.get("address"));
                    }else{
                        strAddress="";
                    }



                    strPhone = (String) country.get("phone");
                    if(strPhone!=null) {
                        map.setEmp_phone((String) country.get("phone"));
                    }else{
                        strPhone="";
                    }



                    username = (String) country.get("username");
                    if(username!=null) {
                        map.setStrUserName((String) country.get("username"));
                    }else{
                        username="";
                    }

//                    strpassword = (String) country.get("password");
//                    if(strpassword!=null) {
//                        map.setPassword((String) country.get("password"));
//                    }else{
//                        strpassword="";
//                    }

                    strEmail = (String) country.get("email");
                    if(strEmail!=null) {
                        map.setEmail((String) country.get("email"));
                    }else{
                        strEmail="";
                    }

                    str_exprience = (String) country.get("experience");

                    if(str_exprience!=null) {
                        map.setEmp_experience((String) country.get("experience"));
                    }else{
                        str_exprience="";
                    }

                  //  imageUrl =	image.getUrl();


                    description = (String) country.get("emp_discription");

                    if(description!=null) {
                        map.setEmp_discription((String) country.get("emp_discription"));
                    }else{
                        description="";
                    }

                    strObjectIdEmployeeUser = (String) country.get("objectId");


                    empName = (String) country.get("name");
                    //	map.setEmp_experience((String) country.get("experience"));
                    map.setEpm_name((String) country.get("name"));
                    map.setEmpId(empId);

                    //	map.setEmpId((String) country.get("objectId"));

                    image = (ParseFile) country.get("photo");
                    if(image!=null) {
                        imageUrl = image.getUrl();
                    }else{
                        imageUrl="";
                    }

                    map.setFlag(imageUrl);
                    worldpopulationlist.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            etsignupname.setText(empName);
            etsignupusername.setText(username);
            etsignupemail.setText(strEmail);
            etsignupphone.setText(strPhone);
            etsignupaddress.setText(strAddress);

            etexperiance.setText(str_exprience);
            et_epm_discription.setText(description);


            if(image== null) {


                final int stub_id = R.drawable.temp_img;
                bgprofile.setImageResource(stub_id);
                imgprofile.setImageResource(stub_id);

            }else {
                imageUrl = image.getUrl();
                imageLoader.DisplayImage(imageUrl,
                        imgprofile);

                imageLoader.DisplayImage(imageUrl,
                        bgprofile);

            }
            mProgressDialog.dismiss();
            // Close the progressdialog
            	mProgressDialog.dismiss();
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
                    parseObject.put("experience", myExperience);
                    parseObject.put("emp_discription", myDescription);

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


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // Compress image to lower quality scale 1 - 100
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
            byte[] image = stream.toByteArray();
            file = new ParseFile("profile"+".png",image);
//            	Bitmap bbmm = MLRoundedImageViewprofilepic.getCroppedBitmap(imageBitmap, 200);
            imgprofile.setImageBitmap(imageBitmap);
            bgprofile.setImageBitmap(imageBitmap);
        }
    }

}