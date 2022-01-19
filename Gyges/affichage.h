#include "board.h"

/* Permet d'afficher soit un pion soit une case vide. 
 */
void afficher_case(board game, int ligne, int colonne);

/* Permet d'afficher une ligne composé de pions et de cases vides. 
 */
void afficher_ligne(board game, int colonne);

/* Permet d'afficher le plateau avec les pions et les cases vides. 
 */
void afficher_plateau(board game);

/* Permet d'afficher à quel joueur de jouer. 
 */
void affichage_joueur(player player);

/* Permet d'afficher le gagnant. 
 */
void affiche_gagnant(player player);

/* Permet d'afficher le gagnant lorsque un Bot est en jeu. 
 */
 void affiche_gagnant_bot(player player);