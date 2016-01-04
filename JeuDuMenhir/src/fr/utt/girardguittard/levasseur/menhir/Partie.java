/*
	JeuDuMenhir is a board game adapted into a computer game.
	Copyright (C) 2015-2016  
	Antoine Girard Guittard (antoine.girard_guittard@utt.fr), Victor Levasseur (victorlevasseur52@gmail.com)
	
	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation; either version 2 of the License, or
	(at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License along
	with this program; if not, write to the Free Software Foundation, Inc.,
	51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package fr.utt.girardguittard.levasseur.menhir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.CartesFabrique;
import fr.utt.girardguittard.levasseur.menhir.cartes.DeckCartes;
import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurVirtuel;
import fr.utt.girardguittard.levasseur.menhir.util.ScorePartieComparator;

/**
 * Représente une partie du jeu du menhir.
 * 
 * Pour faire fonctionner une partie, les controlleurs doivent : 
 * <ul>
 * <li>Construire une instance de Partie : les joueurs physiques et virtuels sont initialisés suivant le nombre de joueurs précisés
 * dans le constructeur</li>
 * <li>Lancer la première manche grâce à demarrerManche(). L'état de la partie passe à EtatPartie.MANCHE_EN_COURS</li>
 * <li>Se mettre observer de la Manche construire (récupérable avec getMancheActuelle())</li>
 * <li>Effectuer le déroulement de la manche (voir la documentation de Manche). A la fin de la manche, l'état de la partie
 * passe à EtatPartie.MANCHE_FINIE ou EtatPartie.FINIE.</li>
 * <li>Suivant l'état de la partie : 
 * <ul>
 * <li>Si EtatPartie.MANCHE_FINIE (cas possible uniquement dans une partie avancée) : lancer la manche suivante</li>
 * <li>Si EtatPartie.FINIE : afficher les scores et signaler que la partie est finie</li>
 * </ul>
 * </li>
 * </ul>
 */
public class Partie extends Observable implements Observer {
	
	/**
	 * Vaut true si c'est une partie avancée.
	 */
	final private boolean partieAvancee;

	/**
	 * Les joueurs participants à la partie.
	 */
	private final ArrayList<Joueur> joueurs;
	
	/**
	 * Le tas de cartes ingrédients de la partie.
	 */
	private DeckCartes<CarteIngredient> deckCartesIngredient;
	
	/**
	 * Le tas de cartes alliés de la partie.
	 */
	private DeckCartes<CarteAllies> deckCartesAllies;
	
	/**
	 * Entier représentant le joueur qui débutera à jouer la 1ère manche.
	 */
	private int premierJoueur;
	
	/**
	 * Etat de la partie.
	 * @see EtatPartie
	 */
	private EtatPartie etat;
	
	/**
	 * Entier représentant le numéro de la manche actuelle.
	 */
	private int numeroMancheEnCours;
	
	/**
	 * La manche actuelle.
	 */
	private Manche mancheEnCours;
	
	/**
	 * Construit une partie.
	 * @param nombreJoueurs le nombre de joueurs qui participent à la partie (joueur physique inclus)
	 * @param partieAvancee true si c'est une partie avancée
	 */
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

	/**
	 * Lance la manche suivante (ou la première manche das le cas où la partie vient d'être créée).
	 * @throws ActionIllegaleException si la méthode est appelée alors qu'une manche est en cours
	 * et n'est pas finie.
	 */
	public void demarrerManche() throws ActionIllegaleException {
		if(this.etat == EtatPartie.FINIE || this.etat == EtatPartie.MANCHE_EN_COURS) {
			throw new ActionIllegaleException("Ne peut pas appeler demarrerManche() lorsque la partie est finie ou lorsqu'une manche est en cours");
		}
		
		this.numeroMancheEnCours++;
		if(this.numeroMancheEnCours >= (this.partieAvancee ? this.joueurs.size() : 1)) {
			this.etat = EtatPartie.FINIE;
			
			this.setChanged();
			this.notifyObservers();
			return;
		}
		
		//Création de la manche
		this.mancheEnCours = new Manche(this.partieAvancee, 
			this.joueurs, 
			this.deckCartesIngredient, 
			this.deckCartesAllies,
			(premierJoueur + this.numeroMancheEnCours) % this.joueurs.size()
			);
		this.mancheEnCours.addObserver(this);
		
		this.etat = EtatPartie.MANCHE_EN_COURS;
		
		this.setChanged();
		this.notifyObservers();
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
	 * @return un tableau de Joueur ordonné du joueur le mieux classé au moins bien classé dans la partie
	 */
	public ArrayList<Joueur> calculerClassementPartie() {
		ArrayList<Joueur> joueursClasses = new ArrayList<Joueur>(this.joueurs);
		
		//Suivant si c'est une partie simple ou avancée, le classement ne fonctionne pas de la même manière
		Collections.sort(joueursClasses, new ScorePartieComparator(this));
		
		return joueursClasses;
	}
	
	/**
	 * Donne la liste des vainqueurs de la partie (ou de ceux en-tête si la partie est en cours).
	 * @return une collection (de type List) des vainqueurs de la partie
	 */
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
	 * La classe Partie observe la Manche en cours pour savoir quand elle se termine. La partie passe à l'état MANCHE_FINIE
	 * dès que la manche actuelle vient de se finir.
	 */
	public void update(Observable arg0, Object arg1) {
		// On regarde si arg0 est une Manche et voir si elle est terminée
		//         ==> si c'est le cas, mettre etat à EtatPartie.MANCHE_FINIE
		if(arg0 instanceof Manche) {
			Manche manche = (Manche) arg0;
			if(this.getEtat() == EtatPartie.MANCHE_EN_COURS && manche.getEtat() == EtatManche.FIN_MANCHE) {
				this.etat = EtatPartie.MANCHE_FINIE;
				this.setChanged();
				this.notifyObservers();
			}
		}
	}
}
