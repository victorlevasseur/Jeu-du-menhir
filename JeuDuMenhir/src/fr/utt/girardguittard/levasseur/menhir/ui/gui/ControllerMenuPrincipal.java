package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.utt.girardguittard.levasseur.menhir.Partie;

public class ControllerMenuPrincipal implements ActionListener{
	
	private Partie partie;
	
	private ViewMenuPrincipal view;
	
	public ControllerMenuPrincipal(Partie p, ViewMenuPrincipal v){
		this.partie = p;
		this.view = v;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.partie = new Partie(view.getNombreJoueur(), view.isSimple());
	}
}
