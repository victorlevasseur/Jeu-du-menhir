package fr.utt.girardguittard.levasseur.menhir.cartes;

import java.util.HashMap;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class ChiensDeGarde extends CarteAllies{
	
	public ChiensDeGarde(HashMap<Saison, Integer> forces) {
		super(forces);
	}
	
	/**
	 * Créée une carte alliés ChienDeGarde à partir des forces sous forme d'un tableau à 4 valeurs
	 * représentant les forces pour chaque saisons (de PRINTEMPS à HIVER).
	 * @param forces un tableau de forces
	 * @return une carte ChienDeGarde
	 */
	public static ChiensDeGarde CreerAvecTableau(int[] forces) {
		if(forces.length != 4) {
			//TODO: Exceptions !
		}
		
		HashMap<Saison, Integer> forcesMap = new HashMap<Saison, Integer>();
		
		forcesMap.put(Saison.PRINTEMPS, forces[0]);
		forcesMap.put(Saison.ETE, forces[1]);
		forcesMap.put(Saison.AUTOMNE, forces[2]);
		forcesMap.put(Saison.HIVER, forces[3]);
		
		return new ChiensDeGarde(forcesMap);
	}
	
	public String getNom() {
		return "Chiens de garde";
	}

	/**
	 * Ajoute de la défense au joueur
	 * Ceci lui permet de se protéger contre une attaque de farfadet et de perdre moins de graines
	 * @param manche la manche en cours
	 * @param main la main du joueur utilisant la carte
	 * @param joueurCible le joueur ciblé par l'action (inutile)
	 * @param tour le tour en cours
	 */
	public int agir(Manche manche, MainJoueur main, int joueurCible, Saison tour){
		main.setDefenseChienDeGarde(this.getForce(tour));
		
		return main.getDefenseChienDeGarde();
	}
}
