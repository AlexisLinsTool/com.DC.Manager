package manager.dc.com.dcoursemanager.UI;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import manager.dc.com.dcoursemanager.OBJ.Course;
import manager.dc.com.dcoursemanager.R;

public class MainTable_fragment extends Fragment {
    List<Course> li = new ArrayList<>();
    View contentView;

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
        return contentView;
    }
}
