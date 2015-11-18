package fr.utt.girardguittard.levasseur.menhir.cartes;

import java.util.HashMap;
import fr.utt.girardguittard.levasseur.menhir.Saison;

/**
 * Représente une carte ingrédient avec son nom et ses forces selon les saisons et actions
 */
public class CarteIngredient {
	
	/**
	 * Nom de la carte
	 */
	private String nom;
	
	/**
	 * Forces de la carte en fonction de la saison et de l'action
	 */
	private HashMap<Saison, HashMap<Action, Integer>> forces;
	
	/**
	 * Construit une carte ingrédient.
	 * @param nom le nom de la carte ingrédient
	 * @param forces les forces de la carte ingrédient
	 */
	public CarteIngredient(String nom, HashMap<Saison, HashMap<Action, Integer>> forces) {
		this.nom = nom;
		this.forces = forces;
	}
	
	/**
	 * Obtient la force de la carte pour une action à une saison donnée.
	 * @param saison la saison
	 * @param action l'action
	 * @return la force de la carte
	 */
	public int getForce(Saison saison, Action action) {
		return this.forces.get(saison).get(action);
	}
	
}
