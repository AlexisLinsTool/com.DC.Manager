package manager.dc.com.dcoursemanager.SQL;

import java.util.Calendar;
public class InfoManager {
    private int termNum;
    private Calendar Date;
    private int weekNum;
    private int week;
    private int courseNum;
    private String address;
    private String courseID;
    private String note;

    public int getTermNum() {
        return termNum;
    }

    public Calendar getDate() {
        return Date;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public int getWeek() {
        return week;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public String getAddress() {
        return address;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getNote() {
        return note;
    }

    public void setTermNum(int termNum) {
        this.termNum = termNum;
    }

    public void setDate(Calendar date) {
        Date = date;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
