package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;


public class ControllerCartesAllies implements ActionListener{
	
	/**
	 * Main du joueur concerné
	 */
	private MainJoueur main;
	
	/**
	 * Vue concernée
	 */
	private ViewCartesAllies view;
	
	/**
	 * Constructeur de ce controller
	 * Initialise les attributs
	 * @param m La main du joueur concerné
	 * @param v La vue concernée
	 */
	public ControllerCartesAllies(MainJoueur m, ViewCartesAllies v) {
		this.main = m;
		this.view = v;
	}
	
	/**
	 * L'action a effectuer lorsque l'utilisateur clique sur le bouton "Jouer" de la VueCarteAllies
	 */
	public void actionPerformed(ActionEvent e) {
		//Reste à trouver à moyen de lui passer la manche et le tour et de rajouter des controles pour la cible
	}

}
