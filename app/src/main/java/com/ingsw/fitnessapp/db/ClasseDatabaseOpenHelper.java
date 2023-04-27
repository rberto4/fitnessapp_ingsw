package com.ingsw.fitnessapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.classi.TipoEsercizio;

public class ClasseDatabaseOpenHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "Test.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "esercizi";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOME = "nome_esercizio";
    private static final String COLUMN_PESO = "zavorra";
    private static final String COLUMN_SERIE = "serie";
    private static final String COLUMN_RIPETIZIONI = "ripetizioni";
    private static final String COLUMN_RECUPERO = "recupero";
    private static final String COLUMN_GRUPPO = "gruppo_muscolare";
    private static final String COLUMN_PREFERITO = "isFavourite";
    private static final String COLUMN_TIPOESERCIZIO = "tipo_esercizio";
    private static final String COLUMN_DIFFICOLTA = "difficolta";
    private static final String COLUMN_DURATA = "durata";



    public ClasseDatabaseOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creiamo una tabella unica contente tutti gli attributi sia di tipo pesistica che gli altri in
        // in sostanza teniamo il padre e eliminiamo i figli nella gerarchia
        // per la colonna preferito sicccome il tipo boolean non esiste definiamo un check contenente 0 e 1
        // dove 1 sta per true e 0 sta per false

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_PREFERITO + " INTEGER CHECK( " + COLUMN_PREFERITO + " IN(0, 1)) NOT NULL DEFAULT 0, " +
                COLUMN_TIPOESERCIZIO + " TEXT CHECK( " + COLUMN_TIPOESERCIZIO + " IN('" + TipoEsercizio.esercizio_cardio.name()
                                                                                +"','" + TipoEsercizio.esercizio_pesistica.name()
                                                                                +"')) NOT NULL DEFAULT " + TipoEsercizio.esercizio_pesistica.name() + ", " +
                COLUMN_PESO + " INTEGER, " +
                COLUMN_DIFFICOLTA + " INTEGER, " +
                COLUMN_DURATA + " TEXT, " +
                COLUMN_SERIE + " INTEGER, " +
                COLUMN_RIPETIZIONI + " INTEGER, " +
                COLUMN_RECUPERO + " TEXT, " +
                COLUMN_GRUPPO + " TEXT CHECK( " + COLUMN_GRUPPO + " IN ('" + GruppiMuscolari.Petto.name()
                                                                + "','" + GruppiMuscolari.Spalle.name()
                                                                + "','" + GruppiMuscolari.Braccia.name()
                                                                + "','" + GruppiMuscolari.Schiena.name()
                                                                + "','" + GruppiMuscolari.Addome.name()
                                                                + "','" + GruppiMuscolari.Gambe.name()
                                                                + "')));";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addEsercizio(String nome, int zavorra, int ripetizioni){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // inserisco alcuni valori di test, poi bisogna recuperare tutti i dati dall'applicazione
        int t = 1;
        int t2 = 10;
        String t3 = "petto";
        cv.put(COLUMN_NOME, nome);
        cv.put(COLUMN_PREFERITO, t);
        cv.put(COLUMN_PESO, zavorra);
        cv.put(COLUMN_SERIE, t2);
        cv.put(COLUMN_RIPETIZIONI, ripetizioni);
        cv.put(COLUMN_GRUPPO, t3);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Aggiunto correttamente!", Toast.LENGTH_SHORT).show();
        }
    }

    // creare due metodi read data uno per gli esercizi di tipo pesistica e uno per gli altri

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
