import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AccelerometerDB {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public double xCoor;

    @ColumnInfo
    public double yCoor;

    @ColumnInfo
    public double zCoor;
}
