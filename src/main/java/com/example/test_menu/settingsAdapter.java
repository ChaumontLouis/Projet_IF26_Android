package com.example.test_menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class settingsAdapter extends ArrayAdapter<settings> {

    public settingsAdapter(Context context, List<settings> settings) {
        super(context, 0, settings);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_settings,parent, false);
        }

        settingsViewHolder viewHolder = (settingsViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new settingsViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.settings_name);
            viewHolder.description = (TextView) convertView.findViewById(R.id.settings_description);
            viewHolder.value = (TextView) convertView.findViewById(R.id.settings_value);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        settings setting = getItem(position);
        viewHolder.name.setText(setting.getName());
        viewHolder.description.setText(setting.getDescription());
        viewHolder.value.setText(setting.getValue());

        return convertView;
    }

    private class settingsViewHolder{
        private TextView name;
        private TextView description;
        private TextView value;

    }
}
