package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.ui.InterfaceManager;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;

/**
 * Représente l'utilisateur de l'application
 *
 */
public class JoueurPhysique extends Joueur {
	
	private boolean veutPrendreCarteAllies;
	
	private ChoixCarteIngredient prochainChoixIngredient;
	
	private ChoixCarteAllies prochainChoixAllies;

	/**
	 * Construit un joueur physique.
	 * @param numero le numéro du joueur (à partir de 0)
	 */
	public JoueurPhysique(int numero) {
		super(numero);
		this.veutPrendreCarteAllies = false;
		this.prochainChoixIngredient = null;
		this.prochainChoixAllies = new ChoixCarteAllies(false, -1);
	}

	/**
	 * Permet de décider de l'action à réaliser en fonction de la carte ingrédient tirée
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 */
	protected ChoixCarteIngredient deciderChoixDuTour(Manche manche, Saison tour){
		return this.prochainChoixIngredient;
	}
	
	/**
	 * Permet de choisir une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur
	 */
	protected ChoixCarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel) {
		return this.prochainChoixAllies;
	}
	
	public boolean veutCarteAllies() {
		return this.veutPrendreCarteAllies;
	}

	public void setVeutPrendreCarteAllies(boolean veutPrendreCarteAllies) {
		this.veutPrendreCarteAllies = veutPrendreCarteAllies;
	}

	public void setProchainChoixIngredient(ChoixCarteIngredient prochainChoixIngredient) {
		this.prochainChoixIngredient = prochainChoixIngredient;
	}

	public void setProchainChoixAllies(ChoixCarteAllies prochainChoixAllies) {
		this.prochainChoixAllies = prochainChoixAllies;
	}
}
