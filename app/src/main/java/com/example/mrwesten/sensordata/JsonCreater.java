package com.example.mrwesten.sensordata;

import com.example.mrwesten.sensordata.data.classes.AccValue;
import com.example.mrwesten.sensordata.data.classes.GPSValue;
import com.example.mrwesten.sensordata.data.classes.GyroValue;
import com.example.mrwesten.sensordata.data.classes.RotValue;
import com.example.mrwesten.sensordata.data.classes.UserSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ikshwaku on 16/10/16.
 */

public class JsonCreater {
    private  UserSession userSession;

    public JsonCreater(UserSession userSession){
        this.userSession = userSession;
    }

    public JSONObject create() throws JSONException {
        JSONObject sessionJsonObj = new JSONObject();
        JSONObject dataJsonObj = new JSONObject();
        sessionJsonObj.put("userName", userSession.userName);

        JSONArray accJsonArry = new JSONArray();
        for (int i = 0; i < userSession.AccList.size(); i++){
            JSONArray tempJsonArry = new JSONArray();
            AccValue accValue = userSession.AccList.get(i);
            tempJsonArry.put(0, accValue.timestamp);
            tempJsonArry.put(1, accValue.accX);
            tempJsonArry.put(2, accValue.accY);
            tempJsonArry.put(3, accValue.accZ);
            accJsonArry.put(tempJsonArry);
        }

        JSONArray gpsJsonArry = new JSONArray();
        for (int i = 0; i < userSession.GPSList.size(); i++){
            JSONArray tempJsonArry = new JSONArray();
            GPSValue gpsValue = userSession.GPSList.get(i);
            tempJsonArry.put(0, gpsValue.timestamp);
            tempJsonArry.put(1, gpsValue.latitude);
            tempJsonArry.put(2, gpsValue.longitude);
            gpsJsonArry.put(tempJsonArry);
        }

        JSONArray gyroJsonArry = new JSONArray();
        for (int i = 0; i < userSession.GyroList.size(); i++){
            JSONArray tempJsonArry = new JSONArray();
            GyroValue gyroValue = userSession.GyroList.get(i);
            tempJsonArry.put(0, gyroValue.timestamp);
            tempJsonArry.put(1, gyroValue.gyroX);
            tempJsonArry.put(2, gyroValue.gyroY);
            tempJsonArry.put(3, gyroValue.gyroZ);
            gyroJsonArry.put(tempJsonArry);
        }

        JSONArray rotJsonArry = new JSONArray();
        for (int i = 0; i < userSession.RotList.size(); i++){
            JSONArray tempJsonArry = new JSONArray();
            RotValue rotValue = userSession.RotList.get(i);
            tempJsonArry.put(0, rotValue.timestamp);
            tempJsonArry.put(1, rotValue.rotX);
            tempJsonArry.put(2, rotValue.rotY);
            tempJsonArry.put(3, rotValue.rotZ);
            rotJsonArry.put(tempJsonArry);
        }

        dataJsonObj.put("accValue", accJsonArry);
        dataJsonObj.put("gpsValue", gpsJsonArry);
        dataJsonObj.put("gyroValue", gyroJsonArry);
        dataJsonObj.put("rotValue", rotJsonArry);

        sessionJsonObj.put("data", dataJsonObj);
        return sessionJsonObj;
    }
}
