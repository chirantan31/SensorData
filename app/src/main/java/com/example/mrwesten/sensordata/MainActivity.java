package com.example.mrwesten.sensordata;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.mrwesten.sensordata.data.classes.*;

import java.util.List;

public class MainActivity extends AppCompatActivity{


    UserSession userSession = new UserSession("chirantan31@gmail.com");

    private SensorManager mSensorManager;
    //private SensorEventListener accelListener;

    private SensorEventListener gyroListener;
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {



                } else {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
                    finish();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);



        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?


                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

        }









       /* accelListener = new SensorEventListener() {
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
*/

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
                    userSession.GyroList.add(new GyroValue(System.currentTimeMillis(),event.values[0],event.values[1],event.values[2]));
                }
                else if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                    //TODO: get values
                    String readings = "Time: \t" + System.currentTimeMillis() + "\t Value1:" + event.values[0] + "\t Value2:" + event.values[1] + "\t Value3:" + event.values[2] + "\t ";
                    Log.i("ACCEL",readings);
                    userSession.AccList.add(new AccValue(System.currentTimeMillis(),event.values[0],event.values[1],event.values[2]));
                }
                else if (sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
                    //TODO: get values
                    String readings = "Time: \t" + System.currentTimeMillis() + "\t Value1:" + event.values[0] + "\t Value2:" + event.values[1] + "\t Value3:" + event.values[2] + "\t ";
                    Log.i("ROT_VECTOR",readings);
                    userSession.RotList.add(new RotValue(System.currentTimeMillis(),event.values[0],event.values[1],event.values[2]));
                }


            }
        };


        mSensorManager.registerListener(gyroListener,mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(gyroListener,mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(gyroListener,mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),SensorManager.SENSOR_DELAY_GAME);


        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                String readings = "Time: \t" + System.currentTimeMillis() + "\t Latitude:" + location.getLatitude() + "\t Longitude:" + location.getLongitude() + "\t ";
                Log.i("GPS:",readings);
                userSession.GPSList.add(new GPSValue(System.currentTimeMillis(),location.getLatitude(),location.getLongitude()));

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


        }


}
