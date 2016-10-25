package com.example.mrwesten.sensordata.data.classes;
/**
 * Created by user on 26-09-2016.
 */
public class AccValue {
    public long timestamp;
    public float accX;
    public float accY;
    public float accZ;
    public AccValue(long timestamp, float accX, float accY, float accZ){
        this.timestamp = timestamp;
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
    }
}
