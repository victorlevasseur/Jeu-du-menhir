package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.Dimension;
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
import javax.swing.ListSelectionModel;

import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class ViewCartesAllies extends JPanel implements Observer {
	
	private MainJoueur main;
			
	private JLabel affichageCarte = new JLabel();
	
	private JButton bouttonJouer = new JButton("Jouer");
	
	private ControllerCartesAllies controller;
	
	private JComboBox comboCible;
	
	public ViewCartesAllies(MainJoueur m) {
		//Ajout en tant qu'observateur
		this.main = m;
		main.addObserver(this);

		//Ajout du controller au boutton
		controller = new ControllerCartesAllies(main, this);
		bouttonJouer.addActionListener(controller);
		
		//On affiche les données de la carte
		if(main.getCarteAllies() != null) {
			affichageCarte.setText("<html><pre>" + main.getCarteAllies().toString() + "</pre></html>");
			//L'utilisation du html permet d'avoir simplement des JLabel multilignes
		}
		else {
			affichageCarte.setText("Vous ne disposez pas de carte alliés.");
		}
		
		//Ajout des éléments au panel
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(affichageCarte);
		this.add(bouttonJouer);
	}

	public void update(Observable obs, Object obj) {
		if(obs == main){
			if(main.getCarteAllies() != null) {
				affichageCarte.setText("<html><pre>" + main.getCarteAllies().toString() + "</pre></html>");
				bouttonJouer.setEnabled(true);
			}
			else {
				affichageCarte.setText("Vous ne disposez pas de carte alliés.");
				bouttonJouer.setEnabled(false);
			}
		}
	}

}
