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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.activities.MainActivity;
import com.ingsw.fitnessapp.classi.EserciziAdapter;
import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.classi.TipoEsercizio;
import com.ingsw.fitnessapp.oggetti.Esercizio;
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
    ArrayList<Esercizio> list;
    EserciziAdapter adapter;
    ChipGroup gruppo_chips;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Richiamo della visibilità del fab quando viene nascosto dopo lo scroll di elenchi lunghi

        ExtendedFloatingActionButton floatingActionButton = getActivity().findViewById(R.id.id_mainactivity_fab);
        floatingActionButton.show();

        // view inflater
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

        // CODICE PER LISTA ESERCIZI

        list = new ArrayList<>();

        // DB , PRENDERE DATI E METTERE IN LIST


        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        Calendar rec = Calendar.getInstance();
        rec.set(0,0,0,0,1,30);

        // oggetto di tipo cardio
        EsercizioCardio es_cardio_test = new EsercizioCardio(rec, 12);
        es_cardio_test.setNome("Cyclette");
        es_cardio_test.setFavorite(true);
        es_cardio_test.setTipo(TipoEsercizio.esercizo_cardio);

        // oggetto di tipo pesistica

        EsercizioPesistica es_pesi_test = new EsercizioPesistica(3,6,rec,GruppiMuscolari.Braccia,50);
        es_pesi_test.setNome("Curl bilanciere");
        es_pesi_test.setTipo(TipoEsercizio.esercizio_pesistica);

        list.add(es_pesi_test);

        es_pesi_test = new EsercizioPesistica(10,5,rec,GruppiMuscolari.Gambe,120);
        es_pesi_test.setNome("Squat");
        es_pesi_test.setTipo(TipoEsercizio.esercizio_pesistica);

        list.add(es_pesi_test);

        es_pesi_test = new EsercizioPesistica(8,3,rec,GruppiMuscolari.Petto,30);
        es_pesi_test.setNome("Panca 45° manubri");
        es_pesi_test.setTipo(TipoEsercizio.esercizio_pesistica);
        // aggiungo alla lista di tipo abstract esercizio
        list.add(es_cardio_test);
        list.add(es_pesi_test);

        adapter = new EserciziAdapter(v.getContext(),list);
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

    // filtro per ricerca testuale

    private void filtraListaPerNome(String newText) {
        ArrayList<Esercizio> lista_filtrata = new ArrayList<>();
        for ( Esercizio esercizio : list){
            if (esercizio.getNome().toLowerCase(Locale.ROOT).contains(newText.toLowerCase(Locale.ROOT))){
                lista_filtrata.add(esercizio);
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