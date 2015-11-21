package fr.utt.girardguittard.levasseur.menhir.strategie;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixJoueur;

/**
 * Permet aux joueurs virtuels de décider des actions à réaliser
 */
public interface Strategie {
	
	/**
	 * Permet de décider de l'action à réaliser en fonction de la carte ingrédient tirée
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param carteChoisie la carte tirée (out)
	 * @param actionChoisie l'action choisie par le joueur (out)
	 */
	public ChoixJoueur deciderChoixDuTour(Manche manche, Saison tour);
	
	/**
	 * Permet de choisir une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur
	 */
	//public CarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel); 

}
