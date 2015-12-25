package fr.utt.girardguittard.levasseur.menhir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.CartesFabrique;
import fr.utt.girardguittard.levasseur.menhir.cartes.DeckCartes;
import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurVirtuel;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.ui.InterfaceManager;
import fr.utt.girardguittard.levasseur.menhir.util.Console;
import fr.utt.girardguittard.levasseur.menhir.util.ScoreMancheComparator;
import fr.utt.girardguittard.levasseur.menhir.util.ScorePartieComparator;

public class Partie extends Observable implements Observer {
	
	final private boolean partieAvancee;

	private final ArrayList<Joueur> joueurs;
	
	private DeckCartes<CarteIngredient> deckCartesIngredient;
	
	private DeckCartes<CarteAllies> deckCartesAllies;
	
	private int premierJoueur;
	
	private EtatPartie etat;
	
	private int numeroMancheEnCours;
	
	private Manche mancheEnCours;
	
	public Partie(int nombreJoueurs, boolean partieAvancee) {
		super();
		
		this.partieAvancee = partieAvancee;
		this.joueurs = new ArrayList<Joueur>();
		
		//Création des joueurs
		this.joueurs.add(new JoueurPhysique(0));
		for(int i = 1; i < nombreJoueurs; i++) {
			this.joueurs.add(new JoueurVirtuel(i));
		}
		
		//Création des decks
		this.deckCartesIngredient = new DeckCartes<CarteIngredient>();
		this.deckCartesIngredient.ajouterCartes(CartesFabrique.genererCartesIngredients());
		
		//Création du deck de cartes alliés si la partie est avancée
		if(this.partieAvancee) {
			this.deckCartesAllies = new DeckCartes<CarteAllies>();
			this.deckCartesAllies.ajouterCartes(CartesFabrique.genererCartesAllies());
		} else {
			this.deckCartesAllies = null;
		}
		
		//On choisit aléatoirement le premier joueur qui va débuter la première manche
		this.premierJoueur = (int)(Math.random() * (float)this.joueurs.size());
		
		//On dit que la partie est lancée
		this.etat = EtatPartie.LANCEE;
		
		//La manche qui sera lancée sera la première manche
		this.numeroMancheEnCours = -1;
		this.mancheEnCours = null;
	}

	public void demarrerManche() throws ActionIllegaleException {
		if(this.etat == EtatPartie.FINIE || this.etat == EtatPartie.MANCHE_EN_COURS) {
			throw new ActionIllegaleException("Ne peut pas appeler demarrerManche() lorsque la partie est finie ou lorsqu'une manche est en cours");
		}
		
		this.numeroMancheEnCours++;
		
		//Création de la manche
		this.mancheEnCours = new Manche(this.partieAvancee, 
			this.joueurs, 
			this.deckCartesIngredient, 
			this.deckCartesAllies,
			(premierJoueur + this.numeroMancheEnCours) % this.joueurs.size()
			);
		this.mancheEnCours.addObserver(this);
		
		this.etat = EtatPartie.MANCHE_EN_COURS;
		
		this.notifyObservers();
	}

	public void jouer() {
		//On notifie l'interface utilisateur que la partie est lancée
		InterfaceManager.get().notifierDebutPartie(this);
		
		//On effectue le nombre de manches souhaités (1 si partie simple, le nombre de manches sinon)
		//La condition ternaire est plutôt utile ici...
		Manche manche = null; //Obligé pour conserver manche à la fin de la boucle.
		for(int i = 0; i < (this.partieAvancee ? this.getNombreJoueurs() : 1); i++) {
			
			//Création de la manche
			manche = new Manche(this.partieAvancee, 
					this.joueurs, 
					this.deckCartesIngredient, 
					this.deckCartesAllies,
					(premierJoueur + i) % this.joueurs.size());
			
			InterfaceManager.get().notifierDebutManche(i, manche);
			
			//On joue la manche
			manche.jouer();
			
			InterfaceManager.get().notifierFinManche();
		}
		
		InterfaceManager.get().notifierFinPartie();
	}
	
	public boolean isPartieAvancee() {
		return this.partieAvancee;
	}
	
	public int getNombreJoueurs() {
		return this.joueurs.size();
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	public EtatPartie getEtat() {
		return etat;
	}

	public int getNumeroMancheEnCours() {
		return numeroMancheEnCours;
	}

	public Manche getMancheEnCours() {
		return mancheEnCours;
	}
	
	/**
	 * Calcule le classement des joueurs dans la manche.
	 * @return un tableau de Joueur ordonné du joueur le mieux classé au moins bien classé dans la manche
	 */
	public ArrayList<Joueur> calculerClassementPartie() {
		ArrayList<Joueur> joueursClasses = new ArrayList<Joueur>(this.joueurs);
		
		//Suivant si c'est une partie simple ou avancée, le classement ne fonctionne pas de la même manière
		Collections.sort(joueursClasses, new ScorePartieComparator(this));
		
		return joueursClasses;
	}
	
	public ArrayList<Joueur> calculerVainqueurs() {
		ArrayList<Joueur> joueursClasses = this.calculerClassementPartie();
		ArrayList<Joueur> vainqueurs = new ArrayList<Joueur>();
		
		int menhirsMax = 0;
		for(Iterator<Joueur> it = joueursClasses.iterator(); it.hasNext(); ) {
			Joueur joueur = it.next();
			
			if(joueur.getScore() > menhirsMax) {
				vainqueurs.clear();
				menhirsMax = joueur.getScore();
			}
			if(joueur.getScore() >= menhirsMax) {
				vainqueurs.add(joueur);
			}
		}
		
		return vainqueurs;
	}

	/**
	 * Méthode update du design pattern observer/observable.
	 * La classe Partie observe la Manche en cours pour savoir quand elle se termine
	 */
	public void update(Observable arg0, Object arg1) {
		// On regarde si arg0 est une Manche et voir si elle est terminée
		//         ==> si c'est le cas, mettre etat à EtatPartie.MANCHE_FINIE
		if(arg0 instanceof Manche) {
			Manche manche = (Manche) arg0;
			if(manche.getEtat() == EtatManche.FIN_MANCHE) {
				this.etat = EtatPartie.MANCHE_FINIE;
				this.notifyObservers();
			}
		}
	}
}
