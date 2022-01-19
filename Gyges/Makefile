jeu: affichage.o entrez_valeur.o bouger.o board.o menu.o Jeu.c
	gcc -Wall affichage.o entrez_valeur.o bouger.o board.o menu.o Jeu.c -o jeu
affichage.o: affichage.c
	gcc affichage.c -c
entrez_valeur.o: entrez_valeur.c
	gcc entrez_valeur.c -c
bouger.o: bouger.c
	gcc bouger.c -c
menu.o: menu.c
	gcc menu.c -c
remove: menu.o bouger.o entrez_valeur.o affichage.o
	rm menu.o bouger.o entrez_valeur.o affichage.o