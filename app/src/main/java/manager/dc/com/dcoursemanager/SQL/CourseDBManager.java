package manager.dc.com.dcoursemanager.SQL;

public class CourseDBManager {
    private String courseID;
    private int termNum;
    private String courseName;
    private String courseT;

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseT(String courseT) {
        this.courseT = courseT;
    }

    public void setTermNum(int termNum) {
        this.termNum = termNum;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseT() {
        return courseT;
    }

    public int getTermNum() {
        return termNum;
    }

}
