package com.example.mrwesten.sensordata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.mrwesten.sensordata.data.classes.GPSValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 30-09-2016.
 */
public class DatabaseOperation {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumnsGPS = { DatabaseHelper.TIMESTAMP, DatabaseHelper.GPS_LAT, DatabaseHelper.GPS_LON };

    public DatabaseOperation(Context context)
    {
        dbHelper = new DatabaseHelper(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public void createGPSRecord(GPSValue gpsValue)
    {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TIMESTAMP, gpsValue.timestamp);
        values.put(DatabaseHelper.GPS_LAT, gpsValue.latitude);
        values.put(DatabaseHelper.GPS_LON, gpsValue.longitude);
        database.insert(dbHelper.GPS_TABLE,null,values);

    }

    public List<GPSValue> getAllGPSValues()
    {
        List<GPSValue> gpsValues = new ArrayList<GPSValue>();
        Cursor cursor = database.query(DatabaseHelper.GPS_TABLE,allColumnsGPS,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            GPSValue gpsValue = cursorToGPSValue(cursor);
            gpsValues.add(gpsValue);
            cursor.moveToNext();
        }
        cursor.close();
        return gpsValues;
    }
    public GPSValue cursorToGPSValue(Cursor cursor)
    {
        GPSValue gpsValue = new GPSValue(cursor.getLong(0),cursor.getDouble(1),cursor.getDouble(2));
        return gpsValue;
    }

}
