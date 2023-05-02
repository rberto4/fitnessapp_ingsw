package com.ingsw.fitnessapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.classi.EsInWorkoutAdapter;
import com.ingsw.fitnessapp.classi.GiorniSettimana;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.Workout;

import java.util.ArrayList;

public class NuovoWorkoutActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    EditText nome,nota;
    ImageView giorno_previous,giorno_next;
    TextView giorno;
    RecyclerView recyclerView;
    EsInWorkoutAdapter adapter;

    Button salva;
    ArrayList<Integer> lista_index;
    GiorniSettimana giorniSettimana = GiorniSettimana.Lunedi;
    int index_giorno = 0;

    ClasseDatabaseOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuovo_workout);

        toolbar = findViewById(R.id.id_nuovoworkout_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nome = findViewById(R.id.id_nuovoworkout_nome);
        nota = findViewById(R.id.id_nuovoworkout_nota);
        giorno = findViewById(R.id.id_nuovoworkout_giorno);
        giorno_previous = findViewById(R.id.id_nuovoworkout_prevoius);
        giorno_next = findViewById(R.id.id_nuovoworkout_next);
        salva = findViewById(R.id.id_nuovoworkout_salva);
        recyclerView = findViewById(R.id.id_nuovoworkout_rv_di_esercizi);

        db = new ClasseDatabaseOpenHelper(this);
        lista_index = new ArrayList<>();
        lista_index.add(0);
        adapter = new EsInWorkoutAdapter(this,db);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        giorno_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                impostaGiorno("Previous");
            }
        });

        giorno_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                impostaGiorno("Next");
            }
        });

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Esercizio> list_esercizi_scelti = adapter.ottieniListaDiEserciziSelezionati();
                Workout workout = new Workout(list_esercizi_scelti,nome.getText().toString(),nota.getText().toString(),giorniSettimana);
                db.aggiungiWorkoutAlDb(workout);
                onBackPressed();
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.caricaListaNomi(true);
    }

    private void impostaGiorno(String string) {

        if (string.equals("Previous")){
            if (index_giorno == 0){
                index_giorno = 6;
            }else{
                index_giorno--;
            }

        }else {
            if (index_giorno == 6){
                index_giorno = 0;
            }else{
                index_giorno++;
            }
        }

        switch (index_giorno){
            case (0):{
                giorno.setText("Lunedì");
                giorniSettimana = GiorniSettimana.Lunedi;
            }break;
            case (1):{
                giorno.setText("Martedì");
                giorniSettimana = GiorniSettimana.Martedi;
            }break;
            case (2):{
                giorno.setText("Mercoledì");
                giorniSettimana = GiorniSettimana.Mercoledi;
            }break;
            case (3):{
                giorno.setText("Giovedì");
                giorniSettimana = GiorniSettimana.Giovedi;
            }break;
            case (4):{
                giorno.setText("Venerdì");
                giorniSettimana = GiorniSettimana.Venerdi;
            }break;
            case (5):{
                giorno.setText("Sabato");
                giorniSettimana = GiorniSettimana.Sabato;
            }break;
            case (6):{
                giorno.setText("Domenica");
                giorniSettimana = GiorniSettimana.Domenica;
            }break;

        }
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