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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.activities.MainActivity;
import com.ingsw.fitnessapp.classi.EserciziAdapter;
import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.classi.TipoEsercizio;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
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
    ClasseDatabaseOpenHelper db;
    RelativeLayout layout_no_esercizi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Richiamo della visibilità del fab quando viene nascosto dopo lo scroll di elenchi lunghi

        ExtendedFloatingActionButton floatingActionButton = getActivity().findViewById(R.id.id_mainactivity_fab);
        floatingActionButton.show();

        // view inflater
        View v = inflater.inflate(R.layout.fragment_esercizi, container, false);


        db = new ClasseDatabaseOpenHelper(v.getContext());
        toolbar = v.findViewById(R.id.id_esercizi_toolbar);
        gruppo_chips = v.findViewById(R.id.id_esercizi_chipgroup);
        recyclerView = v.findViewById(R.id.id_rv_esercizi);
        searchView = v.findViewById(R.id.id_searchview_esercizi);
        layout_no_esercizi = v.findViewById(R.id.id_layout_nessunesercizio);
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        //layoutManager.setReverseLayout(true);
        //layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        // DB , PRENDERE DATI E METTERE IN LIST




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

        gruppo_chips.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {

                if (checkedIds.isEmpty()){
                    adapter.impostaListaFiltrata(list);
                }else{
                   // adapter.impostaListaFiltrata(filtraListaPerTipo(checkedIds.size()));
                }
            }
        });

        // SE ELIMINO O INSERISCO UN ESERCIZIO, MOSTRO O NASCONDO LA UI NESSUN ESERCIZIO
        adapter = new EserciziAdapter(getContext(),list,db);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (list.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    layout_no_esercizi.setVisibility(View.VISIBLE);
                }else{
                    layout_no_esercizi.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
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

    private ArrayList<Esercizio> filtraListaPerTipo(int i) {
        ArrayList<Esercizio> lista_filtrata = new ArrayList<>();
         switch (((Chip) gruppo_chips.getChildAt(i)).getText().toString()) {
                case ("Petto"):{
                    for ( Esercizio esercizio : list){
                        if (esercizio.getTipo().name().equals(TipoEsercizio.esercizio_pesistica.name()) && ((EsercizioPesistica) esercizio).getGruppiMuscolari().name().equals(GruppiMuscolari.Petto.name())){
                            lista_filtrata.add(esercizio);
                        }
                    }
                }
                break;
                case ("Braccia"): {
                    for ( Esercizio esercizio : list){
                        if (esercizio.getTipo().name().equals(TipoEsercizio.esercizio_pesistica.name()) && ((EsercizioPesistica) esercizio).getGruppiMuscolari().name().equals(GruppiMuscolari.Braccia.name())){
                            lista_filtrata.add(esercizio);
                        }
                    }
                }
                break;
                case ("Schiena"): {
                    for ( Esercizio esercizio : list){
                        if (esercizio.getTipo().name().equals(TipoEsercizio.esercizio_pesistica.name()) && ((EsercizioPesistica) esercizio).getGruppiMuscolari().name().equals(GruppiMuscolari.Schiena.name())){
                            lista_filtrata.add(esercizio);
                        }
                    }
                }
                break;
                case ("Spalle"): {
                    for ( Esercizio esercizio : list){
                        if (esercizio.getTipo().name().equals(TipoEsercizio.esercizio_pesistica.name()) && ((EsercizioPesistica) esercizio).getGruppiMuscolari().name().equals(GruppiMuscolari.Spalle.name())){
                            lista_filtrata.add(esercizio);
                        }
                    }
                }
                break;
                case ("Gambe"): {
                    for ( Esercizio esercizio : list){
                        if (esercizio.getTipo().name().equals(TipoEsercizio.esercizio_pesistica.name()) && ((EsercizioPesistica) esercizio).getGruppiMuscolari().name().equals(GruppiMuscolari.Gambe.name())){
                            lista_filtrata.add(esercizio);
                        }
                    }
                }
                break;
                case ("Cardio"): {
                    for ( Esercizio esercizio : list){
                        if (esercizio.getTipo().name().equals(TipoEsercizio.esercizio_cardio.name())){
                            lista_filtrata.add(esercizio);
                        }
                    }
                }
                break;
            }
        return lista_filtrata;
    }

    @Override
    public void onStart() {
        super.onStart();
        list = db.caricaListaEserciziDaDb();
        adapter = new EserciziAdapter(getContext(),list,db);
        recyclerView.setAdapter(adapter);

        if (adapter.getItemCount() == 0){
            recyclerView.setVisibility(View.GONE);
            layout_no_esercizi.setVisibility(View.VISIBLE);
        }else{
            layout_no_esercizi.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}