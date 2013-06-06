package cgu.edu.ist380.solimana.Project.AConnect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {


	/* database variables */
	private static final String DATABASE_NAME = "AConnect.db";
	private static final int DATABASE_VERSION = 1;

	/* ACONNECT Table */

	public static final String TABLE_ACONNECT = "AConnect";
	public static final String ACONNECT_COLUMN_ID = "_id";
	public static final String ACONNECT_COLUMN_NAME = "name";
	public static final String ACONNECT_COLUMN_IMAGE = "image";
	public static final String ACONNECT_COLUMN_SOUND = "sound";
	public static final String ACONNECT_COLUMN_DATE_CREATED = "date_created";
	public static final String ACONNECT_COLUMN_CAT_TAB = "cat_tab";
	public static final String ACONNECT_COLUMN_NUMBER_OF_CLICKS = "number_of_clicks";


	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table " + TABLE_ACONNECT
			+ "("
			+ ACONNECT_COLUMN_ID + " integer primary key autoincrement, "
			+ ACONNECT_COLUMN_NAME + " text not null,"
			+ ACONNECT_COLUMN_IMAGE + " text null,"
			+ ACONNECT_COLUMN_SOUND + " text null,"
			+ ACONNECT_COLUMN_DATE_CREATED + " text not null,"
			+ ACONNECT_COLUMN_CAT_TAB + " text not null,"
			+ ACONNECT_COLUMN_NUMBER_OF_CLICKS+ " integer not null,"
			+ ")";




	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}	

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_ACONNECT);
		onCreate(arg0);
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + arg1 + " to "
						+ arg2 + ", which will destroy all old data");

	}

}

