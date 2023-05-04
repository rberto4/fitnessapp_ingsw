package com.ingsw.fitnessapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ingsw.fitnessapp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class FragmentSchede extends Fragment {


    CalendarView calendar;
    Calendar c;
    Calendar max;
    Calendar min;
    Spinner spinner_nomi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schede, container, false);

        spinner_nomi = v.findViewById(R.id.id_scheda_spinner_nomi);

        calendar = v.findViewById(R.id.id_scheda_calendar);

        min = Calendar.getInstance(Locale.ITALIAN);
        max = Calendar.getInstance(Locale.ITALIAN);

        min.set(2023,4,5);
        max.set(2023,5,20);

        calendar.setMinDate(min.getTimeInMillis());
        calendar.setMaxDate(max.getTimeInMillis());

        spinner_nomi.setSelection(0);

        spinner_nomi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) spinner_nomi.getChildAt(0)).setTextColor(v.getResources().getColor(R.color.white));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
           @Override
           public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
               Toast.makeText(v.getContext(), "", Toast.LENGTH_SHORT).show();
           }
       });
        return v;
    }


}