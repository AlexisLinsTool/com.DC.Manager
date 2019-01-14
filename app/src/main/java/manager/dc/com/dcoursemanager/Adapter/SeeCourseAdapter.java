package manager.dc.com.dcoursemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;

public class SeeCourseAdapter extends BaseAdapter {
    List<SubjectEntity> mList;
    Context mContext;
    View view;
    Holder mHolder;

    public SeeCourseAdapter(Context context, List<SubjectEntity> list){
        mList = list;
        mContext = context;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mHolder = new Holder();
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.list_maintale_listview,parent,false);
            mHolder.termNum = convertView.findViewById(R.id.seeCourse_termNum);
            mHolder.courseID = convertView.findViewById(R.id.seeCourse_courseID);
            mHolder.courseName = convertView.findViewById(R.id.seeCourse_courseName);
            mHolder.courseT = convertView.findViewById(R.id.seeCourse_courseT);
            view.setTag(mHolder);
        }else{
            mHolder = (Holder)view.getTag();
        }
        initDate(position);
        return view;
    }

    private void initDate(int position){
        mHolder.termNum.setText(mList.get(position).getTermNum());
        mHolder.courseID.setText(mList.get(position).getCourseId());
        mHolder.courseName.setText(mList.get(position).getCourseName());
        mHolder.courseT.setText(mList.get(position).getCourseTeacher());
    }

    class Holder{
        TextView termNum;
        TextView courseID;
        TextView courseName;
        TextView courseT;
    }
}
