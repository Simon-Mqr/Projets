package mille_bornes;

import mille_bornes.cartes.*;
import mille_bornes.cartes.attaque.Crevaison;
import mille_bornes.cartes.botte.VehiculePrioritaire;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Joueur {

    public final String nom;
    private final EtatJoueur etat;
    private final Scanner input = new Scanner(System.in);
    private Joueur prochainJoueur;

    public Joueur(String nom) {
        System.out.print("Nom : ");
        nom = input.nextLine();
        this.nom = nom;
        etat = new EtatJoueur(this);
    }

    public void attaque(Jeu jeu, Attaque carte) throws IllegalStateException {
        //TODO Applique une attaque à ce joueur.
        this.etat.attaque(jeu, carte);

    }

    public Joueur choisitAdversaire(Carte carte) throws IllegalStateException {
        //TODO Choisit l'adversaire à attaquer
        List<Joueur> joueurs = new ArrayList<Joueur>();
        List<String> nomJoueurs = new ArrayList<String>();
        String name;
        Joueur target = getProchainJoueur();
        System.out.println("Entrez le nom du joueur à attaquer !");
        System.out.println("Ou 'Retour' pour annuler");


        while (target != this) {
            System.out.println(target);
            nomJoueurs.add(target.nom);
            joueurs.add(target);
            target = target.prochainJoueur;
        }

        name = input.next();
        if (name.equalsIgnoreCase("Retour")) {
            System.out.println("Vous avez annulé l'attaque en cours");
            throw new IllegalStateException();
        }

        while (!nomJoueurs.contains(name)) {
            System.out.println("Il n'y a pas de joueur à ce nom, recommencez");

            name = input.next();
        }
        int nb = nomJoueurs.indexOf(name);
        return joueurs.get(nb);
    }


    public int choisitCarte() {
        //TODO Choisit la carte à jouer.
        System.out.println("\nChoisit une carte à jouer (nombre \033[1;31mnégatif \033[1;34mpour défausser et nombre \033[1;32mpositif \033[1;34mpour jouer)");
        int i = 0;
        boolean test = true;
        while (test) {
            try {
                i = input.nextInt();
                test = false;
                while (i < -7 || i > 7 || i == 0) {
                    System.out.println("Seul un entier compris entre 7 et -7 est accepté");
                    i = input.nextInt();
                }

            } catch (InputMismatchException nonEntier) {
                test = true;
                System.out.println("Vous devez entrer un nombre entier");
                i = input.nextInt();
            }
        }

        System.out.println(i);

        return i;
    }

    public void defausseCarte(Jeu jeu, int numero) {
        //TODO Défausse la carte spéficiée
        etat.defausseCarte(jeu, numero);
    }

    public String ditPourquoiPeutPasAvancer() {
        return etat.ditPourquoiPeutPasAvancer();
    }

    public void setProchainJoueur(Joueur j) {
        this.prochainJoueur = j;
    }

    public int getKm() {
        return this.etat.getKm(); //fait
    }

    public boolean getLimiteVitesse() {
        return etat.getLimiteVitesse(); //fait
    }

    public List<Carte> getMain() {
        //TODO Retourne une copie non modifiable de la main
        return etat.getMain(); //fait
    }

    public Joueur getProchainJoueur() {
        return prochainJoueur; //pas sur
    }

    public void joueCarte(Jeu jeu, int numero) throws IllegalStateException {
        //TODO Joue la carte spécifiée
        if (numero < 0) {
            System.out.println("Vous avez defaussé: " +this.getMain().get(-numero - 1));
            defausseCarte(jeu, -numero - 1);
        } else {
            System.out.println("Vous avez joué: " + this.getMain().get(numero - 1));
            etat.joueCarte(jeu, numero - 1);
        }
    }

    public void joueCarte(Jeu jeu, int numero, Joueur adversaire) throws IllegalStateException {
        //TODO Joue la carte spécifiée
        etat.joueCarte(jeu, numero, adversaire);
    }

    public void prendCarte(Carte carte) throws IllegalStateException {
        //TODO Ajoute une carte à la main du joueur. etat.prendCarte()
        if (etat.getMain().size() > 6) {
            throw new IllegalStateException("Vous avez déjà 6 cartes !");
        }
        this.etat.prendCarte(carte);
    }

    public void getBataille() {
        etat.getBataille();
    }

    public void getBottes() {
        etat.getBottes();
    }

    @Override
    public String toString() {
        return "\033[0;101m" + nom + "\033[0m\033[1;34m :  " + etat.toString();
    }

}


