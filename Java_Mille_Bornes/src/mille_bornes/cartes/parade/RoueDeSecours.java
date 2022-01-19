package mille_bornes.cartes.parade;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Parade;

public class RoueDeSecours extends Parade {

    public RoueDeSecours() {
        super("Roue de secours");
    }

    @Override
    public boolean contre(Attaque carte) {
        if (carte.nom == "Crevaison"){
            return true;
        } else return false;
    }
}