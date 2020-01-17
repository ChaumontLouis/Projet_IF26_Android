package com.example.test_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class EditPalette extends AppCompatActivity {

    PaletteRoomDataBase roomDataBase;
    PaletteDAO paletteDAO;
    String originPaletteName;
    EditText name;
    EditText tags;
    Switch switch_private;
    ListView mListView;
    FloatingActionButton bouton_generate;
    FloatingActionButton validate_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_palette);

        roomDataBase = PaletteRoomDataBase.getDatabase(getApplicationContext());
        paletteDAO = roomDataBase.paletteDAO();

        Bundle bundle = getIntent().getExtras();
        originPaletteName = bundle.getString("paletteName");

        name = (EditText) findViewById(R.id.edit_PaletteName);
        tags = (EditText) findViewById(R.id.edit_PaletteTags);
        switch_private = (Switch) findViewById(R.id.edit_paletteSwitch);
        mListView = (ListView) findViewById(R.id.edit_listView);
        bouton_generate = (FloatingActionButton) findViewById(R.id.edit_generateCouleurs);
        validate_button = (FloatingActionButton) findViewById(R.id.editValide);

        name.setText(originPaletteName);
        tags.setText(bundle.getString("tags"));
        switch_private.setChecked(bundle.getBoolean("isPrivate"));
        String s1 = bundle.getString("color1");
        String s2 = bundle.getString("color2");
        String s3 = bundle.getString("color3");
        String s4 = bundle.getString("color4");
        String s5 = bundle.getString("color5");

        final List<couleur> couleurs = setAllPalette(s1,s2,s3,s4,s5);
        final couleurAdapter adapter = new couleurAdapter(EditPalette.this,couleurs);
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
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                paletteDAO.updatePalette(originPaletteName,name.getText().toString(),couleurs.get(0).getHex_value(),couleurs.get(1).getHex_value(),
                        couleurs.get(2).getHex_value(),couleurs.get(3).getHex_value(),couleurs.get(4).getHex_value(),tags.getText().toString(),date
                        ,switch_private.isChecked());
                finish();
            }
    });

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

    public String genererCouleur() {
        Random rng = new Random();
        int rand_num = rng.nextInt(0xffffff + 1);
        String colorCode = String.format("%06x", rand_num);
        System.out.println("///////////////////////////////////////////////////////////////"+colorCode);
        return colorCode;
    }
}
