package manager.dc.com.dcoursemanager.database.Dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;

@Dao
public interface SubjectDao {

    @Query("select * from subject")
    List<SubjectEntity> getAllSubject();

    @Query("select * from subject where course_id =:courseId and term_num =:termNum")
    SubjectEntity getSubjectBySubjectId(String courseId,int termNum);

    @Query("select distinct  course_name from subject")
    List<String> getAllCourseName();

    @Query("select distinct course_name from subject where term_num =:termNum")
    List<String> getAllCourseNanmeByTermNum(int termNum);

    @Query("select * from subject where id =:id")
    SubjectEntity getSubjectById(long id);

    @Insert
    void add(SubjectEntity entity);

    @Update
    void update(SubjectEntity entity);

    @Query("Update subject set course_teacher =:teacherName where term_num =:termNum and " +
            "course_id =:courseId")
    void updateTeacherNameByTermNumAndCourseId(int termNum,String courseId,String teacherName);

    @Query("Update subject set term_num =:newtermNum where term_num =:termNum and " +
            "course_id =:courseId")
    void updateTermNumByTermNumAndCourseId(int termNum,String courseId,int newtermNum);

    @Delete
    void delete(SubjectEntity entity);


}
