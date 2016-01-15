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

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.utt.girardguittard.levasseur.menhir.Partie;

/**
 * Controlleur contrôlant le menu principal.
 */
public class ControllerMenuPrincipal implements ActionListener{
	
	/**
	 * La partie concernée
	 */
	private Partie partie;
	
	/**
	 * La vue concernée
	 */
	private ViewMenuPrincipal view;
	
	public ControllerMenuPrincipal(){
		this.partie = null;
		this.view = null;
	}
	
	/**
	 * L'action a effectuer lorsque le boutton Jouer du menu principal est utilisé
	 */
	public void actionPerformed(ActionEvent e) {
		this.partie = new Partie(view.getNombreJoueur(), !view.isSimple());
		this.view.setVisible(false);
		
		EventQueue.invokeLater(new Runnable() {
			private Partie partie;
			
			public Runnable setPartie(Partie partie) {
				this.partie = partie;
				
				return this;
			}
			
			public void run() {
				try {
					ViewJeu frame = new ViewJeu(this.partie);
					ControllerJeu menuController = new ControllerJeu(this.partie, frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.setPartie(this.partie));
	}

	public Partie getPartie() {
		return partie;
	}

	public ViewMenuPrincipal getView() {
		return view;
	}

	public void setView(ViewMenuPrincipal view) {
		if(this.view != null) {
			this.view.deconnecterController(this);
		}
		this.view = view;
		this.view.connecterController(this);
	}
}
