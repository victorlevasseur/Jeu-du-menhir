package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.util.Observer;
import java.util.Observable;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class ViewMainJoueur extends JPanel implements Observer{
	
	private static final long serialVersionUID = 8609006407550255268L;
	
	private JLabel nomJoueur = new JLabel();
	
	private JLabel nbrGraines = new JLabel();
	
	private JLabel nbrMenhirs = new JLabel();
		
	private MainJoueur main;
	
	public ViewMainJoueur(boolean physique, MainJoueur m) {
		
		//Initialisation des variables
		this.main = m;
		nomJoueur.setText("Joueur " + main.getJoueur().getNumero());
		nbrGraines.setText("Nombre de graines : " + main.getNombreGraine());
		nbrMenhirs.setText("Nombre de menhirs : " + main.getNombreMenhir());
		
		//Ajout des éléments au panel
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(nomJoueur);
		this.add(nbrGraines);
		this.add(nbrMenhirs);
		
		//Ajout des cartes s'il s'agit d'un joueur physique
		if (physique) {
			ViewCartesIngredients viewCartesIngredients = new ViewCartesIngredients(main);
			this.add(ViewCartesIngredients);
		}
		
		//On s'ajoute en observer de la main
		main.addObserver(this);
	}

	public void update(Observable obs, Object obj) {
		if (obs == main) {
			nbrGraines.setText("Nombre de graines : " + main.getNombreGraine());
			nbrMenhirs.setText("Nombre de menhirs : " + main.getNombreMenhir());
		}
	}
}
