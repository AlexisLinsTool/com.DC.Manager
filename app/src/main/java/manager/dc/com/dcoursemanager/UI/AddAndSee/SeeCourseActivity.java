package manager.dc.com.dcoursemanager.UI.AddAndSee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GETALLLIST:
                    mlist.addAll((List<SubjectEntity>) msg.obj);
                    mAdapter.notifyDataSetChanged();
                    if (mlist.size() == 0)
                        Toast.makeText(getApplicationContext(), "您还没有添加任何课程呢", Toast.LENGTH_SHORT).show();
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

        listView = findViewById(R.id.SeeCourse_ListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick:");
                SubjectEntity entity = mlist.get(position);
                Intent intent = new Intent(SeeCourseActivity.this, AddCourseTimeActivity.class);
                intent.putExtra("Subject", entity.getId());
                startActivity(intent);
            }
        });
        mAdapter = new SeeCourseAdapter(getApplicationContext(),R.layout.list_seecourse_listview,mlist);
        listView.setAdapter(mAdapter);
        getAll();
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
