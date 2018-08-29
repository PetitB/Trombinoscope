package com.example.gwladys.trombinoscope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gwladys.trombinoscope.DataMetier.Personne;
import com.example.gwladys.trombinoscope.DAO.PersonneDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView leRecyclerView;
    private List<Personne> listePersonnes = new ArrayList<>();
    private RecyclerView.Adapter pAdapter;
    private GridLayoutManager leGridLayoutManager = new GridLayoutManager(this, 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ajouterPersonnagesSimpsonsParCode();

        this.leRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        this.leRecyclerView.setHasFixedSize(true);

        this.leRecyclerView.setLayoutManager(this.leGridLayoutManager);

        PersonneDAO unePersonneDao = new PersonneDAO(this);
        listePersonnes = unePersonneDao.selectionnerToutesLesPersonnes();

        this.pAdapter = new MonAdapter(listePersonnes);
        this.leRecyclerView.setAdapter(this.pAdapter);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Méthode d'ajout de personnes tests sans l'utilisation de la base de données et par code, s'ajoute dans la DB après création de la personne
    private void ajouterPersonnagesSimpsonsParCode() {

        PersonneDAO unePersonneDao = new PersonneDAO(this);
        Personne unePersonne = new Personne("Simpson","Homer","+331.111.111.11", "homer.sompson@gmail.com", "homer_simpson");
        unePersonneDao.ajouterPersonne(unePersonne);
        unePersonne = new Personne("Simpson","Marge","+332.222.222.22", "marge.simpson@gmail.com", "marge_simpson");
        unePersonneDao.ajouterPersonne(unePersonne);
        unePersonne = new Personne("Simpson","Bart","+333.333.333.33", "bart.du.93@gmail.com", "bart_simpson");
        unePersonneDao.ajouterPersonne(unePersonne);
        unePersonne = new Personne("Simpson","Lisa","+334.444.444.44", "lisa.simpson.99@gmail.com", "lisa_simpson");
        unePersonneDao.ajouterPersonne(unePersonne);
        unePersonne = new Personne("Simpson","Maggie","+335.555.555.55", "maggie.simpson@gmail.com", "maggie_simpson");
        unePersonneDao.ajouterPersonne(unePersonne);
        unePersonne = new Personne("Nahasapeemapetilon","Apu","+330.000.000.00", "apu.nahasa@aol.com", "apu");
        unePersonneDao.ajouterPersonne(unePersonne);
        unePersonne = new Personne("Flanders","Ned","+336.666.666.66", "i.love.all@god.world", "ned_flanders");
        unePersonneDao.ajouterPersonne(unePersonne);
        unePersonne = new Personne("Mongomery Burns","Charles","+339.999.999.99", "my.money@gmail.com", "charles_burns");
        unePersonneDao.ajouterPersonne(unePersonne);
    }
}