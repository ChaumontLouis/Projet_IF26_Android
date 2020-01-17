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

@Database(entities = {Palette.class, Users.class}, version = 4, exportSchema = false)
public abstract class PaletteRoomDataBase extends RoomDatabase {

    public abstract PaletteDAO paletteDAO();

    private static volatile PaletteRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PaletteRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PaletteRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PaletteRoomDataBase.class, "palette_database")
                            //.addCallback(sRoomDatabaseCallback)
                            .addMigrations(MIGRATION_2_3)
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

                PaletteDAO dao = INSTANCE.paletteDAO();
                dao.deleteAll();

            });
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS 'palette_database' ('user' TEXT,'couleur1' TEXT, 'couleur2' TEXT,'couleur3' TEXT,'couleur4' TEXT,'couleur5' TEXT, 'name' TEXT, 'heartCount' INTEGER, 'data' TEXT, 'tags', TEXT, PRIMARY KEY('name'))");
        }
    };
}
