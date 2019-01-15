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
import manager.dc.com.dcoursemanager.UI.home.MainActivity;
import manager.dc.com.dcoursemanager.database.Dao.SubjectDao;
import manager.dc.com.dcoursemanager.database.Database;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MSG_ADDED = 1;

    EditText TtermNum;
    EditText TcourseId;
    EditText TcourseName;
    EditText TcourseT;

    Button AddCourse;
    Button Cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        TtermNum = findViewById(R.id.AddCourse_T_termNum);
        TcourseId = findViewById(R.id.AddCourse_T_courseID);
        TcourseName = findViewById(R.id.AddCourse_T_courseName);
        TcourseT = findViewById(R.id.AddCourse_T_courseT);

        Cancel = findViewById(R.id.AddCourse_B_Cancel);
        Cancel.setOnClickListener(this);
        AddCourse = findViewById(R.id.AddCourse_B_AddCourse);
        AddCourse.setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ADDED:
                    Toast.makeText(AddCourseActivity.this,"加入成功",Toast.LENGTH_SHORT).show();
                    TtermNum.setText("");TcourseId.setText("");TcourseName.setText("");TcourseT.setText("");
                    break;
            }
        }
    };
    private void AddCourse(){
        boolean check = true;
        int termNum = 0;
        if("".equals(TtermNum.getText().toString()))
            check = false;
        else {
            termNum = Integer.valueOf(TtermNum.getText().toString());
        }
        final String courseName = TcourseName.getText().toString();
        final String courseID = TcourseId.getText().toString();
        final String courseT = TcourseT.getText().toString();
        if("".equals(courseID)||"".equals(courseName)||"".equals(courseT)) {
            check = false;
        }
        if(check) {
            Database.init(getApplication());
            final int finalTermNum = termNum;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SubjectDao subjectDao = Database.getsInstance().getSubjectDao();
                    SubjectEntity subjectEntity = new SubjectEntity();
                    subjectEntity.setCourseId(courseID);
                    subjectEntity.setCourseName(courseName);
                    subjectEntity.setCourseTeacher(courseT);
                    subjectEntity.setTermNum(finalTermNum);
                    subjectDao.add(subjectEntity);

                    Log.d("AddSubject",courseID+"=="+courseName+"=="+courseT+"==");
                    Message m = new Message();
                    m.what = MSG_ADDED;
                    mHandler.sendMessageAtFrontOfQueue(m);

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
