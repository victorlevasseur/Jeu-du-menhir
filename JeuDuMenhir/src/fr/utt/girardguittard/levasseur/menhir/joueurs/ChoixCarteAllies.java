package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;

/**
 * Classe contenant la carte alliés choisie par le joueur.
 *
 */
public class ChoixCarteAllies {

	/**
	 * La carte alliés choisie par le joueur.
	 */
	private final CarteAllies carteChoisie;
	
	/**
	 * La cible de la carte alliés
	 */
	private final int cible;
	
	/**
	 * Le constructeur de la classe, permettant au joueur d'initialiser les variables
	 * @param carteChoisie la carte choisie
	 * @param cible la cible de l'action
	 */
	public ChoixCarteAllies(CarteAllies carteChoisie, int cible) {
		this.carteChoisie = carteChoisie;
		this.cible = cible;
	}

	/**
	 * Getter de la carte
	 * @return la carte alliés choisie
	 */
	public CarteAllies getCarteChoisie() {
		return carteChoisie;
	}

	/**
	 * Getter de la cible
	 * @return le numéro du joueur ciblé
	 */
	public int getCible() {
		return cible;
	}
	
}
