package manager.dc.com.dcoursemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;

public class SeeCourseAdapter extends ArrayAdapter<SubjectEntity> {
    private List<SubjectEntity> mList = null;
    int ResourceId;

    public SeeCourseAdapter(@NonNull Context context, int resource, @NonNull List<SubjectEntity> objects) {
        super(context, resource, objects);
        mList = objects;
        ResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(ResourceId, parent, false);
            holder = new ViewHolder();
            holder.termNum = convertView.findViewById(R.id.seeCourse_termNum);
            holder.courseId = convertView.findViewById(R.id.seeCourse_courseID);
            holder.courseName = convertView.findViewById(R.id.seeCourse_courseName);
            holder.courseT = convertView.findViewById(R.id.seeCourse_courseT);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        initDate(holder, position);
        return convertView;
    }

    private class ViewHolder {
        TextView termNum;
        TextView courseId;
        TextView courseName;
        TextView courseT;
    }

    private void initDate(ViewHolder holder, int postion) {
        holder.termNum.setText(String.valueOf(mList.get(postion).getTermNum()));
        holder.courseId.setText(mList.get(postion).getCourseId());
        holder.courseName.setText(mList.get(postion).getCourseName());
        holder.courseT.setText(mList.get(postion).getCourseTeacher());
    }
}
