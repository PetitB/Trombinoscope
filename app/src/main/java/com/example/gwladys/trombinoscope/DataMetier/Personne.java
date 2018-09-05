package com.example.gwladys.trombinoscope.DataMetier;

import android.content.Context;

import com.example.gwladys.trombinoscope.DAO.PersonneDAO;

import java.util.concurrent.atomic.AtomicInteger;

public class Personne {
    // region Propriétés privées de Personne
    private int id; // L'id est récupéré via la DB
    private String nom;
    private String prenom;
    private String numTel;
    private String courriel;
    private String nomPhoto;
    // endregion

    public Personne(){

    }

    public Personne(int unId, String unNom, String unPrenom, String unNumTel, String unCourriel, String unNomPhoto) {
        this.id = unId;
        this.nom = unNom;
        this.prenom = unPrenom;
        this.numTel = unNumTel;
        this.courriel = unCourriel;
        this.nomPhoto = unNomPhoto;
    }

    //region Getters de Personne
    public int getId(){
        return id;
    }
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public String getNumTel(){
        return numTel;
    }
    public String getCourriel(){
        return courriel;
    }
    public String getNomPhoto(){
        return nomPhoto;
    }
    //endregion

    //region Setters de Personne
    public void setId(int unId){
        id = unId;
    }
    public void setNom(String unNom){
        nom = unNom;
    }
    public void setPrenom(String unPrenom){
        prenom = unPrenom;
    }
    public void setNumTel(String unNumTel){
        numTel = unNumTel;
    }
    public void setCourriel(String unCourriel){
        courriel = unCourriel;
    }
    public void setNomPhoto(String unNomPhoto){
        nomPhoto = unNomPhoto;
    }
    //endregion
}
