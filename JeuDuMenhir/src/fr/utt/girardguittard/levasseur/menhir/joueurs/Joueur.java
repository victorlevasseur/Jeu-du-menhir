package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.InfoCarteAlliesJouee;
import fr.utt.girardguittard.levasseur.menhir.cartes.InfoCarteIngredientJouee;
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
	 * Le numéro du joueur
	 */
	private final int numero;
	
	/**
	 * Constructeur du joueur.
	 * Un joueur commence avec un score de 0.
	 * @param numero le numéro du joueur (à partir de 0)
	 */
	public Joueur(int numero) {
		this.scoreTotal = 0;
		this.numero = numero;
	}

	/**
	 * Effectue les opérations qui ont lieu au cours d'un tour.
	 * Il s'agit de décider qu'elle action réaliser et de l'effectuer.
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @return 
	 * @throws CarteInvalideException 
	 */
	public InfoCarteIngredientJouee jouerTour(Manche manche, Saison tour) throws CarteInvalideException {
		ChoixCarteIngredient choix = deciderChoixDuTour(manche, tour);
		
		//On vérifie bien que la carte est dans la main du joueur
		if(choix == null || choix.getCarteChoisie() == null ||!this.main.contientCarteIngredient(choix.getCarteChoisie())) {
			throw new CarteInvalideException("La carte choisie est invalide (aucune ou pas dans la main du joueur) !");
		}
		
		int forceReelle = choix.getCarteChoisie().agir(manche, this.main, choix.getCible(), tour, choix.getActionChoisie());
		this.getMain().retirerCarteIngredient(choix.getCarteChoisie());
		
		return new InfoCarteIngredientJouee(this.numero, tour, choix, forceReelle);
	}
	
	/**
	 * Permet au joueur de jouer une carte allie s'il en a une.
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur dont c'est le tour actuellement
	 * @return 
	 */
	public InfoCarteAlliesJouee jouerCartesAllies(Manche manche, Saison tour, int joueurActuel) {
		if(this.getMain().getCarteAllies() != null)
		{
			ChoixCarteAllies choix = deciderCarteAllies(manche, tour, joueurActuel);
			if(choix.isJoue())
			{
				CarteAllies carteAllies = this.getMain().getCarteAllies();
				int forceReelle = carteAllies.agir(manche, this.getMain(), choix.getCible(), tour);
				
				this.getMain().retirerCarteAllies();
				
				return new InfoCarteAlliesJouee(this.numero, tour, choix, carteAllies, forceReelle);
			}
		}
		
		return new InfoCarteAlliesJouee(this.numero, tour, new ChoixCarteAllies(false, -1), null, 0);
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
	 * @return true si le joueur veut une carte alliés
	 */
	public abstract boolean veutCarteAllies();
	
	public int getNumero() {
		return numero;
	}
	
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
