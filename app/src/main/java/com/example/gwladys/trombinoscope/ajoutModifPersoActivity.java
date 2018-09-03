package com.example.gwladys.trombinoscope;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.gwladys.trombinoscope.DAO.PersonneDAO;
import com.example.gwladys.trombinoscope.DataMetier.Personne;

import java.io.File;

public class ajoutModifPersoActivity extends AppCompatActivity {

    Toolbar modifToolbar;
    TextView nomTexteModif;
    TextView prenomTexteModif;
    TextView numTelTexteModif;
    TextView courrielTexteModif;
    ImageView photoPersonneModif;

    PersonneDAO unePersonneDao = new PersonneDAO(this);

    int idPersonne;
    Personne unePersonne = new Personne();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoutmodifpersonne_layout);

        modifToolbar = (Toolbar) findViewById(R.id.toolbar_button);
        nomTexteModif = (TextView) findViewById(R.id.nomTexteModif);
        prenomTexteModif = (TextView) findViewById(R.id.prenomTexteModif);
        numTelTexteModif = (TextView) findViewById(R.id.numTelTexteModif);
        courrielTexteModif = (TextView) findViewById(R.id.courrielTexteModif);
        photoPersonneModif = (ImageView) findViewById(R.id.photoPersonneModif);

        Intent intent = getIntent();
        idPersonne = intent.getIntExtra("idPersonne", -1);

        /**
         * Si l'ID est de -1, il s'agit d'un ajout de personne à la DB
         * Sinon, il s'agit de l'ID de la personne à modifier
         */
        if(idPersonne == -1){

            modifToolbar.setTitle("Ajouter");

            modifToolbar.setOnClickListener(boutonAjoutClick);
        } else {
            
            unePersonne = unePersonneDao.selectionnerUnePersonneViaId(idPersonne);
            FloatingActionButton boutonSuppression = (FloatingActionButton) findViewById(R.id.bouton_suppression);

            //BIND
            String nom = unePersonne.getNom().toUpperCase();
            String prenom = unePersonne.getPrenom().substring(0,1).toUpperCase() + unePersonne.getPrenom().substring(1).toLowerCase();

            nomTexteModif.setText(nom);
            prenomTexteModif.setText(prenom);
            numTelTexteModif.setText(unePersonne.getNumTel());
            courrielTexteModif.setText(unePersonne.getCourriel());

            File imgFile = new File("/data/data/com.example.gwladys.trombinoscope/Pictures", unePersonne.getNomPhoto());

            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                photoPersonneModif.setContentDescription(unePersonne.getNomPhoto());
                photoPersonneModif.setImageBitmap(myBitmap);
            }

            modifToolbar.setTitle("Modifier");

            modifToolbar.setOnClickListener(boutonModifClick);
            boutonSuppression.setOnClickListener(boutonSuppressionClick);
        }
    }



    View.OnClickListener boutonAjoutClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String unNom = nomTexteModif.getText().toString();
            String unPrenom = prenomTexteModif.getText().toString();
            String unNumTel = numTelTexteModif.getText().toString();
            String unCourriel = courrielTexteModif.getText().toString();
            String unNomPhoto = photoPersonneModif.getContentDescription().toString();

            final Personne unePersonneAVerifier = new Personne(unNom, unPrenom, unNumTel, unCourriel, unNomPhoto);
            String messageErreur = unePersonneDao.siAutrePersonneExisteAvecIdDifferent(unePersonneAVerifier);
            if (messageErreur != "") {

                Snackbar.make(v, messageErreur, Snackbar.LENGTH_LONG).show();
            } else {
                unePersonneDao.ajouterPersonne(unePersonneAVerifier);
                Snackbar.make(v, "Personne ajoutée avec succès.", Snackbar.LENGTH_LONG)
                        .setAction("Annuler", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                unePersonneDao.supprimerPersonne(unePersonneAVerifier.getId());
                            }
                        }).show();
            }
        }
    };

    View.OnClickListener boutonModifClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String unNom = nomTexteModif.getText().toString();
            String unPrenom = prenomTexteModif.getText().toString();
            String unNumTel = numTelTexteModif.getText().toString();
            String unCourriel = courrielTexteModif.getText().toString();
            String unNomPhoto = photoPersonneModif.getContentDescription().toString();

            Personne unePersonneAVerifier = new Personne(idPersonne, unNom, unPrenom, unNumTel, unCourriel, unNomPhoto);
            String messageErreur = unePersonneDao.siAutrePersonneExisteAvecIdDifferent(unePersonneAVerifier);
            if (messageErreur != "") {

                Snackbar.make(v, messageErreur, Snackbar.LENGTH_LONG).show();
            } else {

                unePersonneDao.modifierPersonne(unePersonneAVerifier);
                Snackbar.make(v, "Personne modifiée avec succès !", Snackbar.LENGTH_LONG).show();
            }
        }
    };

    View.OnClickListener boutonSuppressionClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            unePersonneDao.supprimerPersonne(idPersonne);
            Snackbar.make(v, "Personne supprimée avec succès !", Snackbar.LENGTH_LONG)
                    .setAction("Annuler", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            unePersonneDao.ajouterPersonne(unePersonne);
                        }
                    }).show();
        }
    };
}
