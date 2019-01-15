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
    List<TimeEntity> getUnqiueTimeByCourseNum(long courseId);

    @Query("select Max(week) from courseTime where course_num =:courseNum and week_num =:weekNum " +
            "and course_id =:courseId")
    int getMaxWeekByCourseNumAndWeekNumAndCourseId(int courseNum, int weekNum, long courseId);

    @Query("select Min(week) from courseTime where course_num =:courseNum and week_num =:weekNum " +
            "and course_id =:courseId")
    int getMinWeekByCourseNumAndWeekNumAndCourseId(int courseNum, int weekNum, long courseId);

    @Query("select note from courseTime where week =:week and week_num =:weekNum " +
            "and course_num =:courseNum and term_num =:termNum limit 0,1")
    String getNoteByWeekNumAndWeekAndCourseNumAndTermNum(int week, int weekNum, int courseNum,int termNum);

    @Query("select * from courseTime where week =:week and week_num =:weekNum " +
            "and course_num =:courseNum and term_num =:termNum limit 0,1")
    TimeEntity getOneByWeekNumAndWeekAndCourseNumAndTermNum(int week, int weekNum, int courseNum,int termNum);

    @Insert
    void add(TimeEntity entity);

    @Delete
    void delete(TimeEntity entity);

    @Update
    void update(TimeEntity entity);

    @Query("Update courseTime set note =:note")
    void updateNoteByCourseTime(String note);

}
