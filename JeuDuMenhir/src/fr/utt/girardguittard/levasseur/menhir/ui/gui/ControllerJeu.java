package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.utt.girardguittard.levasseur.menhir.ActionIllegaleException;
import fr.utt.girardguittard.levasseur.menhir.Partie;
import fr.utt.girardguittard.levasseur.menhir.joueurs.CarteInvalideException;

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
				this.partie.getMancheEnCours().distribuerCartesIngredients();
			} else if(e.getActionCommand() == "DEMARRER_SAISON") {
				this.partie.getMancheEnCours().demarrerSaison();
				this.partie.getMancheEnCours().demarrerTour(); //Démarrage du 1er tour
			} else if(e.getActionCommand() == "JOUER_TOUR") {
				this.partie.getMancheEnCours().jouerTourJoueur(); //On fait jouer le joueur actuel
				if(this.partie.isPartieAvancee()) {
					this.partie.getMancheEnCours().jouerCartesAllies();
				}
				this.partie.getMancheEnCours().demarrerTour(); //On passe au tour suivant
			}
		} catch (ActionIllegaleException e1) {
			e1.printStackTrace();
		} catch (CarteInvalideException e2) {
			//Au lieu de planter, vu que l'on sait que cette exception a été lancée car le joueur n'a pas
			//sélectionné de carte, on affiche un message d'erreur au joueur lui demandant de bien choisir une carte
			this.view.afficherErreur("Il faut sélectionner une carte ingrédient et son action (et sa cible si farfadet) avant de poursuivre.");
		}
	}
	
	private void demarrerManche() throws ActionIllegaleException {
		this.partie.demarrerManche();
	}

}
