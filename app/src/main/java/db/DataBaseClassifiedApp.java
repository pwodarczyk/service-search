package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rr.generalservicesapplication.WorldPopulation;

import java.util.ArrayList;

public class DataBaseClassifiedApp extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "Classified_App";

	// Product table name
	public static final String TABLE_CONTACTS_USER = "Employees";
	// Contacts Table Columns names
	public static final String KEY_ID_USER = "id";
	public static final String KEY_EMPLOYEE_ID= "emp_id";
	public static final String KEY_EMPLOYEE_NAME = "emp_name";
	public static final String KEY_EMPLOYEE_EMAIL = "emp_email";
	public static final String KEY_EMPLOYEE_PHONE = "emp_phone";
	public static final String KEY_EMPLOYEE_CATEGORY = "emp_category";
	public static final String KEY_EMPLOYEE_JOBSTTUS = "emp_jobstatus";
	public static final String KEY_EMPLOYEE_DESCRIPTION = "emp_description";
	public static final String KEY_EMPLOYEE_EXPERIENCE = "emp_experience";
    public static final String KEY_EMPLOYEE_RATTING = "emp_ratting";
    public static final String KEY_EMPLOYEE_IMAGE = "emp_image";

	public DataBaseClassifiedApp(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String CREATE_CONTACTS_TABLE_PRODUCT = "CREATE TABLE "
				+ TABLE_CONTACTS_USER + "(" + KEY_ID_USER
				+ " INTEGER PRIMARY KEY," + KEY_EMPLOYEE_ID + " TEXT,"
				+ KEY_EMPLOYEE_NAME + " TEXT," + KEY_EMPLOYEE_EMAIL + " TEXT,"
				+ KEY_EMPLOYEE_PHONE + " TEXT," + KEY_EMPLOYEE_CATEGORY + " TEXT,"
				+ KEY_EMPLOYEE_JOBSTTUS + " TEXT,"+ KEY_EMPLOYEE_DESCRIPTION + " TEXT," +
                KEY_EMPLOYEE_EXPERIENCE + " TEXT," +KEY_EMPLOYEE_RATTING + " TEXT," +
                KEY_EMPLOYEE_IMAGE + " TEXT" + ")";

		db.execSQL(CREATE_CONTACTS_TABLE_PRODUCT);

	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS_USER);
		// Create tables again
		onCreate(db);
	}

	public void deleteAll() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from " + TABLE_CONTACTS_USER);
		db.close();
	}

	public void updateRatting( String companyid,String empid) {
		SQLiteDatabase db = this.getWritableDatabase();
		// Define the updated row content.
		ContentValues updatedValues = new ContentValues();
		// Assign values for each row.
		updatedValues.put(KEY_EMPLOYEE_RATTING, companyid);

		String where = "emp_id = ?";
//        KEY_EMPLOYEE_ID
		db.update(TABLE_CONTACTS_USER, updatedValues, where, new String[]{empid});
	}


	// Adding new Employees
	public void addEmployees(WorldPopulation constant) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EMPLOYEE_ID, constant.getEmpId());
		values.put(KEY_EMPLOYEE_NAME, constant.getEpm_name());
		values.put(KEY_EMPLOYEE_EMAIL, constant.getEmail()); // Contact
        values.put(KEY_EMPLOYEE_PHONE, constant.getEmp_phone());														// Name
		values.put(KEY_EMPLOYEE_CATEGORY, constant.getEmp_category());
		values.put(KEY_EMPLOYEE_JOBSTTUS, constant.getJob_status());
		values.put(KEY_EMPLOYEE_DESCRIPTION, constant.getEmp_discription());
		values.put(KEY_EMPLOYEE_EXPERIENCE, constant.getEmp_experience());
		values.put(KEY_EMPLOYEE_IMAGE, constant.getFlag());
		// Inserting Row
		db.insert(TABLE_CONTACTS_USER, null, values);
		db.close(); // Closing database connection
	}

	// Getting All Employees
	public ArrayList<WorldPopulation> getAllEmployees() {
		ArrayList<WorldPopulation> contactList = new ArrayList<WorldPopulation>();
		// Select All Query

		// String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS ;

		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS_USER;
		// + " WHERE " + "Category_id = " + cateID;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
                WorldPopulation contact = new WorldPopulation(cursor.getString(1),
						cursor.getString(2), cursor.getString(3),
						cursor.getString(4), cursor.getString(5),
						cursor.getString(6), cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));

				contactList.add(contact);
				Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
			}
			while (cursor.moveToNext());
		}
		// return contact list
		return contactList;
	}
}
