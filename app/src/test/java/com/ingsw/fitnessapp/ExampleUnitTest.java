package com.ingsw.fitnessapp;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.Toast;

import com.ingsw.fitnessapp.db.ClasseDatabaseOpenHelper;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    //long result=db.insert(TABLE_NAME_ESERCIZI,null,cv);

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*
    @test
    public void testdbVuoto()
    {
        assertNull(result)
        Toast.makeText(context,"errore",Toast.LENGHT_SHORT).show();
    }

    @test
    public void testdbVuoto()
    {
        assertnotNull(result)
        Toast.makeText(context,"Esercizio aggiunto correttamente", Toast.LENGHT_SHORT).show();
    }

     */
}