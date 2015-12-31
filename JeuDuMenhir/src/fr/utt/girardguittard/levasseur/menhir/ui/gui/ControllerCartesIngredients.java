package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;


public class ControllerCartesIngredients implements ActionListener{
	
	private MainJoueur main;
	
	private ViewCartesIngredients view;
	
	public ControllerCartesIngredients(MainJoueur m, ViewCartesIngredients v) {
		this.main = m;
		this.view = v;
	}
	
	public void actionPerformed(ActionEvent e) {

	}

}
