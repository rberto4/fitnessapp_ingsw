package com.ingsw.fitnessapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.classi.EserciziAdapter;
import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.oggetti.EsercizioCardio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;

import java.util.ArrayList;
import java.util.Calendar;


public class FragmentEsercizi extends Fragment {


    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_esercizi, container, false);

        recyclerView = v.findViewById(R.id.id_rv_esercizi);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        ArrayList<EsercizioPesistica> list_pesistica = new ArrayList<>();
        ArrayList<EsercizioCardio> list_cardio = new ArrayList<>();

        Calendar rec = Calendar.getInstance();
        rec.set(0,0,0,0,1,30);

        EsercizioPesistica es1 = new EsercizioPesistica(10,15,rec,GruppiMuscolari.Gambe,225);
        es1.setNome("Squat");
        es1.setFavorite(false);
        list_pesistica.add(es1);

        rec = Calendar.getInstance();

        rec.set(0,0,0,0,3,30);

        es1 = new EsercizioPesistica(3,3,rec,GruppiMuscolari.Petto,130);
        es1.setNome("Panca piana pesante");
        es1.setFavorite(true);
        list_pesistica.add(es1);

        EserciziAdapter adapter = new EserciziAdapter(v.getContext(),list_pesistica,list_cardio);
        recyclerView.setAdapter(adapter);


        return v;
    }
}