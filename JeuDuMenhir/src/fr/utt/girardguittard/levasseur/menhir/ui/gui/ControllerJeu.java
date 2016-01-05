/*
	JeuDuMenhir is a board game adapted into a computer game.
	Copyright (C) 2015-2016  
	Antoine Girard Guittard (antoine.girard_guittard@utt.fr), Victor Levasseur (victorlevasseur52@gmail.com)
	
	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation; either version 2 of the License, or
	(at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License along
	with this program; if not, write to the Free Software Foundation, Inc.,
	51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.utt.girardguittard.levasseur.menhir.ActionIllegaleException;
import fr.utt.girardguittard.levasseur.menhir.EtatManche;
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
				if(this.partie.getMancheEnCours().getEtat() == EtatManche.DEBUT_SAISON) {
					this.partie.getMancheEnCours().demarrerTour(); //Démarrage du 1er tour si on débute bien manche
				}
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
