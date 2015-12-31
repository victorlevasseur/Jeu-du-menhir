package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;


public class ControllerCartesAllies implements ActionListener{
	private MainJoueur main;
	
	private ViewCartesAllies view;
	
	public ControllerCartesAllies(MainJoueur m, ViewCartesAllies v) {
		this.main = m;
		this.view = v;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
