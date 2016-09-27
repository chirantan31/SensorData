package com.example.mrwesten.sensordata.data.classes;

import java.util.ArrayList;

/**
 * Created by user on 26-09-2016.
 */



public class UserSession {
    public String userName;
    public ArrayList <GPSValue> GPSList;
    public ArrayList <AccValue> AccList;
    public ArrayList <GyroValue> GyroList;
    public ArrayList <RotValue> RotList;
    public UserSession(String userName)
    {
        this.userName = userName;
        GPSList=new ArrayList<GPSValue>();
        AccList=new ArrayList<AccValue>();
        GyroList=new ArrayList<GyroValue>();
        RotList=new ArrayList<RotValue>();

    }


}
