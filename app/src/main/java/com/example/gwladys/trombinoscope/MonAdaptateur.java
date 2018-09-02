package com.example.gwladys.trombinoscope;

import android.content.Context;
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
    Context contexte;
    ArrayList<Personne> listePersonne;

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
            view= LayoutInflater.from(contexte).inflate(R.layout.listepersonnes,viewGroup,false);
        }

        final Personne unePersonne = (Personne) this.getItem(i);

        TextView nomEtPrenomTexte= (TextView) view.findViewById(R.id.nomEtPrenomTexte);
        TextView numTelTexte= (TextView) view.findViewById(R.id.numTelTexte);
        TextView courrielTexte= (TextView) view.findViewById(R.id.courrielTexte);
        ImageView photoPersonne = (ImageView) view.findViewById(R.id.photoPersonne);

        //BIND
        String nom = unePersonne.getNom().toUpperCase();
        String prenom = unePersonne.getPrenom().substring(0,1).toUpperCase() + unePersonne.getPrenom().substring(1).toLowerCase();
        final String nomEtPrenom = nom + " " + prenom ;
        nomEtPrenomTexte.setText(nomEtPrenom);
        numTelTexte.setText(unePersonne.getNumTel());
        courrielTexte.setText(unePersonne.getCourriel());

        File imgFile = new File("/storage/emulated/0/Pictures/" + unePersonne.getNomPhoto());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            photoPersonne.setImageBitmap(myBitmap);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contexte, "Lance l'action de modification de la personne : " + nomEtPrenom, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}