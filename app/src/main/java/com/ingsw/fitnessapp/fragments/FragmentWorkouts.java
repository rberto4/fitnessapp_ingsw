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
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
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

    ClasseDatabaseOpenHelper db;
   // ArrayList<Esercizio> list_esercizi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout, container, false);

        db = new ClasseDatabaseOpenHelper(v.getContext());

       // list_esercizi = new ArrayList<>();
        recyclerView = v.findViewById(R.id.id_rv_workouts);



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

    @Override
    public void onStart() {
        super.onStart();
        list = db.caricaListaWorkoutDaDb();
        adapter = new WorkoutsAdapter(getContext(),list,db);
        recyclerView.setAdapter(adapter);
    }
}