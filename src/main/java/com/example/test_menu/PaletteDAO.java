package com.example.test_menu;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * from palette_table WHERE Isprivate= :bool ORDER BY name ASC")
    LiveData<List<Palette>> getAllPublic(Boolean bool);

    @Query("SELECT * from palette_table WHERE user = :user ORDER BY Nombre_de_like ASC")
    LiveData<List<Palette>> getByHeartCountPalette(String user);

    @Query("UPDATE palette_table SET name= :newName, couleur1 =:color1, couleur2 =:color2, couleur3 =:color3, couleur4 =:color4, couleur5 =:color5, tags =:tags, Date_de_création =:date, Nombre_de_like =0, Isprivate=:isPrivate WHERE name = :oldName")
    void updatePalette(String oldName, String newName,String color1,String color2,String color3,String color4,String color5,String tags,String date,Boolean isPrivate);
}
