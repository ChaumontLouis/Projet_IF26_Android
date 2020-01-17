package com.example.test_menu;
import androidx.core.graphics.ColorUtils;

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

    public String getRgbString() {
        int r = Integer.valueOf(this.hex_value.substring(0,2),16);
        int g = Integer.valueOf(this.hex_value.substring(2,4),16);
        int b = Integer.valueOf(this.hex_value.substring(4,6),16);
        return "R : "+r+", G : "+g+", B : "+b;
    }

    public int[] getRgbStringValues() {
        int[] returnValues = new int[3];
        int r = Integer.valueOf(this.hex_value.substring(0,2),16);
        returnValues[0] = r;
        int g = Integer.valueOf(this.hex_value.substring(2,4),16);
        returnValues[1] = g;
        int b = Integer.valueOf(this.hex_value.substring(4,6),16);
        returnValues[2] = b;
        return returnValues;
    }

    public String getHSV() {
        float[] resultat = new float[3];
        String[] retour = new String[3];
        int[] rgb = this.getRgbStringValues();
        ColorUtils.RGBToHSL(rgb[0],rgb[1],rgb[2],resultat);
        retour[0] = String.format("%.2f",resultat[0]);
        retour[1] = String.format("%.2f",resultat[1]);
        retour[2] = String.format("%.2f",resultat[2]);
        return "H : "+retour[0]+", S : "+retour[1]+", L : "+retour[2];
    }
}
