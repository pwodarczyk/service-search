package testing;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rr.generalservicesapplication.AppController;
import com.rr.generalservicesapplication.Const;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.Utils;

public class JsonRequestActivity extends Activity implements OnClickListener {


	private Button btnJsonObj, btnJsonArray;
	private TextView msgResponse;
	private ProgressDialog pDialog;
String str_response;
	// These tags will be used to cancel the requests
	private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
	private String TAG = JsonRequestActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);

		btnJsonObj = (Button) findViewById(R.id.btnJsonObj);
		btnJsonArray = (Button) findViewById(R.id.btnJsonArray);
		msgResponse = (TextView) findViewById(R.id.msgResponse);

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);

		btnJsonObj.setOnClickListener(this);
		btnJsonArray.setOnClickListener(this);
	}

	private void showProgressDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideProgressDialog() {
		if (pDialog.isShowing())
			pDialog.hide();
	}

	/**
	 * Making json object request
	 * */
	private void makeJsonObjReq() {
		showProgressDialog();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
				Const.URL_JSON_OBJECT, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						msgResponse.setText(response.toString());
						str_response=msgResponse.getText().toString();

						hideProgressDialog();

					}

				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				hideProgressDialog();
			}
		}) {

			/**
			 * Passing some request headers
			 * */
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				return headers;
			}

			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("name", "Androidhive");
				params.put("email", "abc@androidhive.info");
				params.put("pass", "password123");

				return params;
			}

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq,
				tag_json_obj);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
	}

	/**
	 * Making json array request
	 * */
	private void makeJsonArryReq() {
		showProgressDialog();
		JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						msgResponse.setText(response.toString());
						hideProgressDialog();
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				hideProgressDialog();
			}
		});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req,
				tag_json_arry);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
	}
	public static ArrayList<String> convertStringArrayToArraylist(String[] strArr){
		ArrayList<String> stringList = new ArrayList<String>();
		for (String s : strArr) {
			stringList.add(s);
		}
		return stringList;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnJsonObj:
//				makeJsonObjReq();
				makeJsonObjforCategory();

				break;
			case R.id.btnJsonArray:
				makeJsonArryReq();
				break;
		}

	}
	private void makeJsonObjforCategory() {

		String url = Utils.CATEGORY_URL;
		JsonObjectRequest jsonobject = new JsonObjectRequest(Request.Method.GET,
				url, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d("Volley Response", response.toString());
						try {
							JSONArray JA = response.getJSONArray("Quiz");

							try {
								for (int i = 0; i <= JA.length(); i++) {
									JSONObject secondobj = JA.getJSONObject(i);
									String id = secondobj.getString("id");
									String name = secondobj.getString("name");
									Const vehicleOb = new Const(id,name);
									Utils.Category.add(vehicleOb);
//									db.addComments(comment);
								}
							} catch (IndexOutOfBoundsException e) {
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

}
