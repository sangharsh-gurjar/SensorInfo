package com.example.sensorinfo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProximityDB {

    @PrimaryKey(autoGenerate = false)
    int id;

    @ColumnInfo
    double distance;

    ProximityDB(int id , double distance){
        this.id=id;
        this.distance=distance;
    }
}
