package mille_bornes.cartes.botte;
        import mille_bornes.EtatJoueur;
        import mille_bornes.Jeu;
        import mille_bornes.cartes.Attaque;
        import mille_bornes.cartes.Botte;

public class AsDuVolant extends Botte {

    public AsDuVolant() {
        super("AsDuVolant");

    }

    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) {
        joueur.addBotte(this);
    }

    @Override
    public boolean contre(Attaque carte) {
        if(carte.nom == "Accident") {
            return true;
        }
        // il doit sans doute manquer le "et redemmare apres une parade".
        return false;
    }
}
