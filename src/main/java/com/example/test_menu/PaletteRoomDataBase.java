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

@Database(entities = {Palette.class, Users.class}, version = 1, exportSchema = false)
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
                            .allowMainThreadQueries()
                            //.addCallback(resetDataBase)
                            .addCallback(populateWithSamplePalette)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback resetDataBase = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                PaletteDAO dao = INSTANCE.paletteDAO();
                dao.deleteAll();
            });
        }
    };

    private static RoomDatabase.Callback populateWithSamplePalette = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                INSTANCE.paletteDAO().deleteAll();
                Palette p1 = new Palette("oui","FF00FF","FF0000","00FF00","0000FF","FFFF00","AA","J'aimelesTags1","DD",8,false);
                Palette p2 = new Palette("oui","FFFFFF","0000FF","00FF00","0000FF","00FF00","CC","J'aimelesTags2","BB",1,true);
                Palette p3 = new Palette("oui","FF00FF","FF0000","0FB500","0000FF","FA2F05","BB","J'aimelesTags3","AA",4,true);
                Palette p4 = new Palette("oui","FF00FF","FAB954","FFF450","7860BF","AFBF2F","DD","J'aimelesTags4","CC",10,false);
                INSTANCE.paletteDAO().insert(p1);
                INSTANCE.paletteDAO().insert(p2);
                INSTANCE.paletteDAO().insert(p3);
                INSTANCE.paletteDAO().insert(p4);
            });
        }
    };
}
