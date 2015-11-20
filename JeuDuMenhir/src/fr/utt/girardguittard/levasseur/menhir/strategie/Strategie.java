package fr.utt.girardguittard.levasseur.menhir.strategie;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixJoueur;

/**
 * Permet aux joueurs virtuels de d�cider des actions � r�aliser
 */
public interface Strategie {
	
	/**
	 * Permet de d�cider de l'action a r�alis� en fonction de la carte ingr�dient tir�
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param carteChoisie la carte tir�e (out)
	 * @param actionChoisie l'action choisie par le joueur (out)
	 */
	public ChoixJoueur deciderChoixDuTour(Manche manche, Saison tour);
	
	/**
	 * Permet de choisir une carte alli�
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le num�ro du joueur
	 */
	public CarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel); 

}
