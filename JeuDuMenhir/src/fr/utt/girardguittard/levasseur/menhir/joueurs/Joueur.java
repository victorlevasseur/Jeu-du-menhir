package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.*;
/**
 * Représente un joueur avec son score et les différentes actions qu'il réalise au cours d'une manche
 *
 */
public abstract class Joueur {
	
	private int scoreTotal;
	
	private Manche manche;
	
	public Joueur() {
		
	}

	public void jouerTour(Manche manche, Saison tour) {
		
	}
	
	public void jouerCartesAllies(Manche manche, Saison tour) {
		
	}
	
	public void incrementerScore(int inc) {
		
	}
	
	protected abstract void deciderChoixDuTour(Manche manche, Saison tour, CarteIngredient carteChoisie, Action actionChoisie);
	
	protected CarteAllies void deciderCarteAllies(Manche manche, Saison tour, int joueurActuel);
}
