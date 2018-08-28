package com.example.gwladys.trombinoscope;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gwladys.trombinoscope.DataMetier.Personne;

public class ViewHolder extends RecyclerView.ViewHolder{

    private TextView textView;
    private ImageView imageView;

    //
    public ViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    // Afficher la personne
    public void afficherPersonne(Context context, Personne unePersonne){
        int idPhoto = context.getResources().getIdentifier(unePersonne.getNomPhoto(), "drawable", "com.example.gwladys.trombinoscope");
        imageView.setImageResource(idPhoto);
        textView.setText(unePersonne.getNom());
        textView.setText(unePersonne.getPrenom());
        textView.setText(unePersonne.getNumTel());
        textView.setText(unePersonne.getCourriel());
    }
}
