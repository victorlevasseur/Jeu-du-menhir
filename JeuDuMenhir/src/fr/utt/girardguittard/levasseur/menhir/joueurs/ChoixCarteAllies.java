package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;

public class ChoixCarteAllies {

	private final CarteAllies carteChoisie;
	
	private final int cible;

	public ChoixCarteAllies(CarteAllies carteChoisie, int cible) {
		this.carteChoisie = carteChoisie;
		this.cible = cible;
	}

	public CarteAllies getCarteChoisie() {
		return carteChoisie;
	}

	public int getCible() {
		return cible;
	}
	
}
