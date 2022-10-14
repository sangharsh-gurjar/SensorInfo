package com.example.sensorinfo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TempDAO {
    @Query("SELECT * FROM tempdb WHERE id IN (:userId)")
    TempDB loadById(int userId);

    @Insert
    void insert(TempDB tempDB);

    @Update
    void update(TempDB tempDB);

    @Query("SELECT EXISTS(SELECT * FROM tempdb WHERE id =:userId )")
    boolean is_exist(int userId);
}
