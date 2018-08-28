package com.example.gwladys.trombinoscope.DAO;

import com.example.gwladys.trombinoscope.DataMetier.Personne;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

public class PersonneDAO extends DAOBase {
    public static final String TABLE_NAME = "personne";
    public static final String KEY = "id";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String NUMTEL = "numTel";
    public static final String COURRIEL = "courriel";
    public static final String NOMPHOTO = "nomPhoto";

    public static final String PERSONNE_TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOM + " TEXT NOT NULL, " +
                    PRENOM + " TEXT NOT NULL, " +
                    NUMTEL + " TEXT NOT NULL, " +
                    COURRIEL + "TEXT NOT NULL, "+
                    NOMPHOTO + "TEXT  NOT NULL);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public PersonneDAO(Context pContext) {
        super(pContext);
    }

    /**
     * @param p la personne à ajouter dans la base
     */
    public void ajouterPersonne (Personne p) {
        ContentValues value = new ContentValues();
        value.put(NOM, p.getNom());
        value.put(PRENOM, p.getPrenom());
        value.put(NUMTEL, p.getNumTel());
        value.put(COURRIEL, p.getCourriel());
        value.put(NOMPHOTO, p.getNomPhoto());
        pDb.insert(PersonneDAO.TABLE_NAME, null, value);
    }

    /**
     * @param id l'identifiant du métier à supprimer
     */
    public void supprimer(long id) {
        pDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    /**
     * @param p la personne à modifier
     */
    public void modifier(Personne p) {
        ContentValues value = new ContentValues();
        value.put(NOM, p.getNom());
        value.put(PRENOM, p.getPrenom());
        value.put(NUMTEL, p.getNumTel());
        value.put(COURRIEL, p.getCourriel());
        value.put(NOMPHOTO, p.getNomPhoto());
        pDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(p.getId())});
    }

    /**
     * Récupère toutes les personnes de la DB
     */
    public List<Personne> selectionnerToutesLesPersonnes() {

        List<Personne> personneListe = new LinkedList<Personne>();
        Cursor curseur = pDb.rawQuery("select * from " + TABLE_NAME, null);

        if (curseur.moveToFirst()) {
            do {
                Personne p = new Personne(
                        Integer.parseInt(curseur.getString(0)),
                        curseur.getString(1),
                        curseur.getString(2),
                        curseur.getString(3),
                        curseur.getString(4),
                        curseur.getString(5));

                // Ajout de la personne à la liste
                personneListe.add(p);
            } while (curseur.moveToNext());
        }
        return personneListe;
    }
}
