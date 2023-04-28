package com.ingsw.fitnessapp.oggetti;

import java.util.Calendar;
import java.util.Date;

public class EsercizioCardio extends Esercizio{

    int durata;
    int difficolta;

    public EsercizioCardio(int durata, int difficolta) {
        this.durata = durata;
        this.difficolta = difficolta;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public int getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(int difficolta) {
        this.difficolta = difficolta;
    }

}
