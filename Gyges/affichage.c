#include <stdio.h>
#include <stdlib.h>
#include "board.h"
#include "color.h"

void afficher_case(board game, int colonne, int ligne) {
    int piece = get_piece_size(game, ligne, colonne);
    if(piece == 0) {
        if(picked_piece_column(game) == -1) {
            printf("%d", piece);
        } else {
            if(ligne == picked_piece_line(game) && colonne == picked_piece_column(game)) {
                printf(RED "*" RESET);
            } else {
                printf("%d", piece);
            }
        }
    } else {
        printf(CYAN "%d" RESET, piece); // Met une piece en couleur.
    }
}

void afficher_ligne(board game, int ligne) {
    int colonne;
    for(colonne = 0; colonne < 5; colonne++) {
        afficher_case(game, colonne, ligne);
        printf("-");
    }
    afficher_case(game, colonne, ligne);
    printf("\n");
}

void afficher_plateau(board game) {
    int ligne;
    system("clear");
	printf("\nPour gagner il faut que une de vos pieces soit sur la ligne la plus loin de\nvotre camps et mettre "BOLD SURLIGNER"g"RESET" lors de votre dernier déplacement avec votre pieces \n\n");
    printf("     N\n/ / / \\ \\ \\ \n");
    for(ligne = 5; 0 <= ligne; ligne--) {
        afficher_ligne(game, ligne);
        if(0 < ligne) {
            printf("| | | | | |\n");
        }
    }
    printf("\\ \\ \\ / / / \n     S\n\n");
}

void affichage_joueur(player player) {
    if(player == NORTH_P) {
        printf(BLUE "Nord" RESET ", à vous de jouer.\n");
    } else if(player == SOUTH_P) {
        printf(GREEN "Sud" RESET ", à vous de jouer.\n");
    }
}

void affiche_gagnant(player player) {
	if(player == SOUTH_P) {
		printf(BOLDBLUE "\n\nBravo, Nord vous avez gagné la partie.\n\n" RESET);
	}
	if(player == NORTH_P) {
		printf(BOLDGREEN "\n\nBravo, Sud vous avez gagné la partie.\n\n" RESET);
	}
}

void affiche_gagnant_bot(player player) {
	if(player == SOUTH_P) {
		printf(BOLDBLUE "\n\nBravo, Nord vous avez gagné la partie.\n\n" RESET);
	}
	if(player == NORTH_P) {
		printf(BOLDGREEN "\n\nBravo, Le Bot a gagné la partie.\n\n" RESET);
	}
}