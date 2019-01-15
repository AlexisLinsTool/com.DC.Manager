package manager.dc.com.dcoursemanager.UI.home;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import manager.dc.com.dcoursemanager.Adapter.MainTableAdapter;
import manager.dc.com.dcoursemanager.OBJ.Courses;
import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.UI.AddAndSee.AddCourseActivity;
import manager.dc.com.dcoursemanager.UI.AddAndSee.AddCourseNoteActivity;
import manager.dc.com.dcoursemanager.UI.AddAndSee.SeeCourseActivity;

public class MainTable_fragment extends Fragment implements MainTableAdapter.MyClickListener,View.OnClickListener {
    int NowWeekNum;
    int NowTermNum;
    EditText ENowWeekNum;
    Button Jump2Week;
    Button addCourse;
    Button seeConrse;
    Button alarm;
    List<Courses> li = new ArrayList<>();
    ListView mListView;
    MainTableAdapter mAdapter;
    List<Courses> mList = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NowWeekNum = 1;
        NowTermNum = 1;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maintable,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = view.findViewById(R.id.MainTable_List);
        addCourse = view.findViewById(R.id.MainTable_B_AddCourse);
        addCourse.setOnClickListener(this);
        seeConrse = view.findViewById(R.id.MainTable_B_SeeCourse);
        seeConrse.setOnClickListener(this);
        alarm = view.findViewById(R.id.MainTable_B_Alarm);
        ENowWeekNum = view.findViewById(R.id.MainTable_WeekNow);
        ENowWeekNum.setText(String.valueOf(NowWeekNum));
        Jump2Week = view.findViewById(R.id.MainTable_B_Jump2Week);
        Jump2Week.setOnClickListener(this);
        initData(NowWeekNum);
        mAdapter = new MainTableAdapter(getActivity().getApplicationContext(),mList,this);
        mListView.setAdapter(mAdapter);
    }

    public void initData(int weekNum){
        for(int i = 0;i<12;i++){
            Courses c = new Courses();
            mList.add(c);
        }
    }

    @Override
    public void clickListener(View v) {
        int postion = (int) v.getTag(R.id.tag_postion);
        switch (v.getId()) {
            case R.id.MainTable_Monday:
                Jump2NotePage(v,1,postion);
                break;
            case R.id.MainTable_Tuesday:
                Jump2NotePage(v,2,postion);
                break;
            case R.id.MainTable_Wednesday:
                Jump2NotePage(v,3,postion);
                break;
            case R.id.MainTable_Thursday:
                Jump2NotePage(v,4,postion);
                break;
            case R.id.MainTable_Friday:
                Jump2NotePage(v,5,postion);
                break;
            case R.id.MainTable_Saturday:
                Jump2NotePage(v,6,postion);
                break;
            case R.id.MainTable_Sunday:
                Jump2NotePage(v,7,postion);
                break;
        }
    }

    /**
     * 跳转到添加笔记的页面
     * @param v 课程表中课程（可空）的点击
     * @param weeknum 星期数
     */
    private void Jump2NotePage(View v,int weeknum,int postion){
        Intent intent = new Intent(getActivity(),AddCourseNoteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("week",NowWeekNum);
        bundle.putInt("term",NowTermNum);
        bundle.putInt("weekNum",weeknum);
        bundle.putInt("courseNum",postion+1);
        bundle.putString("name",((TextView)v).getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 对本身的监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.MainTable_B_AddCourse:
                intent = new Intent(this.getActivity(),AddCourseActivity.class);
                startActivity(intent);
                break;
            case R.id.MainTable_B_SeeCourse:
                intent = new Intent(this.getActivity(),SeeCourseActivity.class);
                startActivity(intent);
                break;
            case R.id.MainTable_B_Jump2Week:
                NowWeekNum = Integer.valueOf(ENowWeekNum.getText().toString());
                mList = null;
                initData(NowWeekNum);
                mAdapter.notifyDataSetChanged();
                break;


        }
    }
}
