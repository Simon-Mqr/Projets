package mille_bornes.cartes.botte;
        import mille_bornes.EtatJoueur;
        import mille_bornes.Jeu;
        import mille_bornes.cartes.Attaque;
        import mille_bornes.cartes.Botte;

public class Citerne extends Botte {

    public Citerne() {
        super("Camion Citerne");

    }

    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) {
        joueur.addBotte(this);
    }

    @Override
    public boolean contre(Attaque carte) {
        if(carte.nom == "Panne d'essence") {
            return true;
        }
        // il doit sans doute manquer le "et redemmare apres une parade".
        return false;
    }
}
