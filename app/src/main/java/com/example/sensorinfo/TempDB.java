package com.example.sensorinfo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TempDB {

    @PrimaryKey
    int id;

    @ColumnInfo
    int temp;

    TempDB(int id,int temp){
        this.id=id;
        this.temp=temp;
    }
}
