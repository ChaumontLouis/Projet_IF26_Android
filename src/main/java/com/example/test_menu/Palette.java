package com.example.test_menu;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "palette_table")
public class Palette {

    private String user;

    @NonNull
    @ColumnInfo(name="couleur1")
    private String color_value1;

    @NonNull
    @ColumnInfo(name="couleur2")
    private String color_value2;

    @NonNull
    @ColumnInfo(name="couleur3")
    private String color_value3;

    @NonNull
    @ColumnInfo(name="couleur4")
    private String color_value4;

    @NonNull
    @ColumnInfo(name="couleur5")
    private String color_value5;

    @PrimaryKey
    @NonNull
    private String name;

    @ColumnInfo(name="Nombre_de_like")
    private int heartCount;

    @ColumnInfo(name="Date_de_création")
    private String date;

    private String tags;

    @NonNull
    private boolean Isprivate;

    public Palette() {
        this.user = "";
        this.color_value1 = "0";
        this.color_value2 = "0";
        this.color_value3 = "0";
        this.color_value4 = "0";
        this.color_value5 = "0";
        this.name = "0";
        this.tags = "0";
        this.date = "0";
        this.heartCount = 0;
        this.Isprivate = false;
    }

    public Palette(String user,String c1,String c2,String c3,String c4,String c5, String nom, String tags, String date, boolean isprivate) {
        this.user = user;
        this.color_value1 = c1;
        this.color_value2 = c2;
        this.color_value3 = c3;
        this.color_value4 = c4;
        this.color_value5 = c5;
        this.name = nom;
        this.tags = tags;
        this.date = date;
        this.heartCount = 0;
        this.Isprivate = isprivate;
    }

    public Palette(String user,String c1,String c2,String c3,String c4,String c5, String nom, String tags, String date, int heartCount, boolean isprivate) {
        this.user = user;
        this.color_value1 = c1;
        this.color_value2 = c2;
        this.color_value3 = c3;
        this.color_value4 = c4;
        this.color_value5 = c5;
        this.name = nom;
        this.tags = tags;
        this.date = date;
        this.heartCount = heartCount;
        this.Isprivate = isprivate;
    }

    @NonNull
    public String getColor_value1() {
        return color_value1;
    }

    public void setColor_value1(@NonNull String color_value1) {
        this.color_value1 = color_value1;
    }

    @NonNull
    public String getColor_value2() {
        return color_value2;
    }

    public void setColor_value2(@NonNull String color_value2) {
        this.color_value2 = color_value2;
    }

    @NonNull
    public String getColor_value3() {
        return color_value3;
    }

    public void setColor_value3(@NonNull String color_value3) {
        this.color_value3 = color_value3;
    }

    @NonNull
    public String getColor_value4() {
        return color_value4;
    }

    public void setColor_value4(@NonNull String color_value4) {
        this.color_value4 = color_value4;
    }

    @NonNull
    public String getColor_value5() {
        return color_value5;
    }

    public void setColor_value5(@NonNull String color_value5) {
        this.color_value5 = color_value5;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isIsprivate() {
        return Isprivate;
    }

    public void setIsprivate(boolean isprivate) {
        Isprivate = isprivate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Palette{" +
                "user='" + user + '\'' +
                ", color_value1='" + color_value1 + '\'' +
                ", color_value2='" + color_value2 + '\'' +
                ", color_value3='" + color_value3 + '\'' +
                ", color_value4='" + color_value4 + '\'' +
                ", color_value5='" + color_value5 + '\'' +
                ", name='" + name + '\'' +
                ", heartCount=" + heartCount +
                ", date='" + date + '\'' +
                ", tags='" + tags + '\'' +
                ", Isprivate=" + Isprivate +
                '}';
    }
}
