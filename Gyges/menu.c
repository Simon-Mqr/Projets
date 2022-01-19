#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <termios.h>
#include <string.h>
#include <unistd.h>
#include "board.h"
#include "affichage.h"
#include "entrez_valeur.h"
#include "bouger.h"
#include "color.h"
#define JOUER " \033[1m- \033[4mJouer\033[0m"
#define REGLES " \033[1m- \033[4mRegles\033[0m"
#define CREDIT " \033[1m- \033[4mCredit\033[0m"
#define QUITTER " \033[1m- \033[4mQuitter\033[0m"
#define BOT " \033[1m- \033[4mOption Bot\033[0m"
#define AVEC " \033[1m- \033[4mAvec Bot\033[0m"
#define SANS " \033[1m- \033[4mSans Bot\033[0m"

void bienvenu() {
    system("clear");
    printf("\n\n\n\n");
    printf("                          ");
    printf("════════════════════════════\n");
    printf("                           ");
    printf(BLUE SURLIGNER "BIENVENUE SUR LE JEU GYGES\n" RESET);
    printf("                          ");
    printf("════════════════════════════\n");
    printf("\n\n\n");
}

int regles() {
    system("clear");
    printf (BOLD SURLIGNER"CONTENU"RESET"\n");
    printf ("Un plateau de 36 cases communiquant entre elles et une case «but»\n");
    printf ("de chaque côté; 12 pièces en bois de 3 tailles différentes :\n");
    printf ("4 simples, 4 doubles et 4 triples.\n");
    printf ("BUT DU JEU\n");
    printf ("Amener une quelconque pièce sur la case «but» de l’autre côté du\n");
    printf ("plateau.\n\n");
    printf (BOLD SURLIGNER"PREPARATION"RESET"\n");
    printf ("Les joueurs se placent de chaque côté du plateau et les pièces sont\n");
    printf ("partagées: chacun prend 2 triples, 2 doubles et 2 simples. A tour de\n");
    printf ("rôle, chacun place une à une ses 6 pièces comme il le souhaite sur\n");
    printf ("l’une des 6 cases de sa ligne de départ.\n\n");
    printf (BOLD SURLIGNER"DEROULEMENT DU JEU"RESET"\n");
    printf ("Les pièces n’appartiennent à personne, et on doit jouer celles qui\n");
    printf ("sont le plus proches de soi!\n");
    printf ("Le premier qui a placé une pièce fait le premier mouvement, puis\n");
    printf ("chacun à son tour déplace une pièce.\n");
    printf ("On choisit la pièce jouée, mais en la prenant obligatoirement sur la\n");
    printf ("ligne la plus proche de soi.\n");
    printf ("Au début, les joueurs doivent donc déplacer les 6 pièces situées\n");
    printf ("sur leur ligne de départ. Ensuite, quand plus aucune des cases de sa première\n");
    printf ("ligne n’est occupée, le joueur doit déplacer une pièce de sa deuxième\n");
    printf ("ligne, et ainsi de suite… Il peut arriver que toutes les pièces de la\n");
    printf ("première ligne soient bloquées par celles de la deuxième ligne; dans\n");
    printf ("ce cas, on doit exceptionnellement jouer une des pièces de la ligne\n");
    printf ("suivante.\n");
    printf ("Les pièces se déplacent de case en case, de droite à gauche et\n");
    printf ("d’avant en arrière (jamais en diagonale), et en fonction de leur taille:\n");
    printf ("une pièce simple se déplace d’une case, une double de 2 cases, et\n");
    printf ("une triple de 3 cases. Une pièce peut changer de direction au cours\n");
    printf ("de son mouvement.\n");
    printf ("Si le mouvement se termine sur une case vide, il s’arrête là et\n");
    printf ("c’est à l’adversaire de jouer.\n");
    printf ("La pièce peut accéder à une case occupée, si et seulement si\n");
    printf ("elle y arrive au terme de son mouvement: une pièce simple peut\n");
    printf ("donc accéder directement à une case occupée, une pièce double\n");
    printf ("doit d’abord passer par une case vide et une pièce triple doit\n");
    printf ("traverser deux cases vides. Lorsque la pièce arrive sur\n");
    printf ("une case occupée, elle rebondit sur la pièce en place et entame\n");
    printf ("immédiatement un nouveau mouvement d’une, deux ou trois cases\n");
    printf ("(ici encore, les cases traversées doivent être libres); ce nouveau\n");
    printf ("mouvement est fonction de la taille (simple, double ou triple) de la\n");
    printf ("pièce sur laquelle elle rebondit. Une pièce peut rebondir\n");
    printf ("plusieurs fois de suite, voire traverser tout le plateau en une seule\n");
    printf ("série de rebonds successifs.\n");
    printf ("Lors d’un même mouvement, une pièce peut repasser par une\n");
    printf ("même case, à condition d’y entrer et/ou d’en sortir par un chemin\n");
    printf ("différent.\n\n");
    printf (BOLD SURLIGNER"FIN DE PARTIE"RESET"\n");
    printf ("Le gagnant est le premier joueur à amener une pièce sur la case\n");
    printf ("«but» de l’autre côté du plateau. La case «but» peut être atteinte\n");
    printf ("directement de n’importe quelle case de la dernière ligne. La pièce ne\n");
    printf ("peut accéder à cet objectif qu’au terme de son mouvement.\n");
    printf ("Une pièce ne peut pas passer par la case «but»; celle-ci n’est\n");
    printf ("occupée qu’en cas de victoire.\n");
    printf ("C’est presque toujours par une série de rebonds qu’on va sortir une\n");
    printf ("pièce du coté adverse pour gagner la partie.\n");
    printf("\n\n\n"SURLIGNER"Appuyez sur entrer pour retourner au menu."RESET);
    char entrez;
    while(scanf("%c", &entrez) == 0 || entrez != '\n') {}
    return 1;
}

void Gyges_bot() {
    board game = new_game();
    afficher_plateau(game);
    entrez_valeur_bot(game);
	player winner = NO_PLAYER;
    player player = NORTH_P;
	while(winner == NO_PLAYER) {
    	bot_bouger_pion(game, player);
        player = next_player(player);
		winner = get_winner(game);
	}
	affiche_gagnant_bot(player);
	destroy_game(game);
	printf("La partie est finie et le tableau a été supprimé.\n");
    printf("\n\n\n"SURLIGNER"Tapez sur «q» pour quitter le jeu."RESET);
    char entrez;
    while(scanf("%c", &entrez) == 0 || entrez != 'q') {}
    system("clear");
	exit(EXIT_SUCCESS);
}

void Gyges() {
    board game = new_game();
    afficher_plateau(game);
    entrez_valeur(game);
	player winner = NO_PLAYER;
    player player = NORTH_P;
	while(winner == NO_PLAYER) {
    	bouger_pion(game, player);
        player = next_player(player);
		winner = get_winner(game);
	}
	affiche_gagnant(player);
	destroy_game(game);
	printf("La partie est finie et le tableau a été supprimé.\n");
    printf("\n\n\n"SURLIGNER"Tapez sur «q» pour quitter le jeu."RESET);
    char entrez;
    while(scanf("%c", &entrez) == 0 || entrez != 'q') {}
    system("clear");
	exit(EXIT_SUCCESS);
}

void jouer(int mode) {
    if(mode == 1) {
        Gyges_bot();
    } else {
        Gyges();
    }
}

int credit() {
    system("clear");
    printf(BOLD SURLIGNER"Crée par:\n"RESET);
    printf("    - Maquaire Simon\n");
    printf("    - Andres Marius\n\n");
    printf(BOLD SURLIGNER"Merci à:\n"RESET);
    printf("    - ox223252 de openclassrooms pour le code (laisser a disposition)\n");
    printf("      pour la selection dans le menu\n");
    printf("\n\n\n"SURLIGNER"Appuyez sur entrer pour retourner au menu."RESET);
    char entrez;
    while(scanf("%c", &entrez) == 0 || entrez != '\n') {}
    return 1;
}

int menu(int argc, ...) {
    static struct termios oldMask, newMask;
    va_list list;
    char **table  = NULL;
    int i = 0;
    int choix = -1;
    int position = 0;
    tcgetattr (STDIN_FILENO, &oldMask);
    newMask = oldMask;
    newMask.c_lflag &= ~(ICANON);
    newMask.c_lflag &= ~(ECHO);
    tcsetattr(STDIN_FILENO, TCSANOW, &newMask);
    table = malloc (sizeof (char *) * argc);
    va_start (list, argc);
    for (i = 0; i < argc; i++) {
        table[ i ] = va_arg (list, char*);
    }
    va_end (list);
    do {
        for (i = 0; i < argc; i++) {
            printf (" %c %s\n", ( position == i )?'>':' ', table[ i ]);
        }
        switch(i = getchar()){
                case 0x41: position = (position - 1 + argc) % argc;
            break;
                case 0x42: position = (position + 1) % argc;
            break;
                case '\n': choix = position;
            break;
        }
        if (choix < 0) {
            printf ("\e[%dA", argc);
        }
    }
    while(choix < 0);
    free (table);
    tcsetattr(STDIN_FILENO, TCSANOW, &oldMask);
    return choix;
}

int choix_bot() {
    system("clear");
    printf(BOLD SURLIGNER"Option de Bot.\n\n"RESET);
    int choix = menu(2, SANS, AVEC);
    return choix;
}

int retoure() {
    printf("\n\n\n"SURLIGNER"Appuyez sur entrer pour retourner au menu."RESET);
    char entrez;
    while(scanf("%c", &entrez) == 0 || entrez != '\n') {}
    return 1;
}

int return_menu() {
    bienvenu();
    int choix = menu(5, JOUER, REGLES, CREDIT, BOT, QUITTER);
    return choix;
}     

int selection() {
    int bot;
    int choix = return_menu();
    int retour;
    switch(choix) {
            case 0: jouer(bot);    
        break;      
            case 1: retour = regles();
        break;
            case 2: retour = credit();
        break;
            case 3: bot = choix_bot(); retour = retoure();
        break;
            case 4: system("clear"); exit(EXIT_SUCCESS);
        break;
    }
    return retour;
}

void affichage_menu() {
    int retour;
    retour = selection();
    while(retour == 1) {
        retour = selection();
    }
}