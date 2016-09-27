package com.example.mrwesten.sensordata.data.classes;
/**
 * Created by user on 26-09-2016.
 */
public class AccValue {
    private long timestamp;
    private float accX;
    private float accY;
    private float accZ;
    AccValue(long timestamp, float accX, float accY, float accZ){
        this.timestamp = timestamp;
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
    }
}
