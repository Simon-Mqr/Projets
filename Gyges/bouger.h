#include "board.h"

/* Permet de convertire un chararcter en direction.
 */
direction switch_direction(char lettre_direction);

/* Permet de demander une direction.
 */
direction choix_direction(board g);

/* Permet de déplacer un pion du joueur Sud.
 */
void sud_bouger(board game, player player);

/* Permet de choisir une direction pour le Bot.
 */
direction bot_dir();

/* Permet de déplacer un pion du Bot jouant le joueur Sud.
 */
void sud_bouger_bot(board game);

/* Permet de déplacer un pion du joueur Nord.
 */
void nord_bouger(board game, player player);

/* Permet de déplacer un pion du joueur par rapport a une direction donnée sans Bot.
 */
void bouger_pion(board g, player player);

/* Permet de déplacer un pion du joueur par rapport a une direction donnée avec un Bot.
 */
void bot_bouger_pion(board g, player player);