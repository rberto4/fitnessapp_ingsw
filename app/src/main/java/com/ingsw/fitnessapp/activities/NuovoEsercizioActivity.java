package com.ingsw.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.classi.Tempo;
import com.ingsw.fitnessapp.classi.TipoEsercizio;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.EsercizioCardio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;

import java.util.ArrayList;
import java.util.Arrays;

public class NuovoEsercizioActivity extends AppCompatActivity {

    MaterialToolbar toolbar ;
    ClasseDatabaseOpenHelper db;
    ArrayList<String> lista_nomi_esercizi;

    TextView nome,tipo,ripetizioni,serie,recupero,difficolta,durata;
    EditText peso;
    ImageView next_tipo, previous_tipo, current_tipo, piu_ripetione, meno_ripetione, piu_serie, meno_serie, piu_difficolta, meno_difficolta;
    SwitchCompat favorite;
    Button save,nome_personalizzato;

    Tempo tempo;
    LinearLayout layout_pesi,layout_cardio;
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
        nome_personalizzato = findViewById(R.id.id_nuovoesercizio_nome_personalizzato);
        piu_difficolta = findViewById(R.id.id_nuovoesercizio_difficolta_piu);
        meno_difficolta = findViewById(R.id.id_nuovoesercizio_difficolta_meno);
        difficolta = findViewById(R.id.id_nuovoesercizio_difficolta);
        durata = findViewById(R.id.id_nuovoesercizio_cardio_durata);
        save = findViewById(R.id.id_nuovoesercizio_salva);
        layout_pesi = findViewById(R.id.id_nuovoesercizio_layout_pesistica);
        layout_cardio = findViewById(R.id.id_nuovoesercizio_layout_cardio);


        lista_nomi_esercizi = new ArrayList<>();
        lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_petto)));

        db = new ClasseDatabaseOpenHelper(this);

        tempo = new Tempo();
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

        // RECUPERO

        recupero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogRecupero("Tempo recupero");
            }
        });

        // BTN NOME PERSONALIZZATO
        nome_personalizzato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCustomNomeEsercizio();
            }
        });

        // DIFFICOLTA'

        piu_difficolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficolta.setText(String.valueOf(Integer.parseInt(difficolta.getText().toString())+1));
            }
        });
        meno_difficolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(difficolta.getText().toString())>0){
                    difficolta.setText(String.valueOf(Integer.parseInt(difficolta.getText().toString())-1));

                }
            }
        });

        // DURATA

        durata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogRecupero("Durata sessione");
            }
        });

        // SALVATAGGIO DATI

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperaDati(
                        nome.getText().toString(),
                        favorite.isChecked(),
                        tipoEsercizio,
                        gruppoMuscolare,
                        Integer.parseInt(ripetizioni.getText().toString()),
                        Integer.parseInt(serie.getText().toString()),
                        peso.getText().toString(),
                        recupero.getText().toString(),
                        durata.getText().toString(),
                        Integer.parseInt(difficolta.getText().toString())
                );
            }
        });
    }

    private void recuperaDati(String nome, boolean checked, TipoEsercizio tipoEsercizio, GruppiMuscolari gruppoMuscolare, int r, int s, String p, String tr, String td, int d) {
        switch (tipoEsercizio.name())
        {
            case("esercizio_pesistica"):{

                Esercizio esercizio = new EsercizioPesistica(
                        r,
                        s,
                        tempo.calcolaInteroDaTempo(tr),
                        gruppoMuscolare,
                        Float.parseFloat(p)
                );
                esercizio.setNome(nome);
                esercizio.setFavorite(checked);
                esercizio.setTipo(tipoEsercizio);
                db.aggiungiEsercizioAlDb(esercizio);
            }break;

            case("esercizio_cardio"):{
                    Esercizio esercizio = new EsercizioCardio(
                            tempo.calcolaInteroDaTempo(td),
                            d
                    );
                    esercizio.setNome(nome);
                    esercizio.setFavorite(checked);
                    esercizio.setTipo(tipoEsercizio);
                    db.aggiungiEsercizioAlDb(esercizio);
            }break;
        }
        onBackPressed();
    }

    private void dialogRecupero(String type) {
        Dialog dialog = new Dialog(NuovoEsercizioActivity.this);
        dialog.setContentView(R.layout.dialog_recupero_nomiesercizi);
        dialog.setCancelable(true);
        dialog.show();

        MaterialToolbar toolbar1 = dialog.findViewById(R.id.id_dialog_nomiesercizi_toolbar);
        ImageView piu_secondi = dialog.findViewById(R.id.id_dialog_recupero_secondi_piu);
        ImageView meno_secondi = dialog.findViewById(R.id.id_dialog_recupero_secondi_meno);
        ImageView piu_minuti = dialog.findViewById(R.id.id_dialog_recupero_minuti_piu);
        ImageView meno_minuti = dialog.findViewById(R.id.id_dialog_recupero_minuti_meno);
        TextView secondi = dialog.findViewById(R.id.id_dialog_recupero_secondi);
        TextView minuti = dialog.findViewById(R.id.id_dialog_recupero_minuti);

        toolbar1.setTitle(type);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        piu_secondi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(secondi.getText().toString())<50){
                    secondi.setText(String.valueOf(Integer.parseInt(secondi.getText().toString())+10));
                }else{
                    minuti.setText(String.valueOf(Integer.parseInt(minuti.getText().toString())+1));
                    secondi.setText("00");
                }

            }
        });

        meno_secondi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(secondi.getText().toString())>10){
                    secondi.setText(String.valueOf(Integer.parseInt(secondi.getText().toString())-10));
                }else{
                    if(Integer.parseInt(minuti.getText().toString())>0){
                        minuti.setText(String.valueOf(Integer.parseInt(minuti.getText().toString())-1));
                        secondi.setText("50");
                    }else{
                        secondi.setText("00");
                    }
                }

            }
        });
        piu_minuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (Integer.parseInt(minuti.getText().toString())<59){
                        minuti.setText(String.valueOf(Integer.parseInt(minuti.getText().toString())+1));
                    }
            }
        });
        meno_minuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(minuti.getText().toString())>0){
                    minuti.setText(String.valueOf(Integer.parseInt(minuti.getText().toString())-1));
                }
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (type.equals("Durata sessione")){
                    durata.setText(minuti.getText() + ":" + secondi.getText());
                }else {
                    recupero.setText(minuti.getText() + ":" + secondi.getText());
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
                layout_cardio.setVisibility(View.GONE);
                layout_pesi.setVisibility(View.VISIBLE);

            }break;
            case(1):{
                tipo.setText(GruppiMuscolari.Spalle.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.arm_muscle));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Spalle;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_spalle)));
                layout_cardio.setVisibility(View.GONE);
                layout_pesi.setVisibility(View.VISIBLE);
            }break;
            case(2):{
                tipo.setText(GruppiMuscolari.Braccia.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.arm_muscle));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Braccia;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_braccia)));
                layout_cardio.setVisibility(View.GONE);
                layout_pesi.setVisibility(View.VISIBLE);

            }break;
            case(3):{
                tipo.setText(GruppiMuscolari.Schiena.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.back));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Schiena;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_schiena)));
                layout_cardio.setVisibility(View.GONE);
                layout_pesi.setVisibility(View.VISIBLE);

            }break;
            case(4):{
                tipo.setText(GruppiMuscolari.Addome.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.muscle));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Addome;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esericizi_addome)));
                layout_cardio.setVisibility(View.GONE);
                layout_pesi.setVisibility(View.VISIBLE);

            }break;
            case(5):{
                tipo.setText(GruppiMuscolari.Gambe.name());
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.leg));
                tipoEsercizio = TipoEsercizio.esercizio_pesistica;
                gruppoMuscolare = GruppiMuscolari.Gambe;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esercizi_gambe)));
                layout_cardio.setVisibility(View.GONE);
                layout_pesi.setVisibility(View.VISIBLE);

            }break;
            case(6):{
                tipo.setText("Cardio");
                current_tipo.setImageDrawable(getResources().getDrawable(R.drawable.heart_rate));
                tipoEsercizio = TipoEsercizio.esercizio_cardio;
                lista_nomi_esercizi = new ArrayList<>();
                lista_nomi_esercizi.addAll(Arrays.asList(getResources().getStringArray(R.array.Esericizi_cardio)));
                layout_cardio.setVisibility(View.VISIBLE);
                layout_pesi.setVisibility(View.GONE);

            }break;
        }

    }

    private void dialogCustomNomeEsercizio(){
        Dialog dialog = new Dialog(NuovoEsercizioActivity.this);
        dialog.setContentView(R.layout.dialog_custom_nomiesercizi);
        dialog.setCancelable(true);
        dialog.show();

        MaterialToolbar toolbar1 = dialog.findViewById(R.id.id_dialog_nomiesercizi_toolbar);
        EditText nome_custom_editext = dialog.findViewById(R.id.dialog_customname_editext);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!nome_custom_editext.getText().toString().equals("")){
                    nome.setText(nome_custom_editext.getText().toString());
                }
            }
        });


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
                nome.setText(parent.getItemAtPosition(position).toString());
                dialog.dismiss();
            }
        });
    }
}

