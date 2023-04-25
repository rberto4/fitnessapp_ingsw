package com.ingsw.fitnessapp.classi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.fitnessapp.R;

public class ListaEserciziWorkoutViewHolder extends RecyclerView.ViewHolder {

    TextView index_esercizio, nome_esercizio;
    ImageView freccia;

    public ListaEserciziWorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        index_esercizio = itemView.findViewById(R.id.id_cardworkout_esercizio_index);
        nome_esercizio= itemView.findViewById(R.id.id_cardworkout_esercizio_nome);
        freccia = itemView.findViewById(R.id.id_cardworkout_esercizio_freccia);
    }
}
