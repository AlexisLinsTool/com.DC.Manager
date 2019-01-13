package manager.dc.com.dcoursemanager.UI.AddAndSee;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.database.Dao.SubjectDao;
import manager.dc.com.dcoursemanager.database.Database;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;

public class SeeCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_course);
        List<SubjectEntity> li = getAll();
        ((TextView)findViewById(R.id.SeeCourse_T_CourseName)).setText(li.get(0).getCourseName());
    }
    List<SubjectEntity> getAll(){
        List<SubjectEntity> li = new ArrayList<>();
        Database.init(this.getApplication());
        SubjectDao subjectDao = Database.getsInstance().getSubjectDao();
        li = subjectDao.getAllSubject();
        return li;
    }
}
