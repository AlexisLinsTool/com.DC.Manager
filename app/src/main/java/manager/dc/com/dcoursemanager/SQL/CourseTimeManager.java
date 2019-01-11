package manager.dc.com.dcoursemanager.SQL;

import java.util.Calendar;

public class CourseTimeManager {
    private int courseName;
    private Calendar startTime;
    private Calendar endTime;

    public void setCourseName(int courseName) {
        this.courseName = courseName;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public int getCourseName() {
        return courseName;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }
}
