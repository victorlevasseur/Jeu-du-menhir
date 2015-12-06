package fr.utt.girardguittard.levasseur.menhir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.DeckCartes;
import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.ui.InterfaceManager;
import fr.utt.girardguittard.levasseur.menhir.util.Console;
import fr.utt.girardguittard.levasseur.menhir.util.ScoreMancheComparator;

/**
 * La classe Manche représente une manche et permet de faire son déroulement.
 */
public class Manche {
	
	/**
	 * Saison actuelle.
	 */
	private Saison saisonActuelle;
	
	/**
	 * Joueur qui est en train de jouer la saison (0 pour le 1er joueur).
	 */
	private int joueurTour;
	
	/**
	 * Joueur qui débute chaque saison
	 */
	final private int premierJoueur;
	
	/**
	 * Liste des mains des joueurs
	 */
	private ArrayList<MainJoueur> mainsDesJoueurs;
	
	/**
	 * Contient vrai si la partie est avancée
	 */
	boolean partieAvancee;
	
	/**
	 * Contient le nombre de joueurs
	 */
	private int nombreJoueurs;
	
	/**
	 * Le deck contenant les cartes ingrédients
	 */
	private DeckCartes<CarteIngredient> deckIngredient;
	
	/**
	 * Le deck contenant les cartes alliés
	 */
	private DeckCartes<CarteAllies> deckAllies;
	
	/**
	 * Constructeur de Manche.
	 * @param partieAvancee booléen valant vrai si la partie est avancée
	 * @param joueurs une liste de joueurs qui participent à la partie
	 * @param deckIngredient le deck des cartes ingrédients
	 * @param deckAllies le deck des cartes alliés
	 * @param premierJoueur le numéro du joueur qui débute la manche (à partir de 0)
	 */
	public Manche(boolean partieAvancee, ArrayList<Joueur> joueurs, 
			DeckCartes<CarteIngredient> deckIngredient,
			DeckCartes<CarteAllies> deckAllies,
			int premierJoueur) {
		this.saisonActuelle = Saison.PRINTEMPS;
		this.joueurTour = 0;
		this.premierJoueur = premierJoueur;
		this.mainsDesJoueurs = new ArrayList<MainJoueur>();
		this.partieAvancee = partieAvancee;
		this.nombreJoueurs = joueurs.size();
		this.deckIngredient = deckIngredient;
		this.deckAllies = deckAllies;
		
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
	}
	
	/**
	 * Joue la manche.
	 */
	public void jouer() {
		
		//Distribution des cartes ingrédients
		for(int i = 0; i < 4; i++) {
			for(Iterator<MainJoueur> it = this.mainsDesJoueurs.iterator(); it.hasNext(); ) {
				it.next().ajouterCarteIngredient(deckIngredient.getCarte());
			}
		}
		
		//Distribution des cartes alliés (si en partie avancée et si le joueur en veut une)
		//ou distribution de 2 graines (si il n'en veut pas ou si dans une partie rapide)
		{
			int i = 0;
			for(Iterator<MainJoueur> it = this.mainsDesJoueurs.iterator(); it.hasNext(); ) {
				if(this.partieAvancee) {
					boolean veutCarteAllies = this.getJoueur(i).veutCarteAllies();
					if(veutCarteAllies) {
						it.next().setCarteAllies(deckAllies.getCarte());
					} else {
						it.next().ajouterGraines(2);
					}
				} else {
					it.next().ajouterGraines(2);
				}
				i++;
			}
		}
		
		//On fait le déroulement des 4 saisons
		for(Saison saison : Saison.values()) {
			this.saisonActuelle = saison;
			InterfaceManager.get().notifierDebutSaison(this.saisonActuelle);
			
			//On fait jouer chaque joueur, en commençant par le joueur qui doit jouer en premier (this.premierJoueur).
			//(on n'utilise pas Iterator car on a besoin de l'index de l'itération)
			for(int i = 0; i < this.mainsDesJoueurs.size(); i++) {
				//Le numéro du joueur qui doit jouer : 
				int numJoueur = (i + this.premierJoueur) % this.mainsDesJoueurs.size(); 
				InterfaceManager.get().notifierDebutTour(numJoueur);
				
				//On propose à tous les joueurs de jouer une carte alliés s'ils le veulent
				//(car il est possible de jouer des cartes alliés à tout moment)
				//(uniquement pour les parties avancées)
				if(this.partieAvancee) {
					for(Iterator<MainJoueur> itMainJoueur = this.mainsDesJoueurs.iterator(); itMainJoueur.hasNext(); ) {
						itMainJoueur.next().getJoueur().jouerCartesAllies(this, saison, numJoueur);
					}
				}
				
				//On fait jouer le joueur
				this.getJoueur(numJoueur).jouerTour(this, saison);
				
				InterfaceManager.get().notifierFinTour();
			}
			
			InterfaceManager.get().notifierFinSaison();
		}

		//On ajoute les menhirs de la manche au score des joueurs
		for(Iterator<MainJoueur> itMainJoueur = this.mainsDesJoueurs.iterator(); itMainJoueur.hasNext(); ) {
			MainJoueur mainJoueur = itMainJoueur.next();
			mainJoueur.getJoueur().incrementerScore(mainJoueur.getNombreMenhir());
		}
	}
	
	/**
	 * Retourne un joueur.
	 * @param numero le numéro du joueur (à partir de 0)
	 * @return le joueur associé à ce numéro
	 */
	public Joueur getJoueur(int numero) {
		return this.mainsDesJoueurs.get(numero).getJoueur();
	}

	/**
	 * Retourne le premier joueur
	 * @return le premier joueur
	 */
	public int getPremierJoueur() {
		return premierJoueur;
	}
	
	/**
	 * Retourne le nombre de joueur
	 * @return le nombre de joueur
	 */
	public int getNombreJoueurs() {
		return this.nombreJoueurs;
	}
	
	/**
	 * Calcule le classement des joueurs dans la manche (en ne prenant en compte que cette manche)
	 * @return un tableau de Joueur ordonné du joueur le mieux classé au moins bien classé dans la manche
	 */
	public ArrayList<Joueur> calculerClassementManche() {
		ArrayList<Joueur> joueursClasses = new ArrayList<Joueur>();
		
		//Insertion des joueurs à partir de leur main dans le tableau
		for(Iterator<MainJoueur> it = this.mainsDesJoueurs.iterator(); it.hasNext(); ) {
			joueursClasses.add(it.next().getJoueur());
		}
		
		//Tri du tableau en fonction du nombre de menhirs puis de graines de la manche
		Collections.sort(joueursClasses, new ScoreMancheComparator());
		
		return joueursClasses;
	}
	
}
