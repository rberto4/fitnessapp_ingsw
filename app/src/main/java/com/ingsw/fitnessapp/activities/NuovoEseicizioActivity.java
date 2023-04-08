package com.ingsw.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.ingsw.fitnessapp.R;

<<<<<<< Updated upstream
public class NuovoEseicizioActivity extends AppCompatActivity { //Classe figlia AutocompatActivity
=======
import java.util.Objects;

public class NuovoEseicizioActivity extends AppCompatActivity {
>>>>>>> Stashed changes

    MaterialToolbar toolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuovo_eseicizio);
        toolbar = findViewById(R.id.id_nuovoesercizio_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

