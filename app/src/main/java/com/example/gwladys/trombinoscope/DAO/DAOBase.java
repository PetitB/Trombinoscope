package com.example.gwladys.trombinoscope.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DAOBase {
    // Nous sommes à la première version de la base
    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 1;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "database.db";

    protected SQLiteDatabase pDb = null;
    protected DatabaseHandler mHandler = null;

    public DAOBase(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        pDb = mHandler.getWritableDatabase();
        return pDb;
    }

    public void close() {
        pDb.close();
    }

    public SQLiteDatabase getDb() {
        return pDb;
    }
}
