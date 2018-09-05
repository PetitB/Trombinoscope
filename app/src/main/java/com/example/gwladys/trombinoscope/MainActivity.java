package com.example.gwladys.trombinoscope;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.example.gwladys.trombinoscope.DataMetier.Personne;
import com.example.gwladys.trombinoscope.DAO.PersonneDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Personne> listePersonnes = new ArrayList<>();
    private MonAdaptateur adapteur;
    private GridView laGrille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        laGrille = (GridView) findViewById(R.id.grille);

        PersonneDAO unePersonneDao = new PersonneDAO(this);
        listePersonnes = unePersonneDao.selectionnerToutesLesPersonnes();

        this.adapteur = new MonAdaptateur(this, listePersonnes);

        laGrille.setAdapter(adapteur);

        FloatingActionButton boutonAjout = (FloatingActionButton) findViewById(R.id.bouton_ajout);

        boutonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Lance l'activité d'ajout de personne
                Intent intentionAjout = new Intent(MainActivity.this, AjoutPersonneActivity.class);
                MainActivity.this.startActivity(intentionAjout);
            }
        });
    }
}