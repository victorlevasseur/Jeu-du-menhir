package fr.utt.girardguittard.levasseur.menhir.cartes;

import java.util.HashMap;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class TaupesGeantes extends CarteAllies{
	
	public TaupesGeantes(HashMap<Saison, Integer> forces) {
		super(forces);
	}
	
	/**
	 * Créée une carte alliés TaupesGeantes à partir des forces sous forme d'un tableau à 4 valeurs
	 * représentant les forces pour chaque saisons (de PRINTEMPS à HIVER).
	 * @param forces un tableau de forces
	 * @return une carte TaupesGeantes
	 */
	public static TaupesGeantes CreerAvecTableau(int[] forces) {
		if(forces.length != 4) {
			//TODO: Exceptions !
		}
		
		HashMap<Saison, Integer> forcesMap = new HashMap<Saison, Integer>();
		
		forcesMap.put(Saison.PRINTEMPS, forces[0]);
		forcesMap.put(Saison.ETE, forces[1]);
		forcesMap.put(Saison.AUTOMNE, forces[2]);
		forcesMap.put(Saison.HIVER, forces[3]);
		
		return new TaupesGeantes(forcesMap);
	}
	
	public String getNom() {
		return "Taupes géantes";
	}
	
	/**
	 * Détruit les menhirs d'un adversaire (diminue le score)
	 * @param manche la manche en cours
	 * @param main la main du joueur utilisant la carte (inutile)
	 * @param joueurCible le joueur ciblé par l'action
	 * @param tour le tour en cours
	 */
	public void agir(Manche manche, MainJoueur main, int joueurCible, Saison tour) {
		MainJoueur mainCible = manche.getJoueur(joueurCible).getMain();
		mainCible.detruireMenhir(this.getForce(tour));
	}

}
