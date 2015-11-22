package fr.utt.girardguittard.levasseur.menhir.cartes;

import java.util.HashMap;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class ChiensDeGarde extends CarteAllies{
	
	public ChiensDeGarde(HashMap<Saison, Integer> forces) {
		super(forces);
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
	public void agir(Manche manche, MainJoueur main, int joueurCible, Saison tour){
		main.setDefenseChienDeGarde(this.getForce(tour));
	}
}
