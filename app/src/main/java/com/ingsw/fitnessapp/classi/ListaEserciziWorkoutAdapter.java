package com.ingsw.fitnessapp.classi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.oggetti.Esercizio;

import java.util.ArrayList;

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
    public void onBindViewHolder(@NonNull ListaEserciziWorkoutViewHolder holder, int position) {
        holder.index_esercizio.setText(String.valueOf(position+1));
        holder.nome_esercizio.setText(list.get(position).getNome());

        if (getItemCount() == position){
            holder.freccia.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
