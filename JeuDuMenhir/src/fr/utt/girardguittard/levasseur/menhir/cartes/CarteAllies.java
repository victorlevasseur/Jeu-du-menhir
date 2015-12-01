package fr.utt.girardguittard.levasseur.menhir.cartes;

import java.util.HashMap;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

/**
 * Représente les cartes "alliés" présente dans la version avancée du jeu.
 * Chaque carte dispose d'une action
 */
public abstract class CarteAllies {
	
	private HashMap<Saison, Integer> forces;
	
	public CarteAllies(HashMap<Saison, Integer> forces) {
		this.forces = forces;
	}
	
	public abstract String getNom();
	
	public int getForce(Saison tour) {
		return this.forces.get(tour);
	}
	
	/**
	 * Réalise l'action de la carte
	 * @param manche la manche en cours
	 * @param main la main du joueur utilisant la carte
	 * @param joueurCible le joueur ciblé par l'action (si besoin)
	 * @param tour le tour en cours
	 */
	public abstract void agir(Manche manche, MainJoueur main, int joueurCible, Saison tour);
	
	public String toString() {
		StringBuffer buf = new StringBuffer();

		buf.append("== " + this.getNom() + " ==\n");
		buf.append("          P  E  A  H\n");
		buf.append("   Force  ");
		for(Saison saison : Saison.values()) {
			buf.append(this.getForce(saison));
			buf.append("  ");
		}
		
		return buf.toString();
	}
	
}
