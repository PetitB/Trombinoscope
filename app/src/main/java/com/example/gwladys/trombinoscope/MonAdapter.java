package com.example.gwladys.trombinoscope;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gwladys.trombinoscope.DataMetier.Personne;

import java.util.List;

public class MonAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static Context context;
    List<Personne> liste;

    // Constructeur avec uneListe de Personne en paramètre
    public MonAdapter(List<Personne> uneListe) {
        this.liste = uneListe;
    }

    //Permet d'indiquer la vue à inflater
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup uneVueDeGroupe, int itemType) {
        View view = LayoutInflater.from(uneVueDeGroupe.getContext()).inflate(R.layout.activity_main,uneVueDeGroupe,false);
        return new ViewHolder(view);
    }

    //Lance l'affichage de la photo et des textes de chaque personne de la liste
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Personne unePersonne = liste.get(position);
        viewHolder.afficherPersonne(MonAdapter.context, unePersonne);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

}
