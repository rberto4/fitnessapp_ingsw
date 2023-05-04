package com.ingsw.fitnessapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.ingsw.fitnessapp.R;

import com.ingsw.fitnessapp.fragments.FragmentEsercizi;
import com.ingsw.fitnessapp.fragments.FragmentSchede;
import com.ingsw.fitnessapp.fragments.FragmentWorkouts;

public class MainActivity extends AppCompatActivity {

    ExtendedFloatingActionButton fab ;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.id_main_framelayout,new FragmentSchede()).commit();
        fab = findViewById(R.id.id_mainactivity_fab);
        frameLayout = findViewById(R.id.id_main_framelayout);
        bottomNavigationView = findViewById(R.id.id_main_bottomnavigation);
        cambiaFragment(new FragmentSchede());

        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {
                switch (bottomNavigationView.getSelectedItemId()){
                    case R.id.id_menu_bottomnavigationview_esercizi: {

                        startActivity(new Intent(MainActivity.this, NuovoEsercizioActivity.class));
                    }break;
                    case R.id.id_menu_bottomnavigationview_workouts: {
                        startActivity(new Intent(MainActivity.this, NuovoWorkoutActivity.class));

                    }break;
                    case R.id.id_menu_bottomnavigationview_schede: {
                        startActivity(new Intent(MainActivity.this, NuovaSchedaActivity.class));
                    }break;
                }
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.id_menu_bottomnavigationview_esercizi: {
                    fab.setText("Nuovo esercizio");
                    cambiaFragment(new FragmentEsercizi());
                }break;
                case R.id.id_menu_bottomnavigationview_workouts: {

                    fab.setText("Nuovo allenamento");
                    cambiaFragment(new FragmentWorkouts());
                }break;
                case R.id.id_menu_bottomnavigationview_schede: {
                    fab.setText("Nuova scheda");
                    cambiaFragment(new FragmentSchede());
                }break;
            }
            return true;
        });
    }

    private void cambiaFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_main_framelayout,fragment);
        fragmentTransaction.commit();
    }


}