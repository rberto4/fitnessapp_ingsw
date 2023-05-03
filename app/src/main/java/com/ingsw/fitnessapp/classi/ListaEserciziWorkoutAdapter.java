package com.ingsw.fitnessapp.classi;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
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
import java.util.Calendar;

public class ListaEserciziWorkoutAdapter extends RecyclerView.Adapter<ListaEserciziWorkoutViewHolder> {

    ArrayList<Esercizio> list;
    Context context;
    ClasseDatabaseOpenHelper db;


    public ListaEserciziWorkoutAdapter(ArrayList<Esercizio> list, Context context, ClasseDatabaseOpenHelper db) {
        this.list = list;
        this.context = context;
        this.db = db;
    }

    @NonNull
    @Override
    public ListaEserciziWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_esercizi_workouts,parent,false);
        return new ListaEserciziWorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEserciziWorkoutViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // views senza casting

        holder.index_esercizio.setText(String.valueOf(position+1)+"°");
        holder.nome_esercizio.setText(list.get(position).getNome());

        // casting

        switch (list.get(position).getTipo().name()){
            case("esercizio_pesistica"):{
                // NASCONDO griglia di views di cardio e mostro gli attributi per gli esercizi pesi
                holder.grid_pesi.setVisibility(View.VISIBLE);
                holder.grid_cardio.setVisibility(View.GONE);

                holder.ripetizioni.setText(String.valueOf(((EsercizioPesistica) list.get(position)).getRipetizioni()));
                holder.peso.setText(String.valueOf(((EsercizioPesistica) list.get(position)).getPeso()));
                holder.serie.setText(String.valueOf(((EsercizioPesistica) list.get(position)).getSerie() + " serie"));
                holder.recupero.setText(new Tempo().CreaTestoFormattatoRecupero(((EsercizioPesistica) list.get(position)).getRecupero()));

            }break;

            case("esercizio_cardio"):{
                // faccio il contrario ...
                holder.grid_pesi.setVisibility(View.GONE);
                holder.grid_cardio.setVisibility(View.VISIBLE);
                holder.difficolta.setText(String.valueOf(((EsercizioCardio) list.get(position)).getDifficolta()));
                holder.durata.setText(new Tempo().CreaTestoFormattatoDurata(((EsercizioCardio) list.get(position)).getDurata()));


            }break;

        }
        // nascondo la freccia dell'ultimo esercizio in lista
        if (getItemCount() == position+1){
            holder.modifica.setVisibility(View.VISIBLE);
        }

        holder.modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //apriDialogEsercizi();
            }
        });

        holder.popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(context, holder.popup);
                popup.inflate(R.menu.menu_popup);
                popup.setForceShowIcon(true);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.id_menu_popup_modifica:
                                modficaEsercizio(position);
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

    private void modficaEsercizio(int position) {
        Intent i = new Intent(context, NuovoEsercizioActivity.class);
        i.putExtra("id", list.get(position).getId());
        i.putExtra("nome", list.get(position).getNome());
        i.putExtra("favorite",list.get(position).isFavorite());
        i.putExtra("tipo",list.get(position).getTipo());

        switch (list.get(position).getTipo().name()){
            case("esercizio_pesistica"):{

                i.putExtra("gruppo_muscolare", ((EsercizioPesistica) list.get(position)).getGruppiMuscolari());
                i.putExtra("ripetizioni", ((EsercizioPesistica) list.get(position)).getRipetizioni());
                i.putExtra("peso", ((EsercizioPesistica) list.get(position)).getPeso());
                i.putExtra("recupero", ((EsercizioPesistica) list.get(position)).getRecupero());
                i.putExtra("serie", ((EsercizioPesistica) list.get(position)).getSerie());

            }break;

            case("esercizio_cardio"):{

                i.putExtra("durata", ((EsercizioCardio) list.get(position)).getDurata());
                i.putExtra("difficoltà", ((EsercizioCardio) list.get(position)).getDifficolta());

            }break;
        }
        context.startActivity(i);
    }

    private void eliminaEsercizio(int position){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Vuoi davvero rimuovere l'esercizio "+list.get(position).getNome()+" dalla lista di esercizi dell'allenamento?");
        builder.setTitle("Rimuovi esercizio da workout");
        builder.setCancelable(false);

        builder.setNegativeButton("No", (dialog, id) -> dialog.cancel());

        builder.setPositiveButton("Si, rimuovilo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                // DB QUERY PER ELIMINARE

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
