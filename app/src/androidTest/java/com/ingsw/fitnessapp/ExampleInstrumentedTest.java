package com.ingsw.fitnessapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;

import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import com.ingsw.fitnessapp.activities.MainActivity;
import com.ingsw.fitnessapp.activities.NuovoEsercizioActivity;
import com.ingsw.fitnessapp.activities.NuovoWorkoutActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest // possibile esecuzione per piu di 1000ms
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule = new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void apriSchermataNuovoEsercizio(){

        Intents.init(); // Si inizializza e si mette in "ascolto" di un eventuale intent
        onView(withId(R.id.id_menu_bottomnavigationview_esercizi)).perform(click()); // faccio un click sulla sezione esercizi
        onView(withId(R.id.id_mainactivity_fab)).perform(click()); // faccio un click sul fab

        // mi aspetto che venga richiamato l'intent che porti alla schermata NuovoEsercizioActivity

        intended(hasComponent(NuovoEsercizioActivity.class.getName()));
        Intents.release();
        // verifico l'intent tramite il nome della classe
        // Your test code goes here.
    }

    @Test
    public void apriSchermataNuovoWorkout(){
        Intents.init();
        onView(withId(R.id.id_menu_bottomnavigationview_workouts)).perform(click()); // faccio un click sulla sezione esercizi
        onView(withId(R.id.id_mainactivity_fab)).perform(click()); // faccio un click sul fab

        // mi aspetto che venga richiamato l'intent che porti alla schermata NuovoEsercizioActivity

        intended(hasComponent(NuovoWorkoutActivity.class.getName())); // verifico l'intent tramite il nome della classe
        Intents.release(); // rilascio l'intent
        // Your test code goes here.
    }
}