package com.ingsw.fitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.ingsw.fitnessapp.R;

import java.util.Calendar;

public class NuovaSchedaActivity extends AppCompatActivity {

    TextView inizio,fine;
    TextView scritta_giorno_selezionato;
    TextView lunedi,martedi,mercoledi,giovedi,venerdi,sabato,domenica;

    Calendar min;
    Calendar max;
    CalendarView calendario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_scheda);

        min = Calendar.getInstance();
        max = Calendar.getInstance();

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
                modificaGiornoSelezionato(String.valueOf(giovedi.getText()));

            }
        });

        venerdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaGiornoSelezionato(String.valueOf(venerdi.getText()));
            }
        });

        sabato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaGiornoSelezionato(String.valueOf(sabato.getText()));
            }
        });

        domenica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaGiornoSelezionato(String.valueOf(domenica.getText()));
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

            }break;
            case("Mar"):{

            }break;
            case("Mer"):{

            }break;
            case("G"):{

            }break;
            case("V"):{

            }break;
            case("S"):{

            }break;
            case("D"):{

            }break;

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
}