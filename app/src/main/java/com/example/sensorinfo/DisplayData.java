package com.example.sensorinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DisplayData extends AppCompatActivity {
    TextView display_data_acc;
    TextView display_data_gyro;
    TextView display_data_prox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        AccelerometerDatabase acc_db = Room.databaseBuilder(getApplicationContext(),
                AccelerometerDatabase.class, "database-name").allowMainThreadQueries().build();

        AccelerometerDBDAO obj = acc_db.accelerometerDBDAO();

        if(obj.is_exist(1)) {
            AccelerometerDB obj1 = obj.loadById(1);
            display_data_acc = findViewById(R.id.display_data_acc);
            display_data_acc.setText("Accelero info :- \n X coordinates :- "
                    + obj1.xCoor + " \nY coordinates :- " + obj1.yCoor + " \nZ coordinates :- " + obj1.zCoor);
        }
        GyroDatabase gyroDatabase = Room.databaseBuilder(getApplicationContext(),
                GyroDatabase.class, "Gyro-Database").allowMainThreadQueries().build();

        GyroDAO gyroDAO = gyroDatabase.gyroDAO();
        if(gyroDAO.is_exist(2)){
            GyroDB gyroDB = gyroDAO.loadById(2);
            display_data_gyro=findViewById(R.id.display_data_gyro);
            display_data_gyro.setText("Gyro info :- \n X coordinates :- "
                    + gyroDB.xCoor + " \nY coordinates :- " + gyroDB.yCoor + " \nZ coordinates :- " + gyroDB.zCoor);
        }

        ProximityDatabase proximityDatabase =Room.databaseBuilder(getApplicationContext(),
                ProximityDatabase.class,"Proximity - Database").allowMainThreadQueries().build();
        ProximityDAO proximityDAO=proximityDatabase.proximityDAO();

        if(proximityDAO.is_exist(3)){
            ProximityDB proximityDB=proximityDAO.loadById(3);
            display_data_prox=findViewById(R.id.display_data_prox);
            display_data_prox.setText("Proximity info :-  \n Distance:- "+proximityDB.distance);
        }
        else{
            Toast.makeText(this,"no - proximity info",Toast.LENGTH_SHORT).show();
        }








    }
}