package mille_bornes.cartes;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;

public class Borne extends Carte{

    public final int km;


    public Borne(int km){
        super(Categorie.Borne, "Bornes " + km);
        this.km = km;
    }

    public int getKm() {
        return km;
    }

    @Override
    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {
        if (joueur.getBataille().categorie == Categorie.Attaque){
            throw new IllegalStateException("Vous ne pouvez pas avancer !");
        }
        if(joueur.getLimiteVitesse()) {
            if(km <= 50) {
                joueur.ajouteKm(km);
            } else {
                throw new IllegalStateException("Vous êtes Limité a 50 km, vous ne pouvez donc pas avancer de " + km);
            }
        }
        joueur.ajouteKm(km);
        jeu.defausse(this); //attention pour le javafx
    }
}

