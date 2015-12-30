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
		main = m;
		nomJoueur.setText("Joueur " + main.getJoueur().getNumero());
		nbrGraines.setText("Nombre de graines : " + main.getNombreGraine());
		nbrMenhirs.setText("Nombre de menhirs : " + main.getNombreMenhir());
		
		//Ajout des éléments au panel
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(nomJoueur);
		this.add(nbrGraines);
		this.add(nbrMenhirs);
		
		if (physique) {
			//gérer l'affichage des cartes
		}
		
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
