package com.example.mrwesten.sensordata.data.classes;

/**
 * Created by MrWesten on 9/27/2016.
 */
public class RotValue {
    private long timestamp;
    private float rotX;
    private float rotY;
    private float rotZ;
    public RotValue(long timestamp, float accX, float accY, float accZ){
        this.timestamp = timestamp;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }
}
