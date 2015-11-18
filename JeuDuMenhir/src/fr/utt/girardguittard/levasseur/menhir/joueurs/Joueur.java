package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.*;
/**
 * Représente un joueur avec son score et les différentes actions qu'il réalise au cours d'une manche
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
	 * Effectue les opérations qui ont lieu au cours d'un tour.
	 * Il s'agit de décider qu'elle action réaliser et de l'effectuer.
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	public void jouerTour(Manche manche, Saison tour) {
		
	}
	
	/**
	 * Joue une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	public void jouerCartesAllies(Manche manche, Saison tour) {
		
	}
	
	/**
	 * Incrémente le score du joueur
	 * @param inc le nombre de points que le joueur vient de gagner
	 */
	public void incrementerScore(int inc) {
		
	}
	
	/**
	 * Permet de décider de l'action a réalisé en fonction de la carte ingrédient tiré
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param carteChoisie la carte tirée (out)
	 * @param actionChoisie l'action choisie par le joueur (out)
	 */
	protected abstract void deciderChoixDuTour(Manche manche, Saison tour, CarteIngredient carteChoisie, Action actionChoisie);
	
	/**
	 * Permet de choisir une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur
	 */
	//protected abstract CarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel);
}
