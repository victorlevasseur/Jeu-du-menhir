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
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.utt.girardguittard.levasseur.menhir.ActionIllegaleException;
import fr.utt.girardguittard.levasseur.menhir.EtatManche;
import fr.utt.girardguittard.levasseur.menhir.EtatPartie;
import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Partie;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.ChiensDeGarde;
import fr.utt.girardguittard.levasseur.menhir.cartes.InfoCarteAlliesJouee;
import fr.utt.girardguittard.levasseur.menhir.cartes.InfoCarteIngredientJouee;
import fr.utt.girardguittard.levasseur.menhir.cartes.TaupesGeantes;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.BoxLayout;

public class ViewJeu extends JFrame implements Observer {

	private static final long serialVersionUID = -7624969535039883844L;

	private JPanel contentPane;
	
	private Partie partie;
	private Manche manche;
	
	private JButton btnProchaineEtape;
	private JButton btnJ1;
	private JButton btnJ2;
	private JButton btnJ3;
	private JButton btnJ4;
	private JButton btnJ5;
	private JButton btnJ6;
	
	private JButton[] btnJoueurs;
	private JLabel mancheLbl;
	private JTextPane historiqueTextPane;

	/**
	 * Create the frame.
	 */
	public ViewJeu(Partie partie) {
		setTitle("Jeu du menhir");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		
		historiqueTextPane = new JTextPane();
		historiqueTextPane.setEditable(false);
		scrollPane.setViewportView(historiqueTextPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);
		
		mancheLbl = new JLabel("Manche X > Printemps");
		mancheLbl.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_mancheLbl = new GridBagConstraints();
		gbc_mancheLbl.insets = new Insets(0, 0, 5, 5);
		gbc_mancheLbl.gridx = 0;
		gbc_mancheLbl.gridy = 0;
		panel.add(mancheLbl, gbc_mancheLbl);
		
		JLabel lblAuTourDe = new JLabel("Au tour de : ");
		GridBagConstraints gbc_lblAuTourDe = new GridBagConstraints();
		gbc_lblAuTourDe.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuTourDe.gridx = 2;
		gbc_lblAuTourDe.gridy = 0;
		panel.add(lblAuTourDe, gbc_lblAuTourDe);
		
		btnJ1 = new JButton("JX");
		GridBagConstraints gbc_btnJ1 = new GridBagConstraints();
		gbc_btnJ1.insets = new Insets(0, 0, 5, 5);
		gbc_btnJ1.gridx = 3;
		gbc_btnJ1.gridy = 0;
		panel.add(btnJ1, gbc_btnJ1);
		
		btnJ2 = new JButton("JX");
		GridBagConstraints gbc_btnJ2 = new GridBagConstraints();
		gbc_btnJ2.insets = new Insets(0, 0, 5, 5);
		gbc_btnJ2.gridx = 4;
		gbc_btnJ2.gridy = 0;
		panel.add(btnJ2, gbc_btnJ2);
		
		btnJ3 = new JButton("JX");
		GridBagConstraints gbc_btnJ3 = new GridBagConstraints();
		gbc_btnJ3.insets = new Insets(0, 0, 5, 5);
		gbc_btnJ3.gridx = 5;
		gbc_btnJ3.gridy = 0;
		panel.add(btnJ3, gbc_btnJ3);
		
		btnJ4 = new JButton("JX");
		GridBagConstraints gbc_btnJ4 = new GridBagConstraints();
		gbc_btnJ4.insets = new Insets(0, 0, 5, 5);
		gbc_btnJ4.gridx = 6;
		gbc_btnJ4.gridy = 0;
		panel.add(btnJ4, gbc_btnJ4);
		
		btnJ5 = new JButton("JX");
		GridBagConstraints gbc_btnJ5 = new GridBagConstraints();
		gbc_btnJ5.insets = new Insets(0, 0, 5, 5);
		gbc_btnJ5.gridx = 7;
		gbc_btnJ5.gridy = 0;
		panel.add(btnJ5, gbc_btnJ5);
		
		btnJ6 = new JButton("JX");
		GridBagConstraints gbc_btnJ6 = new GridBagConstraints();
		gbc_btnJ6.insets = new Insets(0, 0, 5, 0);
		gbc_btnJ6.gridx = 8;
		gbc_btnJ6.gridy = 0;
		panel.add(btnJ6, gbc_btnJ6);
		
		JPanel listeJoueursVirtuelsPanel = new JPanel();
		contentPane.add(listeJoueursVirtuelsPanel, BorderLayout.EAST);
		GridBagLayout gbl_listeJoueursVirtuelsPanel = new GridBagLayout();
		gbl_listeJoueursVirtuelsPanel.columnWidths = new int[]{0, 0};
		gbl_listeJoueursVirtuelsPanel.rowHeights = new int[]{0, 0, 0};
		gbl_listeJoueursVirtuelsPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_listeJoueursVirtuelsPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		listeJoueursVirtuelsPanel.setLayout(gbl_listeJoueursVirtuelsPanel);
		
		JLabel lblAutresJoueurs = new JLabel("Autres joueurs :");
		GridBagConstraints gbc_lblAutresJoueurs = new GridBagConstraints();
		gbc_lblAutresJoueurs.insets = new Insets(0, 0, 5, 0);
		gbc_lblAutresJoueurs.gridx = 0;
		gbc_lblAutresJoueurs.gridy = 0;
		listeJoueursVirtuelsPanel.add(lblAutresJoueurs, gbc_lblAutresJoueurs);
		
		JPanel listeAutresJoueursPanel = new JPanel();
		GridBagConstraints gbc_listeAutresJoueursPanel = new GridBagConstraints();
		gbc_listeAutresJoueursPanel.fill = GridBagConstraints.BOTH;
		gbc_listeAutresJoueursPanel.gridx = 0;
		gbc_listeAutresJoueursPanel.gridy = 1;
		listeJoueursVirtuelsPanel.add(listeAutresJoueursPanel, gbc_listeAutresJoueursPanel);
		listeAutresJoueursPanel.setLayout(new BoxLayout(listeAutresJoueursPanel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblVotreMain = new JLabel("Votre main");
		lblVotreMain.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_1.add(lblVotreMain, BorderLayout.NORTH);
		
		btnProchaineEtape = new JButton("=== PROCHAINE ETAPE ===");
		panel_1.add(btnProchaineEtape, BorderLayout.SOUTH);
		
		this.btnJoueurs = new JButton[]{btnJ1, btnJ2, btnJ3, btnJ4, btnJ5, btnJ6};
		
		//On affecte des commandes aux différents boutons pour que l'actionListener puisse les reconnaître
		this.btnProchaineEtape.setActionCommand("PROCHAINE_ETAPE");
		
		//On initialise la vue avec la partie
		this.initialiserAvecPartie(partie);
	}

	/**
	 * Méthode de l'observer pour recevoir les notifications du modèle, ici la partie et la manche en cours
	 */
	public void update(Observable o, Object arg) {
		if(o == this.partie) {
			this.mettreAJourSelonEtatDePartie(this.partie.getEtat(), arg);
		} else if(o == this.manche) {
			this.mettreAJourSelonEtatDeManche(this.manche.getEtat(), arg);
		}
	}
	
	/**
	 * Connecte un controlleur à la vue (afin que ce dernier réagissent aux événements de cette dernière)
	 * @param actionListener le controlleur
	 */
	public void connecterControlleur(ActionListener actionListener) {
		this.btnProchaineEtape.addActionListener(actionListener);
	}
	
	/**
	 * Affiche une boîte de dialogue affichant le message d'erreur spécifié.
	 * @param erreur le message d'erreur
	 */
	public void afficherErreur(String erreur) {
		JFrame frame = new JFrame();
	    JOptionPane.showMessageDialog(frame, erreur);
	}
	
	/**
	 * Utilisée par le constructeur pour initialiser la vue avec la partie
	 * @param partie
	 */
	private void initialiserAvecPartie(Partie partie) {
		this.partie = partie;
		this.manche = null;
		this.partie.addObserver(this);
		
		//Initialisation des boutons qui représente l'ordre de passage des joueurs (et donc l'avancement dans la saison)
		for(int i = 0; i < this.btnJoueurs.length; i++) {
			
			if(i >= this.partie.getNombreJoueurs()) {
				//On cache les boutons des joueurs qui ne participent pas (en trop !)
				this.btnJoueurs[i].setVisible(false);
			}
			this.btnJoueurs[i].setText("==");
			this.btnJoueurs[i].setBackground(new Color(175, 175, 175));
		}
		
		this.mettreAJourSelonEtatDePartie(partie.getEtat(), null);
		
		if(this.partie.getMancheEnCours() != null) {
			this.initialiserAvecMancheActuelle();
		}
	}
	
	/**
	 * Initialise la vue pour utiliser la manche actuelle de la partie (utilisée lors du début d'une nouvelle manche)
	 */
	private void initialiserAvecMancheActuelle() {
		if(this.manche == this.partie.getMancheEnCours()) {
			//Pas besoin, cela a déjà été fait !
			return;
		}
		this.manche = this.partie.getMancheEnCours();
		this.manche.addObserver(this);
		
		//Afficher les numéros des joueurs dans l'ordre qu'ils jouent
		for(int i = 0; i < this.partie.getNombreJoueurs(); i++) {
			this.btnJoueurs[i].setText("J" + Integer.toString(
					(this.manche.getPremierJoueur() + i) % this.partie.getNombreJoueurs() + 1
					));
		}
		
		//TODO: Créer les vues des mains
		
		this.mettreAJourSelonEtatDeManche(this.manche.getEtat(), null);
	}
	
	/**
	 * Met à jour la vue selon l'état de la partie (en utilisant la partie stockée en attribut)
	 * @param etat l'état de la partie
	 * @param arg un objet qui a été passé par l'observable
	 */
	private void mettreAJourSelonEtatDePartie(EtatPartie etat, Object arg) {
		this.mettreAJourTitreManche();
		
		if(etat == EtatPartie.LANCEE) {
			this.btnProchaineEtape.setText("Démarrer la première manche");
			this.btnProchaineEtape.setActionCommand("DEMARRER_MANCHE");
		} else if(etat == EtatPartie.MANCHE_EN_COURS) {
			this.initialiserAvecMancheActuelle();
		}
	}
	
	/**
	 * Met à jour la vue selon l'état de la manche (en utilisant la manche stockée en attribut)
	 * @param etat l'état de la manche
	 * @param arg un objet qui a été passé par l'observable
	 */
	private void mettreAJourSelonEtatDeManche(EtatManche etat, Object arg) {
		this.mettreAJourTitreManche();
		
		if(etat == EtatManche.DEBUT_MANCHE) {
			this.ajouterTexteAHistorique("MANCHE " + (this.partie.getNumeroMancheEnCours()+1));
		} else if(etat == EtatManche.EN_ATTENTE_CHOIX_CARTE_ALLIES) {
			//Le joueur doit dire s'il veut la carte alliés ou les 2 graines (partie avancée uniquement)
			if(this.partie.isPartieAvancee()) {
				JFrame frame = new JFrame();
			    int answer = JOptionPane.showConfirmDialog(frame, "Voulez-vous prendre la carte alliés ?", "Démarrage manche", JOptionPane.YES_NO_OPTION);
			    JoueurPhysique joueur = (JoueurPhysique)this.manche.getJoueur(0);
			    joueur.setVeutPrendreCarteAllies(answer == JOptionPane.YES_OPTION);
			}
			try {
				this.manche.distribuerCartesAllies();
			} catch (ActionIllegaleException e) {
				e.printStackTrace();
			}
		} else if(etat == EtatManche.PRET_A_DEMARRER) {
			this.btnProchaineEtape.setText("Démarrer la saison");
			this.btnProchaineEtape.setActionCommand("DEMARRER_SAISON");
		} else if(etat == EtatManche.DEBUT_SAISON) {
			this.ajouterTexteAHistorique(" Saison " + this.manche.getSaisonActuelle().name());
		} else if(etat == EtatManche.DEBUT_TOUR_JOUEUR) {
			this.btnProchaineEtape.setActionCommand("JOUER_TOUR");
			if(this.manche.getJoueurTour() == 0) { //Si c'est au tour du joueur physique, on lui permet de valider son choix de carte avec le bouton
				this.btnProchaineEtape.setText("Valider le choix de carte ingrédient (et de son action/cible)");
				
				//On affiche également une petite boîte de dialogue pour lui signaler que c'est son tour
				JFrame frame = new JFrame();
			    JOptionPane.showMessageDialog(frame, "C'est à votre tour !\nChoisissez une carte et valider votre choix pour la jouer");
			    
			} else {
				this.btnProchaineEtape.setText("Suivant");
			}
		} else if(etat == EtatManche.FIN_TOUR_JOUEUR) {
			//On affiche la carte jouée
			if(arg instanceof InfoCarteIngredientJouee) {
				this.afficherResultatCarte((InfoCarteIngredientJouee)arg);
			} else if(arg instanceof ArrayList<?>) {
				if(((ArrayList<?>) arg).size() > 0 && ((ArrayList<?>) arg).get(0) instanceof InfoCarteAlliesJouee) {
					//C'est bien un ArrayList (non vide) de InfoCarteAlliesJouee qui a été passé,
					//cela veut dire que des cartes alliés ont peut-être été jouées
					@SuppressWarnings("unchecked")
					ArrayList<InfoCarteAlliesJouee> infos = (ArrayList<InfoCarteAlliesJouee>) arg;
					for(Iterator<InfoCarteAlliesJouee> it = infos.iterator(); it.hasNext(); ) {
						InfoCarteAlliesJouee info = it.next();
						if(info.isJouee()) {
							this.afficherResultatCarte(info);
						}
					}
				}
			}
		} else if(etat == EtatManche.FIN_SAISON) {
			this.btnProchaineEtape.setText("Démarrer la saison");
			this.btnProchaineEtape.setActionCommand("DEMARRER_SAISON");
		}
	}
	
	/**
	 * Mets à jour le texte ainsi que les "indicateurs de tour joueurs" en fonction de l'état actuel de la manche.
	 */
	private void mettreAJourTitreManche() {
		if(this.manche != null) {
			//Affichage du numéro de la manche
			this.mancheLbl.setText("Manche " + (this.partie.getNumeroMancheEnCours()+1));
			
			//Affichage du nom de la saison actuelle si le jeu est dans une saison actuellement
			if(this.manche.getEtat() == EtatManche.DEBUT_SAISON || this.manche.getEtat() == EtatManche.DEBUT_TOUR_JOUEUR ||
					this.manche.getEtat() == EtatManche.FIN_TOUR_JOUEUR || this.manche.getEtat() == EtatManche.FIN_SAISON) {
				this.mancheLbl.setText(this.mancheLbl.getText() + " > " + this.manche.getSaisonActuelle().name());
			}
			
			//Affichage du joueur qui joue actuellement (s'il y en a un)
			if(this.manche.getEtat() == EtatManche.DEBUT_TOUR_JOUEUR || this.manche.getEtat() == EtatManche.FIN_TOUR_JOUEUR) {
				int boutonAActiver = (this.manche.getJoueurTour() - this.manche.getPremierJoueur() + this.partie.getNombreJoueurs()) % this.partie.getNombreJoueurs();
				for(int i = 0; i < this.partie.getNombreJoueurs(); i++) {
					if(i < boutonAActiver) {
						this.btnJoueurs[i].setBackground(new Color(0, 80, 0));
					} else if(i == boutonAActiver) {
						this.btnJoueurs[i].setBackground(new Color(0, 255, 0));
					} else {
						this.btnJoueurs[i].setBackground(new Color(175, 175, 175));
					}
				}
				
			}
		} else {
			this.mancheLbl.setText("");
		}
	}
	
	
	private void afficherResultatCarte(InfoCarteIngredientJouee info) {
		StringBuffer str = new StringBuffer();
		
		String designationJoueur1;
		if(info.getNumeroJoueur() == 0) {
			designationJoueur1 = "Vous jouez ";
		} else {
			designationJoueur1 = "Le joueur " + (info.getNumeroJoueur()+1) + " joue ";
		}
		
		str.append("        --> " + designationJoueur1 + "la carte \"" + info.getCarteJouee().getNom() + "\"");
		str.append("\n");
		
		String designationJoueur2;
		if(info.getNumeroJoueur() == 0) {
			designationJoueur2 = "Vous avez ";
		} else {
			designationJoueur2 = "Le joueur " + (info.getNumeroJoueur()+1) + " a ";
		}
		if(info.getActionJouee() == Action.GEANT) {
			str.append("            " + designationJoueur2 + "récupéré " + info.getForceReelle() + " graine(s).");
		} else if(info.getActionJouee() == Action.ENGRAIS) {
			str.append("            " + designationJoueur2 + "fait pousser " + info.getForceReelle() + " graine(s) en menhir(s).");
		} else {
			str.append("            " + designationJoueur2 + "volé " + info.getForceReelle() + " graine(s) au joueur " + (info.getJoueurCible()+1) + ".");
		}
		
		this.ajouterTexteAHistorique(str.toString());
	}
	
	private void afficherResultatCarte(InfoCarteAlliesJouee info) {
		StringBuffer str = new StringBuffer();
		
		String designationJoueur1;
		if(info.getNumeroJoueur() == 0) {
			designationJoueur1 = "Vous jouez ";
		} else {
			designationJoueur1 = "Le joueur " + (info.getNumeroJoueur()+1) + " joue ";
		}
		
		str.append("        --> " + designationJoueur1 + "la carte \"" + info.getCarteJouee().getNom() + "\"");
		str.append("\n");
		
		String designationJoueur2;
		if(info.getNumeroJoueur() == 0) {
			designationJoueur2 = "Vous avez ";
		} else {
			designationJoueur2 = "Le joueur " + (info.getNumeroJoueur()+1) + " a ";
		}
		if(info.getCarteJouee() instanceof ChiensDeGarde) {
			str.append("            " + designationJoueur2 + "protégé " + info.getForceReelle() + " graine(s).");
		} else if(info.getCarteJouee() instanceof TaupesGeantes) {
			str.append("            " + designationJoueur2 + "détruit " + info.getForceReelle() + " menhir(s) du joueur " + (info.getJoueurCible()+1) + ".");
		}
		
		this.ajouterTexteAHistorique(str.toString());
	}
	
	private void ajouterTexteAHistorique(String texte) {
		this.historiqueTextPane.setText(this.historiqueTextPane.getText() + "\n" + texte);
	}
}
