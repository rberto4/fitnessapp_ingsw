package com.ingsw.fitnessapp.oggetti;

import java.util.Calendar;
import java.util.Date;

public class EsercizioCardio extends Esercizio{

    Calendar durata;
    int difficolta;

    public EsercizioCardio(Calendar durata, int difficolta) {
        this.durata = durata;
        this.difficolta = difficolta;
    }

    public Calendar getDurata() {
        return durata;
    }

    public void setDurata(Calendar durata) {
        this.durata = durata;
    }

    public int getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(int difficolta) {
        this.difficolta = difficolta;
    }

}
