package com.ingsw.fitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.classi.WorkoutsAdapter;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Workout;

import java.util.ArrayList;
import java.util.Calendar;

public class NuovaSchedaActivity extends AppCompatActivity {

    MaterialToolbar toolbar ;
    TextView inizio,fine;
    TextView scritta_giorno_selezionato;
    TextView lunedi,martedi,mercoledi,giovedi,venerdi,sabato,domenica;

    ClasseDatabaseOpenHelper db;
    RecyclerView rv;
    WorkoutsAdapter adapter;
    ArrayList<Workout> list;
    Calendar min;
    Calendar max;
    CalendarView calendario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_scheda);

        db = new ClasseDatabaseOpenHelper(this);
        list = db.caricaListaWorkoutDaDb();
        adapter = new WorkoutsAdapter(this,list,db);
        toolbar = findViewById(R.id.id_nuovascheda_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        min = Calendar.getInstance();
        max = Calendar.getInstance();

        rv = findViewById(R.id.id_nuovascheda_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        inizio = findViewById(R.id.id_nuovascheda_inizio);
        fine = findViewById(R.id.id_nuovascheda_fine);
        scritta_giorno_selezionato = findViewById(R.id.id_nuovascheda_giorno_selezionato);
        lunedi = findViewById(R.id.id_nuovascheda_lunedi);
        martedi = findViewById(R.id.id_nuovascheda_martedi);
        mercoledi = findViewById(R.id.id_nuovascheda_mercoledi);
        giovedi = findViewById(R.id.id_nuovascheda_giovedi);
        venerdi = findViewById(R.id.id_nuovascheda_venerdi);
        sabato = findViewById(R.id.id_nuovascheda_sabato);
        domenica = findViewById(R.id.id_nuovascheda_domenica);


        scritta_giorno_selezionato.setText("Lunedì");
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



    }
    private void modificaGiornoSelezionato(String text) {

        switch (text){

            case("L"):{
                scritta_giorno_selezionato.setText("Lunedì");
                modificaVistaTasto(lunedi,true);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(sabato,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(domenica,false);
            }break;
            case("Mar"):{
                scritta_giorno_selezionato.setText("Martedì");
                modificaVistaTasto(martedi,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(sabato,false);
                modificaVistaTasto(domenica,false);

            }break;
            case("Mer"):{
                scritta_giorno_selezionato.setText("Mercoledì");
                modificaVistaTasto(mercoledi,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(sabato,false);
                modificaVistaTasto(domenica,false);

            }break;
            case("G"):{
                scritta_giorno_selezionato.setText("Giovedì");
                modificaVistaTasto(giovedi,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(sabato,false);
                modificaVistaTasto(domenica,false);

            }break;
            case("V"):{
                scritta_giorno_selezionato.setText("Venerdì");
                modificaVistaTasto(venerdi,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(sabato,false);
                modificaVistaTasto(domenica,false);

            }break;
            case("S"):{
                scritta_giorno_selezionato.setText("Sabato");
                modificaVistaTasto(sabato,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(domenica,false);

            }break;
            case("D"):{
                scritta_giorno_selezionato.setText("Domenica");
                modificaVistaTasto(domenica,true);
                modificaVistaTasto(lunedi,false);
                modificaVistaTasto(martedi,false);
                modificaVistaTasto(mercoledi,false);
                modificaVistaTasto(giovedi,false);
                modificaVistaTasto(venerdi,false);
                modificaVistaTasto(sabato,false);
            }break;

        }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}