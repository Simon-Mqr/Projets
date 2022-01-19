package mille_bornes;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import mille_bornes.cartes.*;
import mille_bornes.cartes.attaque.*;
import mille_bornes.cartes.botte.*;
import mille_bornes.cartes.parade.*;

public class TasDeCarte {

    public LinkedList<Carte> cartes = new LinkedList<Carte>();

    public TasDeCarte(boolean creeLesCartes) { // Fait
        if(creeLesCartes) {
            creeLesCartes();
        }
        else cartes = new LinkedList<Carte>();
    }

    public void melangerCartes() { // Fait
        //TODO Mélange le Tas de carte.
        Collections.shuffle(cartes);
    }

    private void creeLesCartes() { // Fait
        //TODO Crée un Tas de catre avec les cartes du mille bornes.
        int i = 1;

        while (i <= 18) {
            if (i <= 5) {
                cartes.add(new FeuRouge());
                i++;
            } else if (i <= 9) {
                cartes.add(new LimiteVitesse());
                i++;
            } else if (i <= 12) {
                cartes.add(new PanneEssence());
                i++;
            } else if (i <= 15) {
                cartes.add(new Crevaison());
                i++;
            } else if (i <= 18) {
                cartes.add(new Accident());
                i++;
            }
        }

        while (i <= 56) {
            if (i <= 32) {
                cartes.add(new FeuVert());
                i++;
            } else if (i <= 38) {
                cartes.add(new FinDeLimite());
                i++;
            } else if (i <= 44) {
                cartes.add(new Essence());
                i++;
            } else if (i <= 50) {
                cartes.add(new RoueDeSecours());
                i++;
            } else if (i <= 56) {
                cartes.add(new Reparations());
                i++;
            }
        }

        while (i <= 102) {
            if (i <= 66) {
                cartes.add(new Borne(25));
                i++;
            } else if (i <= 76) {
                cartes.add(new Borne(50));
                i++;
            } else if (i <= 86) {
                cartes.add(new Borne(75));
                i++;
            } else if (i <= 98) {
                cartes.add(new Borne(100));
                i++;
            } else if (i <= 102) {
                cartes.add(new Borne(200));
                i++;
            }
        }
        cartes.add(new AsDuVolant());
        cartes.add(new Increvable());
        cartes.add(new Citerne());
        cartes.add(new VehiculePrioritaire());
    }

    public int getNbCartes() { // Fait
        //TODO Retourne le nombre de cartes dans le Tas.
        return cartes.size();
    }

    public boolean estVide() { // Fait
        //TODO Regarde si le Tas est vide.
        return cartes.isEmpty();
    }

    public Carte regarde(){
        //TODO Retourne la première carte du tas.
        return cartes.get(cartes.size() - 1);
    }

    public Carte prend(){ // Fait
        //TODO Tire et retourne la première carte du tas.
        return cartes.remove(cartes.size() - 1);
    }

    public void pose(Carte carte){ // Fait
        //TODO Ajoute une carte au dessus du tas.
        cartes.addFirst(carte);
    }
}



