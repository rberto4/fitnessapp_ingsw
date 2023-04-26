package com.ingsw.fitnessapp.classi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.EsercizioCardio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;

import java.util.ArrayList;
import java.util.Calendar;

public class ListaEserciziWorkoutAdapter extends RecyclerView.Adapter<ListaEserciziWorkoutViewHolder> {

    ArrayList<Esercizio> list;
    Context context;


    public ListaEserciziWorkoutAdapter(ArrayList<Esercizio> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaEserciziWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_esercizi_workouts,parent,false);
        return new ListaEserciziWorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEserciziWorkoutViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.index_esercizio.setText(String.valueOf(position+1));
        holder.nome_esercizio.setText(list.get(position).getNome());
        holder.nome_esercizio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dettagliEsercizio(list.get(position));
            }
        });

        // nascondo la freccia dell'ultimo esercizio in lista
        if (getItemCount() == position+1){
            holder.freccia.setVisibility(View.GONE);
        }


    }

    private void dettagliEsercizio(Esercizio esercizio) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Reps: " + ((EsercizioPesistica) esercizio).getRipetizioni() +"\n"+
                    "Set: " + ((EsercizioPesistica) esercizio).getSerie() +"\n"+
                    "Peso: " + ((EsercizioPesistica) esercizio).getPeso() +"\n"+
                    "Recupero: " + ((EsercizioPesistica) esercizio).getRecupero().get(Calendar.MINUTE)+":"+((EsercizioPesistica) esercizio).getRecupero().get(Calendar.SECOND)+"\n");
            builder.setTitle(esercizio.getNome());
            builder.setCancelable(true);
            builder.setPositiveButton("Chiudi", (dialog, id) -> dialog.cancel());
            AlertDialog alert = builder.create();
            alert.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
