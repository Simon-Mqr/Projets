package mille_bornes;

import mille_bornes.cartes.Carte;

import java.util.*;

public class Jeu {

    private TasDeCarte defausse;
    private Joueur joueurActif;
    private final LinkedList<Joueur> joueurs;
    private Joueur prochainJoueur;
    private TasDeCarte sabot;

    public Jeu() {
        //TODO Crée un jeu sans joueur.

        joueurs = new LinkedList<Joueur>();
    }

    public Jeu(Joueur... joueurs) {
        //TODO Crée un jeu avec les joueurs spécifiés.
        this.defausse = new TasDeCarte(false);
        this.joueurs = new LinkedList<Joueur>();

        ajouteJoueurs(joueurs);
        prepareJeu();
    }

    public void activeProchainJoueurEtTireCarte() {
        //TODO Active le joueur suivant de la partie et lui fait tirer une carte si la partie n'est pas terminée.
        if (!estPartieFinie()) {
            joueurActif = prochainJoueur;
            prochainJoueur = joueurs.get((joueurs.indexOf(prochainJoueur) + 1) % joueurs.size());
            this.joueurActif.prendCarte(this.pioche());
        }
        /*
        for(int i = 0; i < joueurs.size(); i++){
            this.joueurs.get((joueurs.indexOf(joueurActif) + i) % joueurs.size()).setProchainJoueur(joueurs.get(joueurs.indexOf(joueurActif) + i + 1 % this.joueurs.size()));
        }
        */
    }

    public void ajouteJoueurs(Joueur...joueurs)throws IllegalStateException{
        if(!this.joueurs.isEmpty()){
            throw new IllegalStateException("La partie a déja commencée !");
        }
        this.joueurs.addAll(Arrays.asList(joueurs));
    }


    public void defausse(Carte carte) {
        //TODO Empile une carte sur la défausse.
        defausse.pose(carte);
    }

    public boolean estPartieFinie() {
        //TODO Teste si la partie est finie.
        for (Joueur j: joueurs) {
            if (j.getKm() == 1000)
                return true;
        }
        // repasser sur tous les joueurs possible aussi, evite un double appel de la méthode
        return sabot.estVide();
    }

    public List<Joueur> getGagnant() {
        //TODO Retourne le gagnant de la partie : le premier à atteindre 1000km.
        ArrayList<Joueur> joueurGagnant = new ArrayList<Joueur>();
        if (sabot.estVide()) {
            int tmp = 0;
            for (Joueur j: joueurs){
                if (tmp < j.getKm()){
                    tmp = j.getKm();
                }
            }
            for (Joueur j: joueurs){
                if (j.getKm() == tmp){
                    joueurGagnant.add(j);
                }
            }
        }

        for (Joueur j : joueurs) {
            if (j.getKm() == 1000) {
                joueurGagnant.add(j);
            }
        }
        return joueurGagnant;
    }

    public Joueur getJoueurActif() {
        return joueurActif;
    }

    public int getNbCarteSabot() {
        return sabot.getNbCartes();
    }

    public boolean joue() {
        //TODO Fait jouer le prochain joueur.
        activeProchainJoueurEtTireCarte();
        boolean test = true;
        while(test) {
            try {

                test = false;
                if (estPartieFinie() || this.sabot.estVide()) {
                    return true;
                }

                System.out.println("\n" + joueurActif.toString());

                System.out.print("Tu possèdes: ");
                for (Carte c : joueurActif.getMain()) {
                    int num = (joueurActif.getMain().indexOf(c) + 1);
                    System.out.print("\033[1;93m/" + "\033[0;97m"+ c.toString() + "\033[1;31m (" + num + ")" + "\033[1;93m" + "\\ \033[1;34m");
                }
                System.out.println("\n");

                int carteChoisie = joueurActif.choisitCarte();

                joueurActif.joueCarte(this, carteChoisie);

                //fait choisir la carte à jouer/défausser au joueur
                //exécute le choix du joueur si possible, ou affiche l'erreur et recommence au 3.

            } catch (IllegalStateException e) {
                System.out.println(e);
                test = true;
            }
        }
            return false;
    }


    public Carte pioche() {
        //TODO Tire une carte du sabot || Check si sabot.pose mieux
        return sabot.prend();
    }

    public void prepareJeu() {
        //TODO Lance le jeu Lance le jeu.
        //randomise l'ordre de jeu des joueurs, fait
        //crée un sabot avec 110 cartes, fait
        //mélange les cartes aléatoirement, fait
        //crée une défausse vide, fait
        //distribue 6 cartes à chaque joueur, une par une, à tour de rôle, fait
        Collections.shuffle(joueurs);

        this.defausse = new TasDeCarte(false);
        this.sabot = new TasDeCarte(true);
        sabot.melangerCartes();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < joueurs.size(); j++) {
                joueurs.get(j).prendCarte(sabot.prend());
            }
        }
        joueurActif = ((LinkedList<Joueur>) this.joueurs).getFirst();
        LinkedList<Joueur> joueurLinkedList = this.joueurs;
        for (int i = 0, joueurLinkedListSize = joueurLinkedList.size(); i < joueurLinkedListSize; i++) {
            Joueur j = joueurLinkedList.get(i);
            j.setProchainJoueur(joueurs.get((i + 1) % this.joueurs.size()));
        }
        this.setProchainJoueur(joueurActif.getProchainJoueur());
    }

    public Carte regardeDefausse() {
        return defausse.regarde();
    }

    public void setProchainJoueur(Joueur prochainJoueurActif) {
        this.prochainJoueur = prochainJoueurActif;
    }

    @Override
    public String toString() {
        String jeuDecrit = "";
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i) == joueurActif) {
                jeuDecrit += "-> ";
            } else {
                jeuDecrit += "   ";
            }
            jeuDecrit += joueurs.get(i).toString() + "\n";
        }
        return jeuDecrit;
    }
}
