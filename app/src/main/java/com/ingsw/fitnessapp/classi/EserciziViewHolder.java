package com.ingsw.fitnessapp.classi;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.fitnessapp.R;


public class EserciziViewHolder extends RecyclerView.ViewHolder {


    TextView nome,ripetizioni,peso,recupero,serie,popup_menu,difficolta,durata;
    ImageView favorite,tipo;
    GridLayout gridLayout_pesi,gridLayout_cardio;

    public EserciziViewHolder(@NonNull View itemView) {

        super(itemView);
        nome = itemView.findViewById(R.id.id_card_esercizio_nome);
        ripetizioni = itemView.findViewById(R.id.id_card_esercizio_ripetizioni);
        recupero = itemView.findViewById(R.id.id_card_esercizio_recupero);
        serie = itemView.findViewById(R.id.id_card_esercizio_serie);
        peso = itemView.findViewById(R.id.id_card_esercizio_peso);
        favorite = itemView.findViewById(R.id.id_card_esercizio_favorite);
        tipo = itemView.findViewById(R.id.id_card_esercizio_icona_tipo);
        popup_menu = itemView.findViewById(R.id.id_card_esercizio_popupmenu);
        difficolta = itemView.findViewById(R.id.id_card_esercizio_difficolta);
        durata = itemView.findViewById(R.id.id_card_esercizio_durata);
        gridLayout_cardio = itemView.findViewById(R.id.id_card_esercizio_gridlayout_cardio);
        gridLayout_pesi = itemView.findViewById(R.id.id_card_esercizio_gridlayout_pesi);


    }
}
