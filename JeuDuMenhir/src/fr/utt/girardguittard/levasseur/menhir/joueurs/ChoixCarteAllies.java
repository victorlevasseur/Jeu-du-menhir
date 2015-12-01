package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;

/**
 * Classe contenant la carte alliés choisie par le joueur.
 *
 */
public class ChoixCarteAllies {

	/**
	 * Le booléen indiquant si le joueur souhaite jouer ou non sa carte alliés.
	 */
	private final boolean joue;
	
	/**
	 * La cible de la carte alliés
	 */
	private final int cible;
	
	/**
	 * Le constructeur de la classe, permettant au joueur d'initialiser les variables
	 * @param carteChoisie la carte choisie
	 * @param cible la cible de l'action
	 */
	public ChoixCarteAllies(boolean joue, int cible) {
		this.joue = joue;
		this.cible = cible;
	}

	/**
	 * Getter de la carte
	 * @return la carte alliés choisie
	 */
	public boolean isJoue() {
		return joue;
	}

	/**
	 * Getter de la cible
	 * @return le numéro du joueur ciblé
	 */
	public int getCible() {
		return cible;
	}
	
}
