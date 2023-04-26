package com.ingsw.fitnessapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.classi.GiorniSettimana;
import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.classi.TipoEsercizio;
import com.ingsw.fitnessapp.classi.WorkoutsAdapter;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.EsercizioCardio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;
import com.ingsw.fitnessapp.oggetti.Workout;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentWorkouts extends Fragment {

    RecyclerView recyclerView;
    WorkoutsAdapter adapter;

    ArrayList<Workout> list;
    ArrayList<Esercizio> list_esercizi, list_ese_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout, container, false);

        list_esercizi = new ArrayList<>();
        list = new ArrayList<>();

        // esercizi di prova
        Calendar rec = Calendar.getInstance();
        rec.set(0,0,0,0,15,30);

        EsercizioCardio es = new EsercizioCardio(rec,7);
        es.setNome("Tapis roulant");
        es.setTipo(TipoEsercizio.esercizio_cardio);
        list_esercizi.add(es);

        rec = Calendar.getInstance();
        rec.set(0,0,0,0,2,30);

        EsercizioPesistica es1 = new EsercizioPesistica(6, 3, rec, GruppiMuscolari.Petto, 90);
        es1.setNome("Panca piana");
        es1.setTipo(TipoEsercizio.esercizio_pesistica);
        list_esercizi.add(es1);

        rec = Calendar.getInstance();
        rec.set(0,0,0,0,1,30);

        es1 = new EsercizioPesistica(8, 5, rec, GruppiMuscolari.Petto, 35);
        es1.setNome("Panca inclinata 30°");
        es1.setTipo(TipoEsercizio.esercizio_pesistica);
        list_esercizi.add(es1);
        es1 = new EsercizioPesistica(12, 3, rec, GruppiMuscolari.Petto, 15);
        es1.setNome("Croci ai cavi alti");
        es1.setTipo(TipoEsercizio.esercizio_pesistica);
        list_esercizi.add(es1);

        list_ese_2 = new ArrayList<>();

        // esercizi di prova
        Calendar rec2 = Calendar.getInstance();
        rec.set(0,0,0,0,10,30);

        EsercizioCardio es2 = new EsercizioCardio(rec,7);
        es2.setNome("Cyclette");
        es2.setTipo(TipoEsercizio.esercizio_cardio);
        list_ese_2.add(es2);

        rec = Calendar.getInstance();
        rec.set(0,0,0,0,3,30);

        EsercizioPesistica es3 = new EsercizioPesistica(9, 32, rec, GruppiMuscolari.Gambe, 130);
        es3.setNome("Squat");
        es3.setTipo(TipoEsercizio.esercizio_pesistica);
        list_ese_2.add(es3);

        list.add(new Workout(list_esercizi,"Petto", "Focus su fermo in panca, bilanciere da 29mm",GiorniSettimana.Lunedi));
        list.add(new Workout(list_ese_2,"pervrvevevevtb", "Focus su fermo in panca, bilanciere da 29mm",GiorniSettimana.Lunedi));

        recyclerView = v.findViewById(R.id.id_rv_workouts);
        adapter = new WorkoutsAdapter(v.getContext(),list);
        recyclerView.setAdapter(adapter);


        // NASCONDI FAB QUANDO SI FA UNO SCROLL IN BASSO
        ExtendedFloatingActionButton floatingActionButton = getActivity().findViewById(R.id.id_mainactivity_fab);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    floatingActionButton.hide();
                } else{
                    floatingActionButton.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return v;
    }
}