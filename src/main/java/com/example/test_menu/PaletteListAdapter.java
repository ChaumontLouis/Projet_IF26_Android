package com.example.test_menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaletteListAdapter extends RecyclerView.Adapter<PaletteListAdapter.PaletteViewHolder> {

    class PaletteViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_name;
        private final TextView tv_tags;
        private final TextView tv_date;
        private final TextView tv_heartCount;
        private final View couleur1;
        private final View couleur2;
        private final View couleur3;
        private final View couleur4;
        private final View couleur5;
        private final Switch isPrivate;
        private final Button view;
        private final Button edit;
        private final Button delete;

        private PaletteViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.nom_palette_item);
            tv_tags = itemView.findViewById(R.id.liste_palette_tags);
            tv_date = itemView.findViewById(R.id.liste_palette_date);
            tv_heartCount = itemView.findViewById(R.id.liste_palette_heatCount);
            couleur1 = itemView.findViewById(R.id.list_palette_color1);
            couleur2 = itemView.findViewById(R.id.list_palette_color2);
            couleur3 = itemView.findViewById(R.id.list_palette_color3);
            couleur4 = itemView.findViewById(R.id.list_palette_color4);
            couleur5 = itemView.findViewById(R.id.list_palette_color5);
            isPrivate = itemView.findViewById(R.id.isPrivate_liste_palette);
            view = itemView.findViewById(R.id.view_list_palette);
            edit = itemView.findViewById(R.id.edit_list_palette);
            delete = itemView.findViewById(R.id.delette_list_palette);
        }
    }

    private final LayoutInflater mInflater;
    private List<Palette> mPalettes;

    PaletteListAdapter(Context context) { mInflater = LayoutInflater.from(context);}

    public PaletteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_palette_item_view, parent, false);
        return new PaletteViewHolder(itemView);
    }

    public void onBindViewHolder(PaletteViewHolder holder, int position) {
        if (mPalettes != null) {
            Palette current = mPalettes.get(position);

            holder.tv_name.setText(current.getName());
            holder.tv_tags.setText(current.getTags());
            holder.tv_date.setText(current.getDate());
            holder.tv_heartCount.setText(String.valueOf(current.getHeartCount()));
            holder.couleur1.setBackgroundColor(Color.parseColor("#"+current.getColor_value1()));
            holder.couleur2.setBackgroundColor(Color.parseColor("#"+current.getColor_value2()));
            holder.couleur3.setBackgroundColor(Color.parseColor("#"+current.getColor_value3()));
            holder.couleur4.setBackgroundColor(Color.parseColor("#"+current.getColor_value4()));
            holder.couleur5.setBackgroundColor(Color.parseColor("#"+current.getColor_value5()));
            holder.isPrivate.setChecked(current.isIsprivate());

        } else {
            holder.tv_name.setText("Not ready");
            holder.tv_tags.setText("Not ready");
            holder.tv_date.setText("Not ready");
            holder.tv_heartCount.setText("Not ready");
            holder.couleur1.setBackgroundColor(Color.parseColor("#000000"));
            holder.couleur2.setBackgroundColor(Color.parseColor("#000000"));
            holder.couleur3.setBackgroundColor(Color.parseColor("#000000"));
            holder.couleur4.setBackgroundColor(Color.parseColor("#000000"));
            holder.couleur5.setBackgroundColor(Color.parseColor("#000000"));
            holder.isPrivate.setChecked(true);
        }

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaletteDAO dao = PaletteRoomDataBase.getDatabase(v.getContext()).paletteDAO();
                dao.deleteByName(holder.tv_name.getText().toString());
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context,EditPalette.class);
                Bundle bundle = new Bundle();

                bundle.putString("paletteName",holder.tv_name.getText().toString());
                bundle.putString("tags", holder.tv_tags.getText().toString());
                bundle.putBoolean("isPrivate", holder.isPrivate.isChecked());

                ColorDrawable col1 = (ColorDrawable) holder.couleur1.getBackground();
                String hexColor1 = String.format("#%06X", (0xFFFFFF & col1.getColor()));
                ColorDrawable col2 = (ColorDrawable) holder.couleur2.getBackground();
                String hexColor2 = String.format("#%06X", (0xFFFFFF & col2.getColor()));
                ColorDrawable col3 = (ColorDrawable) holder.couleur3.getBackground();
                String hexColor3 = String.format("#%06X", (0xFFFFFF & col3.getColor()));
                ColorDrawable col4 = (ColorDrawable) holder.couleur4.getBackground();
                String hexColor4 = String.format("#%06X", (0xFFFFFF & col4.getColor()));
                ColorDrawable col5 = (ColorDrawable) holder.couleur5.getBackground();
                String hexColor5 = String.format("#%06X", (0xFFFFFF & col5.getColor()));
                bundle.putString("color1",hexColor1);
                bundle.putString("color2",hexColor2);
                bundle.putString("color3",hexColor3);
                bundle.putString("color4",hexColor4);
                bundle.putString("color5",hexColor5);

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    void setPalettes(List<Palette> palettes){
        mPalettes = palettes;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if (mPalettes != null)
            return mPalettes.size();
        else return 0;
    }

    public Palette getPaletteAtPosition (int position) {
        return mPalettes.get(position);
    }
}
