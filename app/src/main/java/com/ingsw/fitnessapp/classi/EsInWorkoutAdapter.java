package com.ingsw.fitnessapp.classi;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Esercizio;

import java.util.ArrayList;

public class EsInWorkoutAdapter extends RecyclerView.Adapter<EsInWorkoutViewHolder> {

    ArrayList<Integer> list;
    ArrayList<Esercizio> list_esercizi_selezionati;
    Context context;
    ClasseDatabaseOpenHelper db;
    String selected;
    public EsInWorkoutAdapter(ArrayList<Integer> list, Context context, ClasseDatabaseOpenHelper db) {
        this.list = list;
        this.context = context;
        this.db = db;
        list_esercizi_selezionati = new ArrayList<>();

    }
    @NonNull
    @Override
    public EsInWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_nuovo_esercizio_in_workout,parent,false);
        return new EsInWorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EsInWorkoutViewHolder holder, int position) {
        holder.esercizio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position)==getItemCount()-1){
                    list.add(getItemCount());
                    holder.delete.setVisibility(View.VISIBLE);
                    holder.esercizio.setText(apriDialogEsercizi());
                }else{
                    apriDialogEsercizi();
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove
            }
        });
        holder.index.setText(list.get(position)+1+"°");
    }

    private String apriDialogEsercizi() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_listaesercizi_nuovoworkout);
        dialog.setCancelable(true);
        dialog.show();

        MaterialToolbar toolbar1 = dialog.findViewById(R.id.id_dialog_nomiesercizi_toolbar);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        SearchView searchView = dialog.findViewById(R.id.id_dialog_nomiesercizi_search);
        ListView list = dialog.findViewById(R.id.id_dialog_nomiesercizi_list);

        ArrayList<Esercizio> lista_esercizi = new ArrayList<>();
        ArrayList<String> lista_esercizi_nomi = new ArrayList<>();
        lista_esercizi = db.caricaListaEserciziDaDb();

        for (Esercizio esercizio : lista_esercizi){
            lista_esercizi_nomi.add(esercizio.getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,lista_esercizi_nomi);
        list.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position).toString();
                dialog.dismiss();
            }
        });

       return selected;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
