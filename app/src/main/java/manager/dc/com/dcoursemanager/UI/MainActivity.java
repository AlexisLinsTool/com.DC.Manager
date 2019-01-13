package manager.dc.com.dcoursemanager.UI;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.UI.AddAndSee.AddCourseActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button addCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        addCourse = findViewById(R.id.Main_B_AddCourse);
        addCourse.setOnClickListener(this);
        FragmentManager fram = getSupportFragmentManager();
        FragmentTransaction trans = fram.beginTransaction();
        trans.replace(R.id.Main_frag,new MainTable_fragment());
        trans.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Main_B_AddCourse:
                Intent intent = new Intent(MainActivity.this,AddCourseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
