package com.ingsw.fitnessapp.oggetti;

import java.util.ArrayList;

public class Schede {
    ArrayList<Workout> list_workout;
    int id;
    String nome;
    long dataInizio;
    long dataFine;
    String obiettivo;

    public Schede() {
    }

    public Schede(ArrayList<Workout> list_workout, int id, String nome, long dataInizio, long dataFine, String obiettivo) {
        this.list_workout = list_workout;
        this.id = id;
        this.nome = nome;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.obiettivo = obiettivo;
    }
    public Schede(ArrayList<Workout> list_workout, String nome, long dataInizio, long dataFine, String obiettivo) {
        this.list_workout = list_workout;
        this.nome = nome;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.obiettivo = obiettivo;
    }

    public ArrayList<Workout> getList_workout() {
        return list_workout;
    }

    public void setList_workout(ArrayList<Workout> list_workout) {
        this.list_workout = list_workout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public long getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(long datainizio) {
        this.dataInizio = datainizio;
    }

    public long getDataFine() {
        return dataFine;
    }

    public void setDataFine(long dataFine) {
        this.dataFine = dataFine;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObiettivo() {
        return obiettivo;
    }

    public void setObiettivo(String obiettivo) {
        this.obiettivo = obiettivo;
    }
}
