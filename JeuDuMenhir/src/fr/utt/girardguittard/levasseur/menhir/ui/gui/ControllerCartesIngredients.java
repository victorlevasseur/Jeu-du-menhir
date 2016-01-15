package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * Controlleur traitant les événements liés au choix des cartes ingrédients.
 */
public class ControllerCartesIngredients implements ListSelectionListener, ActionListener {
	
	/**
	 * La vue concernée
	 */
	private ViewCartesIngredients view;

	/**
	 * La main du joueur concerné
	 */
	private MainJoueur main;
	
	/**
	 * Le choix du joueur issue des données récupéré par la vue.
	 */
	private ChoixCarteIngredient choix;
	
	/**
	 * Constructeur de ce controller
	 * Initialise les attributs
	 * @param m La main du joueur concerné
	 * @param v La vue concernée
	 */
	public ControllerCartesIngredients(MainJoueur m, ViewCartesIngredients v) {
		this.main = m;
		this.view = v;
	}

	/**
	 * L'action a effectuer lorsque que le joueur change la sélection de la liste des cartes ingrédients
	 */
	public void valueChanged(ListSelectionEvent e) {
		if(view.getSelection() != -1 && main.getNombreCarteIngredient() > 0) {
			view.setLabel("<html><pre>" + main.getCarteIngredient(view.getSelection()).toString() + "</pre></html>");
			choix = new ChoixCarteIngredient(main.getCarteIngredient(view.getSelection()), view.getCible() + 1, view.getAction());
			JoueurPhysique joueur  = (JoueurPhysique)main.getJoueur();
			joueur.setProchainChoixIngredient(choix);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(view.getSelection() != -1 && main.getNombreCarteIngredient() > 0) {
			choix = new ChoixCarteIngredient(main.getCarteIngredient(view.getSelection()), view.getCible() + 1, view.getAction());
			JoueurPhysique joueur  = (JoueurPhysique)main.getJoueur();
			joueur.setProchainChoixIngredient(choix);
			if (view.getAction() == Action.FARFADET) {
				view.setComboCibleVisibility(true);
			}
			else {
				view.setComboCibleVisibility(false);
			}
		}
	}    
}
