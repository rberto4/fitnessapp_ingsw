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
    ArrayList<Esercizio> list_esercizi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout, container, false);

        list_esercizi = new ArrayList<>();
        ArrayList<EsercizioCardio> list_cardio = new ArrayList<>();
        list = new ArrayList<>();

        Calendar rec = Calendar.getInstance();
        rec.set(0,0,0,0,1,30);

        EsercizioPesistica es1 = new EsercizioPesistica(10,15,rec, GruppiMuscolari.Gambe,225);
        es1.setNome("Squat");
        es1.setFavorite(false);
        list_esercizi.add(es1);

        rec = Calendar.getInstance();

        rec.set(0,0,0,0,3,30);

        es1 = new EsercizioPesistica(3,3,rec,GruppiMuscolari.Petto,130);
        es1.setNome("Panca piana pesante");
        es1.setFavorite(true);
        list_esercizi.add(es1);

        list.add(new Workout(list_esercizi,"Allenamento spinta pesante","Per la panca piana, pollici a 81cm, focus su fermo al petto,", GiorniSettimana.Domenica));

        es1 = new EsercizioPesistica(3,3,rec,GruppiMuscolari.Petto,130);
        es1.setNome("Miliary press");
        es1.setFavorite(true);
        list_esercizi.add(es1);


        list.add(new Workout(list_esercizi,"Workout prova 2","Note di workout 2 irvirvrninrnivrnvrinnrinvir", GiorniSettimana.Sabato));

        list.add(new Workout(list_esercizi,"Workout prova 3","Note d233333333 irvirvrninrnivrnvrinnrinvir", GiorniSettimana.Sabato));

        list.add(new Workout(list_esercizi,"Workout prova 4","Note di workrttrvvrtvrvirvirvrninrnivrnvrinnrinvir", GiorniSettimana.Martedi));

        list.add(new Workout(list_esercizi,"Workout prova 5","Note di 444g4g 2 irvirvrninrnivrnvrinnrinvir", GiorniSettimana.Lunedi));

        recyclerView = v.findViewById(R.id.id_rv_workouts);
        adapter = new WorkoutsAdapter(v.getContext(),list);
        recyclerView.setAdapter(adapter);


        // NASCONDI FAB QUANDO SI FA UNO SCROLL IN BASSO
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    ExtendedFloatingActionButton floatingActionButton = getActivity().findViewById(R.id.id_mainactivity_fab);
                    floatingActionButton.hide();
                } else{
                    ExtendedFloatingActionButton floatingActionButton = getActivity().findViewById(R.id.id_mainactivity_fab);
                    floatingActionButton.show();                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return v;
    }
}