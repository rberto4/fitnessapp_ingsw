package com.ingsw.fitnessapp.oggetti;

public abstract class Esercizio {

    String nome;
    boolean isFavorite = false;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

}
