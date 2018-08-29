package com.example.gwladys.trombinoscope;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gwladys.trombinoscope.DataMetier.Personne;

import java.util.HashMap;

public class MonViewHolder extends RecyclerView.ViewHolder{

    private ImageView personneImageView;
    private TextView personneTextView;

    //
    public MonViewHolder(View view) {
        super(view);

        personneTextView = (TextView) itemView.findViewById(R.id.text);
        personneImageView = (ImageView) itemView.findViewById(R.id.image);
    }

    // Afficher la personne
    public void afficherPersonne(Personne unePersonne){
        String nom, prenom, numTel, courriel, nomPhoto;
        nom = unePersonne.getNom().toUpperCase();
        prenom = unePersonne.getPrenom().substring(0,1).toUpperCase() + unePersonne.getPrenom().substring(1).toLowerCase();
        numTel = unePersonne.getNumTel();
        courriel = unePersonne.getCourriel();
        nomPhoto = unePersonne.getNomPhoto();

        String texte = nom + " " + prenom + "\n" + numTel + "\n" + courriel;
        Context contexte = personneImageView.getContext();
        Resources resource = contexte.getResources();
        int idPhoto = resource.getIdentifier(nomPhoto, "drawable", "com.example.gwladys.trombinoscope.res");

        this.personneImageView.setImageResource(idPhoto);
        this.personneTextView.setText(texte);
    }
}
