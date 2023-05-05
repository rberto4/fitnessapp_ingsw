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
    int index_giorno = 0, id;
    Bundle b;
    ArrayList<Esercizio> list_esercizi_scelti;

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

        b = getIntent().getExtras();
        db = new ClasseDatabaseOpenHelper(this);
        controlloModifica();

        lista_index = new ArrayList<>();
        lista_index.add(0);
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
                Workout workout = new Workout(list_esercizi_scelti,nome.getText().toString(),nota.getText().toString(),giorniSettimana);
                if (b != null){
                    workout.setList_esercizi(adapter.ottieniListaDiEserciziSelezionati());
                    workout.setId(id);
                    db.updateWorkout(workout);
                }else {
                    list_esercizi_scelti = adapter.ottieniListaDiEserciziSelezionati();
                    workout.setList_esercizi(list_esercizi_scelti);
                    db.aggiungiWorkoutAlDb(workout);
                }
                onBackPressed();
            }
        });

    }

    private void controlloModifica() {
        if (b != null){
            Log.d("controllo_enter_controlloModificia","si");
            list_esercizi_scelti = new ArrayList<>();
            ArrayList<Esercizio> lista_totale_esercizi;
            lista_totale_esercizi = db.caricaListaEserciziDaDb();
            toolbar.setTitle("Modifica allenamento");
            id = b.getInt("id");
            nome.setText(b.getString("nome"));
            nota.setText(b.getString("note"));
            for (int i = 0; i<b.getIntArray("array_es").length; i++){
                for (int j = 0; j<lista_totale_esercizi.size(); j++){
                    if (lista_totale_esercizi.get(j).getId() == b.getIntArray("array_es")[i]){
                        list_esercizi_scelti.add(lista_totale_esercizi.get(j));
                    }
                }
            }
            adapter = new EsInWorkoutAdapter(this,db, list_esercizi_scelti);
        }else{
            Log.d("controllo_enter_controlloModificia","no");
            adapter = new EsInWorkoutAdapter(this,db);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.caricaListaNomi();
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