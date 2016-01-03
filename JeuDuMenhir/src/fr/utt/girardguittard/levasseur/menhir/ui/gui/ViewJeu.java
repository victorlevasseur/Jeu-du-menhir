package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.utt.girardguittard.levasseur.menhir.EtatManche;
import fr.utt.girardguittard.levasseur.menhir.EtatPartie;
import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Partie;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JInternalFrame;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class ViewJeu extends JFrame implements Observer {

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
		
		JTextPane historiqueTextPane = new JTextPane();
		historiqueTextPane.setEditable(false);
		historiqueTextPane.setText("Historique partie");
		scrollPane.setViewportView(historiqueTextPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);
		
		JLabel label = new JLabel("Manche X > Printemps");
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
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
			this.mettreAJourSelonEtatDePartie(this.partie.getEtat());
		} else if(o == this.manche) {
			this.mettreAJourSelonEtatDeManche(this.manche.getEtat());
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
		
		this.mettreAJourSelonEtatDePartie(partie.getEtat());
		
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
		
		//TODO: Créer les vues des mains
		
		this.mettreAJourSelonEtatDeManche(this.manche.getEtat());
	}
	
	/**
	 * Met à jour la vue selon l'état de la partie (en utilisant la partie stockée en attribut)
	 * @param etat l'état de la partie
	 */
	private void mettreAJourSelonEtatDePartie(EtatPartie etat) {
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
	 */
	private void mettreAJourSelonEtatDeManche(EtatManche etat) {
		if(etat == EtatManche.DEBUT_MANCHE) {
			this.btnProchaineEtape.setText("Distribuer les cartes ingredient");
			this.btnProchaineEtape.setActionCommand("DISTRIBUER_INGREDIENTS");
		} else if(etat == EtatManche.EN_ATTENTE_CHOIX_CARTE_ALLIES) {
			this.btnProchaineEtape.setText("OK");
			this.btnProchaineEtape.setActionCommand("VALIDER_CHOIX_CARTES_ALLIES");
		}
	}
}
