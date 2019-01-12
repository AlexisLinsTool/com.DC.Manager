package manager.dc.com.dcoursemanager.database.Dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import manager.dc.com.dcoursemanager.database.Table.TimeEntity;

@Dao
public interface TimeDao {

    @Query("select * from courseTime")
    List<TimeEntity> getAll();

    @Query("select * from courseTime where term_num =:termNum and week =:week")
    List<TimeEntity> getCourseTimeByWeek(int termNum,int week);

    @Query("select * from courseTime where term_num =:termNum and year =:year and month" +
            "=:month and day =:day and courseNum =:courseNum")
    TimeEntity getCourseTimeByCourseNum(int termNum,int year,int month,int day,int courseNum);


    @Insert
    void add(TimeEntity entity);

    @Delete
    void delete(TimeEntity entity);

    @Update
    void update(TimeEntity entity);

    @Query("Update courseTime set note =:note")
    void updateNoteByCourseTime(String note);
}
