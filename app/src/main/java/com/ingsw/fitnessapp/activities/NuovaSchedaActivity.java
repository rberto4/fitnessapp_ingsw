package com.ingsw.fitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.classi.GiorniSettimana;
import com.ingsw.fitnessapp.classi.WorkoutsAdapter;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Schede;
import com.ingsw.fitnessapp.oggetti.Workout;

import java.util.ArrayList;
import java.util.Calendar;

public class NuovaSchedaActivity extends AppCompatActivity {

    MaterialToolbar toolbar ;
    TextView inizio,fine;
    TextView lunedi,martedi,mercoledi,giovedi,venerdi,sabato,domenica;

    EditText nome,obiettivo;
    Button save;
    TextView nuovo_workout;
    ClasseDatabaseOpenHelper db;
    RecyclerView rv;
    WorkoutsAdapter adapter;
    ArrayList<Workout> list;
    Calendar min;
    Calendar max;
    CalendarView calendario;


    GiorniSettimana gs = GiorniSettimana.Lunedi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_scheda);


        toolbar = findViewById(R.id.id_nuovascheda_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        min = Calendar.getInstance();
        max = Calendar.getInstance();

        db = new ClasseDatabaseOpenHelper(this);
        adapter = new WorkoutsAdapter(this,list,db,true);

        rv = findViewById(R.id.id_nuovascheda_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        nome = findViewById(R.id.id_nuovascheda_nome);
        obiettivo = findViewById(R.id.id_nuovascheda_obiettivo);
        inizio = findViewById(R.id.id_nuovascheda_inizio);
        fine = findViewById(R.id.id_nuovascheda_fine);
        lunedi = findViewById(R.id.id_nuovascheda_lunedi);
        martedi = findViewById(R.id.id_nuovascheda_martedi);
        mercoledi = findViewById(R.id.id_nuovascheda_mercoledi);
        giovedi = findViewById(R.id.id_nuovascheda_giovedi);
        venerdi = findViewById(R.id.id_nuovascheda_venerdi);
        sabato = findViewById(R.id.id_nuovascheda_sabato);
        domenica = findViewById(R.id.id_nuovascheda_domenica);
        save = findViewById(R.id.id_nuovascheda_salva);
        nuovo_workout = findViewById(R.id.id_nuovascheda_aggiungi_workout);

        // selezione di default : lunedi
        modificaVistaTasto(lunedi,true);
        modificaVistaTasto(martedi,false);
        modificaVistaTasto(mercoledi,false);
        modificaVistaTasto(giovedi,false);
        modificaVistaTasto(sabato,false);
        modificaVistaTasto(venerdi,false);
        modificaVistaTasto(domenica,false);


        lunedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaGiornoSelezionato(String.valueOf(lunedi.getText()));
            }
        });

        martedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaGiornoSelezionato("Mar");
            }
        });

        mercoledi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaGiornoSelezionato("Mer");
            }
        });

        giovedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaGiornoSelezionato("G");

            }
        });

        venerdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaGiornoSelezionato("V");
            }
        });

        sabato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaGiornoSelezionato("S");
            }
        });

        domenica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaGiornoSelezionato("D");
            }
        });


        inizio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCalendario("Inizio scheda");
            }
        });

        fine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCalendario("Fine scheda");

            }
        });

        nuovo_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NuovaSchedaActivity.this, NuovoWorkoutActivity.class));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n;
                String o;
                if (nome.getText().toString().equals("")) {
                    n = "Scheda " + (db.caricaListaSchedeDaDb().size() + 1);
                }else{
                    n = nome.getText().toString();
                }

                if (obiettivo.getText().toString().equals("")) {
                     o = "Nessun obiettivo impostato";
                }else{
                    o= obiettivo.getText().toString();
                }

                Schede scheda = new Schede(
                        adapter.restituisciWorkoutSelezionati(),
                        n,
                        min.getTimeInMillis(),
                        max.getTimeInMillis(),
                        o);

                db.aggiungiSchedeAlDb(scheda);
            }
        });

    }
    private void modificaGiornoSelezionato(String text) {

        switch (text){

            case("L"):{
                modificaVistaTasto(lunedi,true);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(sabato,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(domenica,false);
                gs = GiorniSettimana.Lunedi;
            }break;
            case("Mar"):{
                modificaVistaTasto(martedi,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(sabato,false);
                modificaVistaTasto(domenica,false);
                gs = GiorniSettimana.Martedi;

            }break;
            case("Mer"):{
                modificaVistaTasto(mercoledi,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(sabato,false);
                modificaVistaTasto(domenica,false);
                gs = GiorniSettimana.Mercoledi;

            }break;
            case("G"):{
                modificaVistaTasto(giovedi,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(sabato,false);
                modificaVistaTasto(domenica,false);
                gs = GiorniSettimana.Giovedi;

            }break;
            case("V"):{
                modificaVistaTasto(venerdi,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(sabato,false);
                modificaVistaTasto(domenica,false);
                gs = GiorniSettimana.Venerdi;

            }break;
            case("S"):{
                modificaVistaTasto(sabato,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(domenica,false);
                gs = GiorniSettimana.Sabato;

            }break;
            case("D"):{
                modificaVistaTasto(domenica,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(sabato,false);
                gs = GiorniSettimana.Domenica;
            }break;

        }
        applicaFiltro();
    }

    public void modificaVistaTasto(TextView t, Boolean isSelected){
        if(isSelected){
            t.setBackground(AppCompatResources.getDrawable(this,R.drawable.cerchio_rosso));
            t.setTextColor(getColor(R.color.white));
        }else{
            t.setBackground(ContextCompat.getDrawable(NuovaSchedaActivity.this, R.drawable.cerchio_bianco));
            t.setTextColor(AppCompatResources.getColorStateList(this,R.color.dark_grey));
        }
    }



    public void dialogCalendario(String s){
        Dialog dialog = new Dialog(NuovaSchedaActivity.this);
        dialog.setContentView(R.layout.dialog_calendar);
        dialog.setCancelable(false);
        dialog.show();

        calendario = dialog.findViewById(R.id.id_dialog_calendar);
        MaterialToolbar toolbar1 = dialog.findViewById(R.id.id_dialog_calendar_toolbar);
        toolbar1.setTitle(s);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        switch (s){
            case ("Inizio scheda"):{
                calendario.setMinDate(calendario.getDate());
                min.setTimeInMillis(calendario.getDate());
            }break;
            case ("Fine scheda"):{
                calendario.setMinDate(min.getTimeInMillis());
            }break;
        }

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year,month,dayOfMonth);
                switch (s){
                    case ("Inizio scheda"):{
                        min.setTimeInMillis(c.getTimeInMillis());
                        inizio.setText(min.getTime().getDate()+"");
                        dialog.dismiss();
                    }break;
                    case ("Fine scheda"):{
                        max.setTimeInMillis(c.getTimeInMillis());
                        fine.setText(max.getTime().getDate()+"");
                        dialog.dismiss();
                    }break;
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        db = new ClasseDatabaseOpenHelper(this);
        list = db.caricaListaWorkoutDaDb();
        applicaFiltro();
    }

    private void applicaFiltro() {
        ArrayList<Workout> list_filtered = new ArrayList<>();
        for (Workout workout: list){
            if (workout.getGiorniSettimana().equals(gs)){
                list_filtered.add(workout);
            }
        }
        adapter.filtraPerGiornoSettimana(list_filtered);
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