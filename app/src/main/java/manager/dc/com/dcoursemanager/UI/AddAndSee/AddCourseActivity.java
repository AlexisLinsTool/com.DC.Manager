package manager.dc.com.dcoursemanager.UI.AddAndSee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.UI.MainActivity;
import manager.dc.com.dcoursemanager.database.Dao.SubjectDao;
import manager.dc.com.dcoursemanager.database.Database;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MSG_ADDED = 1;

    Button AddCourse;
    Button Cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        Cancel = findViewById(R.id.AddCourse_B_Cancel);
        Cancel.setOnClickListener(this);
        AddCourse = findViewById(R.id.AddCourse_B_AddCourse);
        AddCourse.setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {//此方法在ui线程运行
            switch (msg.what) {
                case MSG_ADDED:
                    Toast.makeText(AddCourseActivity.this,"加入成功",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private void AddCourse(){
        Database.init(getApplication());
        Database db = Database.getsInstance();
        final SubjectDao subjectDao = db.getSubjectDao();
        boolean check = true;
        int termNum = 0;
        if("".equals(((EditText)findViewById(R.id.AddCourse_T_termNum)).toString()))
            check = false;
        else {
            termNum = Integer.valueOf(((EditText)findViewById(R.id.AddCourse_T_termNum)).getText().toString());
        }
        final String courseName = ((EditText)findViewById(R.id.AddCourse_T_courseName)).toString();
        final String courseID = ((EditText)findViewById(R.id.AddCourse_T_courseID)).toString();
        final String courseT = ((EditText)findViewById(R.id.AddCourse_T_courseT)).toString();
        if("".equals(courseID)||"".equals(courseName)||"".equals(courseT)) {
            check = false;
        }
        if(check) {
            final int finalTermNum = termNum;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SubjectEntity subjectEntity = new SubjectEntity();
                    subjectEntity.setCourseId(courseID);
                    subjectEntity.setCourseName(courseName);
                    subjectEntity.setCourseTeacher(courseT);
                    subjectEntity.setTermNum(finalTermNum);
                    subjectDao.add(subjectEntity);
                    Log.d("ADDED","Done");
                }
            }).start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AddCourse_B_Cancel:
                Intent intent = new Intent(AddCourseActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.AddCourse_B_AddCourse:
                AddCourse();
                break;

        }

    }
}
