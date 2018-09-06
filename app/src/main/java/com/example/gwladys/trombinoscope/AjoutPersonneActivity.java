package com.example.gwladys.trombinoscope;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gwladys.trombinoscope.DAO.PersonneDAO;
import com.example.gwladys.trombinoscope.DataMetier.Personne;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AjoutPersonneActivity extends AppCompatActivity {

    TextView nomTexteAjout;
    TextView prenomTexteAjout;
    TextView numTelTexteAjout;
    TextView courrielTexteAjout;
    ImageView photoPersonneAjout;
    View v;

    PersonneDAO unePersonneDao = new PersonneDAO(this);
    Personne unePersonneAVerifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_personne);

        Toolbar laToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(laToolbar);
        v = (LinearLayout) findViewById(R.id.layoutModif);
        nomTexteAjout = (TextView) findViewById(R.id.nomTexteAjout);
        prenomTexteAjout = (TextView) findViewById(R.id.prenomTexteAjout);
        numTelTexteAjout = (TextView) findViewById(R.id.numTelTexteAjout);
        courrielTexteAjout = (TextView) findViewById(R.id.courrielTexteAjout);
        photoPersonneAjout = (ImageView) findViewById(R.id.photoPersonneAjout);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ajout, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.retour:

                Intent intentionRetour = new Intent(AjoutPersonneActivity.this, MainActivity.class);
                AjoutPersonneActivity.this.startActivity(intentionRetour);
                return true;
            case R.id.parcourir:

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(photoPickerIntent, 1);
                return true;
            case R.id.ajout:

                String unNom = nomTexteAjout.getText().toString();
                String unPrenom = prenomTexteAjout.getText().toString();
                String unNumTel = numTelTexteAjout.getText().toString();
                String unCourriel = courrielTexteAjout.getText().toString();
                String unNomPhoto = "";//photoPersonneAjout.getContentDescription().toString();

                unePersonneAVerifier = new Personne(unePersonneDao.getLastId() + 1, unNom, unPrenom, unNumTel, unCourriel, unNomPhoto);

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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Uri uriData = data.getData();
        String uriDataString = uriData.toString();

        //Snackbar.make(v, uriDataString, Snackbar.LENGTH_LONG).show();
        String nomPhoto = uriDataString.substring(uriDataString.lastIndexOf("/") + 1);

        Integer compteur = 0;
        while(unePersonneDao.siNomPhotoExiste(nomPhoto, -1).equals("")){
            nomPhoto = nomPhoto + "-" + compteur;
            compteur++;
        }

        try {

            Path pathData = Paths.get(uriDataString);
            Path pathTarget = Paths.get(AjoutPersonneActivity.this.getFilesDir() + nomPhoto);
            Files.copy(pathData, pathTarget);
            File imgFile = new File(AjoutPersonneActivity.this.getFilesDir(), nomPhoto);
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            photoPersonneAjout.setContentDescription(nomPhoto);
            photoPersonneAjout.setImageBitmap(myBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}