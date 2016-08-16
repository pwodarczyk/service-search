package com.rr.generalservicesapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import employeesList.Constants;

public class Utils {

	public static final String REGISTER_URL = "http://takeawaymobileapplication.uk/clients/apis/generalservices/api.php";
	public static final String CATEGORY_URL = "http://takeawaymobileapplication.uk/clients/apis/generalservices/api.php?category=category";
	private static final String Server="http://takeawaymobileapplication.uk/clients/tayyab/attendance/public/api/";
	public static final String LoginUrl=Server+"login/new/";
	public static final String CommentsUrl=Server+"comment";
	public static final String IMAGEURL="http://takeawaymobileapplication.uk/clients/apis/generalservices/uploads/";

	public static ArrayList<Const> Category=new ArrayList<Const>();
	public static ArrayList<Constants> EmpList=new ArrayList<Constants>();
	public static ArrayList<Constants> UserJobList=new ArrayList<Constants>();

	public static String getPreferences(String key, Context context) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		String userName = sharedPreferences.getString(key, "");
		return userName;
	}

	public static int getPreferencesInt(String key, Context context) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		int int_value = sharedPreferences.getInt(key, 0);
		return int_value;
	}
	public static boolean savePreferences(String key, String value,
			Context context) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
		return true;
	}

	public static boolean savePreferencesInt(String key, int value,
										  Context context) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
		return true;
	}


	public void CopyStream(InputStream is, OutputStream os)
	{
		final int buffer_size=1024;
		try
		{
			byte[] bytes=new byte[buffer_size];
			for(;;)
			{
				int count=is.read(bytes, 0, buffer_size);
				if(count==-1)
					break;
				os.write(bytes, 0, count);
			}
		}
		catch(Exception ex){}
	}



}
