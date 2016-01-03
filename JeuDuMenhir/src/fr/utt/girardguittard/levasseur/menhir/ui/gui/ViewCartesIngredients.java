package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class ViewCartesIngredients extends JPanel implements Observer{
	
	private MainJoueur main;
	
	private JList listeCarte = new JList();
		
	private JLabel affichageCarte = new JLabel();
	
	private DefaultListModel listModel = new DefaultListModel();
	
	private JScrollPane listScroller = new JScrollPane(listeCarte);
	
	public ViewCartesIngredients(MainJoueur m) {
		//Ajout en tant qu'observateur
		this.main = m;
		main.addObserver(this);
		
		//Initialisation de la liste
		listeCarte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeCarte.setLayoutOrientation(JList.VERTICAL);

		listScroller.setPreferredSize(new Dimension(250, 80)); //A voir suivant la fenêtre de jeu

		for(int i = 0; i < main.getNombreCarteIngredient(); i++){
			listModel.addElement(main.getCarteIngredient(i).getNom());
		}
		
		//On affiche les données de la première carte
		listeCarte.setSelectedIndex(0);
		affichageCarte.setText("<html><pre>" + main.getCarteIngredient(0).toString() + "</pre></html>");
		//L'utilisation du html permet d'avoir simplement des JLabel multilignes
		
		//Ajout des éléments au panel
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(listScroller);
		this.add(affichageCarte);
	}
	
	public void update(Observable obs, Object obj) {
		if(obs == main) {
			for(int i = 0; i < main.getNombreCarteIngredient(); i++){
				listModel.addElement(main.getCarteIngredient(i).getNom());
			}
			listeCarte.setSelectedIndex(0);
			affichageCarte.setText("<html><pre>" + main.getCarteIngredient(0).toString() + "</pre></html>");
		}
	}
}
