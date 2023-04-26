package com.ingsw.fitnessapp.oggetti;

import java.util.Calendar;
import java.util.Date;

public class EsercizioCardio extends Esercizio{

    Calendar durata;
    int difficoltà;

    public EsercizioCardio(Calendar durata, int difficoltà) {
        this.durata = durata;
        this.difficoltà = difficoltà;
    }

    public Calendar getDurata() {
        return durata;
    }

    public void setDurata(Calendar durata) {
        this.durata = durata;
    }

    public int getDifficoltà() {
        return difficoltà;
    }

    public void setDifficoltà(int difficoltà) {
        this.difficoltà = difficoltà;
    }

}
