package manager.dc.com.dcoursemanager.database.Table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subject")
public class SubjectEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "course_id")
    public String courseId;
    @ColumnInfo(name = "term_num")
    public int termNum;
    @ColumnInfo(name = "course_name")
    public String courseName;
    @ColumnInfo(name = "course_teacher")
    public String courseTeacher;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getTermNum() {
        return termNum;
    }

    public void setTermNum(int termNum) {
        this.termNum = termNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }
}
