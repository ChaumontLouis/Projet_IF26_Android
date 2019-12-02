package com.example.test_menu;

public class couleur {

    private String hex_value;

    public couleur(String hex_value) {
        this.hex_value = hex_value;
    }

    public couleur() {
        this.hex_value = "000000";
    }


    public String getHex_value() {
        return hex_value;
    }
    public void setHex_value(String hex_value) {
        this.hex_value = hex_value;
    }
}
