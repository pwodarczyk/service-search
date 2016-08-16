package com.rr.generalservicesapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class ListViewAdapterUserJobs extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<WorldPopulation> worldpopulationlist = null;
    String phoneNumber, strEmpName, strEmpCategory;
    String done;
    public String strEmpId;
    ParseObject parseObject;
    String userId;
    String strUserName;
    String strRatinig;
    String getRating;
    String strCommments;
    String singleEpmId;
    String abbss;
    String imageUrl;


    float sumOfRating;
    float ratingFromUser;
    String totalRating;
    float ratingtolal;
    String strSumRating;
    String strRatingCounter;
    int intRatingCounter;
    String strGetRatingCounter;
    String strEmpObjid;


    String list;
    public Dialog dialog;
    EditText et_comments;
    RatingBar ratingBarNew;
    String strworkername, getRatingNew, getStrworkernameforrating;
    TextView tvGetRating;
    String comments;
    EditText et_jobCancelReason;
    //String stcClosed = "Panding";
    String reasonJobCancel;

    public ListViewAdapterUserJobs(Context context,
                                   List<WorldPopulation> worldpopulationlist) {
        this.context = context;
        this.worldpopulationlist = worldpopulationlist;
        inflater = LayoutInflater.from(context);
        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder {
        TextView population, epm_name, emp_address, tvjobstatusforuser, emp_category, emp_experience,
                txtJobDone, tvratingText, txtJobStatus, tvJobDiscription, tvCallMe, tvjobCancelbyUser, tv_jobCancelReason;
        Button emp_phone;
        EditText etComments;
        ParseFile icon33;
        RatingBar ratingBar;
        ImageView flag, emp_photo;


    }


    @Override
    public int getCount() {
        return worldpopulationlist.size();
    }

    @Override
    public Object getItem(int position) {
        return worldpopulationlist.get(position);
    }


    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_adaptor_user_jobs, null);

            // Locate the TextViews in listview_item.xml
            holder.epm_name = (TextView) view.findViewById(R.id.tv_client_name);
            holder.emp_category = (TextView) view.findViewById(R.id.tv_job_disription);
            holder.emp_experience = (TextView) view.findViewById(R.id.tvexperianceshow);
            holder.emp_phone = (Button) view.findViewById(R.id.btnCall);
            holder.txtJobDone = (TextView) view.findViewById(R.id.tvAddComments);
            holder.tvratingText = (TextView) view.findViewById(R.id.ratingtext);
            holder.etComments = (EditText) view.findViewById(R.id.etcomments);
            holder.txtJobStatus = (TextView) view.findViewById(R.id.tvjobstatus_for_user);
            holder.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            holder.tvCallMe = (TextView) view.findViewById(R.id.callme);
            holder.tvjobstatusforuser = (TextView) view.findViewById(R.id.tvjobstatusforuser);
            holder.tvJobDiscription = (TextView) view.findViewById(R.id.tvEmpSelfDiscription);
            holder.tvjobCancelbyUser = (TextView) view.findViewById(R.id.tvjobCanceledbyUser);

            // Locate the ImageView in listview_item.xml
            holder.flag = (ImageView) view.findViewById(R.id.imgprofile);

//			imageLoader.DisplayImage(worldpopulationlist.get(position).getFlag(),
//					holder.flag);


            view.setTag(holder);


        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        imageUrl = worldpopulationlist.get(position).getFlag();
        holder.txtJobStatus.setText(worldpopulationlist.get(position).getJob_status());
        holder.epm_name.setText(worldpopulationlist.get(position).getEpm_name());
        holder.emp_category.setText(worldpopulationlist.get(position).getEmp_category());
        holder.emp_experience.setText(worldpopulationlist.get(position).getEmp_experience());
        holder.emp_category.setText(worldpopulationlist.get(position).getEmp_category());
        holder.tvratingText.setText(worldpopulationlist.get(position).getRating());
        holder.etComments.setText(worldpopulationlist.get(position).getComments());
        holder.tvJobDiscription.setText(worldpopulationlist.get(position).getJob_Discription());

        strRatinig = worldpopulationlist.get(position).getRating();

        imageLoader.DisplayImage(imageUrl,
                holder.flag);

        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                final int numStars = ratingBar.getNumStars();
                holder.tvratingText.setText(rating + "/" + numStars);
                getRating = holder.tvratingText.getText().toString();
            }
        });
        holder.emp_phone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = worldpopulationlist.get(position).getEmp_phone();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                worldpopulationlist.get(position).getEmp_phone();
                context.startActivity(intent);

            }
        });

        holder.tvCallMe.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = worldpopulationlist.get(position).getEmp_phone();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                worldpopulationlist.get(position).getEmp_phone();
                context.startActivity(intent);
            }
        });

        holder.tvjobstatusforuser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = position;
                singleEpmId = worldpopulationlist.get(position).getSingleEmpId();
                strworkername = worldpopulationlist.get(position).getEpm_name();
                strEmpObjid = worldpopulationlist.get(position).getStrEmpObjid();

//				clicker2();
                showDialog(a);

            }
        });

        holder.txtJobDone.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                singleEpmId = worldpopulationlist.get(position).getSingleEmpId();

                phoneNumber = worldpopulationlist.get(position).getEmp_phone();
                done = worldpopulationlist.get(position).getEmp_phone();
                strEmpName = worldpopulationlist.get(position).getEpm_name();
                strCommments = worldpopulationlist.get(position).getComments();
                strEmpId = worldpopulationlist.get(position).getEmpId();
                strEmpCategory = worldpopulationlist.get(position).getEmp_category();
                Toast.makeText(context, "" + strEmpName, Toast.LENGTH_SHORT).show();
                //strRatinig = holder.tvratingText.getText().toString();
                strCommments = worldpopulationlist.get(position).getComments();
                strCommments = holder.etComments.getText().toString();
                Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
            }
        });

        // Set the results into ImageView
        imageLoader.DisplayImage(worldpopulationlist.get(position).getFlag(),
                holder.flag);

        String reason = worldpopulationlist.get(position).getJob_status();

        if (reason.equals("Cancelled")) {

            holder.tvjobstatusforuser.setVisibility(View.GONE);
            holder.txtJobStatus.setVisibility(View.GONE);

            holder.tvjobCancelbyUser.setText(worldpopulationlist.get(position).getJob_status());

            holder.tvjobCancelbyUser.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custem_alert_dialog_reason_job_cancel);
                    holder.tv_jobCancelReason = (TextView) dialog.findViewById(R.id.tv_jobCancelReason);
                    dialog.show();
                    list = worldpopulationlist.get(position).getStrReasonJobCancel();
                    holder.tv_jobCancelReason.setText(list);

                }
            });

        } else {
            holder.tvjobCancelbyUser.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = position;
                    singleEpmId = worldpopulationlist.get(position).getSingleEmpId();
                    showDialogReason(pos);

                }
            });

        }
        if (reason.equals("Completed")) {

            holder.tvjobstatusforuser.setVisibility(View.GONE);
            holder.tvjobCancelbyUser.setVisibility(View.GONE);

        }

        return view;
    }

    public void showDialog(final int postion) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custem_alert_dialog_job_closed);
        TextView worker_name;
        worker_name = (TextView) dialog.findViewById(R.id.tv_worker_name);
        tvGetRating = (TextView) dialog.findViewById(R.id.ratinformratingbar);
        worker_name.setText(strworkername);
        ratingBarNew = (RatingBar) dialog.findViewById(R.id.ratingBar2);
        et_comments = (EditText) dialog.findViewById(R.id.et_comments_new);
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


        ratingBarNew.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                final float ratting = ratingBar.getRating();
                tvGetRating.setText("" + ratting);
                getRatingNew = tvGetRating.getText().toString();

            }
        });

        btnOk.setOnClickListener(new OnClickListener() {


            public void onClick(View arg0) {
                comments = et_comments.getText().toString();
                if ((et_comments.getText().toString().equals(""))) {

                    Toast.makeText(context, "Please fill the feilds", Toast.LENGTH_SHORT).show();
                } else {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Jobs");

                    query.getInBackground(singleEpmId, new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
                            if (e == null) {
                                parseObject.put("rating", getRatingNew);
                                parseObject.put("comments", comments);
                                parseObject.put("job_status", "Completed");
//                                parseObject.put("ratingCounter", strGetRatingCounter);
                                parseObject.saveInBackground();

                                worldpopulationlist.get(postion).setRating(getRatingNew);
                                worldpopulationlist.get(postion).setComments(comments);
                                worldpopulationlist.get(postion).setJob_status("Completed");
                                getStrworkernameforrating = worldpopulationlist.get(postion).getEpm_name();
                                notifyDataSetChanged();
                                UpdateUser(postion);

                            }
                        }
                    });


                    dialog.dismiss();

                }
            }
        });
    }

    private void UpdateUser(final int postion2) {
//
        getStrworkernameforrating = worldpopulationlist.get(postion2).getEpm_name();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("EmpRating");
        String sa = strEmpObjid;
        query.whereEqualTo("empObjectId", sa);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> nameList, ParseException e) {

                int siz = nameList.size();
                double rating = 0;
                int ratcounter = 0;
                String counter = "";
                String rate = "";
                if (nameList.isEmpty()) {

                    ParseObject gameScore = new ParseObject("EmpRating");
                    gameScore.put("rating", getRatingNew);
                    gameScore.put("empObjectId", strEmpObjid);
                    gameScore.put("empName", getStrworkernameforrating);
                    gameScore.put("ratingCounter", "1");
                    //	gameScore.put("cheatMode", false);
                    gameScore.saveInBackground();


                } else if (e == null) {

                    for (int j = 0; j < nameList.size(); j++) {
                        Object rat = nameList.get(j).get("rating");
                        Object wh = nameList.get(j).get("ratingCounter");
                        String ratting = String.valueOf(rat);
                        String rattingcounter = String.valueOf(wh);
                        ratcounter = Integer.parseInt(rattingcounter);
                        ratcounter = ratcounter + 1;
                        rating = Double.valueOf(ratting);
                        double Userratting = Double.valueOf(getRatingNew);
                        rating = rating + Userratting;

                        counter = String.valueOf(ratcounter);
                        rate = String.valueOf(String.format("%.2f", rating));

                    }
                    for (ParseObject nameObj : nameList) {
                        String ob = nameObj.getObjectId();
                        nameObj.put("rating", rate);
                        nameObj.put("empObjectId", strEmpObjid);
                        nameObj.put("ratingCounter", counter);
                        nameObj.put("empName", getStrworkernameforrating);
                        nameObj.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
//									Toast.makeText(context,"Done", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                } else {
                    Log.d("Post retrieval", "Error: " + e.getMessage());
                }
            }
        });
    }


    public void showDialogReason(final int postion) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custem_alert_dialog_job_cancel);

        et_jobCancelReason = (EditText) dialog.findViewById(R.id.et_jobCancelReason);
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
                if ((et_jobCancelReason.getText().toString().equals(""))) {

                    Toast.makeText(context, "Please fill the feilds", Toast.LENGTH_SHORT).show();
                } else {

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

