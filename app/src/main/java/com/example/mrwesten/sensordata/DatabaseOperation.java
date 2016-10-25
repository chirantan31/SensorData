package com.example.mrwesten.sensordata;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.mrwesten.sensordata.data.classes.AccValue;
import com.example.mrwesten.sensordata.data.classes.GPSValue;
import com.example.mrwesten.sensordata.data.classes.GyroValue;
import com.example.mrwesten.sensordata.data.classes.RotValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 30-09-2016.
 */
public class DatabaseOperation {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumnsGPS = { DatabaseHelper.TIMESTAMP, DatabaseHelper.GPS_LAT, DatabaseHelper.GPS_LON };
    private String[] allColumnsGYRO = { DatabaseHelper.TIMESTAMP, DatabaseHelper.GYRO_X, DatabaseHelper.GYRO_Y, DatabaseHelper.GYRO_Z };
    private String[] getAllColumnsACC = { DatabaseHelper.TIMESTAMP, DatabaseHelper.ACC_X, DatabaseHelper.ACC_Y, DatabaseHelper.ACC_Z};
    private String[] getAllColumnsROT = { DatabaseHelper.TIMESTAMP, DatabaseHelper.ROT_X, DatabaseHelper.ROT_Y, DatabaseHelper.ROT_Z};

    public DatabaseOperation(Context context)
    {
        SharedPreferences prefs;
        int versionDB;
        prefs = context.getSharedPreferences("com.example.mrwesten.sensordata", Context.MODE_PRIVATE);
        versionDB = prefs.getInt("com.example.mrwesten.sensordata.versionDB", 0) + 1;
        dbHelper = new DatabaseHelper(context, versionDB);
        prefs.edit().putInt("com.example.mrwesten.sensordata.versionDB", versionDB).apply();
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

    public void createGYRORecord(GyroValue gyroValue){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TIMESTAMP, gyroValue.timestamp);
        values.put(DatabaseHelper.GYRO_X, gyroValue.gyroX);
        values.put(DatabaseHelper.GYRO_Y, gyroValue.gyroY);
        values.put(DatabaseHelper.GYRO_Z, gyroValue.gyroZ);
        database.insert(dbHelper.GYRO_TABLE, null, values);
    }

    public void createACCRecord(AccValue accValue){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TIMESTAMP, accValue.timestamp);
        values.put(DatabaseHelper.ACC_X, accValue.accX);
        values.put(DatabaseHelper.ACC_Y, accValue.accY);
        values.put(DatabaseHelper.ACC_Z, accValue.accZ);
        database.insert(dbHelper.ACC_TABLE, null, values);
    }

    public void createROTRecord(RotValue rotValue){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TIMESTAMP, rotValue.timestamp);
        values.put(DatabaseHelper.ROT_X, rotValue.rotX);
        values.put(DatabaseHelper.ROT_Y, rotValue.rotY);
        values.put(DatabaseHelper.ROT_Z, rotValue.rotZ);
        database.insert(dbHelper.ROT_TABLE, null, values);
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

    public List<GyroValue> getAllGYROValues()
    {
        List<GyroValue> gyroValues = new ArrayList<GyroValue>();
        Cursor cursor = database.query(DatabaseHelper.GYRO_TABLE,allColumnsGYRO,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            GyroValue gyroValue = cursorToGYROValue(cursor);
            gyroValues.add(gyroValue);
            cursor.moveToNext();
        }
        cursor.close();
        return gyroValues;
    }

    public List<AccValue> getAllACCValues()
    {
        List<AccValue> accValues = new ArrayList<AccValue>();
        Cursor cursor = database.query(DatabaseHelper.ACC_TABLE, getAllColumnsACC, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            AccValue accValue = cursorToACCValue(cursor);
            accValues.add(accValue);
            cursor.moveToNext();
        }
        cursor.close();
        return accValues;
    }

    public List<RotValue> getAllROTValues()
    {
        List<RotValue> rotValues = new ArrayList<RotValue>();
        Cursor cursor = database.query(DatabaseHelper.ACC_TABLE, getAllColumnsACC, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            RotValue rotValue = cursorToROTValue(cursor);
            rotValues.add(rotValue);
            cursor.moveToNext();
        }
        cursor.close();
        return rotValues;
    }

    public GPSValue cursorToGPSValue(Cursor cursor)
    {
        GPSValue gpsValue = new GPSValue(cursor.getLong(0),cursor.getDouble(1),cursor.getDouble(2));
        return gpsValue;
    }

    public GyroValue cursorToGYROValue(Cursor cursor)
    {
        GyroValue gyroValue = new GyroValue(cursor.getLong(0),cursor.getFloat(1),cursor.getFloat(2), cursor.getFloat(3));
        return gyroValue;
    }

    public AccValue cursorToACCValue(Cursor cursor) {
        AccValue accValue = new AccValue(cursor.getLong(0), cursor.getFloat(1), cursor.getFloat(2), cursor.getFloat(3));
        return accValue;
    }

    public RotValue cursorToROTValue(Cursor cursor) {
        RotValue rotValue = new RotValue(cursor.getLong(0), cursor.getFloat(1), cursor.getFloat(2), cursor.getFloat(3));
        return rotValue;
    }

}
