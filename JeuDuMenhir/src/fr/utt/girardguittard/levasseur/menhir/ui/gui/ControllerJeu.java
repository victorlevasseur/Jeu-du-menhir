package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.utt.girardguittard.levasseur.menhir.ActionIllegaleException;
import fr.utt.girardguittard.levasseur.menhir.Partie;

/**
 * Controlleur qui va gérer une partie du jeu du menhir (ainsi que les manches).
 */
public class ControllerJeu implements ActionListener {
	
	/**
	 * La partie gérée par le contrôleur.
	 */
	private Partie partie;
	
	/**
	 * La vue gérée par le contrôleur
	 */
	private ViewJeu view;
	
	public ControllerJeu(Partie partie, ViewJeu view) {
		this.partie = partie;
		this.view = view;
		
		this.view.connecterControlleur(this); //On connecte le controlleur à la vue (pour recevoir ses événements).
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand() == "DEMARRER_MANCHE") {
				//La vue veut démarrer la manche
				this.partie.demarrerManche();
			} else if(e.getActionCommand() == "DISTRIBUER_INGREDIENTS") {
				//La vue veut distribuer les cartes ingrédients
				this.partie.getMancheEnCours().distribuerCartesIngredients();
			}
		} catch (ActionIllegaleException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		}
	}
	
	private void demarrerManche() throws ActionIllegaleException {
		this.partie.demarrerManche();
	}

}
