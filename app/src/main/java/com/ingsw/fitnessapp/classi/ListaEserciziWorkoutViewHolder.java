package com.ingsw.fitnessapp.classi;

import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.fitnessapp.R;

public class ListaEserciziWorkoutViewHolder extends RecyclerView.ViewHolder {

    TextView index_esercizio, nome_esercizio,popup;
    TextView ripetizioni,serie,peso,recupero,durata,difficolta;
    ImageView freccia;
    Button modifica;
    GridLayout grid_pesi,grid_cardio;

    public ListaEserciziWorkoutViewHolder(@NonNull View itemView) {

        super(itemView);
        index_esercizio = itemView.findViewById(R.id.id_card_esercizi_in_workout_index);
        nome_esercizio= itemView.findViewById(R.id.id_card_esercizi_in_workout_nome);
        freccia = itemView.findViewById(R.id.id_cardworkout_esercizio_freccia);
        modifica = itemView.findViewById(R.id.id_card_esercizi_in_workout_modifica);
        serie = itemView.findViewById(R.id.id_card_esercizi_in_workout_serie);
        ripetizioni = itemView.findViewById(R.id.id_card_esercizi_in_workout_ripetizioni);
        peso = itemView.findViewById(R.id.id_card_esercizi_in_workout_peso);
        recupero = itemView.findViewById(R.id.id_card_esercizi_in_workout_recupero);
        durata = itemView.findViewById(R.id.id_card_esercizi_in_workout_durata);
        difficolta = itemView.findViewById(R.id.id_card_esercizi_in_workout_difficolta);
        grid_cardio = itemView.findViewById(R.id.id_card_esercizi_in_workout_grid_cardio);
        grid_pesi = itemView.findViewById(R.id.id_card_esercizi_in_workout_grid_pesi);
        popup = itemView.findViewById(R.id.id_card_esercizi_in_workout_popup);

    }
}
