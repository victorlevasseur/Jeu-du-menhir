package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class ControllerMainJoueur implements ActionListener{

	private MainJoueur main;
	
	private ViewMainJoueur view;
	
	public ControllerMainJoueur(MainJoueur m, ViewMainJoueur v) {
		this.main = m;
		this.view = v;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
