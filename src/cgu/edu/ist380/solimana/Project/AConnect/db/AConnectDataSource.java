package cgu.edu.ist380.solimana.Project.AConnect.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AConnectDataSource {


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {
			MySQLiteHelper.ACONNECT_COLUMN_ID,
			MySQLiteHelper.ACONNECT_COLUMN_NAME,
			MySQLiteHelper.ACONNECT_COLUMN_IMAGE,
			MySQLiteHelper.ACONNECT_COLUMN_SOUND,
			MySQLiteHelper.ACONNECT_COLUMN_DATE_CREATED,
			MySQLiteHelper.ACONNECT_COLUMN_CAT_TAB,
			MySQLiteHelper.ACONNECT_COLUMN_NUMBER_OF_CLICKS,
	};
	public AConnectDataSource(Context context) {
		try{
			dbHelper = new MySQLiteHelper(context);
		}
		catch (Exception e)
		{
			Log.e(AConnectDataSource.class.getName(), "Error opening the db "+ e.getMessage());
		}
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	/*
	 * This method takes an object of type AConnect and insert it to the database
	 * Note that the return type is also AConnect, meaning that the inserted
	 * object will be returned form the database
	 */
	public FB_Record createAConnect(FB_Record AConnect) {
		ContentValues values = new ContentValues();

		values.put( MySQLiteHelper.ACONNECT_COLUMN_NAME,AConnect.getName());
		values.put( MySQLiteHelper.ACONNECT_COLUMN_NAME,AConnect.getName());
		values.put( MySQLiteHelper.ACONNECT_COLUMN_IMAGE,AConnect.getImage());
		values.put( MySQLiteHelper.ACONNECT_COLUMN_SOUND,AConnect.getSound());
		values.put( MySQLiteHelper.ACONNECT_COLUMN_DATE_CREATED,AConnect.getDate_created());
		values.put( MySQLiteHelper.ACONNECT_COLUMN_CAT_TAB,AConnect.getCat_tab());
		values.put( MySQLiteHelper.ACONNECT_COLUMN_NUMBER_OF_CLICKS,AConnect.getNumber_of_clicks());

		/* call the insert method on the database
		 *
		 * Since the method only retuns a number of type "long", I need to downcasted to int to be able to update
		 * the id in my AConnect object. AConnect.setId((int)insertedId);
		 */
		long insertedId = database.insert(MySQLiteHelper.TABLE_ACONNECT,null, values);
		AConnect.setId((int)insertedId);
		Log.i(AConnectDataSource.class.getName(), "Record : Med with id:" + AConnect.getId() +" was inserted to the db.");
		return AConnect;
	}

	public void deleteAConnect(FB_Record AConnect) {
		long id = AConnect.getId();
		database.delete(MySQLiteHelper.TABLE_ACONNECT, MySQLiteHelper.ACONNECT_COLUMN_ID
				+ " = " + id, null);
		Log.i(AConnectDataSource.class.getName(), "Record : Med with id:" + AConnect.getId() +" was deleted from the db.");

	}

	public List<FB_Record> getAllAConnect() {
		List<FB_Record> AConnectList = new ArrayList<FB_Record>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_ACONNECT,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			FB_Record AConnect = cursorToAConnect(cursor);
			AConnectList.add(AConnect);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return AConnectList;
	}

	private FB_Record cursorToAConnect(Cursor cursor) {
		FB_Record AConnect = new FB_Record();
		// get the values from the cursor
		long id = cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.ACONNECT_COLUMN_ID));
		String name=cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.ACONNECT_COLUMN_NAME));
		String image = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.ACONNECT_COLUMN_IMAGE));
		String sound = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.ACONNECT_COLUMN_SOUND));
		String cat_tab = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.ACONNECT_COLUMN_CAT_TAB));
		Long date_created = cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.ACONNECT_COLUMN_DATE_CREATED));
		int number_of_clicks =cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.ACONNECT_COLUMN_NUMBER_OF_CLICKS));		
		AConnect.setId((int) id);
		AConnect.setName(name);
		AConnect.setImage(image);
		AConnect.setSound(sound);
		AConnect.setCat_tab(cat_tab);
		AConnect.setDate_created(date_created);
		AConnect.setNumber_of_clicks(number_of_clicks);		
		return AConnect;
	}
}
