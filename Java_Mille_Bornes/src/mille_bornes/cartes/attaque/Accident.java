package mille_bornes.cartes.attaque;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Bataille;

public class Accident extends Attaque {

    public Accident() {
        super("Accident");
    }

    @Override
    public boolean estContreeParReparations() {
        return super.estContreeParReparations();
    }

}