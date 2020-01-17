package com.example.test_menu;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Users.class}, version = 1, exportSchema = false)
public abstract class UserRoomDataBase extends RoomDatabase{

    public abstract UserDAO UserDAO();

    private static volatile UserRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static UserRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserRoomDataBase.class, "Users")
                            .allowMainThreadQueries()
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {

                UserDAO dao = INSTANCE.UserDAO();
                dao.deleteAll();
                Users users = new Users("oui","non");
                dao.insert(users);
            });
        }
    };
}
