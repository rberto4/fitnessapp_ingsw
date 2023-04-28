package com.ingsw.fitnessapp.oggetti;

import com.ingsw.fitnessapp.classi.GruppiMuscolari;

import java.util.Calendar;
import java.util.Date;

public class EsercizioPesistica extends Esercizio{

    int ripetizioni;
    int serie;
    int recupero;
    GruppiMuscolari gruppiMuscolari;
    float peso;

    public EsercizioPesistica(int ripetizioni, int serie, int recupero, GruppiMuscolari gruppiMuscolari, float peso) {
        this.ripetizioni = ripetizioni;
        this.serie = serie;
        this.recupero = recupero;
        this.gruppiMuscolari = gruppiMuscolari;
        this.peso = peso;
    }

    public int getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(int ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getRecupero() {
        return recupero;
    }

    public void setRecupero(int recupero) {
        this.recupero = recupero;
    }

    public GruppiMuscolari getGruppiMuscolari() {
        return gruppiMuscolari;
    }

    public void setGruppiMuscolari(GruppiMuscolari gruppiMuscolari) {
        this.gruppiMuscolari = gruppiMuscolari;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
}
