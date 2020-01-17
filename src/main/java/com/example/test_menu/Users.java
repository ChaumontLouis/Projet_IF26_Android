package com.example.test_menu;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Users")
public class Users {

    @PrimaryKey
    @NonNull
    private String name;

    @NonNull
    private String mdp;

    public Users(@NonNull String name, @NonNull String mdp) {
        this.name = name;
        this.mdp = mdp;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getMdp() {
        return mdp;
    }

    public void setMdp(@NonNull String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}
