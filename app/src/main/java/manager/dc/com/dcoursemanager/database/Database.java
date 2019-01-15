package manager.dc.com.dcoursemanager.database;


import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import manager.dc.com.dcoursemanager.database.Dao.SubjectDao;
import manager.dc.com.dcoursemanager.database.Dao.TimeDao;
import manager.dc.com.dcoursemanager.database.Table.SubjectEntity;
import manager.dc.com.dcoursemanager.database.Table.TimeEntity;

@androidx.room.Database(entities = {SubjectEntity.class,TimeEntity.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {
    /**
     * DatebaseName
     */
    private static final String DB_NAME = "DCManager";

    /**
     * 单例
     */
    private static Database sInstance;

    /**
     * 内部进行初始化
     * @param application a
     */
    public static Database init(Application application){
        if(sInstance == null){
            synchronized (Application.class){
                if(sInstance == null){
                    sInstance = Room.databaseBuilder(application,Database.class,DB_NAME).build();
                }
            }
        }
        return null;
    }

    /**
     * 获取实例
     *
     * @return 没有初始化则抛出异常
     */
    public static Database getsInstance(){
        synchronized (Application.class){
            if(sInstance == null){
                throw  new NullPointerException("database == null");
            }
        }
        return sInstance;
    }

    /**
     * 获取subjectDao的实例
     * @return SubjectDao实例
     */
    public abstract SubjectDao getSubjectDao();

    /**
     * 获取TimeDao的实例
     * @return TimeDao实例
     */
    public abstract TimeDao getTimeDao();
}
