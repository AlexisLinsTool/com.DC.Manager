package manager.dc.com.dcoursemanager.APP;

import android.app.Application;

import manager.dc.com.dcoursemanager.database.Database;


public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Database.init(this);
    }
}
