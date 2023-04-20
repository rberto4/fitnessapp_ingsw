package com.ingsw.fitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.fragments.FragmentEsercizi;

public class MainActivity extends AppCompatActivity {

    ExtendedFloatingActionButton fab ;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    FragmentEsercizi fragmentEsercizi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.id_mainactivity_fab);
        frameLayout = findViewById(R.id.id_main_framelayout);
        bottomNavigationView = findViewById(R.id.id_main_bottomnavigation);

        fragmentEsercizi = new FragmentEsercizi();
        getSupportFragmentManager().beginTransaction().replace(R.id.id_main_framelayout, fragmentEsercizi).commit();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NuovoEsercizioActivity.class));
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) { // metodo per risposta al tocco del bottom navigation view, il frame layout viene caricato con il fragment in base all'id

                switch (item.getItemId()){
                    case R.id.id_menu_bottomnavigationview_esercizi: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.id_main_framelayout,fragmentEsercizi).commit();

                    }break;
                }

                return false;
            }
        });
    }
}