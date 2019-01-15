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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.database.Dao.TimeDao;
import manager.dc.com.dcoursemanager.database.Database;
import manager.dc.com.dcoursemanager.database.Table.TimeEntity;


public class AddCourseNoteActivity extends AppCompatActivity {

    private static  final String TAG = AddCourseNoteActivity.class.getSimpleName();

    static final int MSG_GETNOTE = 0;
    static final int MSG_GETENTITY = 1;
    TextView courseName;
    TextView week;
    TextView weekNum;
    TextView courseNum;
    EditText note;
    Button saveChange;

    int TERMNUM,WEEK,WEEKNUM,COURSERNUM;
    String NAME,NOTE;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_GETNOTE:
                    if("".equals((String)msg.obj)||msg.obj == null){
                        note.setText("");
                    }else{
                        note.setText((String)msg.obj);
                    }
                    break;
                case MSG_GETENTITY:
                    Toast.makeText(getApplicationContext(),"更新成功",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_note);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        courseName = findViewById(R.id.courseInfo_T_courseName);
        week = findViewById(R.id.courseInfo_T_week);
        weekNum = findViewById(R.id.courseInfo_T_weekNum);
        courseNum = findViewById(R.id.courseInfo_T_courseNum);
        note = findViewById(R.id.courseInfo_T_note);
        saveChange = findViewById(R.id.courseInfo_B_saveChange);

        saveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NOTE = note.getText().toString();
                updateNote();
            }
        });

        TERMNUM = bundle.getInt("term");
        WEEK = bundle.getInt("week");
        WEEKNUM = bundle.getInt("weekNum");
        COURSERNUM = bundle.getInt("courseNum");
        NAME = bundle.getString("name");
        if("".equals(NAME))
            courseName.setText("暂无");
        else
            courseName.setText(NAME);
        week.setText(String.valueOf(WEEK));
        weekNum.setText(String.valueOf(WEEKNUM));
        courseNum.setText(String.valueOf(COURSERNUM));
        getNoteFromDatabase();
    }

    private void updateNote(){
        Database.init(getApplication());
        new Thread(new Runnable() {
            @Override
            public void run() {

                TimeDao timeDao = Database.getsInstance().getTimeDao();
                TimeEntity entity = timeDao.getOneByWeekNumAndWeekAndCourseNumAndTermNum(WEEK,WEEKNUM,COURSERNUM,TERMNUM);
                if(entity == null){
                    entity = new TimeEntity();
                    entity.setTermNum(TERMNUM);
                    entity.setWeek(WEEK);
                    entity.setWeekNum(WEEKNUM);
                    entity.setCourseNum(COURSERNUM);
                    entity.setNote(NOTE);
                    timeDao.add(entity);
                }else{
                    entity.setNote(NOTE);
                    timeDao.update(entity);

                }
                Log.d(TAG,entity.toString());
                Message message = new Message();
                message.what = MSG_GETENTITY;
                handler.sendMessageAtFrontOfQueue(message);
            }
        }).start();
    }

    private void getNoteFromDatabase(){
        Database.init(getApplication());
        new Thread(new Runnable() {
            @Override
            public void run() {
                TimeDao timeDao = Database.getsInstance().getTimeDao();
                String NOTE = timeDao.getNoteByWeekNumAndWeekAndCourseNumAndTermNum(WEEK,WEEKNUM,COURSERNUM,TERMNUM);
                if(NOTE == null)    NOTE = "";
                Log.d(TAG,NOTE);
                Message message = new Message();
                message.obj = NOTE;
                message.what = MSG_GETNOTE;
                handler.sendMessageAtFrontOfQueue(message);

            }
        }).start();
    }

}
