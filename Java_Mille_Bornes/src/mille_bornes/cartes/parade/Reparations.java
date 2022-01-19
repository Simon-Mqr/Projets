package mille_bornes.cartes.parade;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Parade;

public class Reparations extends Parade {

    public Reparations() {
        super("Réparations");
    }

    @Override
    public boolean contre(Attaque carte) {
        if (carte.nom == "Accident"){
            return true;
        } else return false;
    }
}