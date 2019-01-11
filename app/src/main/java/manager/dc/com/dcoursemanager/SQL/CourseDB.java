package manager.dc.com.dcoursemanager.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class CourseDB extends SQLiteOpenHelper {

    private static final String CREATE_TABLE_COURSE="" +
            "CREATE TABLE course(" +
            "courseID String ," +
            "termNum int," +
            "courseName String," +
            "courseT String" +
            ")";
    private static final String CRAETE_TABEL_INFO="" +
            "CREATE TABLE info(" +
            "termNum int," +
            "Year int," +
            "Month int," +
            "Day int," +
            "courseNum int," +
            "WeekNum int," +
            "address String," +
            "courseID String," +
            "note String" +
            ")";
    Context mcontext;
    public CourseDB( Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COURSE);
        db.execSQL(CRAETE_TABEL_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
