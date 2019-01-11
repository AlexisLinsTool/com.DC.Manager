package manager.dc.com.dcoursemanager.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import manager.dc.com.dcoursemanager.R;

public class MainTableFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maintable,container,false);

        return v;
    }
}
