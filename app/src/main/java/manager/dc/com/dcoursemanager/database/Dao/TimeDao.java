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

    @Query("select * from courseTime where term_num =:termNum and course_num =:courseNum")
    TimeEntity getCourseTimeByCourseNum(int termNum,int courseNum);

    @Query("select * from courseTime where course_id = :courseId" +
            " group by (term_num and week_num and course_num)")
    List<TimeEntity> getUnqiueTimeByCourseNum(int termNum,int courseId);

    @Query("select Max(week) from courseTime where course_num =:courseNum and week_num =:weekNum " +
            "and course_id =:courseId")
    int getMaxWeekByCourseNumAndWeekNumAndCourseId(int courseNum, int weekNum, int courseId);

    @Query("select Min(week) from courseTime where course_num =:courseNum and week_num =:weekNum " +
            "and course_id =:courseId")
    int getMinWeekByCourseNumAndWeekNumAndCourseId(int courseNum, int weekNum, int courseId);

    @Insert
    void add(TimeEntity entity);

    @Delete
    void delete(TimeEntity entity);

    @Update
    void update(TimeEntity entity);

    @Query("Update courseTime set note =:note")
    void updateNoteByCourseTime(String note);

}
