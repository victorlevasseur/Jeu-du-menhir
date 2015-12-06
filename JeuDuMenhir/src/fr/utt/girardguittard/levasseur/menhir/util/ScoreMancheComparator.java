package fr.utt.girardguittard.levasseur.menhir.util;

import java.util.Comparator;

import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;

/**
 * Permet de trier de façon décroissante les joueurs suivant leur score dans un manche.
 */
public class ScoreMancheComparator implements Comparator<Joueur> {

	public int compare(Joueur j1, Joueur j2) {
		if(j1.getMain().getNombreMenhir() != j2.getMain().getNombreMenhir()) {
			return j2.getMain().getNombreMenhir() - j1.getMain().getNombreMenhir();
		} else {
			return j2.getMain().getNombreGraine() - j1.getMain().getNombreGraine();
		}
	}

}
