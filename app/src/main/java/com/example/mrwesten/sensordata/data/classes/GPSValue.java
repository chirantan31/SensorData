package com.example.mrwesten.sensordata.data.classes;
/**
 * Created by user on 26-09-2016.
 */
//Stores Latitude and Longitude values at respective timestamps.
public class GPSValue {
    public long timestamp;
    public double latitude;
    public double longitude;
    public GPSValue(long timestamp, double latitude, double longitude)
    {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
