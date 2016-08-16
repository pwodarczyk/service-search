package com.rr.generalservicesapplication;


import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

	private static final String TAG = AppController.class
			.getSimpleName();

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	private static AppController mInstance;

	@Override
	public void onCreate() {
		super.onCreate();


//		ParseObject.registerSubclass(Attendence.class);
//		Parse.initialize(this, "afthSuC4MAUxQugwkb5vDuvcpHEIFfjG8WThNHMg", "94XB1wrvOESsWrRq3T1dYhynzl07iiXNzgxxEH3i");
//		ParseUser.enableAutomaticUser();
//		ParseACL defaultACL = new ParseACL();
//		defaultACL.setPublicReadAccess(true);
//		ParseACL.setDefaultACL(defaultACL, true);

		mInstance = this;
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	private RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public ImageLoader getImageLoader() {
		getRequestQueue();
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(this.mRequestQueue,
					new LruBitmapCache());
		}
		return this.mImageLoader;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
}
