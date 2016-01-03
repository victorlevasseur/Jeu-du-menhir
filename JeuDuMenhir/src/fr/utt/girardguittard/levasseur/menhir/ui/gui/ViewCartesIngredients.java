package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class ViewCartesIngredients extends JPanel implements Observer{
	
	/**
	 * Le serialVersionUID généré par Eclipse
	 */
	private static final long serialVersionUID = 3102850947102073806L;

	/**
	 * La main du joueur concernée (observable)
	 */
	private MainJoueur main;
	
	/**
	 * Une liste affichant le nom des cartes ingrédients contenu dans la main
	 */
	private JList<String> listeCarte = new JList<String>();
		
	/**
	 * Un label permettant d'afficher le contenu de la carte ingrédient
	 */
	private JLabel affichageCarte = new JLabel();
	
	/**
	 * Un boutton permettant de jouer la carte ingrédient sélectionnée
	 */
	private JButton bouttonJouer = new JButton("Jouer");
	
	/**
	 * Le DefaultListModel contenant les données utilisé par la liste
	 */
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	/**
	 * Le JScrollPane permettant de faire défiler la liste
	 */
	private JScrollPane listScroller = new JScrollPane(listeCarte);
	
	/**
	 * Un controlleur écoutant pour les utilisations du boutton jouer
	 */
	private ControllerJouerCartesIngredients controllerBouttonJouer;
	
	/**
	 * Un controlleur écoutant pour les changement de sélection dans la liste
	 */
	private ControllerListCartesIngredients controllerListe;
	
	/**
	 * Une JComboBox permettant à l'utilisateur de choisir une action
	 */
	private JComboBox<String> comboAction;
	
	
	/**
	 * Le constructeur de la classe
	 * @param m La main du joueur concerné
	 */
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
		
		//Ajout d'un controlleur au bouton
		controllerBouttonJouer = new ControllerJouerCartesIngredients(main, this);
		bouttonJouer.addActionListener(controllerBouttonJouer);
		
		//Ajout d'un controlleur à la liste
		controllerListe = new ControllerListCartesIngredients(main, this);
		listeCarte.addListSelectionListener(controllerListe);
		
		//Création de comboAction
		comboAction = new JComboBox<String>(new String[]{"Geant", "Engrais", "Farfadets"});
		comboAction.setSelectedIndex(0);
		
		//Ajout des éléments au panel
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(listeCarte);
		this.add(affichageCarte);
		this.add(comboAction);
		this.add(bouttonJouer);
	}
	
	/**
	 * La méthode update appelée à chaque changement dans la main
	 */
	public void update(Observable obs, Object obj) {
		if(obs == main) {
			for(int i = 0; i < main.getNombreCarteIngredient(); i++){
				listModel.addElement(main.getCarteIngredient(i).getNom());
			}
			listeCarte.setSelectedIndex(0);
			affichageCarte.setText("<html><pre>" + main.getCarteIngredient(0).toString() + "</pre></html>");
		}
	}
	
	/**
	 * Une méthode permettant de modifier le texte affiché par le label
	 * @param s La chaîne de caractère à afficher
	 */
	public void setLabel(String s) {
		affichageCarte.setText(s);
	}
	
	/**
	 * Une méthode permettant de connaître l'indice de la sélection de la liste
	 * @return L'indice de la sélection de la liste
	 */
	public int getSelection() {
		return listeCarte.getSelectedIndex();
	}
	
	/**
	 * Une méthode permettant de connaître l'action sélectionnée par le joueur
	 * @return L'action sélectionnée par le joueur
	 */
	public Action getAction() {
		switch(comboAction.getSelectedIndex()) {
			case 1: 
				return Action.ENGRAIS;
			case 2:
				return Action.FARFADET;
			default:
				return Action.GEANT;
		}
	}
}
