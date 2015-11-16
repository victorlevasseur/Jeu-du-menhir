package fr.utt.girardguittard.levasseur.menhir;

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
	 * Constructeur de Manche.
	 * Construit une manche vide.
	 */
	public Manche() {
		this.tourActuel = Saison.PRINTEMPS;
		this.joueurTour = 0;
	}
	
	/**
	 * Joue la manche.
	 */
	public void jouer() {
		
	}
	
}
