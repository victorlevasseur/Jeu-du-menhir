package fr.utt.girardguittard.levasseur.menhir.cartes;

import java.util.HashMap;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;


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
	 * Instancie une carte ingrédient grâce à son nom et ses forces données par un tableau au lieu
	 * d'une HashMap<Saison, HashMap<Action, int>>. Le tableau doit contenir 12 entiers sous forme de
	 * 3 suites de 4 valeurs pour chaque action de la carte (GEANT, puis ENGRAIS et enfin FARFADET).
	 * @param nom le nom de la carte
	 * @param forces le tableau des forces de la carte
	 * @return une instance de CarteIngredient créée avec ces informations
	 */
	public static CarteIngredient CreerAvecTableau(String nom, int[] forces) {
		if(forces.length != 12) {
			//TODO: Exception !
		}
		
		//La future map des forces de la carte
		HashMap<Saison, HashMap<Action, Integer>> forcesMap = new HashMap<Saison, HashMap<Action, Integer>>();
		
		//Forces pour Saison.PRINTEMPS
		HashMap<Action, Integer> forcesPr = new HashMap<Action, Integer>();
		forcesPr.put(Action.GEANT, forces[0]);
		forcesPr.put(Action.ENGRAIS, forces[4]);
		forcesPr.put(Action.FARFADET, forces[8]);
		forcesMap.put(Saison.PRINTEMPS, forcesPr);
		
		//Forces pour Saison.ETE
		HashMap<Action, Integer> forcesEte = new HashMap<Action, Integer>();
		forcesEte.put(Action.GEANT, forces[1]);
		forcesEte.put(Action.ENGRAIS, forces[5]);
		forcesEte.put(Action.FARFADET, forces[9]);
		forcesMap.put(Saison.ETE, forcesEte);
				
		//Forces pour Saison.AUTOMNE
		HashMap<Action, Integer> forcesAut = new HashMap<Action, Integer>();
		forcesAut.put(Action.GEANT, forces[2]);
		forcesAut.put(Action.ENGRAIS, forces[6]);
		forcesAut.put(Action.FARFADET, forces[10]);
		forcesMap.put(Saison.AUTOMNE, forcesAut);
		
		//Forces pour Saison.HIVER
		HashMap<Action, Integer> forcesHiv = new HashMap<Action, Integer>();
		forcesHiv.put(Action.GEANT, forces[3]);
		forcesHiv.put(Action.ENGRAIS, forces[7]);
		forcesHiv.put(Action.FARFADET, forces[11]);
		forcesMap.put(Saison.HIVER, forcesHiv);
		
		return new CarteIngredient(nom, forcesMap);
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
	
	/**
	 * Effectue l'action de la carte choisie par le joueur
	 * @param manche la manche en cours
	 * @param mainJoueur la main du joueur
	 * @param joueurCible le joueur ciblé par la carte en cas de farfadet
	 * @param tour le tour en cours
	 * @param action l'action choisie par le joueur
	 */
	public void agir(Manche manche, MainJoueur mainJoueur, int joueurCible, Saison tour, Action action) {
		if(action == Action.GEANT) { //Si le joueur veut voir le géant
			mainJoueur.ajouterGraines(this.getForce(tour, action));
		} else if(action == Action.ENGRAIS) { //Si le joueur veut faire pousser des menhirs
			mainJoueur.fairePousserMenhir(this.getForce(tour, action));
		} else if(action == Action.FARFADET) { //Si le joueur veut voler des graines à un adversaire
			//On ajoute à la main du joueur le nombre de graines que l'on a pu réellement voler à l'adversaire
			mainJoueur.ajouterGraines(manche.getJoueur(joueurCible).getMain().volerGraines(this.getForce(tour, action)));
		}
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();

		buf.append("== " + this.nom + " ==\n");
		buf.append("          P  E  A  H\n");
		buf.append("   Geant  ");
		for(Saison saison : Saison.values()) {
			buf.append(this.getForce(saison, Action.GEANT));
			buf.append("  ");
		}
		buf.append("\n");
		buf.append(" Engrais  ");
		for(Saison saison : Saison.values()) {
			buf.append(this.getForce(saison, Action.ENGRAIS));
			buf.append("  ");
		}
		buf.append("\n");
		buf.append("Farfadet  ");
		for(Saison saison : Saison.values()) {
			buf.append(this.getForce(saison, Action.FARFADET));
			buf.append("  ");
		}
		buf.append("\n");
		
		return buf.toString();
	}
}
