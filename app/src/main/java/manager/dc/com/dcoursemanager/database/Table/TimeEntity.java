package manager.dc.com.dcoursemanager.database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courseTime")
public class TimeEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "course_num")
    public int courseNum;
    @ColumnInfo(name = "course_id")
    public String courseId;
    @ColumnInfo(name = "term_num")
    public int termNum;
    @ColumnInfo(name = "week")
    public int week;
    @ColumnInfo(name = "week_num")
    public int weekNum;
    @ColumnInfo(name = "address")
    public String address;
    @ColumnInfo(name = "note")
    public String note;


    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

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

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
