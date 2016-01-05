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
import java.util.ArrayList;
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
	private JList<String> listeCarte;
		
	/**
	 * Un label permettant d'afficher le contenu de la carte ingrédient
	 */
	private JLabel affichageCarte = new JLabel();
		
	/**
	 * Le DefaultListModel contenant les données utilisé par la liste
	 */
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	/**
	 * Le JScrollPane permettant de faire défiler la liste
	 */
	private JScrollPane listScroller = new JScrollPane(listeCarte);
	
	/**
	 * Un controlleur écoutant pour les changement de sélection dans la liste
	 */
	private ControllerCartesIngredients controllerCartesIngredients;
	
	/**
	 * Une JComboBox permettant à l'utilisateur de choisir une action
	 */
	private JComboBox<String> comboAction;
	
	/**
	 * Une JComboBox permettant à l'utilisateur de choisir sa cible
	 */
	private JComboBox<String> comboCible;
		
	/**
	 * Le constructeur de la classe
	 * @param m La main du joueur concerné
	 */
	public ViewCartesIngredients(MainJoueur m, int nbrJoueurs) {
		//Ajout en tant qu'observateur
		this.main = m;
		main.addObserver(this);
		
		//Initialisation de la liste
		for(int i = 0; i < main.getNombreCarteIngredient(); i++){
			listModel.addElement(main.getCarteIngredient(i).getNom());
		}
		listeCarte = new JList(listModel);
		listeCarte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeCarte.setLayoutOrientation(JList.VERTICAL);

		listScroller.setPreferredSize(new Dimension(250, 80)); //A voir suivant la fenêtre de jeu

				
		//Ajout d'un controlleur aux comboBox et à la liste
		controllerCartesIngredients = new ControllerCartesIngredients(main, this);
		listeCarte.addListSelectionListener(controllerCartesIngredients);
		comboAction.addActionListener(controllerCartesIngredients);
		comboCible.addActionListener(controllerCartesIngredients);
		
		//Création de comboAction
		comboAction = new JComboBox<String>(new String[]{"Geant", "Engrais", "Farfadets"});
		comboAction.setSelectedIndex(0);
		
		//Création de comboCible
		ArrayList<String> cibles = new ArrayList<String>();
		for (int i = 0; i < nbrJoueurs; i++) {
			if (i != main.getJoueur().getNumero()) {
				cibles.add(Integer.toString(i + 1));
			}
		}
		
		comboCible = new JComboBox<String>(cibles.toArray(new String[cibles.size()]));
		comboCible.setSelectedIndex(0);
		comboCible.setVisible(false);
		
		//Ajout des éléments au panel
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(listeCarte);
		this.add(affichageCarte);
		this.add(comboAction);
		this.add(comboCible);
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
	
	/**
	 * Une méthode permettant de changer le statut (visible ou invisible) de comboCible
	 * @param visible Vrai si la ComboBox doit être visible
	 */
	public void setComboCibleVisibility(boolean visible) {
		comboCible.setVisible(visible);
	}
	
	public int getCible() {
		return comboCible.getSelectedIndex();
	}
	
}
