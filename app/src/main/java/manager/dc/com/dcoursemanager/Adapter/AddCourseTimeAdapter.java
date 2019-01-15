package manager.dc.com.dcoursemanager.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import manager.dc.com.dcoursemanager.OBJ.Course;

public class AddCourseTimeAdapter extends ArrayAdapter<Course> {

    public AddCourseTimeAdapter(@NonNull Context context, int resource, @NonNull List<Course> objects) {
        super(context, resource, objects);
    }
}
