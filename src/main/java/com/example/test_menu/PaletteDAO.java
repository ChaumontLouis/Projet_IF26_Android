package com.example.test_menu;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PaletteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Palette palette);

    @Query("DELETE FROM palette_table")
    void deleteAll();

    @Query("DELETE FROM palette_table WHERE name= :paletteToDeleteName")
    void deleteByName(String paletteToDeleteName);

    @Query("SELECT * from palette_table WHERE user = :user ORDER BY name ASC")
    LiveData<List<Palette>> getAlphabetizedPalette(String user);

    @Query("SELECT * from palette_table WHERE user = :user ORDER BY Date_de_création ASC")
    LiveData<List<Palette>> getByDatePalette(String user);

    @Query("SELECT * from palette_table WHERE user = :user ORDER BY Nombre_de_like ASC")
    LiveData<List<Palette>> getByHeartCountPalette(String user);

}
