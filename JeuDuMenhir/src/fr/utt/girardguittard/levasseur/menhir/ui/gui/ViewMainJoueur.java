package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.util.Observer;
import java.util.Observable;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class ViewMainJoueur extends JPanel implements Observer{
	
	/**
	 * Le serialVersionUID généré par Eclipse
	 */
	private static final long serialVersionUID = 8609006407550255268L;
	
	/**
	 * Un label permettant d'afficher le nom du joueur
	 */
	private JLabel nomJoueur = new JLabel();
	
	/**
	 * Un label permettant d'afficher le nombre de graines collectées par le joueur
	 */
	private JLabel nbrGraines = new JLabel();
	
	/**
	 * Un label permettant d'fficher le nombre de menhirs obtenus par le joueur
	 */
	private JLabel nbrMenhirs = new JLabel();
		
	/**
	 * La main du joueur concerné
	 */
	private MainJoueur main;
	
	/**
	 * Le constructeur de la classe concernée
	 * @param physique Un booléen vrai s'il s'agit d'un joueur physique
	 * @param avancee Un booléen vrai s'il s'agit d'une partie avancée
	 * @param m La main du joueur concerné
	 */
	public ViewMainJoueur(boolean physique, boolean avancee, MainJoueur m, int nbrJoueurs) {
		
		//Initialisation des variables
		this.main = m;
		nomJoueur.setText("Joueur " + main.getJoueur().getNumero() + 1);
		nbrGraines.setText("Nombre de graines : " + main.getNombreGraine());
		nbrMenhirs.setText("Nombre de menhirs : " + main.getNombreMenhir());
		
		//Ajout des éléments au panel
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(nomJoueur);
		this.add(nbrGraines);
		this.add(nbrMenhirs);
		
		//Ajout des cartes ingrédients s'il s'agit d'un joueur physique
		if (physique) {
			ViewCartesIngredients viewCartesIngredients = new ViewCartesIngredients(main, nbrJoueurs);
			this.add(viewCartesIngredients);
			
			//Ajout de l'affichage de la carte alliés s'il s'agit d'une partie avancée
			if (avancee) {
				ViewCartesAllies viewCartesAllies = new ViewCartesAllies(main);
				this.add(viewCartesAllies);
			}
		}
		
		//On s'ajoute en observer de la main
		main.addObserver(this);
	}

	/**
	 * Méthode appelé lors de chaque changement sur la main
	 */
	public void update(Observable obs, Object obj) {
		if (obs == main) {
			nbrGraines.setText("Nombre de graines : " + main.getNombreGraine());
			nbrMenhirs.setText("Nombre de menhirs : " + main.getNombreMenhir());
		}
	}
}
