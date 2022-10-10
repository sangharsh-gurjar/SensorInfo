package com.example.sensorinfo;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {AccelerometerDB.class}, version = 1)
public abstract class AccelerometerDatabase extends RoomDatabase {
    public abstract AccelerometerDBDAO accelerometerDBDAO();
}
