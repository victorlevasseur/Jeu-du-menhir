package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.*;
/**
 * Repr�sente un joueur avec son score et les diff�rentes actions qu'il r�alise au cours d'une manche
 *
 */
public abstract class Joueur {
	
	/**
	 * Score du joueur
	 */
	private int scoreTotal;
	
	/**
	 * La main du joueur
	 */
	//private MainJoueur main;
	
	/**
	 * Constructeur du joueur
	 */
	public Joueur() {
		
	}

	/**
	 * Effectue les op�rations qui ont lieu au cours d'un tour.
	 * Il s'agit de d�cider qu'elle action r�aliser et de l'effectuer.
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	public void jouerTour(Manche manche, Saison tour) {
		
	}
	
	/**
	 * Joue une carte alli�
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	public void jouerCartesAllies(Manche manche, Saison tour) {
		
	}
	
	/**
	 * Incr�mente le score du joueur
	 * @param inc le nombre de points que le joueur vient de gagner
	 */
	public void incrementerScore(int inc) {
		
	}
	
	/**
	 * Permet de d�cider de l'action a r�alis� en fonction de la carte ingr�dient tir�
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param carteChoisie la carte tir�e (out)
	 * @param actionChoisie l'action choisie par le joueur (out)
	 */
	protected abstract void deciderChoixDuTour(Manche manche, Saison tour, CarteIngredient carteChoisie, Action actionChoisie);
	
	/**
	 * Permet de choisir une carte alli�
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le num�ro du joueur
	 */
	//protected abstract CarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel);
}
