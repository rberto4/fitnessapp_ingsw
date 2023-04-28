package com.ingsw.fitnessapp.oggetti;

import com.ingsw.fitnessapp.classi.GiorniSettimana;

import java.util.ArrayList;

public class Workout {

    ArrayList<Esercizio> list_esercizi;
    String nome;
    String note;
    GiorniSettimana giorniSettimana;
    int idSchedaCorrispondente;
    int id;

    public Workout(){

    }
    public Workout(ArrayList<Esercizio> list_esercizi, String nome, String note, GiorniSettimana giorniSettimana, int idSchedaCorrispondente, int id) {
        this.list_esercizi = list_esercizi;
        this.nome = nome;
        this.note = note;
        this.giorniSettimana = giorniSettimana;
        this.idSchedaCorrispondente = idSchedaCorrispondente;
        this.id = id;
    }

    public ArrayList<Esercizio> getList_esercizi() {
        return list_esercizi;
    }

    public void setList_esercizi(ArrayList<Esercizio> list_esercizi) {
        this.list_esercizi = list_esercizi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public GiorniSettimana getGiorniSettimana() {
        return giorniSettimana;
    }

    public void setGiorniSettimana(GiorniSettimana giorniSettimana) {
        this.giorniSettimana = giorniSettimana;
    }

    public int getIdSchedaCorrispondente() {
        return idSchedaCorrispondente;
    }

    public void setIdSchedaCorrispondente(int idSchedaCorrispondente) {
        this.idSchedaCorrispondente = idSchedaCorrispondente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
