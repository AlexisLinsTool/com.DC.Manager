package manager.dc.com.dcoursemanager.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    private SQLiteDatabase getWDB(){
        if(DBHelper == null)
            DBHelper = new CourseDB(mcontext,"DCManager.db",null,1);
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        return db;
    }
    private boolean insertCourse(Course course){
        boolean Win = false;
        SQLiteDatabase db = getWDB();
        ContentValues values = new ContentValues();
        values.put("courseID",course.getCourseID());
        values.put("termNum",course.getTermNum());
        values.put("courseName",course.getCourseName());
        values.put("courseT",course.getCourseT());
        db.insert("course",null,values);
        return Win;
    }
    private boolean insertInfo(Course course,int Year,int Month, int Day, int weekNum){
        boolean Win = false;
        SQLiteDatabase db = getWDB();
        ContentValues values = new ContentValues();
        values.put("termNum",course.getTermNum());
        values.put("Year",Year);
        values.put("Month",Month);
        values.put("Day",Day);
        values.put("WeekNum",course.getWeekNum());
        values.put("address",course.getAddress());
        values.put("courseID",course.getCourseID());
        values.put("note",course.getNote());
        db.insert("info",null,values);

        return Win;
    }

    private List<Course> getCourseByWeekNum(int week){
        List<Course> li = new ArrayList<Course>();
        SQLiteDatabase db = getWDB();
        Cursor cursor = db.query("info",null,"WeekNum = ?",new String[]{""+week},null,null,null);
        if(cursor.moveToFirst()){
            do{
                Course c = new Course();
                Calendar C = Calendar.getInstance();
                c.setTermNum(cursor.getInt(cursor.getColumnIndex("termNum")));
                int Y = cursor.getInt(cursor.getColumnIndex("Yesr"));
                int M = cursor.getInt(cursor.getColumnIndex("Month"));
                int D = cursor.getInt(cursor.getColumnIndex("Day"));
                C.set(Y,M,D);
                c.setWeek(C.get(Calendar.DAY_OF_WEEK));
                c.setCourseID(cursor.getString(cursor.getColumnIndex("courseID")));
                c.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                c.setNote(cursor.getString(cursor.getColumnIndex("note")));
                c.setCourseNum(cursor.getInt(cursor.getColumnIndex("courseNum")));
                Cursor cursor1 = db.query("course",null,"courseID = ?", new String[]{""+c.getCourseID()},null,null,null);
                cursor1.moveToFirst();
                c.setCoourseName(cursor1.getString(cursor1.getColumnIndex("courseName")));
                c.setCourseT(cursor1.getString(cursor1.getColumnIndex("courseT")));
                li.add(c);
            }while(cursor.moveToNext());
        }
        return li;
    }

}
