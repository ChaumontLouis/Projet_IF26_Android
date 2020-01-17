package com.example.test_menu;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Users users);

    @Query("DELETE FROM Users")
    void deleteAll();

    @Query("SELECT * from Users")
    public Users[] getAlphabetizedUsers();
}
