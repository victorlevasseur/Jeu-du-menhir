package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;


/**
 * Classe repr�sentant les joueurs controll� par l'IA.
 *
 */
public class JoueurVirtuel extends Joueur{
	
	/**
	 * Permet de d�cider de l'action a r�alis� en fonction de la carte ingr�dient tir�
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	protected ChoixJoueur deciderChoixDuTour(Manche manche, Saison tour){
		return new ChoixJoueur(null, 0, null);
	}
	
	/**
	 * Permet de choisir une carte alli�
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le num�ro du joueur
	 */
	protected CarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel){
	
	}
}
