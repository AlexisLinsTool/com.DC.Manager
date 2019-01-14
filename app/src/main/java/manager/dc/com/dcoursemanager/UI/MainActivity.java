package manager.dc.com.dcoursemanager.UI;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import manager.dc.com.dcoursemanager.R;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        FragmentManager fram = getSupportFragmentManager();
        FragmentTransaction trans = fram.beginTransaction();
        trans.replace(R.id.Main_frag,new MainTable_fragment());
        trans.commit();
    }

}
