package fr.utt.girardguittard.levasseur.menhir;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.DeckCartes;
import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.util.Console;

/**
 * La classe Manche représente une manche et permet de faire son déroulement.
 */
public class Manche {
	
	/**
	 * Saison actuelle.
	 */
	private Saison tourActuel;
	
	/**
	 * Joueur qui est en train de jouer la saison (0 pour le 1er joueur).
	 */
	private int joueurTour;
	
	/**
	 * Joueur qui débute chaque saison
	 */
	private int premierJoueur;
	
	/**
	 * Liste des mains des joueurs
	 */
	private ArrayList<MainJoueur> mainsDesJoueurs;
	
	/**
	 * Contient vrai si la partie est avancée
	 */
	boolean partieAvancee;
	
	/**
	 * Constructeur de Manche.
	 * @param partieAvancee booléen valant vrai si la partie est avancée
	 * @param joueurs une liste de joueurs qui participent à la partie
	 * @param deckIngredient le deck des cartes ingrédients
	 * @param deckAllies le deck des cartes alliés
	 */
	public Manche(boolean partieAvancee, ArrayList<Joueur> joueurs, 
			DeckCartes<CarteIngredient> deckIngredient,
			DeckCartes<CarteAllies> deckAllies,
			int premierJoueur) {
		this.tourActuel = Saison.PRINTEMPS;
		this.joueurTour = 0;
		this.premierJoueur = premierJoueur;
		this.mainsDesJoueurs = new ArrayList<MainJoueur>();
		this.partieAvancee = partieAvancee;
		
		
		//On affecte les mains aux joueurs et à la manche afin de créer la liaison
		for(int i = 0; i < joueurs.size(); i++) {
			MainJoueur nouvelleMain = new MainJoueur(joueurs.get(i), this);
			joueurs.get(i).setMain(nouvelleMain);
			this.mainsDesJoueurs.add(nouvelleMain);
		}
		
		//On mélange les deux tas de cartes
		deckIngredient.remettreCartesEtMelanger();
		if(deckAllies != null) {
			deckAllies.remettreCartesEtMelanger();
		}
		
		//Distribution des cartes ingrédients
		for(int i = 0; i < 4; i++) {
			for(Iterator<MainJoueur> it = this.mainsDesJoueurs.iterator(); it.hasNext(); ) {
				it.next().ajouterCarteIngredient(deckIngredient.getCarte());
			}
		}
		
		//Distribution des cartes alliés (si en partie avancée et si le joueur en veut une)
		if(this.partieAvancee) {
			for(Iterator<MainJoueur> it = this.mainsDesJoueurs.iterator(); it.hasNext(); ) {
				boolean veutCarteAllies = true;
				//TODO: Demander à l'utilisateur
				if(veutCarteAllies) {
					it.next().setCarteAllies(deckAllies.getCarte());
				} else {
					it.next().ajouterGraines(2);
				}
			}
		}
	}
	
	/**
	 * Joue la manche.
	 */
	public void jouer() {
		Console.getInstance().println("Le joueur " + (this.premierJoueur + 1) + " commence les tours dans cette manche.");
		
		//On fait le déroulement des 4 saisons
		for(Saison saison : Saison.values()) {
			Console.getInstance().println("C'est la saison " + saison.toString());
			this.tourActuel = saison;
			
			//On fait jouer chaque joueur, en commençant par le joueur qui doit jouer en premier (this.premierJoueur).
			//(on n'utilise pas Iterator car on a besoin de l'index de l'itération)
			for(int i = 0; i < this.mainsDesJoueurs.size(); i++) {
				//Le numéro du joueur qui doit jouer : 
				int numJoueur = (i + this.premierJoueur) % this.mainsDesJoueurs.size(); 
				
				//On propose à tous les joueurs de jouer une carte alliés s'ils le veulent
				//(car il est possible de jouer des cartes alliés à tout moment)
				//(uniquement pour les parties avancées)
				if(this.partieAvancee) {
					for(Iterator<MainJoueur> itMainJoueur = this.mainsDesJoueurs.iterator(); itMainJoueur.hasNext(); ) {
						itMainJoueur.next().getJoueur().jouerCartesAllies(this, saison, numJoueur);
					}
				}
				
				Console.getInstance().println("Au tour du joueur #" + (numJoueur+1) + " : ");
				//On fait jouer le joueur
				this.getJoueur(numJoueur).jouerTour(this, saison);
			}
		}

		//On ajoute les menhirs de la manche au score des joueurs
		for(Iterator<MainJoueur> itMainJoueur = this.mainsDesJoueurs.iterator(); itMainJoueur.hasNext(); ) {
			MainJoueur mainJoueur = itMainJoueur.next();
			mainJoueur.getJoueur().incrementerScore(mainJoueur.getNombreMenhir());
			
		}
	}
	
	/**
	 * Retourne un joueur.
	 * @param numero le numéro du joueur
	 * @return 
	 */
	public Joueur getJoueur(int numero) {
		return this.mainsDesJoueurs.get(numero).getJoueur();
	}
}
