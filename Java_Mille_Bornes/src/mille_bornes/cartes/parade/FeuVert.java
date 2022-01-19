package mille_bornes.cartes.parade;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Carte;
import mille_bornes.cartes.Parade;

public class FeuVert extends Parade {

    public FeuVert() {
        super("Feu Vert");
    }

    @Override
    public boolean contre(Attaque carte) {
        if (carte.nom == "Feu Rouge"){ //TO UPPER CASE ??
            return true;
        } else return false;
    }
}