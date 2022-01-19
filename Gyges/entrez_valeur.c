#include <stdio.h>
#include <stdlib.h>
#include "board.h"
#include "affichage.h"
#include "color.h"


size convertir_num_pion(int num_pion) {
    size pion;
    if(num_pion == 1) {
        pion = ONE;
        }
        if(num_pion == 2) {
        pion = TWO;
        }
        if(num_pion == 3) {
        pion = THREE;
    }
    return pion;
}

int numero_colonne() {
    int num_colonne;
    printf("Sur quelle colonne voulez-vous jouer (entre 1 et 6): ");
    while(scanf("%d", &num_colonne) == 0) {
        getchar();
    }
    while(num_colonne < 1 || 6 < num_colonne) {
        printf(RED "Erreur" RESET ", Sur quelle colonne voulez-vous jouer: ");
        while(scanf("%d", &num_colonne) == 0) {
            getchar();
        }
    }
    return num_colonne;
}

int numero_pion() {
    int num_pion;
    printf("Quelle taille de pièce voulez-vous jouer ? (1/2/3): ");
    while(scanf("%d", &num_pion) == 0) {
        getchar();
    }
    while(num_pion < 1 || 3 < num_pion) {
        printf(RED "Erreur" RESET ", Quelle taille de pièce voulez-vous jouer: ");
        while(scanf("%d", &num_pion) == 0) {
            getchar();
        }
    }
    return num_pion;
}

void verif_place_piece(board game, int num_colonne, size pion, player player) {
    while(place_piece(game, pion, player, num_colonne - 1) == EMPTY) {
        printf(RED "\n Erreur" RESET ", cette case est deja prise.\n\n");
        num_colonne = numero_colonne();
        pion = convertir_num_pion(numero_pion());
    }
    while(place_piece(game, pion, player, num_colonne - 1) == FORBIDDEN) {
        printf(RED "\n Erreur" RESET ", Il y a deja assez de pieces de ce type sur le plateau.\n\n");
        pion = convertir_num_pion(numero_pion());
    }
}

void entrez_valeur(board g) {
    int num_colonne;
    size pion;
    player player = NORTH_P;
    while(nb_pieces_available (g, ONE, NORTH_P) != 0 || nb_pieces_available (g, TWO, NORTH_P) != 0 || nb_pieces_available (g, THREE, NORTH_P) != 0 || nb_pieces_available (g, ONE, SOUTH_P) != 0 || nb_pieces_available (g, TWO, SOUTH_P) != 0 || nb_pieces_available (g, THREE, SOUTH_P) != 0) {

        affichage_joueur(player);

        num_colonne = numero_colonne();
        pion = convertir_num_pion(numero_pion());
        
        verif_place_piece(g, num_colonne, pion, player);

        afficher_plateau(g);
        player = next_player(player);
    }
}

void entrez_valeur_bot(board g) {
    int num_colonne;
    size pion;
    player player = NORTH_P;
    while(nb_pieces_available (g, ONE, NORTH_P) != 0 || nb_pieces_available (g, TWO, NORTH_P) != 0 || nb_pieces_available (g, THREE, NORTH_P) != 0 || nb_pieces_available (g, ONE, SOUTH_P) != 0 || nb_pieces_available (g, TWO, SOUTH_P) != 0 || nb_pieces_available (g, THREE, SOUTH_P) != 0) {
        if(player == NORTH_P) {
            printf(BLUE "Nord" RESET ", à vous de jouer.\n");

            num_colonne = numero_colonne();
            pion = convertir_num_pion(numero_pion());
            
            verif_place_piece(g, num_colonne, pion, player);
            player = next_player(player);
        }
        if(player == SOUTH_P) {
            printf(GREEN "Bot" RESET ", à vous de jouer.\n");

            num_colonne = rand() % 6;
            pion = convertir_num_pion(rand() % 3 + 1);
            
            while(place_piece(g, pion, player, num_colonne) == EMPTY) {
                num_colonne = rand() % 6;
                pion = convertir_num_pion(rand() % 3 + 1);
            }
            while(place_piece(g, pion, player, num_colonne) == FORBIDDEN) {
                pion = convertir_num_pion(rand() % 3 + 1);
            }
            player = next_player(player);
        }
        afficher_plateau(g);
    }
}