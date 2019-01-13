package manager.dc.com.dcoursemanager.Adapter;

import androidx.appcompat.app.AppCompatActivity;

public class MyApplication extends AppCompatActivity {
    private static MyApplication mInstance;

    public static MyApplication getInstance(){
        if(mInstance == null){
            mInstance = new MyApplication();
        }
        return mInstance;
    }
}
