package manager.dc.com.dcoursemanager.SQL;

import android.content.ContentValues;
import android.content.Context;

import manager.dc.com.dcoursemanager.OBJ.Course;

public class DBUtil {
    static Context mcontext;
    CourseDB DBHelper;
    private DBUtil(){
    }
    private static class SingleonInstance{
        private static final DBUtil INSTANCE = new DBUtil();
    }
    public static DBUtil getInstance(Context context){
        mcontext =context;
        return SingleonInstance.INSTANCE;
    }

    private boolean insertCourse(Course course){
        boolean Win = false;
        DBHelper = new CourseDB(mcontext,"DCManager.db",null,1);
        ContentValues values = new ContentValues();
        values.put("courseID",course.getCourseID());
        values.put("termNum",course.getTermNum());
        values.put("courseName",course.getCoourseName());
        values.put("courseT",course.getCourseT());
        return Win;
    }
    private boolean insertCourse(Course course, int weekNum){
        boolean Win = false;
        return Win;
    }
}
