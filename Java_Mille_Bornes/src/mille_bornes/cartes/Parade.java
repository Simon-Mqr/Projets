package mille_bornes.cartes;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;

public abstract class Parade extends Bataille {

    public Parade(String nom) {
        super(Categorie.Parade, nom);
    }

    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {
        if(joueur.ditPourquoiPeutPasAvancer() != null) {
            if(this.nom.equals("Fin de limite de vitesse")) {
                if(joueur.getLimiteVitesse()) {
                    joueur.setLimiteVitesse(false);
                } else {
                    throw new IllegalStateException("Vous n'êtes pas limité par un limite de Vitesse.");
                }
            } else {
                switch (joueur.getBataille().nom) {
                    case "Accident":
                        if (this.nom.equals("Réparations")) {
                            joueur.setBataille(this);
                        } else {
                            throw new IllegalStateException("Il faut mettre une carte 'Réparations' et pas " + this.nom);
                        }
                        break;
                    case "Feu Rouge":
                    case"Essence":
                    case"Roue de secours":
                    case"Réparations":
                        if (this.nom.equals("Feu Vert")) {
                            joueur.setBataille(this);
                        } else {
                            throw new IllegalStateException("Il faut mettre une carte 'Feu Vert' et pas " + this.nom);
                        }
                        break;
                    case "Crevaison":
                        if (this.nom.equals("Roue de secours")) {
                            joueur.setBataille(this);
                        } else {
                            throw new IllegalStateException("Il faut mettre une carte 'Roue de secours' et pas " + this.nom);
                        }
                        break;
                    case "Panne d'essence":
                        if (this.nom.equals("Essence")) {
                            joueur.setBataille(this);
                        } else {
                            throw new IllegalStateException("Il faut mettre une carte 'Essence' et pas " + this.nom);
                        }
                        break;
                }
            }
        } else {
            throw new IllegalStateException("Le joueur peut avancer, il n'y a pas de Parade a mettre.");
        }
    }
}
