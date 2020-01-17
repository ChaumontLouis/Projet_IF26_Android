package com.example.test_menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Ajout_palette extends AppCompatActivity {

    EditText name;
    EditText tags;
    Switch switch_private;
    ListView mListView;
    FloatingActionButton bouton_generate;
    FloatingActionButton validate_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_palette);

        name = (EditText) findViewById(R.id.ajout_palete_name);
        tags = (EditText) findViewById(R.id.ajout_palette_tags);
        switch_private = (Switch) findViewById(R.id.ajout_palette_switch);
        mListView = (ListView) findViewById(R.id.mListView_palette);
        bouton_generate = (FloatingActionButton) findViewById(R.id.bouton_generate_ajout_palette);
        validate_button = (FloatingActionButton) findViewById(R.id.validate_button);

        final List<couleur> couleurs = genererSettings();
        final couleurAdapter adapter = new couleurAdapter(Ajout_palette.this,couleurs);
        mListView.setAdapter(adapter);

        bouton_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<5;i++) {
                    couleurs.get(i).setHex_value(genererCouleur());
                    adapter.notifyDataSetChanged();
                }

            }
        });


        validate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title",name.getText().toString());
                bundle.putString("tags",tags.getText().toString());
                bundle.putBoolean("private",switch_private.isChecked());
                for (int i=0;i<5;i++) {
                    bundle.putString("color"+(i+1),couleurs.get(i).getHex_value());
                    System.out.println("//////////////"+couleurs.get(i).getHex_value()+"///////////////////////");
                }
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                bundle.putString("date",date);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }

    public List<couleur> genererSettings() {
        List<couleur> list_couleurs = new ArrayList<couleur>();
        list_couleurs.add(new couleur("14BA52"));
        list_couleurs.add(new couleur("FC9999"));
        list_couleurs.add(new couleur("8800F0"));
        list_couleurs.add(new couleur("46FA8B"));
        list_couleurs.add(new couleur("D412AB"));

        return list_couleurs;
    }

    public String genererCouleur() {
        Random rng = new Random();
        int rand_num = rng.nextInt(0xffffff + 1);
        String colorCode = String.format("%06x", rand_num);
        System.out.println("///////////////////////////////////////////////////////////////"+colorCode);
        return colorCode;
    }
}
