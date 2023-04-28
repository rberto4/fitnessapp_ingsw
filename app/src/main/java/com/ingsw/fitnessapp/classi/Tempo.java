package com.ingsw.fitnessapp.classi;

public class Tempo {

    public Tempo() {
    }

    public String CreaTestoFormattatoTempo(int d) {
        int minuti = 0;
        if (d >= 60) {
            while (d >= 60) {
                minuti++;
                d = d-60;
            }
            return minuti + ":" + d;
        } else {
            return "00:" + d;
        }
    }
}
