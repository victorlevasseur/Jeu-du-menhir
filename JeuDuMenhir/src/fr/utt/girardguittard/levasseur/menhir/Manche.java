package fr.utt.girardguittard.levasseur.menhir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.DeckCartes;
import fr.utt.girardguittard.levasseur.menhir.joueurs.CarteInvalideException;
import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.ui.InterfaceManager;
import fr.utt.girardguittard.levasseur.menhir.util.Console;
import fr.utt.girardguittard.levasseur.menhir.util.ScoreMancheComparator;

/**
 * La classe Manche représente une manche et permet de faire son déroulement.
 */
public class Manche extends Observable {
	
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
	
	private EtatManche etat;
	
	
	
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
		super();
		
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
		
		this.etat = EtatManche.DEBUT_MANCHE;
	}
	
	public void distribuerCartesIngredients() throws ActionIllegaleException {
		if(this.etat != EtatManche.DEBUT_MANCHE) {
			throw new ActionIllegaleException("Ne peut pas distribuer les cartes ingrédients après le début de la partie !");
		}
		
		//Distribution des cartes ingrédients
		for(int i = 0; i < 4; i++) {
			for(Iterator<MainJoueur> it = this.mainsDesJoueurs.iterator(); it.hasNext(); ) {
				it.next().ajouterCarteIngredient(deckIngredient.getCarte());
			}
		}
		
		this.etat = EtatManche.EN_ATTENTE_CHOIX_CARTE_ALLIES;
		
		this.notifyObservers();
	}
	
	public void distribuerCartesAllies() throws ActionIllegaleException {
		if(this.etat != EtatManche.EN_ATTENTE_CHOIX_CARTE_ALLIES) {
			throw new ActionIllegaleException("distributerCartesAllies() doit être appelée après la distribution des cartes ingrédients !");
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
		
		this.etat = EtatManche.PRET_A_DEMARRER;
		
		this.notifyObservers();
	}
	
	public void demarrerSaison() throws ActionIllegaleException {
		if(this.etat != EtatManche.PRET_A_DEMARRER && this.etat != EtatManche.FIN_SAISON) {
			throw new ActionIllegaleException("demarrerSaison() doit être appelée après la distribution des cartes ou à la fin d'une saison précédente");
		}
		
		//On incrémente la saison (à moins que ce soit le 1er appel de cette méthode)
		if(this.etat == EtatManche.FIN_SAISON) {
			this.saisonActuelle = Saison.PRINTEMPS;
			this.joueurTour = this.premierJoueur;
		} else {
			this.saisonActuelle = Saison.values()[this.saisonActuelle.ordinal() + 1];
			this.joueurTour = this.premierJoueur;
		}
		
		this.etat = EtatManche.DEBUT_SAISON;
		
		this.notifyObservers();
	}
	
	public void demarrerTour() throws ActionIllegaleException {
		if(this.etat != EtatManche.DEBUT_SAISON && this.etat != EtatManche.FIN_TOUR_JOUEUR) {
			throw new ActionIllegaleException("demarrerTour() doit être appelée au début d'une saison ou à la fin du tour précédent !");
		}
		
		if(this.etat == EtatManche.DEBUT_SAISON) {
			this.joueurTour = this.premierJoueur; //C'est le premier tour d'une saison, le premier joueur doit jouer
		} else {
			//Incrémentation du numéro du joueur qui joue
			this.joueurTour = (this.joueurTour + 1) % this.mainsDesJoueurs.size();
			
			if(this.joueurTour == this.premierJoueur) { //Si après incrémentation, on retombe sur le premier joueur
				//Cela signifie que l'on a fini la saison
				this.etat = EtatManche.FIN_SAISON;
				this.notifyObservers();
				return;
			}
		}
		
		this.etat = EtatManche.DEBUT_TOUR_JOUEUR;
		
		this.notifyObservers();
	}
	
	public void jouerTourJoueur() throws ActionIllegaleException, CarteInvalideException {
		if(this.etat != EtatManche.DEBUT_TOUR_JOUEUR) {
			throw new ActionIllegaleException("jouerTourJoueur() doit être appelée après demarrerTour() !");
		}
		
		//On fait jouer le joueur
		this.getJoueur(this.joueurTour).jouerTour(this, this.saisonActuelle);
		
		this.etat = EtatManche.FIN_TOUR_JOUEUR;
		
		this.notifyObservers();
	}
	
	public void jouerCartesAllies() throws ActionIllegaleException {
		if(this.etat != EtatManche.DEBUT_SAISON && this.etat != EtatManche.DEBUT_TOUR_JOUEUR &&
		   this.etat != EtatManche.FIN_TOUR_JOUEUR) {
			throw new ActionIllegaleException("jouerCartesAllies() doit être appelée pendant le tour des joueurs !");
		}
		if(!this.partieAvancee) {
			throw new ActionIllegaleException("jouerCartesAllies() ne peut pas être utilisée dans une partie simple !");
		}
		
		//On propose à tous les joueurs de jouer une carte alliés s'ils le veulent
		//(car il est possible de jouer des cartes alliés à tout moment)
		//(uniquement pour les parties avancées)
		for(Iterator<MainJoueur> itMainJoueur = this.mainsDesJoueurs.iterator(); itMainJoueur.hasNext(); ) {
			itMainJoueur.next().getJoueur().jouerCartesAllies(this, this.saisonActuelle, this.joueurTour);
		}
		
		this.notifyObservers();
	}
	
	public void finManche() throws ActionIllegaleException {
		if(this.etat != EtatManche.FIN_SAISON && this.saisonActuelle != Saison.HIVER) {
			throw new ActionIllegaleException("finManche() doit être appelée à la fin de la saison HIVER !");
		}
		
		//On ajoute les menhirs de la manche au score des joueurs
		for(Iterator<MainJoueur> itMainJoueur = this.mainsDesJoueurs.iterator(); itMainJoueur.hasNext(); ) {
			MainJoueur mainJoueur = itMainJoueur.next();
			mainJoueur.getJoueur().incrementerScore(mainJoueur.getNombreMenhir());
		}
		
		//On signale que la manche est finie
		this.etat = EtatManche.FIN_MANCHE;
		
		this.notifyObservers();
	}
	
	/**
	 * Joue la manche.
	 */
	public void jouer() {
		
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
	
	public Saison getSaisonActuelle() {
		return saisonActuelle;
	}

	public int getJoueurTour() {
		return joueurTour;
	}

	public EtatManche getEtat() {
		return etat;
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
