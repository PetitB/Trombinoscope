package com.example.gwladys.trombinoscope.DataMetier;

public class Personne {
    private String nom;
    private String prenom;
    private String numTel;
    private String courriel;
    private String photoUrl;

    public Personne(String unNom, String unPrenom, String unNumTel, String unCourriel, String unePhotoUrl) {
        this.nom = unNom;
        this.prenom = unPrenom;
        this.numTel = unNumTel;
        this.courriel = unCourriel;
        this.photoUrl = unePhotoUrl;
    }

    //region Getters de Personne
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
    public String getPhotoUrl(){
        return photoUrl;
    }
    //endregion

    //region Setters de Personne
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
    public void setPhotoUrl(String unePhotoUrl){
        photoUrl = unePhotoUrl;
    }
    //endregion
}
