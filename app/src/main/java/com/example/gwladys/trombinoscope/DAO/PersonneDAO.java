package com.example.gwladys.trombinoscope.DAO;

import com.example.gwladys.trombinoscope.DataMetier.Personne;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PersonneDAO extends DAOBase {
    public static final String TABLE_NAME = "personne";
    public static final String KEY = "id";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String NUMTEL = "numtel";
    public static final String COURRIEL = "courriel";
    public static final String NOMPHOTO = "nomphoto";

    public static final String PERSONNE_TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOM + " TEXT NOT NULL, " +
                    PRENOM + " TEXT NOT NULL, " +
                    NUMTEL + " TEXT NOT NULL, " +
                    COURRIEL + " TEXT NOT NULL, "+
                    NOMPHOTO + " TEXT  NOT NULL);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public PersonneDAO(Context pContext) {
        super(pContext);
    }

    /**
     * Insère une nouvelle personne dans la DB
     * @param p la personne à ajouter dans la base
     */
    public void ajouterPersonne (Personne p) {
        ContentValues value = new ContentValues();
        String nom = p.getNom(), prenom = p.getPrenom(), numTel = p.getNumTel(), courriel = p.getCourriel(), nomPhoto = p.getNomPhoto();
        value.put(NOM, nom);
        value.put(PRENOM, prenom);
        value.put(NUMTEL, numTel);
        value.put(COURRIEL, courriel);
        value.put(NOMPHOTO, nomPhoto);
        open();
        pDb.insert(TABLE_NAME, null, value);
        close();
    }

    /**
     * Supprime la personne à l'id indiqué
     * @param id l'identifiant du métier à supprimer
     */
    public void supprimer(long id) {
        open();
        pDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        close();
    }

    /**
     * Remet à jour la personne
     * @param p la personne à modifier
     */
    public void modifier(Personne p) {
        ContentValues value = new ContentValues();
        value.put(NOM, p.getNom());
        value.put(PRENOM, p.getPrenom());
        value.put(NUMTEL, p.getNumTel());
        value.put(COURRIEL, p.getCourriel());
        value.put(NOMPHOTO, p.getNomPhoto());
        open();
        pDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(p.getId())});
        close();
    }

    /**
     * Récupère toutes les personnes de la DB
     */
    public ArrayList<Personne> selectionnerToutesLesPersonnes() {

        ArrayList<Personne> personneListe = new ArrayList<Personne>();
        open();
        Cursor curseur = pDb.rawQuery("select * from " + TABLE_NAME + " order by nom", null);

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
        close();
        return personneListe;
    }

    /**
     * Vérifie si la personne existe déjà dans la base de données
     * @Param p la personne à contrôler l'existance
     */
    public boolean siPersonneExiste(String nom, String prenom){

        Cursor curseur = pDb.rawQuery("select id from " + TABLE_NAME + " where nom = ? and prenom = ?", null);
        return curseur.moveToFirst();
    }

    /**
     * Vérifie si le numéro de téléphone existe déjà dans la base de données
     * @Param p la personne à contrôler l'existance
     */
    public boolean siNumTelExiste(String numTel){

        Cursor curseur = pDb.rawQuery("select id from " + TABLE_NAME + " where numtel = ?", null);
        return curseur.moveToFirst();
    }

    /**
     * Vérifie si le courriel existe déjà dans la base de données
     * @Param p la personne à contrôler l'existance
     */
    public boolean siCourrielExiste(String courriel){

        Cursor curseur = pDb.rawQuery("select id from " + TABLE_NAME + " where courriel = ?", null);
        return curseur.moveToFirst();
    }

    /**
     * Vérifie si le courriel existe déjà dans la base de données
     * @Param p la personne à contrôler l'existance
     */
    public boolean siNomPhotoExiste(String nomPhoto){

        Cursor curseur = pDb.rawQuery("select id from " + TABLE_NAME + " where nomphoto = ?", null);
        return curseur.moveToFirst();
    }
}
