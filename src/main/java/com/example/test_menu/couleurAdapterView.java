package com.example.test_menu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class couleurAdapterView extends ArrayAdapter<couleur> {

    public couleurAdapterView(Context context, List<couleur> couleurs) {
        super(context,0,couleurs);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_couleur_view,parent, false);
        }

        couleurAdapterView.couleurViewHolder viewHolder = (couleurAdapterView.couleurViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new couleurAdapterView.couleurViewHolder();
            viewHolder.affichage_couleur = (View) convertView.findViewById(R.id.viewPalette_colorview);
            viewHolder.color_value_hex = (EditText) convertView.findViewById(R.id.colorValue_HEX);
            viewHolder.color_value_rgb = (EditText) convertView.findViewById(R.id.colorValue_RGB);
            viewHolder.color_value_hsv = (EditText) convertView.findViewById(R.id.colorValue_HSV);
            convertView.setTag(viewHolder);
        }

        final couleur color = getItem(position);
        viewHolder.affichage_couleur.setBackgroundColor(Color.parseColor("#"+color.getHex_value()));
        viewHolder.color_value_hex.setText(color.getHex_value());
        viewHolder.color_value_rgb.setText(color.getRgbString());
        viewHolder.color_value_hsv.setText(color.getHSV());

        return convertView;
    }

    private class couleurViewHolder{
        private View affichage_couleur;
        private EditText color_value_hex;
        private EditText color_value_rgb;
        private EditText color_value_hsv;
    }
}
