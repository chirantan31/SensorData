package com.example.mrwesten.sensordata;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 30-09-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String GPS_TABLE = "GPS_TABLE";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String GPS_LAT = "GPS_LAT";
    public static final String GPS_LON = "GPS_LON";
    private static final String DATABASE_NAME = "SENSOR_DATA.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + GPS_TABLE + "( " + TIMESTAMP
            + " bigint, " + GPS_LAT
            + " float, "+ GPS_LON + " float" + " )";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GPS_TABLE);
        onCreate(db);
    }
}
