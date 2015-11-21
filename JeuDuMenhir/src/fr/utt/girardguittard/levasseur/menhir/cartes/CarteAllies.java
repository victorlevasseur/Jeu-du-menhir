package fr.utt.girardguittard.levasseur.menhir.cartes;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

/**
 * Représente les cartes "alliés" présente dans la version avancée du jeu.
 * Chaque carte dispose d'une action
 */
public abstract class CarteAllies {
	
	/**
	 * Réalise l'action de la carte
	 * @param manche la manche en cours
	 * @param main la main du joueur utilisant la carte
	 * @param joueurCible le joueur ciblé par l'action (si besoin)
	 * @param tour le tour en cours
	 */
	public abstract void agir(Manche manche, MainJoueur main, int joueurCible, Saison tour);
	
}
