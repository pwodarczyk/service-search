package employeesList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.rr.generalservicesapplication.AppController;
import com.rr.generalservicesapplication.CircularNetworkImageView;
import com.rr.generalservicesapplication.Const;
import com.rr.generalservicesapplication.ImageLoader;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.Utils;

import java.util.ArrayList;
import java.util.List;

import empolyee.EmployeeDetails;

public class CategoryAdaptor extends ArrayAdapter<Constants> {
    private com.android.volley.toolbox.ImageLoader imageLoader;
    Context context;
    LayoutInflater inflater;
    List<Constants> worldpopulationlist ;
    private final List<Constants> values;
    private List<Constants> cateid ;
    String phone;

    private ArrayList<Constants> arraylist;

    public CategoryAdaptor(Context context, ArrayList<Constants> search2) {

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
        ViewHolder viewHolder;
        int a = position;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = inflater.inflate(R.layout.urgent_item, null);

            holder = new ViewHolder();


            holder.epm_name = (TextView) view.findViewById(R.id.tv_client_name);
            holder.emp_category = (TextView) view.findViewById(R.id.tv_job_disription);
            holder.emp_experience = (TextView) view.findViewById(R.id.tvexperianceshow);
            holder.ratting = (TextView) view.findViewById(R.id.ratingtext);
            holder.emp_phone = (Button) view.findViewById(R.id.btnCall);
            holder.tvEmpSelfDiscription = (TextView) view.findViewById(R.id.tvEmpSelfDiscription);
            holder.tvCallMe = (TextView) view.findViewById(R.id.callme);
            holder.txtJobDone = (TextView) view.findViewById(R.id.tvjobstatusforuser);

            holder.flag = (CircularNetworkImageView) view.findViewById(R.id.imgprofile);

            view.setTag(holder);


        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            String name = values.get(position).getName();
            String id = values.get(position).getId();
            String address = values.get(position).getAddress();
            String experience = values.get(position).getExperience();
             phone = values.get(position).getPhone();
            String descr = values.get(position).getDescription();
            String imageUrl = values.get(position).getImage();
            String emp_rating = values.get(position).getRating();
//            Log.d("values" ,cateName );
            holder.ratting.setText(emp_rating);
            holder.epm_name.setText(name);
//            holder.emp_experience.setText(experience);
//            holder.emp_phone.setText(phone);
//            holder.epm_name.setText(name);
//            holder.epm_name.setText(name);
//            holder.epm_name.setText(name);
            holder.tvEmpSelfDiscription.setText(descr);
            imageLoader = AppController.getInstance().getImageLoader();


          String emp_imgUrl = Utils.IMAGEURL+imageUrl;


            holder.flag.setImageUrl(emp_imgUrl, imageLoader);

            holder.tvCallMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                    context.startActivity(intent);

                }
            });

            // final String catId=cateid.get(position).getObjectId();
//            imageLoader.DisplayImage(cateImage, holder.addFriendImageView);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }


        view.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                try {
                    String id = values.get(position).getId();
                    String name = values.get(position).getName();
                    String imageUrl = values.get(position).getImage();
                    String phone = values.get(position).getPhone();
                    String description = values.get(position).getDescription();
                    String experience = values.get(position).getExperience();

                    String emp_imgUrl = Utils.IMAGEURL+imageUrl;
//                    Toast.makeText(
//                            getContext(),
//                            "You clciked an item"+id,
//                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, EmployeeDetails.class);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    intent.putExtra("emp_imgUrl", emp_imgUrl);
                    intent.putExtra("experience", experience);
                    intent.putExtra("phone", phone);
                    intent.putExtra("description", description);
                    context.startActivity(intent);
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }

        });
        return view;
    }
    private static class ViewHolder {
        CircularNetworkImageView flag, emp_photo;

        Button emp_phone;
        TextView ratting, epm_name, emp_address, emp_category, emp_experience, txtJobDone, tvCallMe, tvEmpSelfDiscription;
        public TextView fullNameTextView;
        public ImageView addFriendImageView;
    }
}