package com.example.mrwesten.sensordata.data.classes;

/**
 * Created by user on 26-09-2016.
 */
public class GyroValue {
    public long timestamp;
    public float gyroX;
    public float gyroY;
    public float gyroZ;
    public GyroValue(long timestamp, float gyroX, float gyroY, float gyroZ)
    {
        this.timestamp = timestamp;
        this.gyroX = gyroX;
        this.gyroY = gyroY;
        this.gyroZ = gyroZ;
    }
}
