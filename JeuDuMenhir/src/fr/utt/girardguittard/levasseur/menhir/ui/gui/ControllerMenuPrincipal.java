package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.utt.girardguittard.levasseur.menhir.Partie;

public class ControllerMenuPrincipal implements ActionListener{
	
	/**
	 * La partie concernée
	 */
	private Partie partie;
	
	/**
	 * La vue concernée
	 */
	private ViewMenuPrincipal view;
	
	/**
	 * Constructeur de ce controller
	 * Initialise les attributs
	 * @param p La partie concernée
	 * @param v La vue concernée
	 */
	public ControllerMenuPrincipal(Partie p, ViewMenuPrincipal v){
		this.partie = p;
		this.view = v;
	}
	
	/**
	 * L'action a effectuer lorsque le boutton Jouer du menu principal est utilisé
	 */
	public void actionPerformed(ActionEvent e) {
		this.partie = new Partie(view.getNombreJoueur(), view.isSimple());
	}
}
