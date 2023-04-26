package com.ingsw.fitnessapp.oggetti;

import com.ingsw.fitnessapp.classi.TipoEsercizio;

public abstract class Esercizio {

    String nome;
    TipoEsercizio tipo;
    boolean isFavorite;

    // attributo necessario per le recyclerview - controllare UML

    public TipoEsercizio getTipo() {
        return tipo;
    }

    public void setTipo(TipoEsercizio tipo) {
        this.tipo = tipo;
    }

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
