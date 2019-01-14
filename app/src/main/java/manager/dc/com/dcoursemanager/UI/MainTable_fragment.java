package manager.dc.com.dcoursemanager.UI;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import manager.dc.com.dcoursemanager.Adapter.MainTableAdapter;
import manager.dc.com.dcoursemanager.OBJ.Courses;
import manager.dc.com.dcoursemanager.R;

public class MainTable_fragment extends Fragment implements MainTableAdapter.MyClickListener {
    Calendar calendar;
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
        calendar = Calendar.getInstance();
        calendar.set(2018,9,1);
        mListView = view.findViewById(R.id.MainTable_List);
        initData();
        mAdapter = new MainTableAdapter(getActivity().getApplicationContext(),mList,this);
        mListView.setAdapter(mAdapter);
    }

    public void initData(){
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
                break;
            case R.id.MainTable_Tuesday:
                break;
            case R.id.MainTable_Wednesday:
                break;
            case R.id.MainTable_Thursday:
                break;
            case R.id.MainTable_Friday:
                break;
            case R.id.MainTable_Saturday:
                break;
            case R.id.MainTable_Sunday:
                break;
        }
    }
}
