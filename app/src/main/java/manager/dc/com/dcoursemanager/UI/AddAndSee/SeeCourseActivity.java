package manager.dc.com.dcoursemanager.UI.AddAndSee;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.security.auth.login.LoginException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import manager.dc.com.dcoursemanager.Adapter.SeeCourseAdapter;
import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.database.Dao.SubjectDao;
import manager.dc.com.dcoursemanager.database.Database;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;

public class SeeCourseActivity extends AppCompatActivity {

    private static final String TAG = SeeCourseActivity.class.getSimpleName();

    private static final int MSG_GETALLLIST = 1;
    ListView listView;
    List<SubjectEntity> mlist = new ArrayList<>();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GETALLLIST:
                    mlist.addAll((List<SubjectEntity>)msg.obj);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }

    };

    private SeeCourseAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_course);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getAll();
        mAdapter = new SeeCourseAdapter(this.getApplicationContext(),R.layout.list_seecourse_listview, mlist);
        listView = findViewById(R.id.SeeCourse_ListView);
        listView.setAdapter(mAdapter);
    }

    private void getAll() {
        Database.init(this.getApplication());
        new Thread(new Runnable() {

            @Override
            public void run() {
                List<SubjectEntity> li;
                SubjectDao subjectDao = Database.getsInstance().getSubjectDao();
                li = subjectDao.getAllSubject();
                Log.e(TAG, li.toString());
                Message m = new Message();
                m.what = MSG_GETALLLIST;
                m.obj = li;
                handler.sendMessageAtFrontOfQueue(m);
            }
        }).start();
    }
}
