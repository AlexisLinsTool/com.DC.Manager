package manager.dc.com.dcoursemanager.UI.AddAndSee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import manager.dc.com.dcoursemanager.OBJ.Course;
import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.database.Dao.SubjectDao;
import manager.dc.com.dcoursemanager.database.Database;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;

public class AddOrChangeACourseTimeActivity extends AppCompatActivity {

    static final String TAG = AddOrChangeACourseTimeActivity.class.getSimpleName();
    static final int MSG_GETSUBJECT = 1;

    int KEY, MIN, MAX, WEEKNUM, COURSENUM;
    long ID;

    SubjectEntity Sentity;

    TextView TcourseName, TTermNum, TCourseT;
    EditText StartWeek, EndWeek, WeekNum, CourseNum;
    CheckBox IsClear;
    Button SaveOrChange;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GETSUBJECT:
                    Sentity = (SubjectEntity) msg.obj;
                    TcourseName.setText(Sentity.getCourseName());
                    TTermNum.setText(String.valueOf(Sentity.getTermNum()));
                    TCourseT.setText(Sentity.getCourseTeacher());
                    SaveOrChange = findViewById(R.id.AddCourseTime_B_saveChange);
                    SaveOrChange.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SaveChangeCourseTime();
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addorchangecoursetime);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        KEY = intent.getIntExtra("what", 0);
        TcourseName = findViewById(R.id.AddCourseTime_T_CourseName);
        TTermNum = findViewById(R.id.AddCourseTime_T_termNum);
        TCourseT = findViewById(R.id.AddCourseTime_T_courseT);
        StartWeek = findViewById(R.id.AddCourseTime_E_StartWeek);
        EndWeek = findViewById(R.id.AddCourseTime_E_EndWeek);
        WeekNum = findViewById(R.id.AddCourseTime_E_weekNum);
        CourseNum = findViewById(R.id.AddCourseTime_E_courseNum);
        IsClear = findViewById(R.id.AddCourseTime_Check_isClearNote);
        if (KEY == 1) {
            MIN = intent.getIntExtra("Minweek", 0);
            StartWeek.setText(String.valueOf(MIN));
            MAX = intent.getIntExtra("Maxweek", 0);
            EndWeek.setText(String.valueOf(MAX));
            WEEKNUM = intent.getIntExtra("weekNum", 0);
            WeekNum.setText(String.valueOf(WEEKNUM));
            COURSENUM = intent.getIntExtra("courseNum", 0);
            CourseNum.setText(String.valueOf(COURSENUM));
        }
        ID = intent.getLongExtra("Subject", 0);
        getSubject(ID);
    }

    private void getSubject(final long id) {
        Database.init(getApplication());
        new Thread(new Runnable() {
            @Override
            public void run() {
                SubjectDao subjectDao = Database.getsInstance().getSubjectDao();
                SubjectEntity entity = subjectDao.getSubjectById(id);
                Message m = new Message();
                m.what = MSG_GETSUBJECT;
                m.obj = entity;
                handler.sendMessageAtFrontOfQueue(m);
            }
        }).start();
    }

    private void SaveChangeCourseTime() {
        boolean s = true,isClear = false;
        int StartNum = 0, EndNum = 0,WNum = 0,CNum = 0;
        if (StartWeek.getText().toString().matches("[1-9][0-9]+")) {
            StartNum = Integer.valueOf(StartWeek.getText().toString());
        } else {
            s = false;
            Toast.makeText(getApplicationContext(), "开始周数应该是正整数", Toast.LENGTH_SHORT).show();
        }
        if (StartWeek.getText().toString().matches("[1-9][0-9]+")) {
            EndNum = Integer.valueOf(EndWeek.getText().toString());
        } else {
            s = false;
            Toast.makeText(getApplicationContext(), "结束周数应该是正整数", Toast.LENGTH_SHORT).show();
        }
        if (EndNum < StartNum) {
            s = false;
            Toast.makeText(getApplicationContext(), "结束周数应该大于等于开始周数", Toast.LENGTH_SHORT).show();
        }
        if (WeekNum.getText().toString().matches("[1-7]")) {
            WNum = Integer.valueOf(WeekNum.getText().toString());
        } else {
            s = false;
            Toast.makeText(getApplicationContext(), "星期数应该是1-7", Toast.LENGTH_SHORT).show();
        }
        if (CourseNum.getText().toString().matches("[1-9]([0-9])?")) {
            CNum = Integer.valueOf(CourseNum.getText().toString());
        } else {
            s = false;
            Toast.makeText(getApplicationContext(), "课程节数应该是1-99以内", Toast.LENGTH_SHORT).show();
        }
        isClear = IsClear.isChecked();
        if (s) {
            Database.init(getApplication());
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();
        }
    }
}
