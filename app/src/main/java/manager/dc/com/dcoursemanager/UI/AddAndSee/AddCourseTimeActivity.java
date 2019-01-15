package manager.dc.com.dcoursemanager.UI.AddAndSee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import manager.dc.com.dcoursemanager.Adapter.AddCourseTimeAdapter;
import manager.dc.com.dcoursemanager.OBJ.Course;
import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.database.Dao.SubjectDao;
import manager.dc.com.dcoursemanager.database.Dao.TimeDao;
import manager.dc.com.dcoursemanager.database.Database;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;
import manager.dc.com.dcoursemanager.database.Table.TimeEntity;

public class AddCourseTimeActivity extends AppCompatActivity {

    static final String TAG = AddCourseTimeActivity.class.getSimpleName();
    static final int MSG_GETSIMPLETIME = 0;
    static final int MSG_GETSUBJECT = 1;

    SubjectEntity Sentity;
    ListView listView;
    List<Course> mlist;
    AddCourseTimeAdapter mAdapter;
    Button addNowTime;
    TextView Title;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_GETSIMPLETIME:
                    mlist.addAll((List<Course>)msg.obj);
                    mAdapter.notifyDataSetChanged();
                    if(mlist.size() == 0){
                        Toast.makeText(getApplicationContext(),"您还未加入任何上课的时间安排",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case MSG_GETSUBJECT:
                    Sentity = (SubjectEntity)msg.obj;
                    Title.setText(Sentity.getCourseName());
                    getSimpleTime();
                    addNowTime = findViewById(R.id.AddCourseTime_B_AddNew);
                    addNowTime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(),AddOrChangeACourseTimeActivity.class);
                            intent.putExtra("what",0);
                            intent.putExtra("Subject",Sentity.getId());
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_time);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        final Intent intent = getIntent();
        long id = intent.getLongExtra(getString(R.string.Intent_SubjectId),-1);

        Title = findViewById(R.id.AddCourseTime_title);
        mlist = new ArrayList<>();
        getSubject(id);
        mAdapter = new AddCourseTimeAdapter(getApplicationContext(),R.layout.list_addcoursetime_listview,mlist);
        listView = findViewById(R.id.AddCourseTime_Listview_old);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(),AddOrChangeACourseTimeActivity.class);
                intent1.putExtra("what",1);
                Course c = mlist.get(position);
                intent1.putExtra("Subject",c.getCourseId());
                intent1.putExtra("Minweek",c.getStartWeek());
                intent1.putExtra("Maxweek",c.getEndWeek());
                intent1.putExtra("weekNum",c.getWeekNum());
                intent1.putExtra("courseNum",c.getCourseNum());
                startActivity(intent1);
            }
        });

    }

    private void getSubject(final long id){
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

    private void getSimpleTime(){
        Database.init(getApplication());
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Course> mli = new ArrayList<>();
                TimeDao timeDao = Database.getsInstance().getTimeDao();
                List<TimeEntity> li = timeDao.getUnqiueTimeByCourseNum(Sentity.getId());
                for(TimeEntity e:li){
                    Course c = new Course();
                    c.setCourseT(Sentity.getCourseTeacher());
                    c.setCourseName(Sentity.getCourseName());
                    c.setNote(e.getNote());
                    c.setAddress(e.getAddress());
                    c.setTermNum(e.getTermNum());
                    c.setCourseId(e.getCourseId());
                    c.setWeekNum(e.getWeekNum());
                    c.setCourseNum(e.getCourseNum());
                    c.setStartWeek(timeDao.getMinWeekByCourseNumAndWeekNumAndCourseId(c.getCourseNum(),c.getWeekNum(),c.getCourseId()));
                    c.setEndWeek(timeDao.getMaxWeekByCourseNumAndWeekNumAndCourseId(c.getCourseNum(),c.getWeekNum(),c.getCourseId()));
                    mli.add(c);
                }
                Message m = new Message();
                m.what = MSG_GETSIMPLETIME;
                m.obj = mli;
                handler.sendMessageAtFrontOfQueue(m);
            }
        }).start();
    }
}
