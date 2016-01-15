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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Observer;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import fr.utt.girardguittard.levasseur.menhir.Partie;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Vue du menu principal.
 */
public class ViewMenuPrincipal extends JFrame implements Observer{

	private JPanel contentPane;
	
	private static final long serialVersionUID = 42L;
	
	private JRadioButton rdbtnPartieSimple = new JRadioButton("Partie Simple");
	
	private JRadioButton rdbtnPartieAvance = new JRadioButton("Partie Avancée");
	
	private ButtonGroup btnGroup = new ButtonGroup();
	
	private JSpinner spinner = new JSpinner();

	private JLabel lblNombreDeJoueur = new JLabel("Nombre de Joueur");
	
	private JButton btnJouer = new JButton("Jouer");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMenuPrincipal frame = new ViewMenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewMenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
				
		//On ajoute les deux JRadioButton au groupe
		btnGroup.add(rdbtnPartieSimple);
		btnGroup.add(rdbtnPartieAvance);
		
		//Partie simple est la sélection par défaut
		rdbtnPartieSimple.setSelected(true);
		
		spinner.setModel(new SpinnerNumberModel(2, 2, 6, 1));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(rdbtnPartieSimple)
					.addPreferredGap(ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
					.addComponent(rdbtnPartieAvance)
					.addGap(47))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(121, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnJouer, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNombreDeJoueur)
							.addGap(59)))
					.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(154))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnPartieSimple)
						.addComponent(rdbtnPartieAvance))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombreDeJoueur))
					.addGap(18)
					.addComponent(btnJouer)
					.addContainerGap(225, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void update(Observable obs, Object obj) {
		
	}
	
	public void connecterController(ActionListener controller) {
		//Ajout du Controller au bouton
		this.btnJouer.addActionListener(controller);
	}
	
	public void deconnecterController(ActionListener controller) {
		this.btnJouer.removeActionListener(controller);
	}
	
	public boolean isSimple() {
		return rdbtnPartieSimple.isSelected();
	}
	
	public int getNombreJoueur() {
		if(spinner.getValue() instanceof Integer) {
			return (int)spinner.getValue();
		}
		else {
			return 0;
		}
	}
}
