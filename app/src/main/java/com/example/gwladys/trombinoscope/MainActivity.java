package com.example.gwladys.trombinoscope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        setContentView(R.layout.activity_main);

        laGrille = (GridView) findViewById(R.id.grille);

        PersonneDAO unePersonneDao = new PersonneDAO(this);
        listePersonnes = unePersonneDao.selectionnerToutesLesPersonnes();

        this.adapteur = new MonAdaptateur(this, listePersonnes);

        laGrille.setAdapter(adapteur);
    }
}