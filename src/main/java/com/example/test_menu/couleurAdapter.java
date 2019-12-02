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
import android.widget.TextView;

import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class couleurAdapter extends ArrayAdapter<couleur> {

    public couleurAdapter(Context context, List<couleur> couleurs) {
        super(context,0,couleurs);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_couleur,parent, false);
        }

        couleurViewHolder viewHolder = (couleurViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new couleurViewHolder();
            viewHolder.affichage_couleur = (View) convertView.findViewById(R.id.affichage_couleur);
           // viewHolder.choix_color_mode = (Spinner) convertView.findViewById(R.id.spinner_color_mode);
            viewHolder.color_value = (EditText) convertView.findViewById(R.id.editText_color_value);
            convertView.setTag(viewHolder);
        }

        final couleur color = getItem(position);
        viewHolder.affichage_couleur.setBackgroundColor(Color.parseColor("#"+color.getHex_value()));
        viewHolder.color_value.setText(color.getHex_value());
       // viewHolder.choix_color_mode.setAdapter();


        final couleurViewHolder finalViewHolder = viewHolder;
        final TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    color.setHex_value("000000");
                } else if (s.length() == 6){
                    color.setHex_value(s.toString());
                    finalViewHolder.affichage_couleur.setBackgroundColor(Color.parseColor("#"+color.getHex_value()));
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        };


        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker(v,finalViewHolder);
            }
        };

        viewHolder.affichage_couleur.setOnClickListener(clickListener);
        viewHolder.affichage_couleur.setTag(clickListener);
        viewHolder.color_value.addTextChangedListener(watcher);
        viewHolder.color_value.setTag(watcher);

        return convertView;
    }

    private class couleurViewHolder{
        private View affichage_couleur;
        //private Spinner choix_color_mode;
        private EditText color_value;
    }

    public void openColorPicker(View v, final couleurViewHolder viewHolder) {
        ColorDrawable mColorDrawable = (ColorDrawable) v.getBackground();
        int mDefaultColor = mColorDrawable.getColor();
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getContext(), mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                String fullColorCode = String.format("0x%08x",color);
                viewHolder.color_value.setText(fullColorCode.substring(4));
            }
        });
        colorPicker.show();
    }
}
