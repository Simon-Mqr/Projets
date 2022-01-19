package mille_bornes.cartes.parade;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Parade;

public class FinDeLimite extends Parade {

    public FinDeLimite() {
        super("Fin de limite de vitesse");
    }

    @Override
    public boolean contre(Attaque carte) {
        if (carte.nom == "Limite de Vitesse"){
            return true;
        } else return false;
    }
}