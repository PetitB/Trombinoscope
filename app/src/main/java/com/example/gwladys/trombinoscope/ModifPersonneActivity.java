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
import android.widget.TextView;

import com.example.gwladys.trombinoscope.DAO.PersonneDAO;
import com.example.gwladys.trombinoscope.DataMetier.Personne;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModifPersonneActivity extends AppCompatActivity {

    TextView nomTexteModif;
    TextView prenomTexteModif;
    TextView numTelTexteModif;
    TextView courrielTexteModif;
    ImageView photoPersonneModif;
    View v;

    PersonneDAO unePersonneDao = new PersonneDAO(this);
    Personne unePersonneAVerifier;
    int idPersonne;
    Personne unePersonne = new Personne();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_personne);

        nomTexteModif = (TextView) findViewById(R.id.nomTexteModif);
        prenomTexteModif = (TextView) findViewById(R.id.prenomTexteModif);
        numTelTexteModif = (TextView) findViewById(R.id.numTelTexteModif);
        courrielTexteModif = (TextView) findViewById(R.id.courrielTexteModif);
        photoPersonneModif = (ImageView) findViewById(R.id.photoPersonneModif);

        v = photoPersonneModif.getRootView();
        Intent intent = getIntent();
        idPersonne = intent.getIntExtra("idPersonne", -1);

        unePersonne = unePersonneDao.selectionnerUnePersonneViaId(idPersonne);

        String nom = unePersonne.getNom().toUpperCase();
        String prenom = unePersonne.getPrenom().substring(0,1).toUpperCase() + unePersonne.getPrenom().substring(1).toLowerCase();

        nomTexteModif.setText(nom);
        prenomTexteModif.setText(prenom);
        numTelTexteModif.setText(unePersonne.getNumTel());
        courrielTexteModif.setText(unePersonne.getCourriel());

        File imgFile = new File(ModifPersonneActivity.this.getFilesDir(), unePersonne.getNomPhoto());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            photoPersonneModif.setContentDescription(unePersonne.getNomPhoto());
            photoPersonneModif.setImageBitmap(myBitmap);
        }

        Toolbar laToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(laToolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modif, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.retour:

                Intent intentionRetour = new Intent(ModifPersonneActivity.this, MainActivity.class);
                ModifPersonneActivity.this.startActivity(intentionRetour);
                return true;
            case R.id.parcourir:

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(photoPickerIntent, 1);
                return true;
            case R.id.modif:

                String unNom = nomTexteModif.getText().toString();
                String unPrenom = prenomTexteModif.getText().toString();
                String unNumTel = numTelTexteModif.getText().toString();
                String unCourriel = courrielTexteModif.getText().toString();
                String unNomPhoto = photoPersonneModif.getContentDescription().toString();

                unePersonneAVerifier = new Personne(idPersonne, unNom, unPrenom, unNumTel, unCourriel, unNomPhoto);
                String messageErreur = unePersonneDao.siAutrePersonneExisteAvecIdDifferent(unePersonneAVerifier);
                if (messageErreur != "") {

                    Snackbar.make(v, messageErreur, Snackbar.LENGTH_LONG).show();
                } else {

                    unePersonneDao.modifierPersonne(unePersonneAVerifier);
                    Snackbar.make(v, "Personne modifiée avec succès !", Snackbar.LENGTH_LONG).show();
                }
                return true;

            case R.id.suppression:
                unePersonneDao.supprimerPersonne(idPersonne);
                Snackbar.make(v, "Personne supprimée avec succès !", Snackbar.LENGTH_LONG)
                        .setAction("Annuler", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                unePersonneDao.ajouterPersonne(unePersonne);
                            }
                        }).show();
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
        String extension = nomPhoto.substring(nomPhoto.lastIndexOf("."));
        String nomPhotoSansExtension = nomPhoto.substring(nomPhoto.lastIndexOf(".") - 1);

        while(unePersonneDao.siNomPhotoExiste(nomPhoto, -1) != ""){
            nomPhoto += nomPhotoSansExtension + "-1" + extension;
        }

        try {

            Path pathData = Paths.get(uriDataString);
            Path pathTarget = Paths.get(ModifPersonneActivity.this.getFilesDir() + nomPhoto);
            Files.copy(pathData, pathTarget);
            File imgFile = new File(ModifPersonneActivity.this.getFilesDir(), nomPhoto);
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriData);
            photoPersonneModif.setContentDescription(nomPhoto);
            photoPersonneModif.setImageBitmap(myBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
