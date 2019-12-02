package com.example.test_menu;

import android.widget.TextView;

import org.w3c.dom.Text;

public class settings {

    private String name;
    private String description;
    private String value;
    public settings(String name,String description,String value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getValue() {
        return this.value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
