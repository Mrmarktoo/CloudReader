package com.example.jingbin.cloudreader.data.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import com.example.jingbin.cloudreader.app.CloudReaderApplication;


/**
 * @author jingbin
 * @date 2018/4/20
 * @description you've changed schema but forgot to update the version number
 * 改变数据库结构要改变版本号,不然会抛异常！
 */

@Database(entities = {Wait.class}, version = 4, exportSchema = false)
public abstract class WaitDataBase extends RoomDatabase {

    private static WaitDataBase sInstance;

    public abstract WaitDao waitDao();

    /**
     * 版本号迁移：
     * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0728/8278.html
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    public static WaitDataBase getDatabase() {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(CloudReaderApplication.getInstance(),
                    WaitDataBase.class, "User.db")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return sInstance;
    }

    public static void onDestroy() {
        sInstance = null;
    }


}
