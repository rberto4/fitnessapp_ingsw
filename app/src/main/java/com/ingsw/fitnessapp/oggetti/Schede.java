package com.ingsw.fitnessapp.oggetti;

import java.util.ArrayList;

public class Schede {
    ArrayList<Workout> list_workout;
    int id;
    String nome;
    //calendar datainizio;
    //calendar dataFine;
    String obiettivo;

    public Schede() {
    }

    public Schede(ArrayList<Workout> list_workout, int id, String nome, String obiettivo) {
        this.list_workout = list_workout;
        this.id = id;
        this.nome = nome;
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
