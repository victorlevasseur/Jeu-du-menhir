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

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.DeckCartes;
import fr.utt.girardguittard.levasseur.menhir.cartes.InfoCarteAlliesJouee;
import fr.utt.girardguittard.levasseur.menhir.cartes.InfoCarteIngredientJouee;
import fr.utt.girardguittard.levasseur.menhir.joueurs.CarteInvalideException;
import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.util.ScoreMancheComparator;

/**
 * La classe Manche représente une manche et permet de faire son déroulement. Les manches utilisent le pattern Observer/Observable
 * afin de notifier les controlleurs/vues de leur changement d'état (ce qui permet à ces derniers d'agir en conséquence).
 * Pour effectuer le déroulement d'une manche,
 * les controlleurs doivent : 
 * <ol>
 * <li>Récupérer la manche auprès de la partie après le lancement de cette première.</li>
 * <li>S'ajouter en observer de la manche afin de recevoir ses notifications</li>
 * <li><em>La manche est dans l'état EtatManche.DEBUT_MANCHE</em></li>
 * <li>Distribuer les cartes ingrédient en lançant la méthode distribuerCartesIngredients()</li>
 * <li><em>La manche passe dans l'état EtatManche.EN_ATTENTE_CHOIX_CARTE_ALLIES</em></li>
 * <li>La/les vue(s) demande(nt) au joueur physique s'il veut la carte alliés ou prendre 2 graines et affecte le résultat
 * à l'instance de JoueurPhysique de la partie avec setVeutPrendreCarteAllies(ouiOuNon) <em>(uniquement partie avancée).</em></li>
 * <li>Distribuer les cartes alliés/graines en lançant distribuerCartesAllies() (<b>même si partie simple : cela aura pour
 * effet de distribuer les graines aux joueurs</b>)</li>
 * <li><em>La manche passe dans l'état EtatManche.PRET_A_DEMARRER</em></li>
 * <li>Lancer la première saison en appelant demarrerSaison()</li>
 * <li><em>La manche passe dans l'état EtatManche.DEBUT_SAISON et la saison est récupérable avec getSaisonActuelle()</em></li>
 * <li>Démarrer le tour du 1er joueur à jouer la saison demarrerTour()</li>
 * <li><em>La manche passe dans l'état EtatManche.DEBUT_TOUR et le numéro du joueur qui joue est récupérable avec getJoueurTour()</em></li>
 * <li><strong>Si c'est le tour du joueur physique (0) : </strong>Le controlleur/vue doit demander au joueur la carte qu'il veut jouer (voir ChoixCarteIngredient) et
 * affecter ce choix à l'instance JoueurPhysique grâce à setProchainChoixIngredient(choixDuJoueur)</li>
 * <li>Effectuer le déroulement du tour en lançant jouerTourJoueur()</li>
 * <li><em>La manche passe dans l'état EtatManche.FIN_TOUR</em></li>
 * <li><strong>Dans une partie avancée uniquement : </strong>Demander au joueur physique s'il veut jouer sa carte alliés s'il en a
 * une et stocker son choix (voir ChoixCarteAllies) dans son instance de JoueurPhysique en utilisant setProchainChoixAllies</li>
 * <li>Lancer jouerCarteAllies() qui aura pour effet de jouer les cartes alliés que les joueurs souhaitent jouer (tous les joueurs sont autorisés
 * à jouer leur carte alliés à n'importe quel tour et saison)</li>
 * <li><em>La manche ne change pas d'état (EtatManche.FIN_TOUR) mais les observers sont tout de même notifiés</em></li>
 * <li>Appeler demarrerTour() pour <strong>tenter</strong> de lancer le tour suivant.</li>
 * <li><strong>Il y a plusieurs possibilités : </strong>
 * <ul>
 * <li><em>La manche passe à l'état EtatManche.DEBUT_TOUR : </em>Le tour suivant vient d'être démarré, reprendre à l'étape 12 (c'est le tour du joueur suivant).</li>	
 * <li><em>La manche passe à l'état EtatManche.FIN_SAISON : </em>Tous les joueurs ont déjà joué la saison. <strong>Lancer la saison suivante en appelant
 * demarrerSaison().</strong> Ensuite, il y a ici deux sous-possibilités également : 
 * <ul>
 * <li><em>La manche passe à l'état EtatManche.DEBUT_SAISON : </em>La saison suivante est prête à démarrer, reprendre à l'étape 11</li>
 * <li><em>La manche passe à l'état EtatManche.FIN_MANCHE : </em>La manche est finie (Voir la documentation de Partie pour lancer la manche suivante).</li>
 * </ul>
 * </li>	
 * </ul>
 * </li>
 * </ol>
 * <strong>Note : </strong>Tout ce déroulement ne doit pas être fait en appelant les différentes méthodes à la suite mais en
 * attendant les notifications de Manche/Partie lors des changements d'état pour lancer l'étape suivante (ou pour autoriser
 * l'utilisateur à cliquer sur le bouton pour aller à l'étape suivante, dans le cas d'une GUI).
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
	 * Joueur qui débute chaque saison.
	 */
	final private int premierJoueur;
	
	/**
	 * Liste des mains des joueurs.
	 */
	private ArrayList<MainJoueur> mainsDesJoueurs;
	
	/**
	 * Contient vrai si la partie est avancée.
	 */
	boolean partieAvancee;
	
	/**
	 * Contient le nombre de joueurs.
	 */
	private int nombreJoueurs;
	
	/**
	 * Le deck contenant les cartes ingrédients.
	 */
	private DeckCartes<CarteIngredient> deckIngredient;
	
	/**
	 * Le deck contenant les cartes alliés.
	 */
	private DeckCartes<CarteAllies> deckAllies;
	
	/**
	 * L'état de la manche.
	 */
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
	
	/**
	 * Distribue les cartes ingrédients aux joueurs.
	 * <br/>
	 * <b>Voir la description de la classe Manche pour avoir le déroulement d'une manche et dans quel ordre appeler
	 * les méthodes</b>
	 * 
	 * @throws ActionIllegaleException si la méthode est appelée quand les cartes ont déjà été distribuées
	 */
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
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Distribue les cartes alliés ou les 2 graines de départ aux joueurs .
	 * <br/>
	 * <b>Voir la description de la classe Manche pour avoir le déroulement d'une manche et dans quel ordre appeler
	 * les méthodes</b>
	 * 
	 * @throws ActionIllegaleException si la méthode est appelée plus d'une fois ou pendant la manche
	 */
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
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Démarre la saison suivante (ou la première saison si la manche a tout juste été démarrée).
	 * <br/>
	 * Si la dernière saison a déjà été faite (HIVER), la Manche passe à l'état EtatManche.FIN_MANCHE pour indiquer
	 * qu'il n'y a pas de saison suivante (l'interface n'a donc pas besoin de compter les saisons).
	 * <br/>
	 * <b>Voir la description de la classe Manche pour avoir le déroulement d'une manche et dans quel ordre appeler
	 * les méthodes</b>
	 * 
	 * @throws ActionIllegaleException si une saison est déjà en cours, si les cartes n'ont pas été distribuées, si la manche est finie
	 */
	public void demarrerSaison() throws ActionIllegaleException {
		if(this.etat != EtatManche.PRET_A_DEMARRER && this.etat != EtatManche.FIN_SAISON) {
			throw new ActionIllegaleException("demarrerSaison() doit être appelée après la distribution des cartes ou à la fin d'une saison précédente");
		}
		
		//On incrémente la saison (à moins que ce soit le 1er appel de cette méthode)
		if(this.etat == EtatManche.PRET_A_DEMARRER) {
			this.saisonActuelle = Saison.PRINTEMPS;
			this.joueurTour = this.premierJoueur;
		} else if(this.saisonActuelle == Saison.HIVER) {
			this.finManche();
			return;
		} else {
			this.saisonActuelle = Saison.values()[this.saisonActuelle.ordinal() + 1];
			this.joueurTour = this.premierJoueur;
		}
		
		this.etat = EtatManche.DEBUT_SAISON;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Démarre le tour du joueur suivant (ou du premier joueur à jouer si la saison vient de débuter).
	 * <br/>
	 * Si le dernier joueur à déjà joué, la Manche passe dans l'état EtatManche.FIN_SAISON automatiquement
	 * pour indiquer la fin de la saison (donc l'interface n'a pas besoin de compter les tours des joueurs).
	 * <br/>
	 * <b>Voir la description de la classe Manche pour avoir le déroulement d'une manche et dans quel ordre appeler
	 * les méthodes</b>
	 * 
	 * @throws ActionIllegaleException si la méthode est appelée si la saison n'a pas débuté, si le tour d'un joueur a déjà commencé, si la saison est terminée
	 */
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
				
				this.setChanged();
				this.notifyObservers();
				return;
			}
		}
		
		this.etat = EtatManche.DEBUT_TOUR_JOUEUR;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Joue le tour du joueur en cours (pose et joue la carte qu'il a choisi).
	 * A la fin de son exécution, la méthode notifie les observers en passant un InfoCarteIngredientJouee en argument
	 * indiquant la carte jouée ainsi que la force effective.
	 * <br/>
	 * <b>Voir la description de la classe Manche pour avoir le déroulement d'une manche et dans quel ordre appeler
	 * les méthodes</b>
	 * 
	 * @throws ActionIllegaleException si aucun tour de joueur n'a été lancé (voir demarrerTourJoueur())
	 * @throws CarteInvalideException si la carte que le joueur actuel tente de jouer est invalide (null ou pas dans sa main)
	 */
	public void jouerTourJoueur() throws ActionIllegaleException, CarteInvalideException {
		if(this.etat != EtatManche.DEBUT_TOUR_JOUEUR) {
			throw new ActionIllegaleException("jouerTourJoueur() doit être appelée après demarrerTour() !");
		}
		
		//On fait jouer le joueur
		InfoCarteIngredientJouee info = this.getJoueur(this.joueurTour).jouerTour(this, this.saisonActuelle);
		
		this.etat = EtatManche.FIN_TOUR_JOUEUR;
		
		this.setChanged();
		this.notifyObservers(info);
	}
	
	/**
	 * Joue les cartes alliés choisies par les joueurs (pour tous les joueurs).
	 * A la fin de son exécution, la méthode notifie les observers en passant un ArrayList<InfoCarteAlliesJouee> en argument
	 * indiquant les cartes jouées ou non par les joueurs ainsi que leur force effective.
	 * <br/>
	 * <b>Voir la description de la classe Manche pour avoir le déroulement d'une manche et dans quel ordre appeler
	 * les méthodes</b>
	 * 
	 * @throws ActionIllegaleException si la méthode n'est pas appelée pendant le tour d'un joueur ou si la méthode est utilisée en partie simple
	 */
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
		ArrayList<InfoCarteAlliesJouee> infos = new ArrayList<InfoCarteAlliesJouee>();
		for(Iterator<MainJoueur> itMainJoueur = this.mainsDesJoueurs.iterator(); itMainJoueur.hasNext(); ) {
			InfoCarteAlliesJouee info = itMainJoueur.next().getJoueur().jouerCartesAllies(this, this.saisonActuelle, this.joueurTour);
			infos.add(info);
		}
		
		this.setChanged();
		this.notifyObservers(infos);
	}
	
	/**
	 * Méthode appelée par demarrerSaison() si la dernière saison a déjà été faite (donc fin de la la manche)
	 * pour signaler les fin de la manche et incrémenter le score des joueurs.
	 * @throws ActionIllegaleException
	 */
	private void finManche() throws ActionIllegaleException {
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
		
		this.setChanged();
		this.notifyObservers();
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
	 * Retourne le nombre de joueur.
	 * @return le nombre de joueur
	 */
	public int getNombreJoueurs() {
		return this.nombreJoueurs;
	}
	
	/**
	 * Retourne la saison actuelle.
	 * @return la saison actuelle
	 */
	public Saison getSaisonActuelle() {
		return saisonActuelle;
	}

	/**
	 * Retourne le numéro du joueur qui est en train de jouer.
	 * @return le numéro du joueur
	 */
	public int getJoueurTour() {
		return joueurTour;
	}

	/**
	 * Retourne l'état de la manche.
	 * @return l'état de la manche
	 */
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
