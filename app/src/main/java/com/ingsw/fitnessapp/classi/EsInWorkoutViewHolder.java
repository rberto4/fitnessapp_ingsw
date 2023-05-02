package com.ingsw.fitnessapp.classi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.fitnessapp.R;

public class EsInWorkoutViewHolder extends RecyclerView.ViewHolder {

    TextView index,esercizio;
    ImageView delete,freccia;

    public EsInWorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        index = itemView.findViewById(R.id.id_workouteslist_index);
        esercizio = itemView.findViewById(R.id.id_workouteslist_esercizio);
        delete = itemView.findViewById(R.id.id_workouteslist_delete);
        freccia = itemView.findViewById(R.id.id_workouteslist_freccia);

    }
}
