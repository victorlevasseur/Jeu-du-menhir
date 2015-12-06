package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.strategie.*;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import java.util.Random;


/**
 * Classe représentant les joueurs controllés par l'IA.
 *
 */
public class JoueurVirtuel extends Joueur{
	
	private Strategie strat;
	
	/**
	 * Le constructeur de joueur virtuel lui assigne aléatoirement une stratégie.
	 */
	public JoueurVirtuel(int numero) {
		super(numero);
		Random random = new Random();
		if (random.nextInt(2) == 0) {
			this.strat = new StrategieAggressive();
		}
		else {
			this.strat = new StrategieDefensive();
		}
	}
	
	/**
	 * Permet de décider de l'action à réaliser en fonction de la carte ingrédient tirée
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	protected ChoixCarteIngredient deciderChoixDuTour(Manche manche, Saison tour){
		return strat.deciderChoixDuTour(manche, tour, main);
	}
	
	/**
	 * Permet de choisir une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur
	 */
	protected ChoixCarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel) {
		return strat.deciderCarteAllies(manche, tour, joueurActuel, main);
	}
	
	public boolean veutCarteAllies() {
		return strat.deciderCarteOuGraines(main);
	}
}
