package mille_bornes.cartes;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;

public abstract class Attaque extends Bataille {

    public Attaque(String nom) {
        super(Categorie.Attaque, nom);
    }

    public boolean contre(Attaque carte){
        return false;
    }

    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {
        if(joueur.ditPourquoiPeutPasAvancer() == null) {
            for (Botte bottes : joueur.getBottes()) {
                switch (bottes.nom) {
                    case "AsDuVolant":
                        if (this.nom.equals("Accident")) {
                            throw new IllegalStateException("Le joueur a la botte As du Volant");
                        }
                        break;
                    case "VehiculePrioritaire":
                        if (this.nom.equals("Feu Rouge") || this.nom.equals("Limite de Vitesse")) {
                            throw new IllegalStateException("Le joueur a la botte Vehicule Prioritaire");
                        }
                        break;
                    case "Increvable":
                        if (this.nom.equals("Crevaison")) {
                            throw new IllegalStateException("Le joueur a la botte Increvable");
                        }
                        break;
                    case "Citerne":
                        if (this.nom.equals("Panne d'essence")) {
                            throw new IllegalStateException("Le joueur a la botte Citerne");
                        }
                        break;
                }
            }
            if(this.nom.equals("Limite de Vitesse")) {
                if (joueur.getLimiteVitesse()) {
                    throw new IllegalStateException("Vous ne pouvais pas limiter la vitesse du joueur.");
                } else {
                    joueur.setLimiteVitesse(true);
                }
            } else {
                joueur.setBataille(this);
            }
        } else {
            throw new IllegalStateException(joueur.ditPourquoiPeutPasAvancer());
        }
    }

}
