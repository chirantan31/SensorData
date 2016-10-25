package com.example.mrwesten.sensordata.data.classes;

/**
 * Created by MrWesten on 9/27/2016.
 */
public class RotValue {
    public long timestamp;
    public float rotX;
    public float rotY;
    public float rotZ;
    public RotValue(long timestamp, float accX, float accY, float accZ){
        this.timestamp = timestamp;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }
}
