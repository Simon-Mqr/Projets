package mille_bornes.cartes.parade;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Parade;

public class Essence extends Parade {

    public Essence() {
        super("Essence");
    }

    @Override
    public boolean contre(Attaque carte) {
        if (carte.nom == "Panne d'essence"){
            return true;
        } else return false;
    }
}