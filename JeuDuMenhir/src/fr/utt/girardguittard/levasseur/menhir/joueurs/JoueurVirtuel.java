package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;


/**
 * Classe représentant les joueurs controllés par l'IA.
 *
 */
public class JoueurVirtuel extends Joueur{
	
	/**
	 * Permet de décider de l'action à réaliser en fonction de la carte ingrédient tirée
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	protected ChoixCarteIngredient deciderChoixDuTour(Manche manche, Saison tour){
		return new ChoixCarteIngredient(null, 0, null);
	}
	
	/**
	 * Permet de choisir une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur
	 */
	protected ChoixCarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel) {
		return null;
	}
}
