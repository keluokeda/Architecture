package com.keluokeda.architecture;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userModel();


    private static AppDatabase sAppDatabase;

    public static AppDatabase getAppDatabase(Context context) {
        if (sAppDatabase == null) {
            sAppDatabase = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).allowMainThreadQueries()
                    .build();
        }

        return sAppDatabase;
    }
}
