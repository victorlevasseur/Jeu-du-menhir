package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteAllies;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;

/**
 * Controlleur qui reçoit les événements de l'interface liés au choix de cartes alliés.
 */
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
	 * Carte alliés choisie par le joueur
	 */
	private ChoixCarteAllies choix;
	
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
		choix = new ChoixCarteAllies(view.getJouer(), view.getCible() + 1);
		JoueurPhysique joueur = (JoueurPhysique)main.getJoueur();
		joueur.setProchainChoixAllies(choix);
	}

}
