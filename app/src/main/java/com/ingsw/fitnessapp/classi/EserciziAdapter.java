package com.ingsw.fitnessapp.classi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.transition.Hold;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.EsercizioCardio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;

import java.util.ArrayList;
import java.util.Calendar;

public class EserciziAdapter extends RecyclerView.Adapter<EserciziViewHolder> {


    private Context context;

    private ArrayList<EsercizioPesistica> list_pesistica;
    private ArrayList<EsercizioCardio> list_cardio;

    public EserciziAdapter(Context context, ArrayList<EsercizioPesistica> list_pesistica, ArrayList<EsercizioCardio> list_cardio) {
        this.context = context;
        this.list_pesistica = list_pesistica;
        this.list_cardio = list_cardio;


    }

    @NonNull
    @Override
    public EserciziViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_esercizio,parent,false);
        return new EserciziViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EserciziViewHolder holder, int position) {


        holder.nome.setText(list_pesistica.get(position).getNome());
        holder.recupero.setText(String.valueOf(list_pesistica.get(position).getRecupero().get(Calendar.MINUTE) + ":" + list_pesistica.get(position).getRecupero().get(Calendar.SECOND)));
        holder.ripetizioni.setText(String.valueOf(list_pesistica.get(position).getRipetizioni()));
        holder.serie.setText(String.valueOf(list_pesistica.get(position).getSerie()));
        holder.peso.setText(String.valueOf(list_pesistica.get(position).getPeso()));

        if (list_pesistica.get(position).isFavorite()){
            holder.favorite.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_favorite_true,context.getTheme()));
        }else{
            holder.favorite.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_favorite_false,context.getTheme()));
        }

        switch (list_pesistica.get(position).getGruppiMuscolari().name()){
            case ("Braccia"):{
                holder.tipo.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.arm_muscle,context.getTheme()));
            }break;
            case ("Addome"): {
                holder.tipo.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.muscle,context.getTheme()));
            }break;
            case ("Petto"):{
                holder.tipo.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.muscle,context.getTheme()));
            }break;
            case ("Gambe"):{
                holder.tipo.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.leg,context.getTheme()));
            }break;
            case ("Schiena"):{
                holder.tipo.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.back,context.getTheme()));

            }break;
        }

    }

    @Override
    public int getItemCount() {
        return list_pesistica.size()+list_cardio.size();
    }
}
