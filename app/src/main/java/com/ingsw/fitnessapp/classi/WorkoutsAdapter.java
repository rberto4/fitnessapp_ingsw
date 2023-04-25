package com.ingsw.fitnessapp.classi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.fitnessapp.R;
import com.ingsw.fitnessapp.oggetti.Workout;

import java.util.ArrayList;


public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsViewHolder> {

    ArrayList<Workout> list;
    Context context;
    ListaEserciziWorkoutAdapter adapter;

    public WorkoutsAdapter(Context context, ArrayList<Workout> list) {
        this.list = list;
        this.context = context;

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
                apriDialogNota(list.get(position).getNote());
            }
        });
        adapter = new ListaEserciziWorkoutAdapter(list.get(position).getList_esercizi(),context);
        holder.recyclerView.setAdapter(adapter);
    }

    private void apriDialogNota(String note) {
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
