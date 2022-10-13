package com.example.sensorinfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GyroDAO {

    // accelerometerdb table se id ko search karo
    @Query("SELECT * FROM gyrodb WHERE id IN (:userId)")
    GyroDB loadById(int userId);

    // insert in accelerometerdb table //var is just object of accelerometer class
    @Insert
    void insert(GyroDB var);

    @Update
    void update(GyroDB var);

    @Query("SELECT EXISTS(SELECT * FROM GyroDB WHERE id =:userId )"   )
    Boolean is_exist(int userId);
}

