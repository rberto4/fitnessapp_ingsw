package com.ingsw.fitnessapp.classi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.fitnessapp.R;


public class WorkoutsViewHolder extends RecyclerView.ViewHolder {


    RecyclerView recyclerView;
    TextView nome_workout,giorno_settimana,popup;
    ImageView note;

    public WorkoutsViewHolder(@NonNull View itemView) {

        super(itemView);
        recyclerView = itemView.findViewById(R.id.id_rv_workouts_list_esercizi);
        nome_workout = itemView.findViewById(R.id.id_cardworkout_nome);
        giorno_settimana = itemView.findViewById(R.id.id_cardworkout_giornosettimana);
        note = itemView.findViewById(R.id.id_cardworkout_note);
        popup = itemView.findViewById(R.id.id_card_workout_popupmenu);

    }
}
