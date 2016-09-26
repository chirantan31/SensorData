package com.example.mrwesten.sensordata;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity{



    private SensorManager mSensorManager;
    private SensorEventListener accelListener;
    private SensorEventListener gyroListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelListener = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                Sensor sensor = event.sensor;
                if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                    //TODO: get values
                    String readings = "Time: \t" + System.currentTimeMillis() + "\t Value1:" + event.values[0] + "\t Value2:" + event.values[1] + "\t Value3:" + event.values[2] + "\t ";
                            Log.i("ACCEL",readings);
                }
            }
        };


        gyroListener= new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                Sensor sensor = event.sensor;
                if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                    //TODO: get values
                    String readings = "Time: \t" + System.currentTimeMillis() + "\t Value1:" + event.values[0] + "\t Value2:" + event.values[1] + "\t Value3:" + event.values[2] + "\t ";
                    Log.i("GYRO",readings);
                }

            }
        };


        mSensorManager.registerListener(accelListener,mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(gyroListener,mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_GAME);

        }


}
