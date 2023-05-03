package com.ingsw.fitnessapp.classi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Workout;

import java.util.ArrayList;


public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsViewHolder> {

    ArrayList<Workout> list;
    Context context;
    ListaEserciziWorkoutAdapter adapter;

    ClasseDatabaseOpenHelper db;

    public WorkoutsAdapter(Context context, ArrayList<Workout> list, ClasseDatabaseOpenHelper db) {
        this.list = list;
        this.context = context;
        this.db = db;

    }

    @NonNull
    @Override
    public WorkoutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_workouts,parent,false);
        return new WorkoutsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nome_workout.setText(list.get(position).getNome());
        holder.giorno_settimana.setText(list.get(position).getGiorniSettimana().name());
        holder.note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apriDialogNota(list.get(position).getNote(), position);
            }
        });
        adapter = new ListaEserciziWorkoutAdapter(list.get(position).getList_esercizi(), context);
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
                        if(item.getItemId() == R.id.id_menu_popup_elimina){

                            // elimina workout
                            eliminaWorkout(position);

                            return true;
                        }else {
                            return false;
                        }
                    }
                });
                popup.show();
            }
        });
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

    private void apriDialogNota(String note,int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(note);
        builder.setTitle("Note allenamento");
        builder.setCancelable(true);
        builder.setPositiveButton("Chiudi", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();

        alert.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
