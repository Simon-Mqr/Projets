#include <stdio.h>
#include <stdlib.h>
#include "affichage.h"
#include "board.h"
#include "color.h"

direction switch_direction(char lettre_direction) {
    direction dir;
    switch(lettre_direction) {
            case 'w': dir = WEST; printf("\nVous avez choisi l'Ouest\n");
        break;
            case 'W': dir = WEST; printf("\nVous avez choisi l'Ouest\n");
        break;
            case 's': dir = SOUTH; printf("\nVous avez choisi le Sud\n");
        break;
            case 'S': dir = SOUTH; printf("\nVous avez choisi le Sud\n");
        break;
            case 'e': dir = EAST; printf("\nVous avez choisi l'Est\n");
        break;
            case 'E': dir = EAST; printf("\nVous avez choisi l'Est\n");
        break;
            case 'n': dir = NORTH; printf("\nVous avez choisi le Nord\n");
        break;
            case 'N': dir = NORTH; printf("\nVous avez choisi le Nord\n");
        break;
            case 'g': dir = GOAL;
        break;
            case 'G': dir = GOAL;
        break;
    }
    return dir;
}

direction choix_direction(board g) {
    char lettre_direction;
    direction dir;
    printf("Dans quelle direction voulez vous déplacer la piece (N/S/E/W)\n");
    scanf("%c", &lettre_direction);
    while(lettre_direction == '\n' || lettre_direction == ' ') {
        scanf("%c", &lettre_direction);
    }
    dir = switch_direction(lettre_direction);

    while(is_move_possible (g, dir) == false){
        printf(RED " Erreur" RESET ", Cette direction n'est pas possible: "),
        scanf("%c", &lettre_direction);
        while(lettre_direction == '\n' || lettre_direction == ' ') {
            scanf("%c", &lettre_direction);
        }
        dir = switch_direction(lettre_direction);
    }
    return dir;
}

void nord_bouger(board game, player player) {
	direction direction;
    int ligne_possible = northmost_occupied_line(game);
    int colonne;
    printf(BLUE "Nord" RESET ", Vous pouvez jouer les pieces de la ligne: %d\n",ligne_possible + 1);
    printf("Selectionner un pion avec le numero de sa colonne: ");
    while(scanf("%d", &colonne) == 0) {
            getchar();
        }
        size pion = get_piece_size (game, ligne_possible, colonne - 1);
    while(pion == NONE || ligne_possible < 0 || 5 < ligne_possible) {
        printf(RED " Erreur " RESET ",Votre colonne est invalide, recommencez: ");
        while(scanf("%d", &colonne) == 0) {
            getchar();
        }
        pion = get_piece_size (game, ligne_possible, colonne - 1);
    }
    pick_piece(game, player ,ligne_possible ,colonne - 1);
    int nombre_mouvement = movement_left (game);
    int coup_sup = 0;
    if(1 < nombre_mouvement + coup_sup) {
        while(movement_left(game) != -1) {
            if(movement_left(game) != 0) {
                printf("Il vous reste %d mouvement\n", movement_left(game));
            }
            if(movement_left(game) == 0) {
                printf("Il vous reste %d mouvement\n", coup_sup - 1);
            }
            direction = choix_direction(game);
            move_piece (game, direction);
            afficher_plateau(game);
            if(movement_left(game) == 0) {
                int choix = 0;
                printf("Vous etes tombez sur une piece que voulez vous faire:\n");
                printf("    -" BOLDMAGENTA "1" RESET ". Vous déplacer.\n");
                printf("    -" BOLDMAGENTA "2" RESET ". Téléporter la piece de l'adversaire n'importe où.\n");
                printf("Choix: ");
                while(scanf("%d", &choix) == 0 || choix < 1 || 2 < choix) {
                    printf(RED "Erreur " RESET ",Veuillez saisir une valeur valide: ");
                    getchar();
                }
                if(choix == 1) {
                    coup_sup = get_piece_size(game, picked_piece_column(game), picked_piece_line(game)) + 1;
                    printf("\nVous avez %d de coup en plus car vous etes tomber sur une pieces de taille %d\n", coup_sup - 1, coup_sup - 1);
                } else if(choix == 2) {
                    while(swap_piece (game, rand() % 6, rand() % 6) != OK){}
                    printf("\nLa piece adverse c'est téléporté.\n");
                }
                printf("\n");
            }
        }
        afficher_plateau(game);
    } else {
        direction = choix_direction(game);
        move_piece (game, direction);
        afficher_plateau(game);
    }
}

void sud_bouger(board game, player player) {
	direction direction;
    int ligne_possible = southmost_occupied_line(game);
    int colonne;
    printf(GREEN "SUD" RESET ", Vous pouvez jouer les pieces de la ligne: %d\n",ligne_possible + 1);
    printf("Selectionner un pion avec le numero de sa colonne: ");
    while(scanf("%d", &colonne) == 0) {
            getchar();
        }
        size pion = get_piece_size (game, ligne_possible, colonne - 1);
    while(pion == NONE || ligne_possible < 0 || 5 < ligne_possible) {
        printf(RED " Erreur " RESET ",Votre colonne est invalide, recommencez: ");
        while(scanf("%d", &colonne) == 0) {
            getchar();
        }
        pion = get_piece_size (game, ligne_possible, colonne - 1);
    }
    pick_piece(game, player ,ligne_possible, colonne - 1);
    int nombre_mouvement = movement_left (game);
    int coup_sup = 0;
    if(1 < nombre_mouvement + coup_sup) {
        while(movement_left(game) != -1) {
            if(movement_left(game) != 0) {
                printf("Il vous reste %d mouvement\n", movement_left(game));
            }
            if(movement_left(game) == 0) {
                printf("Il vous reste %d mouvement\n", coup_sup - 1);
            }
            direction = choix_direction(game);
            move_piece (game, direction);
            afficher_plateau(game);
            if(movement_left(game) == 0) {
                int choix = 0;
                printf("Vous etes tombez sur une piece que voulez vous faire:\n");
                printf("    -" BOLDMAGENTA "1" RESET ". Vous déplacer.\n");
                printf("    -" BOLDMAGENTA "2" RESET ". Téléporter la piece de l'adversaire n'importe où.\n");
                printf("Choix: ");
                while(scanf("%d", &choix) == 0 || choix < 1 || 2 < choix) {
                    printf(RED "Erreur " RESET ",Veuillez saisir une valeur valide: ");
                    getchar();
                }
                if(choix == 1) {
                    coup_sup = get_piece_size(game, picked_piece_column(game), picked_piece_line(game)) + 1;
                    printf("\nVous avez %d de coup en plus car vous etes tomber sur une piece de taille %d\n", coup_sup - 1, coup_sup - 1);
                } else if(choix == 2) {
                    while(swap_piece (game, rand() % 6, rand() % 6) != OK){}
                    printf("\nLa piece adverse c'est téléporté.\n");
                }
                printf("\n");
            }
        }
        afficher_plateau(game);
    } else {
        direction = choix_direction(game);
        move_piece (game, direction);
        afficher_plateau(game);
    }
}

direction bot_dir() {
    int choix = rand() % 4;
    direction dir;
    switch(choix) {
        case 0: dir = NORTH;
    break;
        case 1: dir = WEST;
    break;
        case 2: dir = SOUTH;
    break;
        case 3: dir = EAST;
    break;    
    }
    return dir;
}

void sud_bouger_bot(board game) {
	direction direction;
    size pion = NONE;
    int ligne_possible = southmost_occupied_line(game);
    int colonne = rand() % 6;
    while(pion == NONE || ligne_possible < 0 || 5 < ligne_possible) {
        int colonne = rand() % 6;
        pion = get_piece_size (game, ligne_possible, colonne);
    }
    pick_piece(game, SOUTH_P, ligne_possible, colonne);
    int nombre_mouvement = movement_left (game);
    int coup_sup = 0;
    if(1 < nombre_mouvement + coup_sup) {
        while(movement_left(game) != -1) {
            direction = bot_dir();
            move_piece (game, direction);
            afficher_plateau(game);
            if(movement_left(game) == 0) {
                int choix = 0;
                choix = rand() % 2;
                if(choix == 0) {
                    coup_sup = get_piece_size(game, picked_piece_column(game), picked_piece_line(game)) + 1;
                } else if(choix == 2) {
                    while(swap_piece (game, rand() % 6, rand() % 6) != OK){}
                }
            }
        }
        afficher_plateau(game);
    } else {
        direction = bot_dir();
        move_piece (game, direction);
        afficher_plateau(game);
    }
}

void bouger_pion(board g, player player) {
    if(player == NORTH_P){
        nord_bouger(g, player);
    } else if(player == SOUTH_P){
        sud_bouger(g, player);
    }
}

void bot_bouger_pion(board g, player player) {
    if(player == NORTH_P){
        nord_bouger(g, player);
    } else if(player == SOUTH_P){
        sud_bouger_bot(g);
    }
}