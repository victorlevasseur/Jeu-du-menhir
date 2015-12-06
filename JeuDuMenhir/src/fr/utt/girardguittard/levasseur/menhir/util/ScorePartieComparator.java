package fr.utt.girardguittard.levasseur.menhir.util;

import java.util.Comparator;

import fr.utt.girardguittard.levasseur.menhir.Partie;
import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;

/**
 * Comparateur pour trier les Joueurs selon leur nombre de menhirs en fin de partie.
 */
public class ScorePartieComparator implements Comparator<Joueur> {
	
	private Partie partie;
	
	public ScorePartieComparator(Partie partie) {
		this.partie = partie;
	}

	public int compare(Joueur j1, Joueur j2) {
		if(partie.isPartieAvancee()) {
			//Si c'est une partie avancée, on compare juste par rapport au nombre total de menhirs
			return j2.getScore() - j1.getScore();
		} else {
			//Si c'est une partie simple, il faut prendre en compte les graines en cas d'égalité de menhirs
			if(j1.getMain().getNombreMenhir() != j2.getMain().getNombreMenhir()) {
				return j2.getMain().getNombreMenhir() - j1.getMain().getNombreMenhir();
			} else {
				return j2.getMain().getNombreGraine() - j1.getMain().getNombreGraine();
			}
		}
	}

}
