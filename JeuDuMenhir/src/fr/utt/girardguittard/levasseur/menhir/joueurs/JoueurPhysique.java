package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;

/**
 * Repr�sente l'utilisateur de l'application
 *
 */
public class JoueurPhysique extends Joueur {

	/**
	 * Permet de d�cider de l'action a r�alis� en fonction de la carte ingr�dient tir�
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param carteChoisie la carte tir�e (out)
	 * @param actionChoisie l'action choisie par le joueur (out)
	 */
	protected void deciderChoixDuTour(Manche manche, Saison tour, CarteIngredient carteChoisie, Action actionChoisie){
		
	}
	
	/**
	 * Permet de choisir une carte alli�
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le num�ro du joueur
	 */
	//protected CarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel){
	
	//}
}
