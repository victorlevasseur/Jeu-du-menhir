package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

/**
 * Représente un joueur avec son score et les différentes actions qu'il réalise au cours d'une manche
 *
 */
public abstract class Joueur {
	
	/**
	 * Score du joueur
	 */
	private int scoreTotal;
	
	/**
	 * La main du joueur
	 */
	protected MainJoueur main;
	
	/**
	 * Constructeur du joueur
	 * Un joueur commence avec un score de 0
	 */
	public Joueur() {
		this.scoreTotal = 0;
	}

	/**
	 * Effectue les opérations qui ont lieu au cours d'un tour.
	 * Il s'agit de décider qu'elle action réaliser et de l'effectuer.
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	public void jouerTour(Manche manche, Saison tour) {
		ChoixCarteIngredient choix = deciderChoixDuTour(manche, tour);
		choix.getCarteChoisie().agir(manche, this.main, choix.getCible(), tour, choix.getActionChoisie());
		this.getMain().retirerCarteIngredient(choix.getCarteChoisie());
	}
	
	/**
	 * Permet au joueur de jouer une carte allie s'il en a une.
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	public void jouerCartesAllies(Manche manche, Saison tour, int joueurActuel) {
		if(this.getMain().getCarteAllies() != null)
		{
			ChoixCarteAllies choix = deciderCarteAllies(manche, tour, joueurActuel);
			if(choix.isJoue())
			{
				this.getMain().getCarteAllies().agir(manche, this.getMain(), choix.getCible(), tour);
				this.getMain().retirerCarteAllies();
			}
		}
	}
	
	/**
	 * Incrémente le score du joueur
	 * @param inc le nombre de points que le joueur vient de gagner
	 */
	public void incrementerScore(int inc) {
		this.scoreTotal += inc;
	}
	
	/**
	 * Permet de décider de l'action a réaliser en fonction de la carte ingrédient tirée
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	protected abstract ChoixCarteIngredient deciderChoixDuTour(Manche manche, Saison tour);
	
	/**
	 * Permet de choisir une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur
	 */
	protected abstract ChoixCarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel);
	
	/**
	 * Permet de savoir si le joueur souhaite une carte alliée ou deux graines
	 * @param manche la manche en cours
	 * @return true si le joueur veut une carte alliés
	 */
	public abstract boolean veutCarteAllies(Manche manche);
	
	public int getScore() {
		return this.scoreTotal;
	}
	
	public MainJoueur getMain() {
		return this.main;
	}
	
	public void setMain(MainJoueur main) {
		this.main = main;
	}
}
