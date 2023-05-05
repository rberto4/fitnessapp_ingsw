package com.ingsw.fitnessapp.classi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.activities.NuovoEsercizioActivity;
import com.ingsw.fitnessapp.activities.NuovoWorkoutActivity;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.EsercizioCardio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;
import com.ingsw.fitnessapp.oggetti.Workout;

import java.util.ArrayList;
import java.util.List;


public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsViewHolder> {

    ArrayList<Workout> list;
    ArrayList<Workout> list_selected;
    ArrayList<Workout> list_total;

    Context context;
    ListaEserciziWorkoutAdapter adapter;
    ClasseDatabaseOpenHelper db;

    boolean showCheckbox;

    public WorkoutsAdapter(Context context, ArrayList<Workout> list, ClasseDatabaseOpenHelper db, Boolean showCheckbox) {
        this.list = list;
        this.context = context;
        this.db = db;
        this.showCheckbox = showCheckbox;
        if(showCheckbox){
            list_selected = new ArrayList<>();
            list_total = db.caricaListaWorkoutDaDb();
        }
    }

    @NonNull
    @Override
    public WorkoutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_workouts,parent,false);
        return new WorkoutsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(showCheckbox){
            holder.checkBox.setVisibility(View.VISIBLE);
            statoCheckbox(holder.checkBox,position);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (!list_selected.contains(list.get(position))){
                        list_selected.add(list.get(position));
                    }
                }else{
                    if (list_selected.contains(list.get(position))){
                        list_selected.remove(list.get(position));
                    }
                }
            }
        });

        holder.nome_workout.setText(list.get(position).getNome());
        holder.giorno_settimana.setText(list.get(position).getGiorniSettimana().name());
        holder.note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apriDialogNota(list.get(position).getNote(), position);
            }
        });
        adapter = new ListaEserciziWorkoutAdapter(list.get(position).getList_esercizi(), context,db);
        holder.recyclerView.setAdapter(adapter);

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
                                modificaWorkout(position);
                                return true;
                            case R.id.id_menu_popup_elimina:
                                eliminaWorkout(position);
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });

    }

    private void statoCheckbox(CheckBox c,int p) {
        if (list_selected.isEmpty()){
            c.setChecked(false);
        }else{
            if (list_selected.contains(list.get(p))){
                c.setChecked(true);
            }else{
                c.setChecked(false);
            }
        }
    }


    private void eliminaWorkout(int p) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Vuoi davvero elimanare il workout "+list.get(p).getNome()+" dalla lista dei tuoi workouts?");
            builder.setTitle("Elimina workout");
            builder.setCancelable(false);

            builder.setNegativeButton("No", (dialog, id) -> dialog.cancel());

            builder.setPositiveButton("Si, eliminalo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    db.deleteWorkout(list.get(p).getId());
                    list.remove(p);
                    notifyItemRemoved(p);
                    notifyItemRangeChanged(p,getItemCount());
                }
            });
            AlertDialog alert = builder.create();
            alert.show();


    }

    public ArrayList<Workout> restituisciWorkoutSelezionati(){
        return list_selected;
    }
    private void modificaWorkout(int p){
        Intent i = new Intent(context, NuovoWorkoutActivity.class);
        i.putExtra("id", list.get(p).getId());
        i.putExtra("nome", list.get(p).getNome());
        i.putExtra("note", list.get(p).getNote());
        i.putExtra("giorno_settimana", list.get(p).getGiorniSettimana());
        int[] array_id = new int[list.get(p).getList_esercizi().size()];
        for(int j=0; j<list.get(p).getList_esercizi().size(); j++){
            array_id[j] = list.get(p).getList_esercizi().get(j).getId();
            Log.d("controllo0",array_id[j]+"");
        }
        i.putExtra("array_es",array_id);
        context.startActivity(i);
    }

    private void apriDialogNota(String note,int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(note);
        builder.setTitle("Note allenamento");
        builder.setCancelable(true);
        builder.setPositiveButton("Chiudi", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();

        alert.show();
    }

    public void filtraPerGiornoSettimana(ArrayList<Workout> lista_filtrata){
        this.list = lista_filtrata;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
