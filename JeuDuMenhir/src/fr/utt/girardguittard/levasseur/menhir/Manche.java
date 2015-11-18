package fr.utt.girardguittard.levasseur.menhir;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

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
	 * Liste des mains des joueurs
	 */
	private ArrayList<MainJoueur> mainsDesJoueurs;
	
	/**
	 * Constructeur de Manche.
	 * Construit une manche vide.
	 */
	public Manche(ArrayList<Joueur> joueurs) {
		this.tourActuel = Saison.PRINTEMPS;
		this.joueurTour = 0;
		this.mainsDesJoueurs = new ArrayList<MainJoueur>();
		
		for(int i = 0; i < joueurs.size(); i++) {
			MainJoueur nouvelleMain = new MainJoueur(joueurs.get(i), this);
			//joueurs.get(i).setMain(nouvelleMain);
			this.mainsDesJoueurs.add(nouvelleMain);
		}
	}
	
	/**
	 * Joue la manche.
	 */
	public void jouer() {
		
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
