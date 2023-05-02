package com.ingsw.fitnessapp;

import static org.junit.Assert.assertEquals;

import com.ingsw.fitnessapp.classi.Tempo;

import org.junit.Test;

public class TestTempo {

    Tempo tempo = new Tempo();

    @Test
    public void testConversioneIntero(){

        // test di conversione da intero a stringa
        // da 250 secondi mi aspetto 4 minuti e 10 secondi
        int n = 250;
        assertEquals("4:11",tempo.CreaTestoFormattatoRecupero(n));
    }

    @Test
    public void testConversioneStringa(){

        // test di conversione da stringa a intero
        // da 1:45 mi aspetto 60+45= 105 secondi

        String t = "1:45";
        assertEquals(105,tempo.calcolaInteroDaTempo(t));
    }


}
