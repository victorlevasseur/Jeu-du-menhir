package fr.utt.girardguittard.levasseur.menhir.cartes;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class TaupesGeantes extends CarteAllies{
	
	/**
	 * D�truit les menhirs d'un adversaire (diminue le score)
	 * @param manche la manche en cours
	 * @param main la main du joueur utilisant la carte
	 * @param joueurCible le joueur cibl� par l'action
	 * @param tour le tour en cours
	 */
	public void agir(Manche manche, MainJoueur main, int joueurCible, Saison tour) {
		MainJoueur mainCible = manche.getJoueur(joueurCible).getMain();
	}

}
