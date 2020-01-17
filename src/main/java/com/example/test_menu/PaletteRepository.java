package com.example.test_menu;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PaletteRepository {

    private PaletteDAO mPaletteDao;
    private LiveData<List<Palette>> mAllPalettes;

    PaletteRepository(Application application) {
        PaletteRoomDataBase db = PaletteRoomDataBase.getDatabase(application);
        mPaletteDao = db.paletteDAO();
        mAllPalettes = mPaletteDao.getAlphabetizedPalette(UserLogged.UserLooged);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Palette>> getUserPalettes() {
        return mAllPalettes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Palette palette) {
        PaletteRoomDataBase.databaseWriteExecutor.execute(() -> {
            mPaletteDao.insert(palette);
        });
    }

    void deleteItem(String palette_name) {
        PaletteRoomDataBase.databaseWriteExecutor.execute(() -> {
            mPaletteDao.deleteByName(palette_name);
        });
    }

    LiveData<List<Palette>> sortByName() {
        mAllPalettes = mPaletteDao.getAlphabetizedPalette(UserLogged.UserLooged);
        return mAllPalettes;
    }

    LiveData<List<Palette>> sortByDate() {
        mAllPalettes = mPaletteDao.getByDatePalette();
        return mAllPalettes;
    }

    LiveData<List<Palette>> sortByHeart() {
        mAllPalettes = mPaletteDao.getByHeartCountPalette();
        return mAllPalettes;
    }
}
