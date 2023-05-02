package com.ingsw.fitnessapp;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;
import com.ingsw.fitnessapp.oggetti.Workout;

import org.junit.Test;

import java.util.ArrayList;

public class TestWorkout {

    // Oggetto workout
    Workout workout = new Workout();
    // creo una lista di esercizi per i test
    ArrayList<Esercizio> lista_esercizi_test = new ArrayList<>();

    @Test
    public void testInseriementoListaEserciziInWorkout() throws Exception
    {
        // inserisco un esercizio in lista e faccio il test per vedere se viene effettivamente caricato negli attributi di workout
        lista_esercizi_test.add(new EsercizioPesistica(1,1,1, GruppiMuscolari.Addome,100));
        workout.setList_esercizi(lista_esercizi_test);
        assertArrayEquals(lista_esercizi_test.toArray(),workout.getList_esercizi().toArray());
    }

    @Test
    public void testNomeWorkout() throws Exception
    {
        workout.setNome("nome");
        assertEquals("Il nome è corretto","nome",workout.getNome());
    }

    @Test
    public void testNoteWorkout() throws Exception
    {
        workout.setNote("note workout");
        assertEquals("Le note sono corrette","note workout",workout.getNote());
    }

    @Test
    public void testIdWorkout() throws Exception
    {
        workout.setId(0);
        assertEquals("id corretto",0,workout.getId());
    }

}
