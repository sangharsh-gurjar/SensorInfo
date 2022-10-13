package com.example.sensorinfo;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GyroDB {


    @PrimaryKey(autoGenerate = false)
    public int id;

    @ColumnInfo
    public double xCoor;

    @ColumnInfo
    public double yCoor;

    @ColumnInfo
    public double zCoor;
    GyroDB(int id,double xCoor,double yCoor,double zCoor){
        this.id =id;
        this.xCoor=xCoor;
        this.yCoor=yCoor;
        this.zCoor=zCoor;
    }
}