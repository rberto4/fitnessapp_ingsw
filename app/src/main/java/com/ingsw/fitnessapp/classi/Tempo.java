package com.ingsw.fitnessapp.classi;

public class Tempo {

    public Tempo() {

    }

    public String CreaTestoFormattatoRecupero(int d) {
        int minuti = 0;

        if (d >= 60) {
            while (d >= 60) {
                minuti++;
                d = d-60;
            }
            if (d<10){
                return minuti + ":0" + d;
            }else{
                return minuti + ":" + d;
            }

        } else {
            if (d<10){
                return "00:0" + d;
            }else{
                return "00" + ":" + d;
            }
        }
    }

    public int calcolaInteroDaTempo(String s)
    {
        int n = 0;
        String[] split = s.split(":");
        n  = Integer.parseInt(split[0])*60;
        n = n + Integer.parseInt(split[1]);
        return n;
    }

    public String CreaTestoFormattatoDurata(int d) {
        int minuti = 0;

        if (d >= 60) {
            while (d >= 60) {
                minuti++;
                d = d-60;
            }
            if (d<10){
                return minuti + ":0" + d;
            }else if (d>=10){
                return minuti + ":" + d;
            }else if(d == 0){
                if (minuti == 1){
                    return minuti + " minuto";
                }else{
                    return minuti + " minuti";
                }
            }

        } else {
            if (d<10){
                return "00:0" + d;
            }else{
                return "00" + ":" + d;
            }
        }
        return null;
    }
}
