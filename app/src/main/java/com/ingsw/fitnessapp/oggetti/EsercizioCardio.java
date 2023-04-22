package com.ingsw.fitnessapp.oggetti;

import java.util.Date;

public class EsercizioCardio extends Esercizio{

    Date durata;
    int difficoltà;

    public EsercizioCardio(Date durata, int difficoltà) {
        this.durata = durata;
        this.difficoltà = difficoltà;
    }

    public Date getDurata() {
        return durata;
    }

    public void setDurata(Date durata) {
        this.durata = durata;
    }

    public int getDifficoltà() {
        return difficoltà;
    }

    public void setDifficoltà(int difficoltà) {
        this.difficoltà = difficoltà;
    }

}
