package com.rr.generalservicesapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import empolyee.EmployeeDetails;

public class ListViewAdapterAllEpmz extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<Const> worldpopulationlist = null;
    private ArrayList<Const> arraylist;
    String phoneNumber, strEmpName, strEmpCategory;
    String done;
    public String strEmpId;
    ParseObject parseObject;
    String userId;
    String strUserName;
    String empImage;
    int POSITION;
    public Dialog dialog;
    TextView date, time;
    public Bitmap customdialog;
    TextView address, txt_addresslocal;
    String add2, addresslocal;
    EditText user_address, discription;
    String strUserAddress;
    String singleEpmId;
    String jobStatus;
    String empSelfDescription;

    public ListViewAdapterAllEpmz(Context context,
                                  List<Const> worldpopulationlist) {
        this.context = context;
        this.worldpopulationlist = worldpopulationlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(worldpopulationlist);
        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder {
        TextView ratting, epm_name, emp_address, emp_category, emp_experience, txtJobDone, tvCallMe, tvEmpSelfDiscription;
        Button emp_phone;
        ImageView flag, emp_photo;

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
            view = inflater.inflate(R.layout.urgent_item, null);
            POSITION = position;
            // Locate the TextViews in listview_item.xml
            holder.epm_name = (TextView) view.findViewById(R.id.tv_client_name);
            holder.emp_category = (TextView) view.findViewById(R.id.tv_job_disription);
            holder.emp_experience = (TextView) view.findViewById(R.id.tvexperianceshow);
            holder.ratting = (TextView) view.findViewById(R.id.ratingtext);
            holder.emp_phone = (Button) view.findViewById(R.id.btnCall);
            holder.tvEmpSelfDiscription = (TextView) view.findViewById(R.id.tvEmpSelfDiscription);
            holder.tvCallMe = (TextView) view.findViewById(R.id.callme);
            holder.txtJobDone = (TextView) view.findViewById(R.id.tvjobstatusforuser);
            // Locate the ImageView in listview_item.xml
            holder.flag = (ImageView) view.findViewById(R.id.imgprofile);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
//        holder.ratting.setText(worldpopulationlist.get(position).getRating());
//        holder.epm_name.setText(worldpopulationlist.get(position).getEpm_name());
//        holder.tvEmpSelfDiscription.setText(worldpopulationlist.get(position).getEmp_discription());
//        holder.emp_category.setText(worldpopulationlist.get(position).getEmp_category());
//        holder.emp_experience.setText(worldpopulationlist.get(position).getEmp_experience());
        //  worldpopulationlist.get(position).getFlag();


        holder.emp_phone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                phoneNumber = worldpopulationlist.get(position).getEmp_phone();
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
//                worldpopulationlist.get(position).getEmp_phone();
//                context.startActivity(intent);
            }
        });


        holder.tvCallMe.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                phoneNumber = worldpopulationlist.get(position).getEmp_phone();
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
//                worldpopulationlist.get(position).getEmp_phone();
//                context.startActivity(intent);
            }
        });

        holder.txtJobDone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//				holder.txtJobDone.setText(worldpopulationlist.get(position).getJob_status());
//                singleEpmId = worldpopulationlist.get(position).getSingleEmpId();
//                jobStatus = worldpopulationlist.get(position).getJob_status();
//                phoneNumber = worldpopulationlist.get(position).getEmp_phone();
//                done = worldpopulationlist.get(position).getEmp_phone();
//                strEmpName = worldpopulationlist.get(position).getEpm_name();
//                strEmpCategory = worldpopulationlist.get(position).getEmp_category();
//                strEmpId = worldpopulationlist.get(position).getEmpId();
//                empImage = worldpopulationlist.get(position).getFlag();

                showDialog();
            }
        });


        // Set the results into ImageView
//        imageLoader.DisplayImage(worldpopulationlist.get(position).getFlag(),
//                holder.flag);
        // Listen for ListView  bmp Item Click


        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(context, "position: " + position, Toast.LENGTH_SHORT).show();
//                strEmpName = worldpopulationlist.get(position).getEpm_name();
//                empImage = worldpopulationlist.get(position).getFlag();
//                String emp_Category = worldpopulationlist.get(position).getEmp_category();
//                singleEpmId = worldpopulationlist.get(position).getSingleEmpId();
//                phoneNumber = worldpopulationlist.get(position).getEmp_phone();
//                strEmpId = worldpopulationlist.get(position).getEmpId();
////                String userphone = worldpopulationlist.get(position).getUserPhone();
//                empSelfDescription = worldpopulationlist.get(position).getEmp_discription();

                Intent intent = new Intent(context, EmployeeDetails.class);

//                intent.putExtra("empImage", empImage);
//                intent.putExtra("strEmpName", strEmpName);
//                intent.putExtra("empSelfDescription", empSelfDescription);
////                intent.putExtra("emp_Category", emp_Category);
//                intent.putExtra("phoneNumber", phoneNumber);
//                intent.putExtra("singleEpmId", singleEpmId);
//                intent.putExtra("strEmpId", strEmpId);
//                intent.putExtra("userphone",userphone);
                context.startActivity(intent);
            }
        });

        return view;
    }

    public void clicker() {

        //ParseUser usern = new ParseUser();
        parseObject = new ParseObject("Jobs");

        userId = ParseUser.getCurrentUser().getObjectId();
        ParseUser User = ParseUser.getCurrentUser();
        strUserName = User.getUsername();
        String phoneNo = User.get("phone").toString();
//	ParseFile	image = (ParseFile) parseObject.get("photo");
        parseObject.put("user_phone", phoneNo);
        parseObject.put("employee_name", strEmpName);
        //parseObject.put("category", spcategory);
        //parseObject.put("experience", experiencetxt);
        parseObject.put("employee_phone", done);
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


        //Toast.makeText(context, "" + strEmpId, Toast.LENGTH_SHORT).show();


    }


    public void showDialog() {
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

        user_address = (EditText) dialog.findViewById(R.id.et_user_address);
        //	strUserAddress = user_address.getText().toString();


        discription = (EditText) dialog.findViewById(R.id.et_comments_new);

        image.setImageBitmap(customdialog);

        dialog.show();

        Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
        //address = (TextView) dialog.findViewById(R.id.tvaddress);
        txt_addresslocal = (TextView) dialog.findViewById(R.id.tvaddresslocal);
        //address.setText(add2);
        txt_addresslocal.setText(addresslocal);
        Button btnOk = (Button) dialog.findViewById(R.id.okButton);
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new OnClickListener() {


            public void onClick(View arg0) {

                if ((user_address.getText().toString().equals("")) || (discription.getText().toString().equals(""))) {

                    Toast.makeText(context, "Please fill the feilds", Toast.LENGTH_SHORT).show();
                } else {

                    clicker();
                    dialog.dismiss();
                }
            }
        });
    }


}
