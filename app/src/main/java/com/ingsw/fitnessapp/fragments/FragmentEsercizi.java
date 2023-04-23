package com.ingsw.fitnessapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.ChipGroup;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.classi.EserciziAdapter;
import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.oggetti.EsercizioCardio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class FragmentEsercizi extends Fragment {


    RecyclerView recyclerView;
    SearchView searchView;
    MaterialToolbar toolbar;
    ArrayList<EsercizioPesistica> list_pesistica;
    EserciziAdapter adapter;
    ChipGroup gruppo_chips;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_esercizi, container, false);


        toolbar = v.findViewById(R.id.id_esercizi_toolbar);
        gruppo_chips = v.findViewById(R.id.id_esercizi_chipgroup);


        recyclerView = v.findViewById(R.id.id_rv_esercizi);
        searchView = v.findViewById(R.id.id_searchview_esercizi);

        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtraListaPerNome(newText);
                return false;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        list_pesistica = new ArrayList<>();
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

        adapter = new EserciziAdapter(v.getContext(),list_pesistica,list_cardio);
        recyclerView.setAdapter(adapter);


        return v;
    }

    private void filtraListaPerNome(String newText) {
        ArrayList<EsercizioPesistica> lista_filtrata = new ArrayList<>();
        for (EsercizioPesistica esercizioPesistica : list_pesistica){
            if (esercizioPesistica.getNome().toLowerCase(Locale.ROOT).contains(newText.toLowerCase(Locale.ROOT))){
                lista_filtrata.add(esercizioPesistica);
            }
        }
        if(!lista_filtrata.isEmpty()){
            adapter.impostaListaFiltrata(lista_filtrata);
        }
    }

    private void filtraListaPerTipo(List<Integer> checkedIds) {
        ArrayList<EsercizioPesistica> lista_filtrata = new ArrayList<>();
        for (Integer integer: checkedIds){
            /*
            switch (integer){
                case(){

                }break;
                case(){

                }break;
                case(){

                }break;
                case(){

                }break;
                case(){

                }break;
                case(){

                }break;
            }
            *
             */
        }
    }

}