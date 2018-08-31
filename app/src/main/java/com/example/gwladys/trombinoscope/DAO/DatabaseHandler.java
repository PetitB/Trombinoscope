package com.example.gwladys.trombinoscope.DAO;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String PERSONNE_KEY = "id";
    public static final String PERSONNE_NOM = "nom";
    public static final String PERSONNE_PRENOM = "prenom";
    public static final String PERSONNE_NUMTEL = "numtel";
    public static final String PERSONNE_COURRIEL = "courriel";
    public static final String PERSONNE_NOMPHOTO = "nomphoto";

    public static final String PERSONNE_TABLE_NAME = "personne";
    public static final String PERSONNE_TABLE_CREATE =
            "CREATE TABLE " + PERSONNE_TABLE_NAME + " (" +
                    PERSONNE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PERSONNE_NOM + " TEXT NOT NULL, " +
                    PERSONNE_PRENOM + " TEXT NOT NULL, " +
                    PERSONNE_NUMTEL + " TEXT NOT NULL, " +
                    PERSONNE_COURRIEL + " TEXT NOT NULL, "+
                    PERSONNE_NOMPHOTO + " TEXT NOT NULL);";

    public static final String PERSONNE_TABLE_DROP = "DROP TABLE IF EXISTS " + PERSONNE_TABLE_NAME + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PERSONNE_TABLE_CREATE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PERSONNE_TABLE_DROP);
        onCreate(db);
    }
}
