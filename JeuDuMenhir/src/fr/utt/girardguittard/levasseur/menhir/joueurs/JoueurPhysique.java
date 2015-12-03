package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.ui.InterfaceManager;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;

/**
 * Représente l'utilisateur de l'application
 *
 */
public class JoueurPhysique extends Joueur {

	/**
	 * Permet de décider de l'action à réaliser en fonction de la carte ingrédient tirée
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	protected ChoixCarteIngredient deciderChoixDuTour(Manche manche, Saison tour){
		return InterfaceManager.get().demanderCarteIngredientAJouer(getMain());
	}
	
	/**
	 * Permet de choisir une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur
	 */
	protected ChoixCarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel) {
		return InterfaceManager.get().demanderCarteAllies(this.getMain());
	}
	
	public boolean veutCarteAllies(Manche manche) {
		return InterfaceManager.get().demanderCarteOuGraines();
	}
}
