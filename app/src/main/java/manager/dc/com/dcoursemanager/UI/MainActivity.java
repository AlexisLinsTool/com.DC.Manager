package manager.dc.com.dcoursemanager.UI;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import manager.dc.com.dcoursemanager.R;
import manager.dc.com.dcoursemanager.UI.AddAndSee.AddCourseActivity;
import manager.dc.com.dcoursemanager.UI.AddAndSee.SeeCourseActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button addCourse;
    Button seeConrse;
    Button alarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        addCourse = findViewById(R.id.Main_B_AddCourse);
        addCourse.setOnClickListener(this);
        seeConrse = findViewById(R.id.Main_B_SeeCourse);
        seeConrse.setOnClickListener(this);
        alarm = findViewById(R.id.Main_B_Alarm);
        ((EditText)findViewById(R.id.Main_WeekNow)).setText("1");
        FragmentManager fram = getSupportFragmentManager();
        FragmentTransaction trans = fram.beginTransaction();
        trans.replace(R.id.Main_frag,new MainTable_fragment());
        trans.commit();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.Main_B_AddCourse:
                intent = new Intent(MainActivity.this,AddCourseActivity.class);
                startActivity(intent);
                break;
            case R.id.Main_B_SeeCourse:
                intent = new Intent(MainActivity.this,SeeCourseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
