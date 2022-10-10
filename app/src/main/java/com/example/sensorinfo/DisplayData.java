package com.example.sensorinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayData extends AppCompatActivity {
    TextView display_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        AccelerometerDatabase db = Room.databaseBuilder(getApplicationContext(),
                AccelerometerDatabase.class, "database-name").allowMainThreadQueries().build();

        AccelerometerDBDAO obj = db.accelerometerDBDAO();
        AccelerometerDB obj1 = obj.loadById(1);
        display_data = findViewById(R.id.display_data);
        display_data.setText("Accelero info :- \n X coordinates :- " + obj1.xCoor + " \nY coordinates :- " + obj1.yCoor + " \nZ coordinates :- " + obj1.zCoor);




    }
}