package fr.utt.girardguittard.levasseur.menhir.strategie;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteAllies;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

/**
 * Permet aux joueurs virtuels de décider des actions à réaliser
 */
public interface Strategie {
	
	/**
	 * Permet de décider de l'action à réaliser en fonction de la carte ingrédient tirée
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param main la main du joueur
	 * @return le choix d'action et de carte que fait l'algorithme
	 */
	public ChoixCarteIngredient deciderChoixDuTour(Manche manche, Saison tour, MainJoueur main);
	
	/**
	 * Permet de choisir une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur dont c'est le tour actuellement
	 * @param main la main du joueur (qui choisit s'il joue la carte alliés ou non, pas forcément le joueur dont c'est le tour actuellement)
	 * @return le choix de jouer ou non la carte alliés (ainsi que la cible dans le cas des Taupes Geantes)
	 */
	public ChoixCarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel, MainJoueur main); 

	/**
	 * Permet de choisir si l'on prend deux graines ou une carte.
	 * @param main la main du joueur
	 * @return true si une carte allies est choisie
	 */
	public boolean deciderCarteOuGraines(MainJoueur main);
}
