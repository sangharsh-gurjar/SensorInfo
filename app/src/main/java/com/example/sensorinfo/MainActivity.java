package com.example.sensorinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button show_data;
    Boolean data_hai = false;
    ToggleButton acc;
    ToggleButton gyro;
    ToggleButton prox;
    boolean acc_enabled =false;
    boolean gyro_enabled =false;
    boolean prox_enabled= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acc =findViewById(R.id.toggleButton1);
        gyro=findViewById(R.id.toggleButton4);
        prox=findViewById(R.id.toggleButton2);

        acc_enabled=false;
        prox_enabled=false;
        gyro_enabled=false;

        // on checklistener for acceleromter
        final CompoundButton.OnCheckedChangeListener toggleButtonChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // The user changed the button, do something
                if (isChecked) {
                  acc_enabled=true;
                }
                else{
                    acc_enabled=false;
                }
            }
        };
        acc.setOnCheckedChangeListener(toggleButtonChangeListener);

        // on checklistener for gyroscope
        final CompoundButton.OnCheckedChangeListener toggleButtonChangeListener_gyro = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // The user changed the button, do something
                if (isChecked) {
                    gyro_enabled=true;
                }
                else{
                    gyro_enabled=false;
                }
            }
        };
        gyro.setOnCheckedChangeListener(toggleButtonChangeListener_gyro);

        // onCheckListener for proximity sensor
        final CompoundButton.OnCheckedChangeListener toggleButtonChangeListener_prox = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // The user changed the button, do something
                if (isChecked) {
                    prox_enabled=true;
                }
                else{
                    prox_enabled=false;
                }
            }
        };
        prox.setOnCheckedChangeListener(toggleButtonChangeListener_prox);




        show_data = (Button)findViewById(R.id.show_data) ;
        show_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data_hai){
                    Intent intent = new Intent(MainActivity.this,DisplayData.class);
                    startActivity(intent);
                }
                else{
                    if(!data_hai) {
                        Toast.makeText(MainActivity.this, "nothing to show", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager!=null){
            Sensor acceleroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Sensor gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            Sensor proxSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            if(acceleroSensor!=null){
                sensorManager.registerListener(this,acceleroSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
            if(gyroSensor!=null){
                sensorManager.registerListener(this,gyroSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
            if(proxSensor!=null){
                sensorManager.registerListener(this,proxSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        else{
            Toast.makeText(this,"Sensor Manager Not found ",Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // for accelerometer
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && acc_enabled){
            // TODO :- create a object of database( for accelerometer ) class and store data into it
            AccelerometerDatabase acc_db = Room.databaseBuilder(getApplicationContext(),
                    AccelerometerDatabase.class, "database-name").allowMainThreadQueries().build();

            AccelerometerDBDAO obj = acc_db.accelerometerDBDAO();
            boolean exist = obj.is_exist(1);

            if(exist){
                obj.update(new AccelerometerDB(1,event.values[0],event.values[1],event.values[2]));
                data_hai=true;
            }
            else{
                obj.insert(new AccelerometerDB(1,event.values[0],event.values[1],event.values[2]));

            }
        }

        // for gyroscope
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && gyro_enabled){
            GyroDatabase gyroDatabase=Room.databaseBuilder(getApplicationContext(),
                    GyroDatabase.class,"Gyro-Database").allowMainThreadQueries().build();
            GyroDAO gyroDAO=gyroDatabase.gyroDAO();
            boolean exist=gyroDAO.is_exist(2);
            if(exist){
                gyroDAO.update(new GyroDB(2,event.values[0],event.values[1],event.values[2]));
                data_hai=true;
            }
            else{
                gyroDAO.insert(new GyroDB(2,event.values[0],event.values[1],event.values[2]));

            }

        }

        // for proximity sensor
        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY && prox_enabled){
            ProximityDatabase proximityDatabase =Room.databaseBuilder(getApplicationContext(),
                    ProximityDatabase.class,"Proximity - Database").allowMainThreadQueries().build();
            ProximityDAO proximityDAO=proximityDatabase.proximityDAO();
            boolean exist = proximityDAO.is_exist(3);
            if(exist){
                proximityDAO.update(new ProximityDB(3,event.values[0]));
                data_hai=true;
            }
            else{
                proximityDAO.insert(new ProximityDB(3,event.values[0]));

            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}