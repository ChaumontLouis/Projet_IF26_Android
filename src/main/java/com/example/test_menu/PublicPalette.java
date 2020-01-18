package com.example.test_menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class PublicPalette extends AppCompatActivity {

    private PaletteViewModel mPaletteViewModel;
    private PublicListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicpalette);

        RecyclerView recyclerView = findViewById(R.id.public_recycler);
        adapter = new PublicListAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPaletteViewModel = new ViewModelProvider(this).get(PaletteViewModel.class);

        mPaletteViewModel.getPublic().observe(this, new Observer<List<Palette>>() {
            @Override
            public void onChanged(@Nullable final List<Palette> palettes) {
                adapter.setPalettes(palettes);
            }
        });
    }
}
