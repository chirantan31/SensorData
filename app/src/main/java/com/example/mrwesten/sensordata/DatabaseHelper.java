package com.example.mrwesten.sensordata;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 30-09-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SENSOR_DATA.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TIMESTAMP = "TIMESTAMP";

    public static final String GPS_TABLE = "GPS_TABLE";
    public static final String GPS_LAT = "GPS_LAT";
    public static final String GPS_LON = "GPS_LON";

    public static final String GYRO_TABLE = "GYRO_TABLE";
    public static final String GYRO_X = "GYRO_X";
    public static final String GYRO_Y = "GYRO_Y";
    public static final String GYRO_Z = "GYRO_Z";

    public static final String ACC_TABLE = "ACC_TABLE";
    public static final String ACC_X = "ACC_X";
    public static final String ACC_Y = "ACC_Y";
    public static final String ACC_Z = "ACC_Z";

    public static final String ROT_TABLE = "ROT_TABLE";
    public static final String ROT_X = "ROT_X";
    public static final String ROT_Y = "ROT_Y";
    public static final String ROT_Z = "ROT_Z";

    private static final String DATABASE_CREATE_GPS = "create table "
            + GPS_TABLE + "( " + TIMESTAMP
            + " bigint, " + GPS_LAT
            + " double, "+ GPS_LON + " double" + " )";
    private static final String DATABASE_CREATE_GYRO = "create table "
            + GYRO_TABLE + "( " + TIMESTAMP
            + " bigint, " + GYRO_X
            + " float, "+ GYRO_Y + " float," + GYRO_Z + " float" + " )";
    private static final String DATABASE_CREATE_ACC = "create table " +
            ACC_TABLE + "( " + TIMESTAMP + " bigint, " + ACC_X + " float, " +
            ACC_Y + " float, " + ACC_Z + " float " + ")";

    private static final String DATABASE_CREATE_ROT = "create table " +
            ROT_TABLE + "( " + TIMESTAMP + " bigint, " + ROT_X + " float, " +
            ROT_Y + " float, " + ROT_Z + " float " + ")";

    public DatabaseHelper(Context context, int versionDB) {
       // SharedPreferences prefs = context.getSharedPreferences("com.example.mrwesten.sensordata", Context.MODE_PRIVATE);

        super(context, versionDB + "_" + DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_GPS);
        db.execSQL(DATABASE_CREATE_GYRO);
        db.execSQL(DATABASE_CREATE_ACC);
        db.execSQL(DATABASE_CREATE_ROT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GPS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GYRO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ACC_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ROT_TABLE);
        onCreate(db);
    }
}
