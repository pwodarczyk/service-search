package employeesList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rr.generalservicesapplication.Const;
import com.rr.generalservicesapplication.ListViewAdapterAllEpmz;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.Utils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;


/**
 * Created by Awais on 9/19/2015.
 */
public class CategoryVehical extends Activity {
    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/"; // 13
    String mImageURL; // 13
    ListView lv;
    CategoryAdaptor adapter;
    Context context;
    Button addbtn;
    String objectId;
    ParseObject query,categoryList;

    String user_id,objId,objId_addcar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        context = this;
        lv = (ListView) findViewById(R.id.listview);


        try {
            getEmployeesList();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String list = Utils.getPreferences("list",context);
        if(list.equalsIgnoreCase("true")){
            Utils.savePreferences("list", "false", this);
            Toast.makeText(CategoryVehical.this, "Welcome ...... !", Toast.LENGTH_SHORT).show();

            
        }else{
            Toast.makeText(CategoryVehical.this, "Sorry... ! this employee list is empty", Toast.LENGTH_SHORT).show();
        }

    }
    public void getEmployeesList() throws JSONException {
        String emp_list = Utils.getPreferences("emp_list",getApplicationContext());
        JSONObject first = new JSONObject(emp_list);


        try {
            JSONArray JA = first.getJSONArray("data");
            Utils.EmpList.clear();
            try {
                for (int i = 0; i < JA.length(); i++) {
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
                    String rating = secondobj.getString("rating");
                    Utils.savePreferences("list", "true", this);


                    Constants ob = new Constants(id,result_name,result_email,result_phone,result_address,result_image,result_user_type,result_cat_id,result_exp,result_desceription,rating);
                    Utils.EmpList.add(ob);

                    adapter = new CategoryAdaptor(context, Utils.EmpList);
                    lv.setAdapter(adapter);


//									db.addComments(comment);
                }
            } catch (JSONException | IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


//    private void logout() {
//        ParseUser.logOut();
//        Intent intent = new Intent(this, LoginParse.class);
//        startActivity(intent);
//        this.finish();
//    }






    }
