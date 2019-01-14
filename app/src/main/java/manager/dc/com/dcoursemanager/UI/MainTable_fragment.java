package manager.dc.com.dcoursemanager.UI;

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
import manager.dc.com.dcoursemanager.UI.AddAndSee.SeeCourseActivity;

public class MainTable_fragment extends Fragment implements MainTableAdapter.MyClickListener,View.OnClickListener {
    int NowWeekNum;
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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maintable,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NowWeekNum = 1;
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
        int postion = ((Integer) v.getTag()).intValue();
        int termNum = ((Integer) v.getTag(R.id.tag_term_num));
        int weekNum = ((Integer) v.getTag(R.id.tag_week_num));
        int courseNum = ((Integer) v.getTag(R.id.tag_course_num));
        switch (v.getId()) {
            case R.id.MainTable_Monday:
                Jump2NotePage(v,1);
                break;
            case R.id.MainTable_Tuesday:
                Jump2NotePage(v,2);
                break;
            case R.id.MainTable_Wednesday:
                Jump2NotePage(v,3);
                break;
            case R.id.MainTable_Thursday:
                Jump2NotePage(v,4);
                break;
            case R.id.MainTable_Friday:
                Jump2NotePage(v,5);
                break;
            case R.id.MainTable_Saturday:
                Jump2NotePage(v,6);
                break;
            case R.id.MainTable_Sunday:
                Jump2NotePage(v,7);
                break;
        }
    }

    /**
     * 跳转到添加笔记的页面
     * @param v 课程表中课程（可空）的点击
     * @param weeknum 星期数
     */
    private void Jump2NotePage(View v,int weeknum){
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
