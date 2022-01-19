package mille_bornes.cartes;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;
import mille_bornes.cartes.attaque.*;

public abstract class Bataille extends Carte {

    public Bataille(Categorie categorie, String nom) {
        super(categorie, nom);

    }

    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {
        if(this.categorie == Categorie.Parade) {
            appliqueEffet(jeu, joueur);
        }
        if(this.categorie == Categorie.Attaque) {
            appliqueEffet(jeu, joueur);
        }
    }

    public boolean estContreeParFeuVert(){
        return contre(new FeuRouge());
    }

    public boolean estContreeParFinDeLimite(){
        return contre(new LimiteVitesse());
    }

    public boolean estContreeParEssence(){
        return contre(new PanneEssence());
    }

    public boolean estContreeParRoueDeSecours(){
        return contre(new Crevaison());
    }

    public boolean estContreeParReparations(){
        return contre(new Accident());
    }

    public abstract boolean contre(Attaque carte);

}