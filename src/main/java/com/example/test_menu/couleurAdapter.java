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
import android.widget.Toast;

import androidx.core.graphics.ColorUtils;

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
            viewHolder.color_value_hex = (EditText) convertView.findViewById(R.id.editText_color_value);
            viewHolder.color_value_rgb = (EditText) convertView.findViewById(R.id.editText_color_value_rgb);
            viewHolder.color_value_hsv = (EditText) convertView.findViewById(R.id.editText_color_value_hsv);
            convertView.setTag(viewHolder);
        }

        final couleur color = getItem(position);
        viewHolder.affichage_couleur.setBackgroundColor(Color.parseColor("#"+color.getHex_value()));
        viewHolder.color_value_hex.setText(color.getHex_value());
        viewHolder.color_value_rgb.setText(color.getRgbString());
        viewHolder.color_value_hsv.setText(color.getHSV());


        final couleurViewHolder finalViewHolder = viewHolder;
        final TextWatcher HexWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    color.setHex_value("000000");
                } else if (s.length() == 6){
                    color.setHex_value(s.toString());
                    finalViewHolder.affichage_couleur.setBackgroundColor(Color.parseColor("#"+color.getHex_value()));
                    finalViewHolder.color_value_rgb.setText(color.getRgbString());
                    finalViewHolder.color_value_hsv.setText(color.getHSV());
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        };

       /* final TextWatcher RGGBWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String regExp = "R:(\\d+),G:(\\d+),B:(\\d+)";
                String sansEspace = s.toString().replaceAll("\\s","");
                if (sansEspace.matches(regExp)) {
                    sansEspace = sansEspace.replaceAll(regExp,"$1,$2,$3");
                    String[] str = sansEspace.split(",");
                    int[] rgb = new int[3];
                    rgb[0] = Integer.parseInt(str[0]);
                    rgb[1] = Integer.parseInt(str[1]);
                    rgb[2] = Integer.parseInt(str[2]);
                    if (rgb[0]>=0 && rgb[0]<=255 && rgb[1]>=0 && rgb[1]<=255 && rgb[2]>=0 && rgb[2]<=255) {
                        String hex = String.format("%02x%02x%02x", rgb[0], rgb[1], rgb[2]);
                        finalViewHolder.color_value_hex.removeTextChangedListener(HexWatcher);

                        color.setHex_value(hex);
                        finalViewHolder.affichage_couleur.setBackgroundColor(Color.parseColor("#" + color.getHex_value()));
                        finalViewHolder.color_value_hsv.setText(color.getHSV());
                        finalViewHolder.color_value_hex.setText(hex);

                        finalViewHolder.color_value_hex.addTextChangedListener(HexWatcher);
                    } else {
                        Toast.makeText(
                                getContext(),
                                "Saisie de valeur incorrect",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        final TextWatcher HSBWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String regExp = "H:(\\d+\\.?\\d+),S:(\\d+\\.?\\d+),L:(\\d+\\.?\\d+)";
                String sansEspace = s.toString().replaceAll("\\s","");
                if (sansEspace.matches(regExp)) {
                    sansEspace = sansEspace.replaceAll(regExp,"$1,$2,$3");
                    String[] str = sansEspace.split(",");
                    float[] hsv = new float[3];
                    hsv[0] = Float.parseFloat(str[0]);
                    hsv[1] = Float.parseFloat(str[1]);
                    hsv[2] = Float.parseFloat(str[2]);
                    if (hsv[0]>=0 && hsv[0]<=360 && hsv[1]>=0 && hsv[1]<=1 && hsv[2]>=0 && hsv[2]<=1) {
                        finalViewHolder.color_value_hex.removeTextChangedListener(HexWatcher);
                        finalViewHolder.color_value_rgb.removeTextChangedListener(RGGBWatcher);

                        System.out.println("//////////////"+ColorUtils.HSLToColor(hsv)+ "//////////////////////////");
                        int intColor = ColorUtils.HSLToColor(hsv);
                        int red = Color.red(intColor);
                        int green = Color.green(intColor);
                        int blue = Color.blue(intColor);
                        String hex = String.format("%02x%02x%02x", red, green, blue);

                        color.setHex_value(hex);
                        finalViewHolder.affichage_couleur.setBackgroundColor(Color.parseColor("#" + color.getHex_value()));
                        finalViewHolder.color_value_rgb.setText(color.getRgbString());
                        finalViewHolder.color_value_hex.setText(hex);

                        finalViewHolder.color_value_rgb.addTextChangedListener(RGGBWatcher);
                        finalViewHolder.color_value_hex.addTextChangedListener(HexWatcher);
                    } else {
                        Toast.makeText(
                                getContext(),
                                "Saisie de valeur incorrect",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        };*/


        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker(v,finalViewHolder);
            }
        };

        viewHolder.affichage_couleur.setOnClickListener(clickListener);
        viewHolder.color_value_hex.addTextChangedListener(HexWatcher);
       // viewHolder.color_value_rgb.addTextChangedListener(RGGBWatcher);
       // viewHolder.color_value_hsv.addTextChangedListener(HSBWatcher);

        return convertView;
    }

    private class couleurViewHolder{
        private View affichage_couleur;
        private EditText color_value_hex;
        private EditText color_value_rgb;
        private EditText color_value_hsv;
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
                viewHolder.color_value_hex.setText(fullColorCode.substring(4));
            }
        });
        colorPicker.show();
    }
}
