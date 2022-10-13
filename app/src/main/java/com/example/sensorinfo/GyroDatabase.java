package com.example.sensorinfo;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GyroDB.class}, version = 1)
public abstract class GyroDatabase extends RoomDatabase {
    public abstract GyroDAO gyroDAO();
}

