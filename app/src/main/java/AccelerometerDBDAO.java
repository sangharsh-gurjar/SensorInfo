import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface AccelerometerDBDAO {

    // accelerometerdb table se id ko search karo
    @Query("SELECT * FROM accelerometerdb WHERE id IN (:userId)")
    AccelerometerDB loadById(int userId);

    // insert in accelerometerdb table //var is just object of accelerometer class
    @Insert
    void insert(AccelerometerDB var);

    @Query("SELECT EXISTS(SELECT * FROM AccelerometerDB WHERE id =:userId )"   )
    Boolean is_exist(int userId);
}
