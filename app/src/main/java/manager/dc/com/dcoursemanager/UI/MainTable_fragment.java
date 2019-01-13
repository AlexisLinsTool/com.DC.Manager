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
import java.util.List;

import manager.dc.com.dcoursemanager.Adapter.BaseFragment;
import manager.dc.com.dcoursemanager.Adapter.MainTableAdapter;
import manager.dc.com.dcoursemanager.OBJ.Courses;
import manager.dc.com.dcoursemanager.R;

public class MainTable_fragment extends BaseFragment implements MainTableAdapter.MyClickListener {
    List<Courses> li = new ArrayList<>();
    View contentView;
    ListView mListView;
    MainTableAdapter mAdapter;
    List<Courses> mList;

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
        contentView = inflater.inflate(R.layout.fragment_maintable,container,false);
        mListView = contentView.findViewById(R.id.MainTable_List);
        initData();
        mAdapter = new MainTableAdapter(getContext(),mList,this);
        mListView.setAdapter(mAdapter);
        return contentView;
    }

    public void initData(){
        for(int i = 0;i<8;i++){
            Courses c = new Courses();
            mList.add(c);
        }
    }


    @Override
    public void clickListener(View v) {

        int postion = (Integer) v.getTag();
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
