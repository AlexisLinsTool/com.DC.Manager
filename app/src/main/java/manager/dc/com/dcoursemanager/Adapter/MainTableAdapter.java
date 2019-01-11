package manager.dc.com.dcoursemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import manager.dc.com.dcoursemanager.OBJ.Course;

public class MainTableAdapter extends ArrayAdapter<Course> {
    int resourceID;
    View contentView;
    public MainTableAdapter() {
        super(context, resource, textViewResourceId);
        resourceID = resource;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        Course course = getItem(position);
        View view = null;
        if (contentView == null){
            contentView = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        }else{
            view = contentView;
        }
        return view;
    }
}
