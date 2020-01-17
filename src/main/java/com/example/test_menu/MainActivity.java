package com.example.test_menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int NEW_PALETTE_ACTIVITY_REQUEST_CODE = 1;
    private PaletteViewModel mPaletteViewModel;
    private PaletteRoomDataBase dataBase;
    private PaletteListAdapter adapter;
    private PaletteDAO dao;
    private UserRoomDataBase userRoomDataBase;
    private UserDAO userDAO;
    private String userlogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userlogged = UserLogged.UserLooged;

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.nav_header_main,null);
        TextView text = view.findViewById(R.id.tv_logged);
        text.setText(UserLogged.UserLooged);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.button_add_palette);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Ajout_palette.class);
                startActivityForResult(intent,NEW_PALETTE_ACTIVITY_REQUEST_CODE);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new PaletteListAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPaletteViewModel = new ViewModelProvider(this).get(PaletteViewModel.class);

        mPaletteViewModel.getUserPalette().observe(this, new Observer<List<Palette>>() {
            @Override
            public void onChanged(@Nullable final List<Palette> palettes) {
                adapter.setPalettes(palettes);
            }
        });

        userRoomDataBase = UserRoomDataBase.getDatabase(this);
        userDAO = userRoomDataBase.UserDAO();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_mypalettes) {
            mPaletteViewModel.getByDate();
        } else if (id == R.id.nav_publicpalettes) {
            mPaletteViewModel.getByHeart();
        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_cardpalettes) {

        } else if (id == R.id.nav_rate) {

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, Settings_activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_log_out) {
            finish();
        } else if (id == R.id.action_hearts) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == NEW_PALETTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                String name = bundle.getString("title");
                String tags = bundle.getString("tags");
                String date = bundle.getString("date");
                Boolean isPrivate = bundle.getBoolean("private");
                String color1 = bundle.getString("color1");
                String color2 = bundle.getString("color2");
                String color3 = bundle.getString("color3");
                String color4 = bundle.getString("color4");
                String color5 = bundle.getString("color5");

                Palette newPalette = new Palette(this.userlogged,color1,color2,color3,color4,color5,name,tags,date,isPrivate);
                mPaletteViewModel.insert(newPalette);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Enregistrement échoué",
                    Toast.LENGTH_LONG).show();
        }
    }

}
