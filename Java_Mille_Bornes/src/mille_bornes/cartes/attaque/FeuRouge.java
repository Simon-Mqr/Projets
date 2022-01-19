package mille_bornes.cartes.attaque;

import mille_bornes.cartes.Attaque;

public class FeuRouge extends Attaque {

    public FeuRouge(){
        super("Feu Rouge");
    }

    @Override
    public boolean estContreeParFeuVert() {
        return super.estContreeParFeuVert();
    }
}