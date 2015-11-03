package com.tencent.tws.locationtrack.test;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;
import com.tencent.tws.locationtrack.database.LocationDbHelper;
import com.tencent.tws.locationtrack.database.MyContentProvider;
import com.tencent.tws.locationtrack.database.SPUtils;

/**
 * Created by microzhang on 2015/11/1.
 */
public class MyTest extends AndroidTestCase {


	public void testQuery() throws Exception {
		String[] PROJECTION = new String[]{LocationDbHelper.ID, LocationDbHelper.LATITUDE, LocationDbHelper.LONGITUDE, LocationDbHelper.INS_SPEED, LocationDbHelper.BEARING, LocationDbHelper.ALTITUDE, LocationDbHelper.ACCURACY, LocationDbHelper.TIME, LocationDbHelper.DISTANCE, LocationDbHelper.AVG_SPEED, LocationDbHelper.KCAL,};

		Cursor cursor = getContext().getContentResolver().query(MyContentProvider.CONTENT_URI, PROJECTION, null, null, null);
		if (cursor.moveToFirst()) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				Log.i("kermit", "ID=" + cursor.getInt(cursor.getColumnIndex(LocationDbHelper.ID)));
				Log.i("kermit", "LATITUDE=" + cursor.getDouble(cursor.getColumnIndex(LocationDbHelper.LATITUDE)));
				Log.i("kermit", "LONGITUDE=" + cursor.getInt(cursor.getColumnIndex(LocationDbHelper.LONGITUDE)));
				Log.i("kermit", "INS_SPEED=" + cursor.getInt(cursor.getColumnIndex(LocationDbHelper.INS_SPEED)));
				Log.i("kermit", "BEARING=" + cursor.getInt(cursor.getColumnIndex(LocationDbHelper.BEARING)));
				Log.i("kermit", "ALTITUDE=" + cursor.getInt(cursor.getColumnIndex(LocationDbHelper.ALTITUDE)));
				Log.i("kermit", "ACCURACY=" + cursor.getInt(cursor.getColumnIndex(LocationDbHelper.ACCURACY)));
				Log.i("kermit", "TIME=" + cursor.getLong(cursor.getColumnIndex(LocationDbHelper.TIME)));

			}
		}
	}

	public void testInsert() throws Exception {

		ContentValues values = new ContentValues();
		values.put(LocationDbHelper.LATITUDE, 11.11);
		values.put(LocationDbHelper.LONGITUDE, 12.12);
		values.put(LocationDbHelper.INS_SPEED, 13.13);
		values.put(LocationDbHelper.BEARING, 14.14f);
		values.put(LocationDbHelper.ALTITUDE, 15.15);
		values.put(LocationDbHelper.ACCURACY, 15.19f);
		values.put(LocationDbHelper.TIME, System.currentTimeMillis());

		values.put(LocationDbHelper.DISTANCE, 16.16f);
		values.put(LocationDbHelper.AVG_SPEED, 17.17f);
		values.put(LocationDbHelper.KCAL, 18.18f);

		getContext().getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
	}

	public void testUpdate() throws Exception {
		Uri uri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, 1);
		ContentValues values = new ContentValues();
		values.put(LocationDbHelper.LATITUDE, 7.15);
		getContext().getContentResolver().update(uri, values, null, null);
	}

	public void testDelet() throws Exception {
		Uri uri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, 2);
		getContext().getContentResolver().delete(uri, null, null);
	}

	public void testAllDistance() {
		String[] PROJECTION = new String[]{LocationDbHelper.ID, LocationDbHelper.LATITUDE, LocationDbHelper.LONGITUDE, LocationDbHelper.INS_SPEED, LocationDbHelper.BEARING, LocationDbHelper.ALTITUDE, LocationDbHelper.ACCURACY, LocationDbHelper.TIME, LocationDbHelper.DISTANCE, LocationDbHelper.AVG_SPEED, LocationDbHelper.KCAL,};
		double allDistance = 0;
		Cursor cursor = getContext().getContentResolver().query(MyContentProvider.CONTENT_URI, PROJECTION, null, null, null);
		if (cursor.moveToFirst()) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				allDistance += cursor.getDouble(cursor.getColumnIndex(LocationDbHelper.DISTANCE));
			}
		}
		Log.i("kermit", "cursor.getCount()=" + cursor.getCount() + "allDistance=" + allDistance);
	}

	public void testTimes() {
		long time = 0;
		String[] PROJECTION = new String[]{LocationDbHelper.ID, LocationDbHelper.LATITUDE, LocationDbHelper.LONGITUDE, LocationDbHelper.INS_SPEED, LocationDbHelper.BEARING, LocationDbHelper.ALTITUDE, LocationDbHelper.ACCURACY, LocationDbHelper.TIME, LocationDbHelper.DISTANCE, LocationDbHelper.AVG_SPEED, LocationDbHelper.KCAL,};
		Cursor cursor = getContext().getContentResolver().query(MyContentProvider.CONTENT_URI, PROJECTION, null, null, null);
		if (cursor != null && cursor.moveToFirst()) {
			time = cursor.getLong(cursor.getColumnIndex(LocationDbHelper.TIME));
		}

		Log.i("kermit", "first time = " + time);
		Log.i("kermit", "current time = " + System.currentTimeMillis());
		Log.i("kermit", "delt time = " + (System.currentTimeMillis() - time) / 1000);
	}

	public void testSP(){
		SPUtils.writeSp(getContext(),"1111111");
	}
}


