package com.example.sensorinfo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProximityDAO {
    @Query("SELECT * FROM proximitydb WHERE id IN (:userId)")
    ProximityDB loadById(int userId);

    @Insert
    void insert(ProximityDB proximityDB);

    @Update
    void update(ProximityDB proximityDB);

    @Query("SELECT EXISTS(SELECT * FROM ProximityDB WHERE id =:userId )")
    boolean is_exist(int userId);

}
