package com.example.mrwesten.sensordata;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by ikshwaku on 16/10/16.
 */

public class FileHelper {
    private JSONObject sessionJsonObj;
    private SharedPreferences prefs;
    private int currentFileNumber;
    private Context context;
    public FileHelper(Context context){
        this.context = context;
    }

    public void createSessionFile(JSONObject sessionJsonObj) throws IOException {
        this.sessionJsonObj = sessionJsonObj;
        prefs = context.getSharedPreferences("com.example.mrwesten.sensordata", Context.MODE_PRIVATE);
        currentFileNumber = prefs.getInt("com.example.mrwesten.sensordata.currentFileNumber", 0) + 1;
        prefs.edit().putInt("com.example.mrwesten.sensordata.currentFileNumber", currentFileNumber).apply();
        FileOutputStream fileOutputStream = context.openFileOutput("SessionJsonFile_"+currentFileNumber, Context.MODE_WORLD_WRITEABLE);
        fileOutputStream.write(sessionJsonObj.toString().getBytes());
        fileOutputStream.close();
        Log.i("Hello",sessionJsonObj.toString());
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        FileInputStream inputStream = context.openFileInput("SessionJsonFile_"+currentFileNumber);
        String temp="";
        int c;
        while( (c = inputStream.read()) != -1){
            temp = temp + Character.toString((char)c);
        }
        Log.d("FileContent", temp);
        inputStream.close();
    }


}
