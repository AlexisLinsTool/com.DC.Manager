package manager.dc.com.dcoursemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.logging.Handler;

import androidx.constraintlayout.solver.LinearSystem;
import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;

public class SeeCourseAdapter extends ArrayAdapter<SubjectEntity> {
    List<SubjectEntity> mList;
    Context mContext;
    View view;
    SubjectEntity entity;
    Holder mHolder;
    int resourceId;

    public SeeCourseAdapter(Context context,int ResourceId,List<SubjectEntity> list){
        super(context,ResourceId,list);
        mContext = context;
        resourceId = ResourceId;
        mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mHolder = new Holder();
        entity = getItem(position);
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            mHolder.termNum = view.findViewById(R.id.seeCourse_termNum);
            mHolder.courseId = view.findViewById(R.id.seeCourse_courseID);
            mHolder.courseName = view.findViewById(R.id.seeCourse_courseName);
            mHolder.courseT = view.findViewById(R.id.seeCourse_courseT);
            view.setTag(mHolder);
        }else{
            view = convertView;
            mHolder = (Holder) view.getTag();
        }
        initDate(position);
        return view;
    }

    private void initDate(int postion){
        mHolder.termNum.setText(String.valueOf(mList.get(postion).getTermNum()));
        mHolder.courseId.setText(mList.get(postion).getCourseId());
        mHolder.courseName.setText(mList.get(postion).getCourseName());
        mHolder.courseT.setText(mList.get(postion).getCourseTeacher());
    }

    class Holder{
        TextView termNum;
        TextView courseId;
        TextView courseName;
        TextView courseT;
    }
}
