package com.ingsw.fitnessapp;

import org.junit.Test;
import org.junit.runner.manipulation.Ordering;

import static org.junit.Assert.*;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.classi.TipoEsercizio;
import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    //long result=db.insert(TABLE_NAME_ESERCIZI,null,cv);
    Context context;
    EsercizioPesistica esercizio_pesistica = new EsercizioPesistica(1,1,60,GruppiMuscolari.Addome,100);
    // parametri di test
    String string_nome = "test";
    boolean favorite = true;
    TipoEsercizio tipoEsercizio = TipoEsercizio.esercizio_pesistica;
    int reps = 1;
    int serie = 1;
    int recupero = 60;
    GruppiMuscolari gruppiMuscolari = GruppiMuscolari.Addome;
    float peso = 100;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

    }

   @Test
    public void testNomeEsercizio() throws Exception{
       esercizio_pesistica.setNome("test");
       assertEquals(string_nome,esercizio_pesistica.getNome());
   }
    @Test
    public void testFavoriteEsercizio() throws Exception{
        esercizio_pesistica.setFavorite(true);
        assertEquals(favorite,esercizio_pesistica.isFavorite());
    }
    @Test
    public void testTipoEsercizio() throws Exception {
        esercizio_pesistica.setTipo(TipoEsercizio.esercizio_pesistica);
        assertEquals(tipoEsercizio, esercizio_pesistica.getTipo());
    }

    @Test
    public void testRepsEsercizio() throws Exception {
        assertEquals(reps, esercizio_pesistica.getRipetizioni());
    }
    @Test
    public void testSerieEsercizio() throws Exception {
        assertEquals(serie, esercizio_pesistica.getSerie());

    }
    @Test
    public void testRecuperoEsercizio() throws Exception {
        assertEquals(recupero, esercizio_pesistica.getRecupero());


    }
    @Test
    public void testGruppiMuscolariEsercizio() throws Exception {
        assertEquals(gruppiMuscolari, esercizio_pesistica.getGruppiMuscolari());
    }

    @Test
    public void testPesoEsercizio() throws Exception {
        assertEquals(peso, esercizio_pesistica.getPeso(),0);
    }

}