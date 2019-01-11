package manager.dc.com.dcoursemanager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import manager.dc.com.dcoursemanager.UI.MainTable_fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fram = getSupportFragmentManager();
        FragmentTransaction trans = fram.beginTransaction();
        trans.replace(R.id.Main_frag,new MainTable_fragment());
        trans.commit();
    }
}
