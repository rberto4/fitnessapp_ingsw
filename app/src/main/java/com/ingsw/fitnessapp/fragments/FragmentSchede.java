package com.ingsw.fitnessapp.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.classi.GiorniSettimana;
import com.ingsw.fitnessapp.classi.WorkoutsAdapter;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Schede;
import com.ingsw.fitnessapp.oggetti.Workout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentSchede extends Fragment {


    CalendarView calendario;
    Calendar oggi,c,min,max;
    Spinner spinner_nomi;
    RecyclerView rv;
    TextView workout_giornocorrente;

    MaterialToolbar toolbar;
    ScrollView scrollView;
    RelativeLayout layout_nessunascheda;
    WorkoutsAdapter adapter;
    ClasseDatabaseOpenHelper db;

    ArrayList<Schede> list ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schede, container, false);

        layout_nessunascheda = v.findViewById(R.id.id_layout_nessunascheda);
        toolbar = v.findViewById(R.id.id_scheda_appbar);
        db = new ClasseDatabaseOpenHelper(v.getContext());
        spinner_nomi = v.findViewById(R.id.id_scheda_spinner_nomi);
        rv = v.findViewById(R.id.id_scheda_rv);
        scrollView = v.findViewById(R.id.id_scheda_scroolview);
        workout_giornocorrente = v.findViewById(R.id.id_scheda_workoutgiorno);
        calendario = v.findViewById(R.id.id_scheda_calendar);

        c = Calendar.getInstance(Locale.ITALIAN);
        c.setTimeInMillis(calendario.getDate());
        oggi = Calendar.getInstance(Locale.ITALIAN);
        min = Calendar.getInstance(Locale.ITALIAN);
        max = Calendar.getInstance(Locale.ITALIAN);

        workout_giornocorrente.setText(impostaScrittaGiorno());

        impostaSpinner(v.getContext());
        spinner_nomi.setSelection(0);
        spinner_nomi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) spinner_nomi.getChildAt(0)).setTextColor(v.getResources().getColor(R.color.white));
                ((TextView) spinner_nomi.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                ((TextView) spinner_nomi.getChildAt(0)).setTextSize(18);

                min.setTimeInMillis(list.get(position).getDataInizio());
                max.setTimeInMillis(list.get(position).getDataFine());
                calendario.setMinDate(min.getTimeInMillis());
                calendario.setMaxDate(max.getTimeInMillis());

                cambiaScheda(position,v.getContext());
                workout_giornocorrente.setText(impostaScrittaGiorno());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
           @Override
           public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
               c.set(year,month,dayOfMonth);
               cambiaScheda(spinner_nomi.getSelectedItemPosition(),v.getContext());
               workout_giornocorrente.setText(impostaScrittaGiorno());
           }
       });

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY > 0){
                    ExtendedFloatingActionButton floatingActionButton = getActivity().findViewById(R.id.id_mainactivity_fab);
                    floatingActionButton.hide();
                } else{
                    ExtendedFloatingActionButton floatingActionButton = getActivity().findViewById(R.id.id_mainactivity_fab);
                    floatingActionButton.show();
                }
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_menu_scheda_nota:
                        mostraNotaScheda(spinner_nomi.getSelectedItemPosition(),v.getContext());
                        return true;
                    case R.id.id_menu_scheda_delete:
                        eliminaScheda(spinner_nomi.getSelectedItemPosition(),v.getContext());
                    default:
                        return false;
                }
            }
        });
        return v;
    }

    private void eliminaScheda(int selectedItemPosition, Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Vuoi eliminare la scheda corrente in maniera definitiva? Nota: tutti i workouts e gli esercizi non verranno eliminati, potrai sempre riutilizzarli per creare altre schede");
        builder.setTitle("Elimina "+list.get(selectedItemPosition).getNome());
        builder.setCancelable(false);
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Si, elimina", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.deleteScheda(list.get(selectedItemPosition));
                impostaSpinner(getContext());
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void mostraNotaScheda(int selectedItemPosition, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(list.get(selectedItemPosition).getObiettivo());
        builder.setTitle("Note scheda");
        builder.setCancelable(false);
        builder.setNeutralButton("Chiudi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public void onStart() {
        super.onStart();
        impostaSpinner(getContext());

    }


    private void cambiaScheda(int position, Context context) {
        ArrayList<Workout> list_filtered = new ArrayList<>();
        for (Workout workout: list.get(position).getList_workout()){
            if (workout.getGiorniSettimana().equals(riconosciGiornoSettimanaDaCalendario())){
                list_filtered.add(workout);
            }
        }

        adapter = new WorkoutsAdapter(context,list_filtered,db,false);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(adapter);
    }

    private GiorniSettimana riconosciGiornoSettimanaDaCalendario() {
        GiorniSettimana gs = null;
        switch (c.get(Calendar.DAY_OF_WEEK)){
            case(Calendar.MONDAY):{
                gs = GiorniSettimana.Lunedi;
            }break;
            case(Calendar.TUESDAY):{
                gs =  GiorniSettimana.Martedi;

            }break;
            case(Calendar.WEDNESDAY):{
                gs =  GiorniSettimana.Mercoledi;

            }break;
            case(Calendar.THURSDAY):{
                gs =  GiorniSettimana.Giovedi;

            }break;
            case(Calendar.FRIDAY):{
                gs =  GiorniSettimana.Venerdi;

            }break;
            case(Calendar.SATURDAY):{
                gs =  GiorniSettimana.Sabato;

            }break;
            case(Calendar.SUNDAY):{
                gs =  GiorniSettimana.Domenica;
            }break;
        }
        return gs;
    }

    private String impostaScrittaGiorno(){
        String s;

        if (c.get(Calendar.DAY_OF_MONTH) == oggi.get(Calendar.DAY_OF_MONTH) && c.get(Calendar.MONTH) == oggi.get(Calendar.MONTH) ){
            s = "Workout di oggi";
        }else if (riconosciGiornoSettimanaDaCalendario().equals(GiorniSettimana.Domenica)){
            s = "Workout della "+riconosciGiornoSettimanaDaCalendario().name();
        }else{
            s = "Workout del "+riconosciGiornoSettimanaDaCalendario().name();
        }
        return s;
    }
    private void impostaSpinner(Context context) {
        list = db.caricaListaSchedeDaDb();
        if (list.isEmpty()){
            scrollView.setVisibility(View.GONE);
            layout_nessunascheda.setVisibility(View.VISIBLE);
        }else{
            scrollView.setVisibility(View.VISIBLE);
            layout_nessunascheda.setVisibility(View.GONE);
            ArrayList<String> array_nomi_schede = new ArrayList<>();

            for (Schede schede: list){
                array_nomi_schede.add(schede.getNome());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, array_nomi_schede);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_nomi.setAdapter(spinnerArrayAdapter);
        }

    }


}