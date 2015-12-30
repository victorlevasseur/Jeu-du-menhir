package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import java.util.Observer;

import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class ViewCartesIngredients extends JPanel implements Observer{
	
	private MainJoueur main;
	
	private JList listeCarte = new JList();
		
	private JLabel nomCarte = new JLabel();
	
	private JLabel forcesGeant = new JLabel();
	
	private JLabel forcesEngrais = new JLabel();
	
	private JLabel forcesFarfadets = new JLabel();
	
	public ViewCartesIngredients(MainJoueur m) {
		this.main = m;
		
		//Initialisation de la liste
		listeCarte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeCarte.setLayoutOrientation(JList.VERTICAL);

		JScrollPane listScroller = new JScrollPane(listeCarte);
		listScroller.setPreferredSize(new Dimension(250, 80)); //A voir suivant la fenêtre de jeu

		DefaultListModel listModel = new DefaultListModel();
		
		for(int i = 0; i < main.getNombreCarteIngredient(); i++){
			listModel.addElement(main.getCarteIngredient(i).getNom());
		}
		
		//On affiche les données de la première carte
		listeCarte.setSelectedIndex(0);
		nomCarte.setText(main.getCarteIngredient(0).getNom());
		//reste à faire l'affichage des actions/forces
	}
	
	
}
