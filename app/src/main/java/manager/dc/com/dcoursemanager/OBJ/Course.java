package manager.dc.com.dcoursemanager.OBJ;

public class Course {
    int termNum;
    long CourseId;
    long Timeid;
    String courseID;
    String courseName;
    String courseT;
    int startWeek;
    int endWeek;
    int weekNum;
    int week;
    int courseNum;
    String address;
    String note;

    public String getNote() {
        return note;
    }

    public String getCourseID() {
        return courseID;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public String getAddress() {
        return address;
    }

    public int getWeek() {
        return week;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public int getTermNum() {
        return termNum;
    }

    public String getCourseT() {
        return courseT;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public void setTermNum(int termNum) {
        this.termNum = termNum;
    }

    public void setCourseT(String courseT) {
        this.courseT = courseT;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStartWeek() { return startWeek; }

    public void setStartWeek(int startWeek) { this.startWeek = startWeek; }

    public int getEndWeek() { return endWeek; }

    public void setEndWeek(int endWeek) { this.endWeek = endWeek; }

    public long getCourseId() { return CourseId; }

    public void setCourseId(long courseId) { CourseId = courseId; }

    public long getTimeid() { return Timeid; }

    public void setTimeid(long timeid) { Timeid = timeid; }
}
