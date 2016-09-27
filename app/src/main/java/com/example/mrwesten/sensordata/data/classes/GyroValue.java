package com.example.mrwesten.sensordata.data.classes;

/**
 * Created by user on 26-09-2016.
 */
public class GyroValue {
    private long timestamp;
    private float gyroX;
    private float gyroY;
    private float gyroZ;
    public GyroValue(long timestamp, float gyroX, float gyroY, float gyroZ)
    {
        this.timestamp = timestamp;
        this.gyroX = gyroX;
        this.gyroY = gyroY;
        this.gyroZ = gyroZ;
    }
}
