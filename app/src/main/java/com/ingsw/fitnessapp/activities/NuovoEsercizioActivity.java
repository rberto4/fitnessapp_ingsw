package com.ingsw.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.classi.TipoEsercizio;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class NuovoEsercizioActivity extends AppCompatActivity {

    MaterialToolbar toolbar ;
    ClasseDatabaseOpenHelper db;
    ArrayList<String> lista_nomi_esercizi;

    TextView nome,tipo,ripetizioni,serie,recupero;
    EditText peso;
    ImageView next_tipo, previous_tipo, current_tipo, piu_ripetione, meno_ripetione, piu_serie, meno_serie;
    SwitchCompat favorite;
    Button save;

    int index_tipo = 0; // PETTO CORRISPONDE ALLO 0, IL PRIMO CHE VIENE MOSTRATO;

    // variabili per instanziare l'oggetto

    TipoEsercizio tipoEsercizio = TipoEsercizio.esercizio_pesistica;
    GruppiMuscolari gruppoMuscolare = GruppiMuscolari.Petto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuovo_esercizio);
        toolbar = findViewById(R.id.id_nuovoesercizio_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nome = findViewById(R.id.id_nuovoesercizio_nome);
        tipo = findViewById(R.id.id_nuovoesercizio_tipo_text);
        ripetizioni = findViewById(R.id.id_nuovoesercizio_ripetizioni);
        serie = findViewById(R.id.id_nuovoesercizio_serie);
        recupero = findViewById(R.id.id_nuovoesercizio_recupero);
        peso = findViewById(R.id.id_nuovoesercizio_peso);
        next_tipo = findViewById(R.id.id_nuovoesercizio_tipo_next);
        previous_tipo = findViewById(R.id.id_nuovoesercizio_tipo_previous);
        current_tipo = findViewById(R.id.id_nuovoesercizio_tipo_current);
        piu_ripetione = findViewById(R.id.id_nuovoesercizio_ripetizioni_piu);
        meno_ripetione = findViewById(R.id.id_nuovoesercizio_ripetizioni_meno);
        piu_serie = findViewById(R.id.id_nuovoesercizio_serie_piu);
        meno_serie = findViewById(R.id.id_nuovoesercizio_serie_meno);
        favorite = findViewById(R.id.id_nuovoesercizio_isfavorite);
        save = findViewById(R.id.id_nuovoesercizio_salva);


        lista_nomi_esercizi = new ArrayList<>();
        lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_petto)));


        // SELETTORE DEL TIPO
        previous_tipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CambiaTipoEsercizio("Previous");
            }
        });

        next_tipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CambiaTipoEsercizio("Next");
            }
        });
        // NOME
        nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogNomiEsercizi(NuovoEsercizioActivity.this);
            }
        });

        // RIPETIZIONI
        piu_ripetione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ripetizioni.setText(String.valueOf(Integer.parseInt(ripetizioni.getText().toString())+1));
            }
        });
        meno_ripetione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(ripetizioni.getText().toString())>0){
                    ripetizioni.setText(String.valueOf(Integer.parseInt(ripetizioni.getText().toString())-1));

                }
            }
        });

        // SERIE
        piu_serie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serie.setText(String.valueOf(Integer.parseInt(serie.getText().toString())+1));
            }
        });
        meno_serie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(serie.getText().toString())>0){
                    serie.setText(String.valueOf(Integer.parseInt(serie.getText().toString())-1));

                }
            }
        });
    }

    private void CambiaTipoEsercizio(String string) {

        if (string.equals("Previous")){
           if (index_tipo == 0){
               index_tipo = 6;
           }else{
               index_tipo--;
           }

        }else {
            if (index_tipo == 6){
                index_tipo = 0;
            }else{
                index_tipo++;
            }
        }
        
        switch (index_tipo){
            case(0):{
                tipo.setText(GruppiMuscolari.Petto.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.muscle));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Petto;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_petto)));

            }break;
            case(1):{
                tipo.setText(GruppiMuscolari.Spalle.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.arm_muscle));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Spalle;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_spalle)));
            }break;
            case(2):{
                tipo.setText(GruppiMuscolari.Braccia.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.arm_muscle));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Braccia;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_braccia)));

            }break;
            case(3):{
                tipo.setText(GruppiMuscolari.Schiena.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.back));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Schiena;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_schiena)));

            }break;
            case(4):{
                tipo.setText(GruppiMuscolari.Addome.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.muscle));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Addome;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esericizi_addome)));

            }break;
            case(5):{
                tipo.setText(GruppiMuscolari.Gambe.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.leg));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Gambe;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_gambe)));

            }break;
            case(6):{
                tipo.setText("Cardio");
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.heart_rate));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esericizi_cardio)));

            }break;
        }

        Toast.makeText(this, index_tipo+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dialogNomiEsercizi(Context context){
        Dialog dialog = new Dialog(NuovoEsercizioActivity.this);
        dialog.setContentView(R.layout.dialog_searchbox_nomiesercizi);
        dialog.setCancelable(true);
        dialog.show();

        MaterialToolbar toolbar1 = dialog.findViewById(R.id.id_dialog_nomiesercizi_toolbar);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        SearchView searchView = dialog.findViewById(R.id.id_dialog_nomiesercizi_search);
        ListView list = dialog.findViewById(R.id.id_dialog_nomiesercizi_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,lista_nomi_esercizi);
        list.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                nome.setText(parent.getItemAtPosition(position).toString());
                dialog.dismiss();
            }
        });
    }
}

