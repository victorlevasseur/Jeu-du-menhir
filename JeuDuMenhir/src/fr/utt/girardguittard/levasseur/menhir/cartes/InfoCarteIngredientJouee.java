package fr.utt.girardguittard.levasseur.menhir.cartes;

import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteIngredient;

/**
 * Classe permettant de transporter les données à notifier aux vues à
 * propos d'une carte ingrédient qui a été jouée. Elle permet, entre autre,
 * de connaître la force effective/réelle de la carte lorsqu'elle a été jouée
 * (exemple : le nombre de graines vraiment volées à un autre joueur).
 */
public class InfoCarteIngredientJouee {
	
	private final int numeroJoueur;
	
	private final Saison saison;
	
	private final CarteIngredient carteJouee;
	
	private final Action actionJouee;
	
	private final int joueurCible;
	
	private final int forceTheorique;
	
	private final int forceReelle;
	
	public InfoCarteIngredientJouee(int numeroJoueur, Saison saison, ChoixCarteIngredient choix, int forceReelle) {
		this.numeroJoueur = numeroJoueur;
		this.saison = saison;
		this.carteJouee = choix.getCarteChoisie();
		this.actionJouee = choix.getActionChoisie();
		this.joueurCible = choix.getCible();
		this.forceTheorique = choix.getCarteChoisie().getForce(saison, choix.getActionChoisie());
		this.forceReelle = forceReelle;
	}

	public int getNumeroJoueur() {
		return numeroJoueur;
	}

	public Saison getSaison() {
		return saison;
	}

	public CarteIngredient getCarteJouee() {
		return carteJouee;
	}

	public Action getActionJouee() {
		return actionJouee;
	}

	public int getJoueurCible() {
		return joueurCible;
	}

	public int getForceTheorique() {
		return forceTheorique;
	}

	public int getForceReelle() {
		return forceReelle;
	}
	
}
