package fr.utt.girardguittard.levasseur.menhir.cartes;

import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteAllies;

/**
 * Classe permettant de transporter les données à notifier aux vues à
 * propos d'une carte alliés qui a été jouée. Elle permet, entre autre,
 * de connaître la force effective/réelle de la carte lorsqu'elle a été jouée
 * (exemple : le nombre de menhirs vraiment détruit chez un adversaire).
 */
public class InfoCarteAlliesJouee {

	private final int numeroJoueur;
	
	private final Saison saison;
	
	private final CarteAllies carteJouee;
	
	private final int joueurCible;
	
	private final int forceTheorique;
	
	private final int forceReelle;
	
	public InfoCarteAlliesJouee(int numeroJoueur, Saison saison, ChoixCarteAllies choix, CarteAllies carteAllies, int forceReelle) {
		this.numeroJoueur = numeroJoueur;
		this.saison = saison;
		this.carteJouee = choix.isJoue() ? carteAllies : null;
		this.joueurCible = choix.getCible();
		this.forceTheorique = carteAllies != null ? carteAllies.getForce(saison) : 0;
		this.forceReelle = forceReelle;
	}

	public int getNumeroJoueur() {
		return numeroJoueur;
	}

	public Saison getSaison() {
		return saison;
	}
	
	public boolean isJouee() {
		return this.carteJouee != null;
	}

	public CarteAllies getCarteJouee() {
		return carteJouee;
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
