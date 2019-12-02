package com.example.test_menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Settings_activity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mListView = (ListView) findViewById(R.id.mlistView);

        final List<settings> settings = genererSettings();
        settingsAdapter adapter = new settingsAdapter(Settings_activity.this,settings);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = view.findViewById(R.id.settings_name);

                List<String> list_radio_button = new ArrayList<String>();
                if (position == 0) {
                    list_radio_button.add("RGB");
                    list_radio_button.add("HSV");
                    list_radio_button.add("HEX");
                    dialogshow(name.getText().toString(),list_radio_button,view);
                } else {
                    list_radio_button.add("Title");
                    list_radio_button.add("Hears Count");
                    list_radio_button.add("Date");
                    dialogshow(name.getText().toString(),list_radio_button,view);
                }
            }
        });


    }

    public List<settings> genererSettings() {
        List<settings> list_settings = new ArrayList<settings>();
        list_settings.add(new settings("Default Color Input Type","Select dafault color input type","HEX"));
        list_settings.add(new settings("Default My Palette Order Type","Select dafault order type","Date"));
        list_settings.add(new settings("Default Public Palette Order Type","Select dafault order type","Date"));
        return list_settings;
    }



    public void dialogshow(String name,List<String> stringList, View view) {

        final TextView value = view.findViewById(R.id.settings_value);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radiobuttonlist_dialog);
        /*List<String> stringList=new ArrayList<>();  // here is list
        for(int i=0;i<5;i++) {
            stringList.add("RadioButton " + (i + 1));
        }*/

        TextView textView_dialog = (TextView) dialog.findViewById(R.id.title_dialog);
        textView_dialog.setText(name);
        final RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);
        Button validate = (Button) dialog.findViewById(R.id.validate_button);

        for(int i=0;i<stringList.size();i++){
            RadioButton rb=new RadioButton(this); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rg.addView(rb);
        }

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int RadioButtonSelectedId = rg.getCheckedRadioButtonId();

                RadioButton r = (RadioButton) rg.findViewById(RadioButtonSelectedId);
                value.setText(r.getText().toString());
                dialog.dismiss();
            }
        });

        dialog.show();
    }



}
