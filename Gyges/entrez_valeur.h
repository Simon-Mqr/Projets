#include <stdio.h>
#include "board.h"
#include "affichage.h"

/* Permet de convertir le numero du pion au pion qui correspond. 
 */
size convertir_pion(int num_pion);

/* Permet de retourner le numero de la colonne. 
 */
int numero_colonne();

/* Permet de retourner le numero de la ligne. 
 */
int numero_pion();

/* Permet d'inserer des valeurs pour que l'affichage puisse changer. 
 */
void entrez_valeur(board g);

/* Permet d'inserer des valeurs pour que l'affichage avec un Bot puisse changer. 
 */
void entrez_valeur_bot(board g);