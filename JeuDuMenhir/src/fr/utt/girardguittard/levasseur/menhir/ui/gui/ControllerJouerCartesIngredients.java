package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;


public class ControllerJouerCartesIngredients implements ActionListener{
	
	/**
	 * La main du joueur concerné
	 */
	private MainJoueur main;
	
	/**
	 * La vue concernée
	 */
	private ViewCartesIngredients view;
	
	/**
	 * Constructeur de ce controller
	 * Initialise les attributs
	 * @param m La main du joueur concerné
	 * @param v La vue concernée
	 */
	public ControllerJouerCartesIngredients(MainJoueur m, ViewCartesIngredients v) {
		this.main = m;
		this.view = v;
	}
	
	public void actionPerformed(ActionEvent e) {
		CarteIngredient carte = main.getCarteIngredient(view.getSelection());
		//nécessite encore de récupérer l'action
	}

}
