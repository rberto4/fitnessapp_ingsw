package com.ingsw.fitnessapp.classi;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.activities.NuovoEsercizioActivity;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.EsercizioCardio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;

import java.util.ArrayList;

public class EsInWorkoutAdapter extends RecyclerView.Adapter<EsInWorkoutViewHolder> {

    ArrayList<Esercizio> list_esercizi_selezionati;
    Context context;
    ClasseDatabaseOpenHelper db;
    int index = 1;
    boolean es_selezionato;

    public EsInWorkoutAdapter( Context context, ClasseDatabaseOpenHelper db) {
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
    public void onBindViewHolder(@NonNull EsInWorkoutViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.index.setText(position+1+"°");

        holder.esercizio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apriDialogEsercizi(position,holder.delete,holder.freccia,holder.esercizio);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,getItemCount());
                list_esercizi_selezionati.remove(position);
            }
        });
        modificaVistaDeleteBtn(position,holder.delete,holder.freccia);
    }


    public void modificaVistaDeleteBtn(int position, ImageView btn_delete, ImageView freccia){
        if (getItemCount() == 1){
            btn_delete.setVisibility(View.GONE);
            freccia.setVisibility(View.GONE);

        } else if (position+1 == getItemCount()){
            btn_delete.setVisibility(View.GONE);
            freccia.setVisibility(View.GONE);
        } else {
            btn_delete.setVisibility(View.VISIBLE);
            freccia.setVisibility(View.VISIBLE);
        }
    }

    public void impostaNomeSelezionato(TextView t,int position){
        if (getItemCount() == 1){
            t.setText("Aggiungi esercizio");
        }else if (position+1 == getItemCount()){
            t.setText("Aggiungi esercizio");
        }else{
            t.setText(list_esercizi_selezionati.get(position).getNome());
        }
    }

    private void apriDialogEsercizi(int i, ImageView delete, ImageView freccia, TextView esercizio_text) {
        es_selezionato = false;
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_listaesercizi_nuovoworkout);
        dialog.setCancelable(true);
        dialog.show();

        Button btn_new_esercizio = dialog.findViewById(R.id.id_dialog_nomiesercizi_btn_new);
        MaterialToolbar toolbar1 = dialog.findViewById(R.id.id_dialog_nomiesercizi_toolbar);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        SearchView searchView = dialog.findViewById(R.id.id_dialog_nomiesercizi_search);
        ListView list = dialog.findViewById(R.id.id_dialog_nomiesercizi_list);
        ArrayList<Esercizio> lista_esercizi;
        ArrayList<String> lista_esercizi_nomi = new ArrayList<>();
        lista_esercizi = db.caricaListaEserciziDaDb();

        for (Esercizio esercizio : lista_esercizi){
            switch (esercizio.getTipo().name()){
                case("esercizio_pesistica"):{
                    lista_esercizi_nomi.add(esercizio.getNome()+"\n"+
                            " • Ripetizioni: "+((EsercizioPesistica)esercizio).getRipetizioni()+"\n"+
                            " • Serie: "+((EsercizioPesistica)esercizio).getSerie()+"\n"+
                            " • Peso: "+((EsercizioPesistica)esercizio).getPeso()
                    );

                }break;
                case("esercizio_cardio"):{
                    Tempo tempo = new Tempo();
                    lista_esercizi_nomi.add(esercizio.getNome()+"\n"+
                            " • Difficoltà: "+((EsercizioCardio)esercizio).getDifficolta()+"\n"+
                            " • Durata: "+tempo.CreaTestoFormattatoDurata(((EsercizioCardio) esercizio).getDurata())+"\n"
                    );
                }break;

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, androidx.appcompat.R.layout.select_dialog_item_material,lista_esercizi_nomi);
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
                if (i == getItemCount()-1){
                    index++;
                    notifyItemInserted(i+1);
                    modificaVistaDeleteBtn(i,delete,freccia);
                    list_esercizi_selezionati.add(i,lista_esercizi.get(position));
                } else{
                    list_esercizi_selezionati.remove(i);
                    list_esercizi_selezionati.add(i,lista_esercizi.get(position));
                }
                dialog.dismiss();
            }
        });

        btn_new_esercizio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NuovoEsercizioActivity.class);
                context.startActivity(i);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public ArrayList<Esercizio> ottieniListaDiEserciziSelezionati(){
        return list_esercizi_selezionati;
    }

    @Override
    public int getItemCount() {
        return index;
    }
}
