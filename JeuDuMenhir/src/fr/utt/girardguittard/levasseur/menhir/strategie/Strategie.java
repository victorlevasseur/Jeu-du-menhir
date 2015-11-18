package fr.utt.girardguittard.levasseur.menhir.strategie;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;

/**
 * Permet aux joueurs virtuels de décider des actions à réaliser
 */
public interface Strategie {
	
	/**
	 * Permet de décider de l'action a réalisé en fonction de la carte ingrédient tiré
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param carteChoisie la carte tirée (out)
	 * @param actionChoisie l'action choisie par le joueur (out)
	 */
	public void deciderChoixDuTour(Manche manche, Saison tour, CarteIngredient carteChoisie, Action actionChoisie);
	
	/**
	 * Permet de choisir une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur
	 */
	public CarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel); 

}
