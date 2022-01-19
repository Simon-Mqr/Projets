package mille_bornes.cartes;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;
import mille_bornes.cartes.attaque.FeuRouge;
import mille_bornes.cartes.attaque.LimiteVitesse;

public abstract class Carte {

    public final Categorie categorie;
    public final String nom;

    public Carte(Categorie categorie, String nom) {
        this.categorie = categorie;
        this.nom = nom;
    }

    public abstract void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException;

    @Override
    public String toString() {
        return nom;
    }

}
