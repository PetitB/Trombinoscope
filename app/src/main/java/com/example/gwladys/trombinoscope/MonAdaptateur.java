package com.example.gwladys.trombinoscope;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gwladys.trombinoscope.DataMetier.Personne;

import java.io.File;
import java.util.ArrayList;

public class MonAdaptateur extends BaseAdapter {
    private Context contexte;
    private ArrayList<Personne> listePersonne;

    public MonAdaptateur(Context contexte, ArrayList<Personne> listePersonne) {
        this.contexte = contexte;
        this.listePersonne = listePersonne;
    }

    @Override
    public int getCount() {
        return listePersonne.size();
    }

    @Override
    public Object getItem(int i) {
        return listePersonne.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(contexte).inflate(R.layout.liste_personnes,viewGroup,false);
        }

        view.setClickable(true);

        final Personne unePersonne = (Personne) this.getItem(i);

        TextView nomTexte= (TextView) view.findViewById(R.id.nomTexte);
        TextView prenomTexte= (TextView) view.findViewById(R.id.prenomTexte);
        TextView numTelTexte= (TextView) view.findViewById(R.id.numTelTexte);
        TextView courrielTexte= (TextView) view.findViewById(R.id.courrielTexte);
        ImageView photoPersonne = (ImageView) view.findViewById(R.id.photoPersonne);

        //BIND
        String nom = unePersonne.getNom().toUpperCase();
        String prenom = unePersonne.getPrenom().substring(0,1).toUpperCase() + unePersonne.getPrenom().substring(1).toLowerCase();
        final int unId = unePersonne.getId();

        nomTexte.setText(nom);
        prenomTexte.setText(prenom);
        numTelTexte.setText(unePersonne.getNumTel());
        courrielTexte.setText(unePersonne.getCourriel());

        File imgFile = new File(contexte.getFilesDir(), unePersonne.getNomPhoto());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            photoPersonne.setImageBitmap(myBitmap);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentionModif = new Intent(contexte, ModifPersonneActivity.class);
                intentionModif.putExtra("idPersonne", unId);
                contexte.startActivity(intentionModif);
            }
        });

        return view;
    }
}