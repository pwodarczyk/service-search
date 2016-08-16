package drawer;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.rr.generalservicesapplication.AndroidMultiPartEntity;
import com.rr.generalservicesapplication.MainActivity;
import com.rr.generalservicesapplication.R;
import com.rr.generalservicesapplication.Utils;
import com.rr.generalservicesapplication.employeeJobsList.Fr_Employee_Jobs;
import com.rr.generalservicesapplication.userJobsList.Fr_UserJobs;
import com.parse.ParseUser;

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

import userProfile.EmployeeProfile;
import userProfile.MainActivityUserProfile;
import userProfile.SelectCategory;


public class MainActivityDrawer extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	ImageView imgView ;
Context context;
	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_drawer);
//		MainActivity.mainActivity.finish();
		mTitle = mDrawerTitle = getTitle();
context=getApplicationContext();

		final ActionBar actionBar = getActionBar();
		actionBar.setCustomView(R.layout.custem_action_bar);

//		imgView = (ImageView) findViewById(R.id.imageView4);


		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
//		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setIcon(
				new ColorDrawable(getResources().getColor(android.R.color.transparent)));
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00a1d8")));


		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
//		Fragment fragment = null;
//		fragment = new SelectCategory();
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Find People
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// Photos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// Communities, Will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
		// Pages
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		// What's hot, We  will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
		

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);


	mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
			R.drawable.ic_drawer, //nav menu toggle icon
			R.string.app_name, // nav drawer open - description for accessibility
			R.string.app_name // nav drawer close - description for accessibility
	) {
		public void onDrawerClosed(View view) {
			getActionBar().setTitle(mTitle);
			// calling onPrepareOptionsMenu() to show action bar icons
			invalidateOptionsMenu();
		}


		public void onDrawerOpened(View drawerView) {


			getActionBar().setTitle(mDrawerTitle);
			// calling onPrepareOptionsMenu() to hide action bar icons
			invalidateOptionsMenu();

		}

	};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}


//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			ParseUser.logOut();
			Utils.savePreferences("logged", "", getApplicationContext());
			Utils.savePreferencesInt("DelayPeriod", 0, getApplicationContext());
			Intent logout = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(logout);
			finish();

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * MainActivityUserProfile
	 * FragmentUserJobs
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new SelectCategory();
			break;
		case 1:
			String login = Utils.getPreferences("login", context);
			if(login.equalsIgnoreCase("true")) {
				String user_type1 = Utils.getPreferences("usertype",getApplicationContext());
				if (user_type1.equalsIgnoreCase("1")){
					new AsyncCallWS2().execute();
					fragment = new Fr_Employee_Jobs();
				}else{
					new AsyncCallWS2().execute();
					fragment = new Fr_UserJobs();
				}

			}else{
				Intent intent=new Intent(MainActivityDrawer.this,MainActivity.class);
				startActivity(intent);
				finish();
			}



			break;
		case 2:
			String login1 = Utils.getPreferences("login", context);
			if(login1.equalsIgnoreCase("true")) {
			String user_type = Utils.getPreferences("usertype",getApplicationContext());
			if (user_type.equalsIgnoreCase("1")){
				fragment = new EmployeeProfile();
			}else{
				fragment = new MainActivityUserProfile();
			}
			}else{
				Intent intent=new Intent(MainActivityDrawer.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
			break;
		case 3:
			fragment = new AboutUsFragment();
			break;
		case 4:

			Utils.savePreferences("login", "false", this);
//			fragment = new PagesFragment();
//
//			ParseUser.logOut();
//			Utils.savePreferences("logged", "", getApplicationContext());
//			Utils.savePreferencesInt("DelayPeriod", 0, getApplicationContext());
			Intent logout = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(logout);
			finish();

			break;
		case 5:
			finish();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
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
	public void logupload2() {
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


			String user_id = Utils.getPreferences("user_id",context);

			entity.addPart("user_id", new StringBody(user_id));
			entity.addPart("user_jobs", new StringBody("true"));



//            Log.d("Encodeed Img ", encodedImage);

			post.setEntity(entity);
			response = client.execute(post);
                    /* Checking response */
			if (response != null) {
				InputStream in = response.getEntity().getContent();
			String	result = convertInputStreamToString(in);
				Utils.savePreferences("user_jobs_result", result, context);
//				Utils.savePreferences("emp_list", result, getActivity());
//				Intent intent = new Intent(getActivity(), CategoryVehical.class);
////                    intent.putExtra("Slecteditem", servicename);
//				startActivity(intent);
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

		final ProgressDialog dialog = new ProgressDialog(MainActivityDrawer.this);

		@Override
		protected Void doInBackground(String... params) {
			logupload2();
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
