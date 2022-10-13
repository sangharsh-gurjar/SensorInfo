package com.example.sensorinfo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ProximityDB.class},version = 1)
public abstract class ProximityDatabase extends RoomDatabase {
    public abstract ProximityDAO proximityDAO();
}
