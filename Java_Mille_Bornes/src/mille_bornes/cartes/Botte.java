package mille_bornes.cartes;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;

public abstract class Botte extends Carte {

    public Botte (String nom) {
        super(Categorie.Botte, nom);
    }

    public abstract void appliqueEffet(Jeu jeu, EtatJoueur joueur);

    public abstract boolean contre(Attaque carte);
}
