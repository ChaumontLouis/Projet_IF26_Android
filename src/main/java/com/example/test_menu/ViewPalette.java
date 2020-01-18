package com.example.test_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ViewPalette extends AppCompatActivity {

    EditText name;
    EditText tags;
    TextView date;
    TextView heart;
    Switch switch_private;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_palette);

        name = (EditText) findViewById(R.id.ViewPalette_name);
        tags = (EditText) findViewById(R.id.ViewPalette_tags);
        date = (TextView) findViewById(R.id.ViewPalette_date);
        heart = (TextView) findViewById(R.id.ViewPalette_heart);
        switch_private = (Switch) findViewById(R.id.ViewPalette_isPrivate);
        mListView = (ListView) findViewById(R.id.ViewPalette_listView);

        Bundle bundle = getIntent().getExtras();
        name.setText(bundle.getString("paletteName"));
        tags.setText(bundle.getString("tags"));
        date.setText(bundle.getString("date"));
        heart.setText(bundle.getString("heart_count"));
        switch_private.setChecked(bundle.getBoolean("isPrivate"));

        String s1 = bundle.getString("color1");
        String s2 = bundle.getString("color2");
        String s3 = bundle.getString("color3");
        String s4 = bundle.getString("color4");
        String s5 = bundle.getString("color5");

        final List<couleur> couleurs = setAllPalette(s1,s2,s3,s4,s5);
        final couleurAdapterView adapter = new couleurAdapterView(ViewPalette.this,couleurs);
        mListView.setAdapter(adapter);


    }

    public List<couleur> setAllPalette(String s1,String s2,String s3,String s4,String s5) {
        List<couleur> list_couleurs = new ArrayList<couleur>();

        list_couleurs.add(new couleur(s1.substring(1)));
        list_couleurs.add(new couleur(s2.substring(1)));
        list_couleurs.add(new couleur(s3.substring(1)));
        list_couleurs.add(new couleur(s4.substring(1)));
        list_couleurs.add(new couleur(s5.substring(1)));

        return list_couleurs;
    }

}
