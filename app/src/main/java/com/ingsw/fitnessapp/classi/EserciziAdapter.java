package com.ingsw.fitnessapp.classi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Visibility;

import com.google.android.material.transition.Hold;
import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.EsercizioCardio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;

import java.util.ArrayList;
import java.util.Calendar;

public class EserciziAdapter extends RecyclerView.Adapter<EserciziViewHolder> {


    private Context context;

    private ArrayList<Esercizio> list;
    private ClasseDatabaseOpenHelper db;

    public EserciziAdapter(Context context, ArrayList<Esercizio> list, ClasseDatabaseOpenHelper db) {

        this.context = context;
        this.list = list;
        this.db =db;

    }

    public void impostaListaFiltrata(ArrayList<Esercizio> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EserciziViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_esercizio,parent,false);
        return new EserciziViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EserciziViewHolder holder, @SuppressLint("RecyclerView") int position) {


        // attributi di Esercizio - no casting
        holder.nome.setText(list.get(position).getNome());

        if (list.get(position).isFavorite()){
            holder.favorite.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_favorite_true,context.getTheme()));
        }else{
            holder.favorite.setVisibility(View.GONE);
        }

        // CASTING
        switch (list.get(position).getTipo().name()){
            case("esercizio_pesistica") :{

                // mostrare la griglia di views relative a gli esercizi con i pesi
                holder.gridLayout_pesi.setVisibility(View.VISIBLE);
                holder.gridLayout_cardio.setVisibility(View.GONE);

                // caricamento dati da list
                holder.recupero.setText(new Tempo().CreaTestoFormattatoTempo(((EsercizioPesistica) list.get(position)).getRecupero()));
                holder.ripetizioni.setText(String.valueOf(((EsercizioPesistica) list.get(position)).getRipetizioni()));
                holder.serie.setText(String.valueOf(((EsercizioPesistica) list.get(position)).getSerie()));
                holder.peso.setText(String.valueOf(((EsercizioPesistica) list.get(position)).getPeso()));

                switch (((EsercizioPesistica) list.get(position)).getGruppiMuscolari().name()){
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

            }break;

            case("esercizio_cardio"):{
                // mostrare views relative a gli esercizi cardio

                holder.gridLayout_pesi.setVisibility(View.GONE);
                holder.gridLayout_cardio.setVisibility(View.VISIBLE);

                // caricamento dati da list

                holder.difficolta.setText(new Tempo().CreaTestoFormattatoTempo(((EsercizioCardio) list.get(position)).getDifficolta()));
                holder.difficolta.setText(String.valueOf(((EsercizioCardio) list.get(position)).getDifficolta()));
                holder.tipo.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.heart_rate,context.getTheme()));

            }break;
        }

        //
        holder.popup_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(context, holder.popup_menu);
                popup.inflate(R.menu.menu_popup);
                popup.setForceShowIcon(true);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.id_menu_popup_modifica:
                                //
                                return true;
                            case R.id.id_menu_popup_elimina:
                                eliminaEsercizio(position);
                            default:
                                return false;
                        }
                    }
                });
                popup.show();

            }
        });

    }

    private void eliminaEsercizio(int position) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Vuoi davvero elimanare l'esercizio "+list.get(position).getNome()+" dalla lista dei tuoi esercizi?");
            builder.setTitle("Elimina esercizio");
            builder.setCancelable(false);

            builder.setNegativeButton("No", (dialog, id) -> dialog.cancel());

            builder.setPositiveButton("Si, eliminalo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // DB QUERY PER ELIMINARE
                db.deleteEsercizio(list.get(position).getId());
                list.remove(position);
                notifyDataSetChanged();
            }
        });
            AlertDialog alert = builder.create();
            alert.show();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
