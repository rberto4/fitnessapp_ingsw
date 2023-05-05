package com.ingsw.fitnessapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ingsw.fitnessapp.classi.GiorniSettimana;
import com.ingsw.fitnessapp.classi.GruppiMuscolari;
import com.ingsw.fitnessapp.classi.TipoEsercizio;
import com.ingsw.fitnessapp.oggetti.Esercizio;
import com.ingsw.fitnessapp.oggetti.EsercizioCardio;
import com.ingsw.fitnessapp.oggetti.EsercizioPesistica;
import com.ingsw.fitnessapp.oggetti.Schede;
import com.ingsw.fitnessapp.oggetti.Workout;

import java.util.ArrayList;


public class ClasseDatabaseOpenHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "Test.db";
    private static final int DATABASE_VERSION = 1;

    //lista degli attributi della tabella esercizi
    private static final String TABLE_NAME_ESERCIZI = "esercizi";
    private static final String COLUMN_ID_ESERCIZIO= "_id";
    private static final String COLUMN_NOME_ESERCIZIO = "nome_esercizio";
    private static final String COLUMN_PESO = "zavorra";
    private static final String COLUMN_SERIE = "serie";
    private static final String COLUMN_RIPETIZIONI = "ripetizioni";
    private static final String COLUMN_RECUPERO = "recupero";
    private static final String COLUMN_GRUPPO = "gruppo_muscolare";
    private static final String COLUMN_PREFERITO = "isFavourite";
    private static final String COLUMN_TIPOESERCIZIO = "tipo_esercizio";
    private static final String COLUMN_DIFFICOLTA = "difficolta";
    private static final String COLUMN_DURATA = "durata";

    //Lista degli attributi della tabella workout
    private static final String TABLE_NAME_WORKOUT = "workout";
    private static final String COLUMN_ID_WORKOUT = "_id";
    private static final String COLUMN_NOME_WORKOUT = "nome_workout";
    private static final String COLUMN_GIORNI = "giorni";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_ID_SCHEDA_CORRISPONDENTE = "scheda_corrispondente";

    //Lista degli attributi della tabella di supporto
    private static final String TABLE_NAME_SUPPORTO = "supporto";
    private static final String COLUMN_ID_ESERCIZIO_SUPPORTO= "_id_esercizio";
    private static final String COLUMN_ID_WORKOUT_SUPPORTO= "_id_workout";

    //Lista degli attributi della tabella schede
    private static final String TABLE_NAME_SCHEDE = "schede";
    private static final String COLUMN_ID_SCHEDE = "_id";
    private static final String COLUMN_NOME_SCHEDE = "nome_schede";

    private static final String COLUMN_DATA_INIZIO = "data_inizio";
    private static final String COLUMN_DATA_FINE= "data_fine";
    private static final String COLUMN_OBIETTIVO = "obiettivo";

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

        String tabellaEsercizio = "CREATE TABLE " + TABLE_NAME_ESERCIZI + " (" +
                COLUMN_ID_ESERCIZIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME_ESERCIZIO + " TEXT, " +
                COLUMN_PREFERITO + " INTEGER CHECK( " + COLUMN_PREFERITO + " IN(0, 1)) NOT NULL DEFAULT 0, " +
                COLUMN_TIPOESERCIZIO + " TEXT CHECK( " + COLUMN_TIPOESERCIZIO + " IN('" + TipoEsercizio.esercizio_cardio.name()
                                                                                +"','" + TipoEsercizio.esercizio_pesistica.name()
                                                                                +"')) NOT NULL DEFAULT " + TipoEsercizio.esercizio_pesistica.name() + ", " +
                COLUMN_PESO + " INTEGER, " +
                COLUMN_DIFFICOLTA + " INTEGER, " +
                COLUMN_DURATA + " INTEGER, " +
                COLUMN_SERIE + " INTEGER, " +
                COLUMN_RIPETIZIONI + " INTEGER, " +
                COLUMN_RECUPERO + " INTEGER, " +
                COLUMN_GRUPPO + " TEXT CHECK( " + COLUMN_GRUPPO + " IN ('" + GruppiMuscolari.Petto.name()
                                                                + "','" + GruppiMuscolari.Spalle.name()
                                                                + "','" + GruppiMuscolari.Braccia.name()
                                                                + "','" + GruppiMuscolari.Schiena.name()
                                                                + "','" + GruppiMuscolari.Addome.name()
                                                                + "','" + GruppiMuscolari.Gambe.name()
                                                                + "')));";


        String tabellaWorkout = "CREATE TABLE " + TABLE_NAME_WORKOUT + " (" +
                COLUMN_ID_WORKOUT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME_WORKOUT + " TEXT, " +
                COLUMN_NOTE + " INTEGER, " +
                COLUMN_GIORNI + " TEXT CHECK( " + COLUMN_GIORNI + " IN ('" + GiorniSettimana.Lunedi.name()
                                                                + "','" + GiorniSettimana.Martedi.name()
                                                                + "','" + GiorniSettimana.Mercoledi.name()
                                                                + "','" + GiorniSettimana.Giovedi.name()
                                                                + "','" + GiorniSettimana.Venerdi.name()
                                                                + "','" + GiorniSettimana.Sabato.name()
                                                                + "','" + GiorniSettimana.Domenica.name()
                                                                + "')), " +
                COLUMN_ID_SCHEDA_CORRISPONDENTE + " INTEGER, " +
                "FOREIGN KEY ("+ COLUMN_ID_SCHEDA_CORRISPONDENTE +") REFERENCES " + TABLE_NAME_SCHEDE + " ("+ COLUMN_ID_SCHEDE +"));";


        String tabellaSupporto = "CREATE TABLE " + TABLE_NAME_SUPPORTO + " (" +
                COLUMN_ID_ESERCIZIO_SUPPORTO + " INTEGER , " +
                COLUMN_ID_WORKOUT_SUPPORTO + " INTEGER , " +
                "PRIMARY KEY(" + COLUMN_ID_ESERCIZIO_SUPPORTO + " , " + COLUMN_ID_WORKOUT_SUPPORTO + ")," +
                "FOREIGN KEY ("+ COLUMN_ID_ESERCIZIO_SUPPORTO +") REFERENCES " + TABLE_NAME_ESERCIZI + " ("+ COLUMN_ID_ESERCIZIO +")," +
                "FOREIGN KEY ("+ COLUMN_ID_WORKOUT_SUPPORTO +") REFERENCES " + TABLE_NAME_WORKOUT + " ("+ COLUMN_ID_WORKOUT +"));";



        String tabellaSchede = "CREATE TABLE " + TABLE_NAME_SCHEDE+ " (" +
                COLUMN_ID_SCHEDE+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME_SCHEDE + " TEXT, " +
                COLUMN_DATA_INIZIO + " INTEGER, " +
                COLUMN_DATA_FINE + " INTEGER, " +
                COLUMN_OBIETTIVO + " TEXT); ";

        db.execSQL(tabellaSchede);
        db.execSQL(tabellaEsercizio);
        db.execSQL(tabellaWorkout);
        db.execSQL((tabellaSupporto));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ESERCIZI);
        onCreate(db);
    }


    public ContentValues caricaCvEsercizio(Esercizio esercizio){
        ContentValues cv = new ContentValues();

        //Lista di attributi da caricare nel cv standard per entrambi i tipi di esercizio
        cv.put(COLUMN_NOME_ESERCIZIO, esercizio.getNome());
        cv.put(COLUMN_PREFERITO, esercizio.isFavorite());
        cv.put(COLUMN_TIPOESERCIZIO, esercizio.getTipo().name());

        //In base al tipo di esercizio carico i dati adeguati
        switch (esercizio.getTipo().name()){

            case("esercizio_pesistica"):
            {
                cv.put(COLUMN_PESO, ((EsercizioPesistica) esercizio).getPeso());
                cv.put(COLUMN_SERIE, ((EsercizioPesistica) esercizio).getSerie());
                cv.put(COLUMN_RIPETIZIONI, ((EsercizioPesistica) esercizio).getRipetizioni());
                cv.put(COLUMN_RECUPERO, ((EsercizioPesistica) esercizio).getRecupero());
                cv.put(COLUMN_GRUPPO, ((EsercizioPesistica) esercizio).getGruppiMuscolari().name());

            }break;

            case("esercizio_cardio"):
            {
                cv.put(COLUMN_DIFFICOLTA, ((EsercizioCardio) esercizio).getDifficolta());
                cv.put(COLUMN_DURATA, ((EsercizioCardio) esercizio).getDurata());

            }break;
        }
        return cv;
    }

    public void aggiungiEsercizioAlDb(Esercizio esercizio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = caricaCvEsercizio(esercizio);

        //Test che controlla il corretto inserimento dei dati
        long result = db.insert(TABLE_NAME_ESERCIZI, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed: "+cv.get("_id"), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Aggiunto correttamente!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void aggiungiWorkoutAlDb(Workout workout){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOME_WORKOUT,workout.getNome());
        cv.put(COLUMN_NOTE,workout.getNote());
        cv.put(COLUMN_GIORNI,workout.getGiorniSettimana().name());
        cv.put(COLUMN_ID_SCHEDA_CORRISPONDENTE,workout.getIdSchedaCorrispondente());

        long result = db.insert(TABLE_NAME_WORKOUT, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Aggiunto correttamente!", Toast.LENGTH_SHORT).show();
        }

        String query = "SELECT " + COLUMN_ID_WORKOUT + " FROM " + TABLE_NAME_WORKOUT + ";";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        cursor.moveToLast();
        workout.setId(cursor.getInt(0));

        aggiungiEserciziInWorkout(workout);

        Log.d("id_w",workout.getId()+"");

        db.close();
    }

    public void aggiungiSchedeAlDb(Schede schede){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOME_SCHEDE, schede.getNome());
        cv.put(COLUMN_DATA_INIZIO,schede.getDataInizio());
        cv.put(COLUMN_DATA_FINE,schede.getDataFine());
        cv.put(COLUMN_OBIETTIVO, schede.getObiettivo());

        long result = db.insert(TABLE_NAME_SCHEDE, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Aggiunta correttamente!", Toast.LENGTH_SHORT).show();
        }

        String query = "SELECT " + COLUMN_ID_SCHEDE + " FROM " + TABLE_NAME_SCHEDE + ";";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        cursor.moveToLast();
        schede.setId(cursor.getInt(0));

        aggiungiWorkoutInScheda(schede);

        Log.d("id_w",schede.getId()+"");

        db.close();
    }

    public void aggiungiEserciziInWorkout(Workout workout){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        for(int i = 0; i < workout.getList_esercizi().size(); i++){
            cv.put(COLUMN_ID_ESERCIZIO_SUPPORTO,workout.getList_esercizi().get(i).getId());
            cv.put(COLUMN_ID_WORKOUT_SUPPORTO, workout.getId());
            db.insert(TABLE_NAME_SUPPORTO, null, cv);
        }

        db.close();
    }

    public void aggiungiWorkoutInScheda(Schede schede){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = COLUMN_ID_WORKOUT + " =?";

        cv.put(COLUMN_ID_SCHEDA_CORRISPONDENTE, schede.getId());

        for(int i =0; i < schede.getList_workout().size(); i++){
            String[] whereArgs = new String[]{String.valueOf(schede.getList_workout().get(i).getId())};
            db.update(TABLE_NAME_WORKOUT, cv, whereClause, whereArgs);
        }

        db.close();
    }


    // creare due metodi read data uno per gli esercizi di tipo pesistica e uno per gli altri

    public ArrayList<Esercizio> caricaListaEserciziDaDb(){

        ArrayList<Esercizio> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_ESERCIZI + ";";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        cursor.moveToLast();
        do {
            if(cursor.getPosition()!=-1){
            switch (cursor.getString(3)){
                case("esercizio_pesistica"):{
                    EsercizioPesistica es = new EsercizioPesistica(
                            cursor.getInt(8),
                            cursor.getInt(7),
                            cursor.getInt(9),
                            convertiInGruppoMuscolare(cursor.getString(10)),
                            cursor.getFloat(4)
                    );

                    es.setNome(cursor.getString(1));

                    if (cursor.getInt(2) == 1){
                        es.setFavorite(true);
                    }else{
                        es.setFavorite(false);
                    }

                    es.setTipo(TipoEsercizio.esercizio_pesistica);
                    es.setId(cursor.getInt(0));
                    list.add(es);
                }break;

                case("esercizio_cardio"):{
                    EsercizioCardio es = new EsercizioCardio(
                            cursor.getInt(6),
                            cursor.getInt(5)
                    );

                    es.setNome(cursor.getString(1));

                    if (cursor.getInt(2) == 1){
                        es.setFavorite(true);
                    }else{
                        es.setFavorite(false);
                    }

                    es.setTipo(TipoEsercizio.esercizio_cardio);
                    es.setId(cursor.getInt(0));
                    list.add(es);
                }
            }
            }
        }while (cursor.moveToPrevious());

        db.close();

        return list;
    }

    public ArrayList<Esercizio> eserciziNelWorkout(int id){

        ArrayList<Esercizio> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT *" +
                        " FROM " + TABLE_NAME_ESERCIZI +
                        " JOIN " + TABLE_NAME_SUPPORTO +
                        " ON " + TABLE_NAME_SUPPORTO + "." + COLUMN_ID_ESERCIZIO_SUPPORTO + " = " + COLUMN_ID_ESERCIZIO +
                        " WHERE " + TABLE_NAME_SUPPORTO + "." + COLUMN_ID_WORKOUT_SUPPORTO + " = " + id + ";";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        while (cursor.moveToNext()){
            switch (cursor.getString(3)){
                case("esercizio_pesistica"):{
                    EsercizioPesistica es = new EsercizioPesistica(
                            cursor.getInt(8),
                            cursor.getInt(7),
                            cursor.getInt(9),
                            convertiInGruppoMuscolare(cursor.getString(10)),
                            cursor.getFloat(4)
                    );

                    es.setNome(cursor.getString(1));

                    if (cursor.getInt(2) == 1){
                        es.setFavorite(true);
                    }else{
                        es.setFavorite(false);
                    }

                    es.setTipo(TipoEsercizio.esercizio_pesistica);
                    es.setId(cursor.getInt(0));
                    list.add(es);
                }break;

                case("esercizio_cardio"):{
                    EsercizioCardio es = new EsercizioCardio(
                            cursor.getInt(6),
                            cursor.getInt(5)
                    );

                    es.setNome(cursor.getString(1));

                    if (cursor.getInt(2) == 1){
                        es.setFavorite(true);
                    }else{
                        es.setFavorite(false);
                    }

                    es.setTipo(TipoEsercizio.esercizio_cardio);
                    es.setId(cursor.getInt(0));
                    list.add(es);
                }
            }
        }
        db.close();

        return list;
    }


    public ArrayList<Workout> workoutNelleSchede(int id){
        ArrayList<Workout> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT *" +
                        " FROM " + TABLE_NAME_WORKOUT +
                        " WHERE " + COLUMN_ID_SCHEDA_CORRISPONDENTE + " = " + id + ";";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        while (cursor.moveToNext()) {

            Workout workout = new Workout(
                    eserciziNelWorkout(cursor.getInt(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    convertiInGiornoSettimana(cursor.getString(3)),
                    cursor.getInt(4),
                    cursor.getInt(0)
            );
            list.add(workout);
        }
        db.close();

        return list;
    }

    public ArrayList<Workout> caricaListaWorkoutDaDb(){

        ArrayList<Workout> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_WORKOUT + ";";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        cursor.moveToLast();
        do {
            if(cursor.getPosition()!=-1) {

                Workout workout = new Workout(
                        eserciziNelWorkout(cursor.getInt(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        convertiInGiornoSettimana(cursor.getString(3)),
                        cursor.getInt(4),
                        cursor.getInt(0)
                );
                list.add(workout);
            }
        }while (cursor.moveToPrevious());

        return list;
    }

    public ArrayList<Schede> caricaListaSchedeDaDb(){
        ArrayList<Schede> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_SCHEDE + ";";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        cursor.moveToLast();
        do{
            Schede schede = new Schede(
                    workoutNelleSchede(cursor.getInt(0)),
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getLong(2),
                    cursor.getLong(3),
                    cursor.getString(4)
            );
            list.add(schede);
        }while(cursor.moveToPrevious());

        return list;
    }

    private GiorniSettimana convertiInGiornoSettimana (String valore){

        switch (valore){
            case "Lunedi":{
                return GiorniSettimana.Lunedi;
            }
            case "Martedi":{
                return GiorniSettimana.Martedi;
            }
            case "Mercoledi":{
                return GiorniSettimana.Mercoledi;
            }
            case "Giovedi":{
                return GiorniSettimana.Giovedi;
            }
            case "Venerdi":{
                return GiorniSettimana.Venerdi;
            }
            case "Sabato":{
                return GiorniSettimana.Sabato;
            }
            case "Domenica":{
                return GiorniSettimana.Domenica;
            }
            default: return null;
        }
    }

    private GruppiMuscolari convertiInGruppoMuscolare (String valore){

        switch (valore){
            case "Petto":{
                return GruppiMuscolari.Petto;
            }
            case "Gambe":{
                return GruppiMuscolari.Gambe;
            }
            case "Spalle":{
                return GruppiMuscolari.Spalle;
            }
            case "Schiena":{
                return GruppiMuscolari.Schiena;
            }
            case "Braccia":{
                return GruppiMuscolari.Braccia;
            }
            case "Addome":{
                return GruppiMuscolari.Addome;
            }
            default: return null;
        }
    }

    public void deleteEsercizio(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClauseEsercizio = COLUMN_ID_ESERCIZIO + " =?";
        String whereClauseSupporto = COLUMN_ID_ESERCIZIO_SUPPORTO + " =?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        long resultEsercizio = db.delete(TABLE_NAME_ESERCIZI, whereClauseEsercizio, whereArgs );
        long resultSupporto = db.delete(TABLE_NAME_SUPPORTO, whereClauseSupporto, whereArgs );

        if(resultEsercizio == -1 || resultSupporto == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eliminato correttamente!", Toast.LENGTH_SHORT).show();
        }
        db.close();
        //aggiungere il controllo sull'eliminazione
    }

    public void deleteWorkout(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID_WORKOUT + " =?";
        String whereClauseSupporto = COLUMN_ID_WORKOUT_SUPPORTO + " =?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        long resultWorkout = db.delete(TABLE_NAME_WORKOUT, whereClause, whereArgs );
        long resultSupporto = db.delete(TABLE_NAME_SUPPORTO, whereClauseSupporto, whereArgs );


        if(resultWorkout == -1 || resultSupporto == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eliminato correttamente!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void deleteScheda(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID_SCHEDE + " =?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        long result = db.delete(TABLE_NAME_SCHEDE, whereClause, whereArgs );

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eliminato correttamente!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    // Passo l'oggetto esercizio e modifico la riga corrispondente nel db
    public void updateEsercizio(Esercizio esercizio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = caricaCvEsercizio(esercizio);
        String whereClause = COLUMN_ID_ESERCIZIO + " =?";
        String[] whereArgs = new String[]{String.valueOf(esercizio.getId())};

        Log.d("where_log",whereClause);
        long result = db.update(TABLE_NAME_ESERCIZI, cv, whereClause, whereArgs);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Modificato correttamente!", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    //Passando l'oggetto workout permette di modificare la riga corrispondente nel db
    public void updateWorkout(Workout workout){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = COLUMN_ID_WORKOUT + " =?";
        String[] whereArgs = new String[]{String.valueOf(workout.getId())};
        String whereClauseSupporto = COLUMN_ID_WORKOUT_SUPPORTO + " =?";
        String[] whereArgsSupporto = new String[]{String.valueOf(workout.getId())};

        cv.put(COLUMN_NOME_WORKOUT,workout.getNome());
        cv.put(COLUMN_NOTE,workout.getNote());
        cv.put(COLUMN_GIORNI,workout.getGiorniSettimana().name());

        long result = db.update(TABLE_NAME_WORKOUT, cv, whereClause, whereArgs);
        db.delete(TABLE_NAME_SUPPORTO, whereClauseSupporto, whereArgsSupporto);
        aggiungiEserciziInWorkout(workout);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Modificato correttamente!", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void rimuoviWorkoutDaScheda(Workout workout){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID_SCHEDA_CORRISPONDENTE, "null");
        String whereClause = COLUMN_ID_WORKOUT + " =?";
        String[] whereArgs = new String[]{String.valueOf(workout.getId())};

        db.update(TABLE_NAME_WORKOUT, cv, whereClause, whereArgs);
        db.close();
    }
}
