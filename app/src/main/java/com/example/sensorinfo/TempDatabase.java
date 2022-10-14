package com.example.sensorinfo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {TempDB.class},version = 1)
public abstract class TempDatabase extends RoomDatabase {
    public abstract TempDAO tempDAO();
}
