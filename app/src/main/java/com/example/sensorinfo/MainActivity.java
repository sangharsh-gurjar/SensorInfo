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
    boolean acc_enabled =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acc =findViewById(R.id.toggleButton1);

        acc_enabled = acc.isEnabled();
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




        show_data = (Button)findViewById(R.id.show_data) ;
        show_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data_hai && acc_enabled){
                    Intent intent = new Intent(MainActivity.this,DisplayData.class);
                    startActivity(intent);
                }
                else{
                    if(!data_hai) {
                        Toast.makeText(MainActivity.this, "nothing to show", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "enable toggle switch", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager!=null){
            Sensor acceleroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if(acceleroSensor!=null){
                sensorManager.registerListener(this,acceleroSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        else{
            Toast.makeText(this,"Sensor Manager Not found ",Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            // TODO :- create a object of database( for accelerometer ) class and store data into it
            AccelerometerDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AccelerometerDatabase.class, "database-name").allowMainThreadQueries().build();

            AccelerometerDBDAO obj = db.accelerometerDBDAO();
            boolean exist = obj.is_exist(1);

            if(exist){
                obj.update(new AccelerometerDB(1,event.values[0],event.values[1],event.values[2]));
                data_hai=true;
            }
            else{
                obj.insert(new AccelerometerDB(1,event.values[0],event.values[1],event.values[2]));

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}