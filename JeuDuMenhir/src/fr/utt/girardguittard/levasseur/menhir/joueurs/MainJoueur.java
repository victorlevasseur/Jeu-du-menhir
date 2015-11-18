package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;

public class MainJoueur {

	private final Joueur joueur;
	
	private final Manche manche;
	
	public MainJoueur(Joueur joueur, Manche manche) {
		this.joueur = joueur;
		this.manche = manche;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public Manche getManche() {
		return manche;
	}
	
}
