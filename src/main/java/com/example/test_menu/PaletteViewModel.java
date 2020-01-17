package com.example.test_menu;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PaletteViewModel extends AndroidViewModel {

    private PaletteRepository mRepository;

    private LiveData<List<Palette>> mAllPalette;

    public PaletteViewModel (Application application) {
        super(application);
        mRepository = new PaletteRepository(application);
        mAllPalette = mRepository.getUserPalettes();
    }

    LiveData<List<Palette>> getUserPalette() { return mAllPalette; }
    void getByName() { mAllPalette = mRepository.sortByName(); }
    void getByDate() { mAllPalette = mRepository.sortByDate(); }
    void getByHeart() {
        mAllPalette = mRepository.sortByHeart();

    }

    public void insert(Palette palette) { mRepository.insert(palette); }
    public void deleteItem(String palette_name) { mRepository.deleteItem(palette_name); }
}
