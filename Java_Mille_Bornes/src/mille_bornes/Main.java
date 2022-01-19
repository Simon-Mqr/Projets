package mille_bornes;

/*
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;
 */

import java.io.IOException;
import java.util.*;

public class Main /*extends Application */ {

    private static int i = 0;

   /* public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/


    public static void main(String[] args) {

        System.out.println("\033[1;33m" + "==================================================");
        System.out.println("       \033[1;34m" + "Début de la Partie de Mille Bornes !");
        System.out.println("\033[1;33m" + "==================================================");
        System.out.println("\033[1;34mCombien de joueurs y'a t'il ? \033[1;36m(compris entre " + "\033[1;31m2" +"\033[1;36m et "+ "\033[1;31m8\033[1;36m)\033[1;34m");
        Scanner s = new Scanner(System.in);
        int nbJoueurs = s.nextInt();
        String nom;
        Jeu jeu = null;
        boolean bool = false;
        while (nbJoueurs < 2 || nbJoueurs > 8) {
            System.out.println("le nombre de joueur doit etre compris entre 2 et 8");
            nbJoueurs = s.nextInt();
        }

        Joueur j1 = null;
        Joueur j2 = null;
        Joueur j3 = null;
        Joueur j4 = null;


        switch (nbJoueurs) {
            case 2:
                System.out.println("Donnez le nom des deux joueurs");
                jeu = new Jeu(new Joueur(""), new Joueur(""));
                break;
            case 3:
                System.out.println("Donnez le nom des trois joueurs");
                jeu = new Jeu(new Joueur(""), new Joueur(""), new Joueur(""));
                break;
            case 4:
                System.out.println("Donnez le nom des quatre joueurs");
                jeu = new Jeu(new Joueur(""), new Joueur(""), new Joueur(""), new Joueur(""));
                break;
            case 5:
                System.out.println("Donnez le nom des trois joueurs et du duo");
                jeu = new Jeu(new Joueur(""), new Joueur(""), new Joueur(""), new Joueur(""));
                break;
            case 6:
                System.out.println("Donnez le nom des deux joueurs et des deux duos");
                jeu = new Jeu(new Joueur(""), new Joueur(""), new Joueur(""), new Joueur(""));
                break;
            case 7:
                System.out.println("Donnez le nom du joueur et des trois duos");
                jeu = new Jeu(new Joueur(""), new Joueur(""), new Joueur(""), new Joueur(""));
                break;
            case 8:
                System.out.println("Donnez le nom des duos");
                jeu = new Jeu(new Joueur(""), new Joueur(""), new Joueur(""), new Joueur(""));
                break;
            default:
                System.out.println("le nombre de joueur doit etre compris entre 2 et 8");
                nbJoueurs = s.nextInt();
        }


        while (!bool) {
            bool = jeu.joue();
        }

        System.out.println("Félicitations ");
        for (int i = 0; i < jeu.getGagnant().size(); i++) {
            System.out.println(jeu.getGagnant().get(i).nom + " ");
        }
        System.out.print(", vous avez remporté la partie haut la main !");

    }


    // launch(args);//Java fx

        /*System.out.println("Vous jouez une partie de 4 joueurs, écrivez les noms:");
        Scanner s = new Scanner(System.in);

        System.out.print("Les joueurs seront ");

        while(i < 4){
            Joueur j = new Joueur(s.next());
            i++;
            System.out.print(j.nom);
        }*/


        /*
        TasDeCarte Pioche = new TasDeCarte(true);

        System.out.println(Pioche.getCartes());

        Pioche.melangerCartes(); //Mélange les cartes

        System.out.println(Pioche.getCartes()); //Renvoie la liste des cartes

        System.out.println(Pioche.estVide()); //Teste si la Pioche est vide

        System.out.println(Pioche.getNbCartes()); //Renvoie le nombre de cartes dans la pioche

        System.out.println(Pioche.regarde());

        System.out.println(Pioche.prend());

        System.out.println(Pioche.getNbCartes()); //Test si la carte est enlevée de la pioche



        Jeu testGame = new Jeu(new Joueur("Marc"), new Joueur("Jean"));
         */

}
