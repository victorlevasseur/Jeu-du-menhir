package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;

/**
 * Classe contenant la carte ingrédient choisie par le joueur, l'action choisie et sa cible 
 * Cette carte sert de retour à la méthode deciderChoixDuTour
 *
 */
public class ChoixCarteIngredient {
	
	/**
	 * La carte ingrédient choisie par le joueur
	 */
	private final CarteIngredient carteChoisie;
	
	/**
	 * Le numéro correspondant au joueur ciblé
	 */
	private final int cible;
	
	/**
	 * L'action choisie par le joueur
	 */
	private final Action actionChoisie;
	
	/**
	 * Le constructeur de la classe, permettant d'initialiser les variables
	 * @param carteChoisie la carte choisie par le joueur
	 * @param cible le joueur ciblé
	 * @param actionChoisie l'action choisie par le joueur
	 */
	public ChoixCarteIngredient(CarteIngredient carteChoisie, int cible, Action actionChoisie) {
		this.carteChoisie = carteChoisie;
		this.cible = cible;
		this.actionChoisie = actionChoisie;
	}

	/**
	 * Getter de la carte choisie
	 * @return la carte choisie par le joueur
	 */
	public CarteIngredient getCarteChoisie() {
		return this.carteChoisie;
	}
	
	/**
	 * Getter du numéro du joueur ciblé
	 * @return le numéro du joueur ciblé
	 */
	public int getCible() {
		return this.cible;
	}
	
	/**
	 * Getter de l'action
	 * @return l'action choisie par le joueur
	 */
	public Action getActionChoisie() {
		return this.actionChoisie;
	}
}
