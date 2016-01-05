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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class ViewCartesAllies extends JPanel implements Observer {
	
	/**
	 * Le serialVersionUID généré par Eclipse
	 */
	private static final long serialVersionUID = -4227936361613708315L;

	/**
	 * La main du joueur concerné (observable)
	 */
	private MainJoueur main;
	
	/**
	 * Un label permettant d'afficher le contenu de la carte alliés du joueur
	 */
	private JLabel affichageCarte = new JLabel();
	
	/**
	 * Un bouton permettant de jouer la carte "sélectionnée"
	 */
	private JToggleButton bouttonJouer = new JToggleButton("Jouer");
	
	/**
	 * Le controlleur écoutant pour l'utilisation du boutton
	 */
	private ControllerCartesAllies controller;
	
	/**
	 * Une comboBox permettant de choisir la cible
	 */
	private JComboBox comboCible;
	
	/**
	 * Le constructeur de la classe
	 * @param m La main du joueur concernée
	 */
	public ViewCartesAllies(MainJoueur m, int nbrJoueurs) {
		//Ajout en tant qu'observateur
		this.main = m;
		main.addObserver(this);

		//Ajout du controller au boutton
		controller = new ControllerCartesAllies(main, this);
		bouttonJouer.addActionListener(controller);
		
		//Création de comboCible
		ArrayList<String> cibles = new ArrayList<String>();
		for (int i = 0; i < nbrJoueurs; i++) {
			if (i != main.getJoueur().getNumero()) {
				cibles.add(Integer.toString(i + 1));
			}
		}
		
		comboCible = new JComboBox<String>(cibles.toArray(new String[cibles.size()]));
		comboCible.setSelectedIndex(0);

		
		//On affiche les données de la carte
		if(main.getCarteAllies() != null) {
			affichageCarte.setText("<html><pre>" + main.getCarteAllies().toString() + "</pre></html>");
			//L'utilisation du html permet d'avoir simplement des JLabel multilignes
			if (main.getCarteAllies().getNom() == "Taupes géantes") {
				comboCible.setVisible(true);
			}
			else {
				comboCible.setVisible(false);
			}
		}
		else {
			affichageCarte.setText("Vous ne disposez pas de carte alliés.");
		}
		
		//Ajout des éléments au panel
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.add(affichageCarte);
		this.add(comboCible);
		this.add(bouttonJouer);
	}

	/**
	 * La méhode update appelée à chaque changement sur la main
	 * Permet de mettre à jour l'affichage de la carte allié et de permettre ou non l'utilisation du boutton
	 */
	public void update(Observable obs, Object obj) {
		if(obs == main){
			if(main.getCarteAllies() != null) {
				affichageCarte.setText("<html><pre>" + main.getCarteAllies().toString() + "</pre></html>");
				bouttonJouer.setVisible(true);
				if (main.getCarteAllies().getNom() == "Taupes géantes") {
					comboCible.setVisible(true);
				}
				else {
					comboCible.setVisible(false);
				} 
			}
			else {
				affichageCarte.setText("Vous ne disposez pas de carte alliés.");
				bouttonJouer.setVisible(false);
				comboCible.setVisible(false);
			}
		}
	}
	
	/**
	 * Renvoie la cible choisir par le joueur
	 * @return
	 */
	public int getCible() {
		return comboCible.getSelectedIndex();
	}
	
	/**
	 * Renvoie le statut du bouton (vrai si le joueur souhaite jouer la carte).
	 * @return
	 */
	public boolean getJouer() {
		return bouttonJouer.isSelected();
	}
}
