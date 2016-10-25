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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mrwesten.sensordata.data.classes.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{


    UserSession userSession = new UserSession("chirantan31@gmail.com");

    private SensorManager mSensorManager;
    //private SensorEventListener accelListener;

    private SensorEventListener listener;
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    public TextView gpsTextView;
    public TextView accelTextView;
    public TextView rotVectTextView;
    public TextView gyroTextView;

    public Button startButton;
    public Button stopButton;

    private DatabaseOperation dataSource;

    private boolean record = false;

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

        gpsTextView = (TextView) findViewById(R.id.gps_text_view);
        gyroTextView = (TextView) findViewById(R.id.gyro_text_view);
        rotVectTextView = (TextView) findViewById(R.id.rotvec_text_view);
        accelTextView = (TextView) findViewById(R.id.accel_text_view);

        startButton = (Button) findViewById(R.id.start_button);
        stopButton = (Button) findViewById(R.id.stop_button);

        dataSource = new DatabaseOperation(this);
        dataSource.open();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record = true;
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record = false;
                JsonCreater jsonCreater = new JsonCreater(userSession);
                try {
                    JSONObject sessionJsonObject = jsonCreater.create();
                    FileHelper fileHelper = new FileHelper(getApplicationContext());
                    fileHelper.createSessionFile(sessionJsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /* for (int i = 0; i < userSession.GPSList.size(); i++){
                    dataSource.createGPSRecord(userSession.GPSList.get(i));
                }

                for (int i = 0; i < userSession.AccList.size(); i++){
                    dataSource.createACCRecord(userSession.AccList.get(i));
                }
                for (int i = 0; i < userSession.GyroList.size(); i++){
                    dataSource.createGYRORecord(userSession.GyroList.get(i));
                }
                for (int i = 0; i < userSession.RotList.size(); i++){
                    dataSource.createROTRecord(userSession.RotList.get(i));
                }*/
            }
        });

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

        listener= new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                Sensor sensor = event.sensor;
                if(record) {
                    if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                        //TODO: get values
                        long timestamp = System.currentTimeMillis();
                        float value1 = event.values[0];
                        float value2 = event.values[1];
                        float value3 = event.values[2];
                        String readings = "Time: \t" + timestamp + "\t Value1:" + value1 + "\t Value2:" + value2 + "\t Value3:" + value3 + "\t ";
                        String displayReading = "GYRO:\t Value1:" + value1 + "\t Value2:" + value2 + "\t Value3:" + value3 + "\t ";
                        gyroTextView.setText(displayReading);
                        //Log.i("GYRO",readings);
                        GyroValue gyroValue = new GyroValue(timestamp, value1, value2, value3);
                        //dataSource.createGYRORecord(gyroValue);
                        userSession.GyroList.add(new GyroValue(System.currentTimeMillis(), event.values[0], event.values[1], event.values[2]));

                   /* List<GyroValue> gyroValues = dataSource.getAllGYROValues();
                    String print = "";
                    for(int i = 0; i < gyroValues.size(); i++){
                        print = print + gyroValues.get(i).timestamp + "\t";
                    }
                    Log.i("GYRO", print); */
                    } else if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                        //TODO: get values
                        String readings = "Time: \t" + System.currentTimeMillis() + "\t Value1:" + event.values[0] + "\t Value2:" + event.values[1] + "\t Value3:" + event.values[2] + "\t ";
                        String displayReading = "Accel:\t Value1:" + event.values[0] + "\t Value2:" + event.values[1] + "\t Value3:" + event.values[2] + "\t ";
                        accelTextView.setText(displayReading);
                        //Log.i("ACCEL",readings);
                        userSession.AccList.add(new AccValue(System.currentTimeMillis(), event.values[0], event.values[1], event.values[2]));
                    } else if (sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
                        //TODO: get values
                        String readings = "Time: \t" + System.currentTimeMillis() + "\t Value1:" + event.values[0] + "\t Value2:" + event.values[1] + "\t Value3:" + event.values[2] + "\t ";
                        String displayReading = "ROTVECTOR:\t Value1:" + event.values[0] + "\t Value2:" + event.values[1] + "\t Value3:" + event.values[2] + "\t ";
                        rotVectTextView.setText(displayReading);
                        //Log.i("ROT_VECTOR",readings);
                        userSession.RotList.add(new RotValue(System.currentTimeMillis(), event.values[0], event.values[1], event.values[2]));
                    }
                }

            }
        };


        mSensorManager.registerListener(listener,mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listener,mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listener,mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),SensorManager.SENSOR_DELAY_UI);


        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
               if(record) {
                   // Called when a new location is found by the network location provider.
                   long timestamp = System.currentTimeMillis();
                   double latitude = location.getLatitude();
                   double longitude = location.getLongitude();
                   String readings = "Time: \t" + timestamp + "\t Latitude:" + latitude + "\t Longitude:" + longitude + "\t ";
                   String displayReading = "GPS:\t Latitude:" + location.getLatitude() + "\t Longitude:" + location.getLongitude() + "\t ";
                   gpsTextView.setText(displayReading);
                   //GPSValue gpsValue = new GPSValue(timestamp, latitude, longitude);
                   //dataSource.createGPSRecord(gpsValue);

               /* List<GPSValue> gpsValues =  dataSource.getAllGPSValues();
                for(int i=0;i<gpsValues.size();i++) {
                    Log.i("GPS", String.valueOf(gpsValues.get(i).timestamp));
                }*/
                   //  Log.i("GPS:",readings);
                   //Log.i("Values",dataSource.getAllGPSValues().toString());
                   userSession.GPSList.add(new GPSValue(timestamp, latitude, longitude));
               }
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


        }


}
