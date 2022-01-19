// Je ne me sert pas de se fichier car je n'arrive pas a le faire fonctionner
// Mais je le laisse car j'ai quand meme essayer de le faire.

#include <stdlib.h>
#include "board.h"

/**
 * \file board.c
 *
 * \brief Source code associated with \ref board.h
 *
 * \author You?
 */

typedef struct {
    size taille;
    int ligne;
    int colonne;
    player joueur;
}piked_piece;

/**
 * @brief The board of the game, define it as you wish.
 */
struct board_s {
    int win;
    size tableau[DIMENSION][DIMENSION];
    player joueur;
    piked_piece piece;
};

board new_game(){ 
	// memory allocation for the board (leave it as is)
	board new_board = malloc(sizeof(struct board_s));
	// TODO: Insérer les traitements que vous voudriez faire.
    new_board->win = 0;
    for(int i = 0; i < DIMENSION; i++) {
        for(int j = 0; j < DIMENSION; j++) {
            new_board->tableau[i][j] = NONE;
        }
    }
    new_board->joueur = NO_PLAYER;
    new_board->piece.ligne = -1;
    new_board->piece.colonne = -1;

	return new_board;
}

void destroy_game(board game){
	// freeing memory, necessary for memory allocated with malloc (leave as is)
	free(game);
}

player next_player(player current_player) {
    player next_player;
    if(current_player == NORTH_P) {
        next_player = SOUTH_P;
    } else if(current_player == NORTH_P) {
        next_player = NORTH_P;
    }
    return next_player;
}

board copy_game(board original_game) {
    return original_game;
}

size get_piece_size(board game, int line, int column) {
    return game->tableau[line][column];
}

player get_winner(board game) {
    if(game->win == 1) {
        return game->piece.joueur;
    }
    return NO_PLAYER;
}

int southmost_occupied_line(board game) {
    for(int i = 0; i < DIMENSION; i++) {
        for(int j = 0; j < DIMENSION; j++) {
            if(get_piece_size(game, i, j) != NONE) {
                return i;
            }
        }
    }
    return 0;
}

int northmost_occupied_line(board game) {
    for(int i = DIMENSION - 1; 0 - 1 < i; i++) {
        for(int j = 0; j < DIMENSION; j++) {
            if(get_piece_size(game, i, j) != NONE) {
                return i;
            }
        }
    }
    return 0;
}

player picked_piece_owner(board game) {
    return game->piece.joueur;
}

size picked_piece_size(board game) {
    return game->piece.taille;    
}

int picked_piece_line(board game) {
    return game->piece.ligne;    
}

int picked_piece_column(board game) {
    return game->piece.colonne;    
}

// Je sais pas comment faire pour "decrementer la valeur" tout en la retournant.
int movement_left(board game) {
    if(picked_piece_size(game) == THREE) {
        return 3;
    }
    if(picked_piece_size(game) == TWO) {
        return 2;
    }
    if(picked_piece_size(game) == ONE) {
        return 1;
    }
    if(picked_piece_size(game) == NONE) {
        return -1;
    }
    if(get_piece_size(game, game->piece.ligne, game->piece.colonne) != NONE) {
        return 0;
    }
    return 0;
}

int nb_pieces_available(board game, size piece, player player) {
    int piece_max = 2;
    int piece_posee = 0;
    if(player == NORTH_P) {
            for(int i = 0; i < 6; i++) {
            if(piece == game->tableau[i][5]) {
                piece_posee++;
            }
        }
    }
    if(player == SOUTH_P) {
            for(int i = 0; i < 6; i++) {
            if(piece == game->tableau[i][0]) {
                piece_posee++;
            }
        }
    }
    int piece_restante = piece_max - piece_posee;
    return piece_restante;
}

return_code place_piece(board game, size piece, player player, int column) {
    if(column < 0 || 5 < column || player == NO_PLAYER || piece == NONE) {
        return PARAM;
    }
    if(nb_pieces_available(game, piece, player) < 1) {
        return FORBIDDEN;
    }

    if(player == NORTH_P) {
        if(game->tableau[5][column] != NONE) {
            return EMPTY;
        }
        game->tableau[5][column] = piece;
        return OK;
    }
    if(player == SOUTH_P) {
        if(game->tableau[0][column] != NONE) {
            return EMPTY;
        }
        game->tableau[0][column] = piece;
        return OK;
    }
    return OK;
}

return_code pick_piece(board game, player current_player, int line, int column) {
    game->piece.joueur = current_player;
    game->piece.ligne = line;
    game->piece.colonne = column;
    game->piece.taille = get_piece_size(game, line, column);

    if(game->piece.taille == NONE) {
        return EMPTY;
    }
    if(current_player == NORTH_P) {
        if(northmost_occupied_line(game) != line || get_winner(game) != NONE) {
            return FORBIDDEN;
        }
    } else {
        if(southmost_occupied_line(game) != line || get_winner(game) != NONE) {
            return FORBIDDEN;
        }
    }
    if(line < 0 || DIMENSION > line || column < 0 || DIMENSION > column) {
        return PARAM;
    }
    return OK;
}

// Manque des conditions...
bool is_move_possible(board game, direction direction) {
    if(game->piece.joueur == NORTH_P) {
        if(direction == GOAL && game->piece.ligne == 0) {
            return true;
        } else {
            return false;
        }
    }
    if(game->piece.joueur == SOUTH_P) {
       if(direction == GOAL && game->piece.ligne == 5) {
            return true;
        } else {
            return false;
        } 
    }
}

return_code move_piece(board game, direction direction) {
    if(game->piece.taille == NONE) {
        return EMPTY;
    }
    if(direction == NORTH) {
        game->piece.ligne++;
        if(5 < game->piece.ligne) {
            return PARAM;
        }
        return OK;
    }
    if(direction == SOUTH) {
        game->piece.ligne--;
        if(5 < game->piece.ligne) {
            return PARAM;
        }
        return OK;
    }
    if(direction == EAST) {
        game->piece.colonne++;
        if(5 < game->piece.colonne) {
            return PARAM;
        }
        return OK;
    }
    if(direction == WEST) {
        game->piece.colonne--;
        if(5 < game->piece.colonne) {
            return PARAM;
        }
        return OK;
    }
    if(direction == GOAL) {
        if(game->piece.joueur == NORTH) {
        if(game->piece.ligne != 5) {
            return FORBIDDEN;
        } else {
            game->win= 1;
            return OK;
        }
        }
        if(game->piece.joueur == SOUTH) {
        if(game->piece.ligne != 0) {
            return FORBIDDEN;
        } else {
            game->win= 1;
            return OK;
        }
        }
    }
    return OK;
}

return_code swap_piece(board game, int target_line, int target_column) {
    if(target_line < 0 || 5 < target_line || target_column < 0 || 5 < target_column) {
        return PARAM;
    }
    if(movement_left(game) == 0) {
        return EMPTY;
    }
    if(get_piece_size(game, target_line, target_column) != NONE) {
        return FORBIDDEN;
    }

    size other_piece = game->tableau[game->piece.ligne][game->piece.colonne];
    game->tableau[target_line][target_column] = other_piece;
    return OK;
}

return_code cancel_movement(board game) {
    
}

return_code cancel_step(board game) {

}