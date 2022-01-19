
package mille_bornes;

import mille_bornes.cartes.*;
import mille_bornes.cartes.attaque.FeuRouge;
import mille_bornes.cartes.attaque.LimiteVitesse;
import mille_bornes.cartes.botte.AsDuVolant;
import mille_bornes.cartes.botte.Citerne;
import mille_bornes.cartes.botte.Increvable;
import mille_bornes.cartes.botte.VehiculePrioritaire;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class EtatJoueur {

    private int km;
    private boolean limiteVitesse;
    private final Joueur joueur;
    private final Stack<Bataille> pileBataille;
    private final List<Carte> main;
    private final List<Botte> bottes;
    private int nb200;

    public EtatJoueur(Joueur joueur) {

        this.joueur = joueur;
        this.km = km;
        this.limiteVitesse = false;
        this.pileBataille = new Stack<Bataille>();
        pileBataille.add(new FeuRouge());
        this.main = new ArrayList<Carte>();
        this.bottes = new ArrayList<Botte>();
        int nb200 = 0;
    }

    public boolean getLimiteVitesse() {
        return limiteVitesse;
    }

    public void ajouteKm(int km) throws IllegalStateException {
        //TODO Ajoute des kilomètres parcourus au compteur.
        int kmTotal = this.km + km;
        if (kmTotal > 1000) {
            throw new IllegalStateException("Le Kilometrage total a dépassé 1000 km.");
        }
        if (ditPourquoiPeutPasAvancer() != null) {
            throw new IllegalStateException(ditPourquoiPeutPasAvancer());
        }
        if (km == 200){
            nb200 ++;
            if (nb200 == 2){
                throw new IllegalStateException("Tu as déjà posé 2 x Bornes 200");
            }
        }
        this.km = kmTotal;
    }

    public void attaque(Jeu jeu, Attaque carte) throws IllegalStateException { //Attention au coup fourré pour les bottes
        //TODO Applique une attaque à ce joueur.
        if (this.pileBataille.peek().categorie == Categorie.Attaque) {
            throw new IllegalStateException("Le joueur est déjà immobilisé par une attaque !");
        }
        carte.appliqueEffet(jeu, this);
    }

    public void defausseBataille(Jeu jeu) {
        jeu.defausse(this.pileBataille.peek());
        this.pileBataille.pop(); //Suppression de la carte de la pile bataille
    }

    public void defausseCarte(Jeu jeu, int numero) {
        jeu.defausse(this.main.get(numero));
        main.remove(numero);
    }

    public String ditPourquoiPeutPasAvancer() {
        if (!pileBataille.isEmpty()) {
            if (getBataille().categorie == Categorie.Attaque && !getBataille().nom.equals("Limite de Vitesse")) {
                if (getBataille().nom.equals("Feu Rouge")) {
                    return joueur.nom + " ne peut pas avancer car il a un feu rouge";
                }
                if (getBataille().nom.equals("Accident")) {
                    return joueur.nom + " ne peut pas avancer car il a un accident";
                }
                if (getBataille().nom.equals("Crevaison")) {
                    return joueur.nom + " ne peut pas avancer car il a un pneu crevé";
                }
                if (getBataille().nom.equals("Panne d'essence")) {
                    return joueur.nom + " ne peut pas avancer car il a une panne d'essence";
                }
            }
        }
        if (!getBataille().nom.equals("Feu Vert")) { //|| getBottes().get(i).nom.equals("VehiculePrioritaire")){
            return joueur.nom + " ne peut pas avancer sans feu vert ou véhicule prioritaire";
        }

        for (int i = 0; i < getBottes().size(); i++) {
            if ((!(getBataille().categorie == Categorie.Parade || getBataille().nom.equals("Feu Vert")) && getBottes().get(i).nom.equals("VehiculePrioritaire"))) {
                // Regarde si dans le Tas de Botte du joueur il y a le "VehiculePrioritaire", si il l'a et qu'il
                // a une Parade(réparation, essence ...) alors il n'a pas besoin de feu vert et donc il n'est donc pas immobilisé.
                return joueur.nom + " ne peut pas avancer car il n'a pas posé de feu vert !";
            }
        }

        return null;
    }

    public Bataille getBataille() {
        if (pileBataille.isEmpty()) {
            return null;
        } else {
            return pileBataille.lastElement();
        }
    }

    public int getKm() {
        return this.km;
    }

    public List<Carte> getMain() {
        return Collections.unmodifiableList(main);
    }

    public void joueCarte(Jeu jeu, int numero) {
        //TODO Joue la carte spécifiée.
        String choix;
        Scanner input = new Scanner(System.in);
        if (numero < 0) {
            main.remove(numero);
        }
            if (this.main.get(numero).categorie == Categorie.Attaque) {
                boolean test = true;
                while (test) {
                    try {
                        test = false;
                        joueCarte(jeu, numero, jeu.getJoueurActif().choisitAdversaire(main.get(numero)));
                    } catch (IllegalStateException e) {
                        test = true;
                        joueCarte(jeu, numero, jeu.getJoueurActif().choisitAdversaire(main.get(numero)) );
                    }
                }
            }

        else if (this.main.get(numero).categorie == Categorie.Parade) {
            Parade parade = (Parade) this.main.get(numero);
            parade.appliqueEffet(jeu, this);
            main.remove(numero);
        }
        else if (this.main.get(numero).categorie == Categorie.Borne) {
            Borne borne = (Borne) this.main.get(numero);
            ((Borne)this.main.get(numero)).appliqueEffet(jeu, this);
            main.remove(numero);
        }
        else if (this.main.get(numero).categorie == Categorie.Botte) {
            switch (this.main.get(numero).nom) {
                case "AsDuVolant":
                    AsDuVolant asDuVolant = (AsDuVolant) this.main.get(numero);
                    asDuVolant.appliqueEffet(jeu, this);
                    main.remove(numero);
                    break;
                case "Citerne":
                    Citerne citerne = (Citerne) this.main.get(numero);
                    citerne.appliqueEffet(jeu, this);
                    main.remove(numero);
                    break;
                case "Increvable":
                    Increvable increvable = (Increvable) this.main.get(numero);
                    increvable.appliqueEffet(jeu, this);
                    main.remove(numero);
                    break;
                case "VehiculePrioritaire":
                    VehiculePrioritaire vehiculePrioritaire = (VehiculePrioritaire) this.main.get(numero);
                    vehiculePrioritaire.appliqueEffet(jeu, this);
                    main.remove(numero);
                    break;
            }
        }
    }

    public void joueCarte(Jeu jeu, int numero, Joueur adversaire) throws IllegalStateException {
        //TODO Joue la carte spécifiée.
        Attaque attaque = (Attaque) this.main.get(numero);
        adversaire.attaque(jeu, attaque);
        main.remove(numero);
    }

    public void prendCarte(Carte carte) throws IllegalStateException {
        //TODO Ajoute une carte à la main du joueur.
        if (joueur.getMain().size() > 6) {
            throw new IllegalStateException("Vous avez déjà plus de 6 cartes");
        }
        main.add(carte);
    }

    public void setBataille(Bataille carte) {
        this.pileBataille.push(carte);
    }

    public void setLimiteVitesse(boolean limiteVitesse) {
        //TODO Change la limite de vitesse.
        if (limiteVitesse) {
            this.limiteVitesse = true;
        } else {
            this.limiteVitesse = false;
        }
    }

    public List<Botte> getBottes() {
        return bottes;
    }

    public void addBotte(Botte carte) {
        bottes.add(carte);
    }

    @Override
    public String toString() {
        String jeuDecrit;
        jeuDecrit = this.getKm() + " km  ";
        jeuDecrit += "[";
        if (bottes.size() == 0) {
            jeuDecrit += "....";
        } else {
            for (int i = 0; i < bottes.size(); i++) {
                if (getBottes().get(i).nom.equals("Camion Citerne")) {
                    jeuDecrit += "C";
                }
            }
            jeuDecrit += '.';
            for (int i = 0; i < bottes.size(); i++) {
                if (getBottes().get(i).nom.equals("VehiculePrioritaire")) {
                    jeuDecrit += "V";
                }
            }
            jeuDecrit += ".";
            for (int i = 0; i < bottes.size(); i++) {
                if (getBottes().get(i).nom.equals("AsDuVolant")) {
                    jeuDecrit += "A";
                }
            }
            jeuDecrit += ".";
            for (int i = 0; i < bottes.size(); i++) {
                if (getBottes().get(i).nom.equals("Increvable")) {
                    jeuDecrit += "I";
                }
            }
        }
        jeuDecrit += "] \033[1;33mPile \033[1;31m-> \033[1;35m";

        if (!pileBataille.isEmpty()) {
            jeuDecrit += pileBataille.peek().nom;
        }

        jeuDecrit += "\n\033[1;34mLimité à 50: ";
        if (joueur.getLimiteVitesse()) {
            jeuDecrit += "\033[1;31mOui \033[1;34m";
        } else {
            jeuDecrit += "\033[1;31mNon \033[1;34m";
        }

        return jeuDecrit;
    }
}
